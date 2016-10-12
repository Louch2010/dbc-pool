package com.louch2010.dbc.pool.base;

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
	//private 
	
	public BasePool(BasePoolFactory<T> factory, BasePoolConfig config) {
		super();
		this.config = config;
		this.factory = factory;
		initPool();
	}
	
	/**
	  *description : 初始化池子
	  *@param      : 
	  *@return     : void
	  *modified    : 1、2016年10月12日 下午9:04:05 由 luocihang 创建 	   
	  */ 
	private void initPool(){
		
	}
	
	/**
	  *description : 还回资源
	  *@param      : @param o
	  *@return     : void
	  *modified    : 1、2016年10月12日 下午9:04:19 由 luocihang 创建 	   
	  */ 
	public void returnObject(T o){
		
	}
	
	/**
	  *description : 借资源
	  *@param      : @return
	  *@return     : T
	  *modified    : 1、2016年10月12日 下午9:04:32 由 luocihang 创建 	   
	  */ 
	public T borrowObject(){
		return null;
	}
	
	/**
	  *description : 在指定时间内借资源，如果没借到则抛出异常
	  *@param      : @param wait
	  *@param      : @return
	  *@return     : T
	  *modified    : 1、2016年10月12日 下午9:04:42 由 luocihang 创建 	   
	  */ 
	public T borrowObject(long wait){
		return null;
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
