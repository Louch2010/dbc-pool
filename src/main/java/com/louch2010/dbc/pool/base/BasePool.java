package com.louch2010.dbc.pool.base;

import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.DateUtils;

import com.louch2010.dbc.pool.constants.Constant;
import com.louch2010.dbc.pool.utils.Logger;

/** 
  * @Description: 基础池
  * @author: luocihang
  * @date: 2016年10月12日 下午8:56:41
  * @version: V1.0 
  * @see：
  */
public class BasePool<T> {
	private BasePoolConfig config;
	private BasePoolFactory<T> factory;
	private LinkedBlockingQueue<T> queue = new LinkedBlockingQueue<T>();
	private Map<String, BasePoolObject<T>> allObject = new ConcurrentHashMap<String, BasePoolObject<T>>();
	private Logger logger = new Logger();
	
	public BasePool(BasePoolFactory<T> factory, BasePoolConfig config) {
		this.config = config;
		this.factory = factory;
		//初始化池
		initPool();
		//启动定时
		long interval = config.getCheckObjectTimeSecond() * 1000;
		new Timer("dbc-pool-check-timer").schedule(new TimerTask() {
			public void run() {
				doCheck();
			}
		}, interval, interval);
	}
	
	/**
	  *description : 初始化池子
	  *@param      : 
	  *@return     : void
	  *modified    : 1、2016年10月12日 下午9:04:05 由 luocihang 创建 	   
	  */ 
	private void initPool(){
		for (int i = 0; i < config.getInitObjectNum(); i++) {
			try {
				BasePoolObject<T> obj = factory.makeObject();
				allObject.put(this.getObjectIdentityHashCode(obj.getObject()), obj);
				queue.offer(obj.getObject());
			} catch (Exception e) {
				logger.error("初始化资源失败！", e);
			}
		}
	}
	
	/**
	  *description : 还回资源
	  *@param      : @param o
	  *@return     : void
	  *modified    : 1、2016年10月12日 下午9:04:19 由 luocihang 创建 	   
	  */ 
	public void returnObject(T o){
		String hash = this.getObjectIdentityHashCode(o);
		BasePoolObject<T> poolObject = allObject.get(hash);
		poolObject.setLastReturnTime(new Date());
		//如果设置了CheckBeforeReturn，则执行检查，如果不可用，则资源不放入队列
		if(config.isCheckBeforeReturn()){
			if(!factory.validateObject(poolObject)){
				poolObject.setStatus(Constant.POOL_OBJECT_STATUS.INVALID);
				logger.debug("资源不可用，无需还回资源！");
				return;
			}
		}
		poolObject.setStatus(Constant.POOL_OBJECT_STATUS.IDLE);
		queue.offer(o);
	}
	
	/**
	  *description : 借资源
	  *@param      : @return
	  *@return     : T
	  *modified    : 1、2016年10月12日 下午9:04:32 由 luocihang 创建 	   
	 * @throws Exception 
	  */ 
	public T borrowObject() throws Exception{
		T t = queue.poll(config.getMaxWaitTimeSecondForGetObject(), TimeUnit.SECONDS);
		if(t == null){
			//如果没有达到最大资源数，则尝试再创建资源
			if(queue.size() < config.getMaxObjectNum()){
				BasePoolObject<T> obj = factory.makeObject();
				allObject.put(this.getObjectIdentityHashCode(obj.getObject()), obj);
				t = obj.getObject();
			}else{
				throw new Exception("无可用资源，无法获取资源！");
			}
		}
		//对资源属性进行更新
		BasePoolObject<T> poolObject = allObject.get(this.getObjectIdentityHashCode(t));
		poolObject.setStatus(Constant.POOL_OBJECT_STATUS.ALLOCATED);
		poolObject.setBorrowedCount(poolObject.getBorrowedCount() + 1);
		poolObject.setLastBorrowTime(new Date());
		//如果设置了CheckBeforeBorrow，则执行检查，如果不可用，则重新获取
		if(config.isCheckBeforeBorrow()){
			if(!factory.validateObject(poolObject)){
				poolObject.setStatus(Constant.POOL_OBJECT_STATUS.INVALID);
				logger.debug("资源不可用，重新获取！");
				return borrowObject();
			}
		}
		return t;
	}
	
	/**
	  *description : 获取资源对象的identityHashCode
	  *@param      : @param t
	  *@param      : @return
	  *@return     : String
	  *modified    : 1、2016年10月13日 上午10:39:29 由 luocihang 创建 	   
	  */ 
	public String getObjectIdentityHashCode(T t){
		return "" + System.identityHashCode(t);
	}
	
	/**
	  *description : 执行检查
	  *@return     : void
	  *modified    : 1、2016年10月13日 下午2:08:23 由 luocihang 创建 	   
	  */ 
	private void doCheck(){
		logger.info("开始执行检查...");
		for(String hash:allObject.keySet()){
			BasePoolObject<T> poolObject = allObject.get(hash);
			//正在使用的，不处理
			if(poolObject.getStatus() == Constant.POOL_OBJECT_STATUS.ALLOCATED){
				continue;
			}
			//已经不可用的，销毁
			if(poolObject.getStatus() == Constant.POOL_OBJECT_STATUS.INVALID){
				try {
					factory.destroyObject(poolObject);
				} catch (Exception e) {
					logger.error("销毁资源失败！", e);
				}
				allObject.remove(hash);
				continue;
			}
			//空闲的，如果当前资源的空闲时间大于最大空闲时间，则销毁
			if(poolObject.getStatus() == Constant.POOL_OBJECT_STATUS.IDLE
					&& queue.size() > config.getMinObjectNum()){
				Date expireDate = DateUtils.addSeconds(poolObject.getLastReturnTime(), config.getIdleAliveTimeSecond());
				boolean expire = new Date().before(expireDate);
				if(!expire){
					continue;
				}
				try {
					factory.destroyObject(poolObject);
				} catch (Exception e) {
					logger.error("销毁资源失败！", e);
				}
				allObject.remove(hash);
				queue.remove(poolObject.getObject());
			}
		}
		logger.info("检查执行完成！");
	}

	public BasePoolConfig getConfig() {
		return config;
	}

	public void setConfig(BasePoolConfig config) {
		this.config = config;
	}

	public BasePoolFactory<T> getFactory() {
		return factory;
	}

	public void setFactory(BasePoolFactory<T> factory) {
		this.factory = factory;
	}

}
