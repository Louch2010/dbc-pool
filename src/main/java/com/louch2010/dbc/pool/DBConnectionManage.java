package com.louch2010.dbc.pool;

import java.sql.Connection;

import com.louch2010.dbc.pool.base.BasePool;
import com.louch2010.dbc.pool.base.BasePoolFactory;
import com.louch2010.dbc.pool.utils.Logger;

/** 
  * @Description: 数据库连接管理器
  * @author: luocihang
  * @date: 2016年10月12日 下午8:16:10
  * @version: V1.0 
  * @see：
  */
public class DBConnectionManage extends DBConnectionPoolConfig{
	
	private static BasePool<Connection> pool;
	private Logger logger = new Logger();
	
	private void init(){
		//初始化配置
		
		//初始化工厂
		BasePoolFactory<Connection> factory = new DBConnectionFactory(this);
		pool = new BasePool<Connection>(factory, this);
	}
	
	/**
	  *description : 获取连接
	  *@param      : @return
	  *@return     : Connection
	  *modified    : 1、2016年10月12日 下午8:34:22 由 luocihang 创建 	   
	  */ 
	public Connection getConnection(){
		if(pool == null){
			init();
		}
		try {
			return pool.borrowObject(this.getMaxWaitTimeSecondForGetConnection() * 1000);
		} catch (Exception e) {
			logger.error("获取连接失败！", e);
			return null;
		}
	}
	
	/**
	  *description : 释放连接
	  *@param      : @param connection
	  *@return     : void
	  *modified    : 1、2016年10月12日 下午8:34:33 由 luocihang 创建 	   
	  */ 
	public void releaseConnection(Connection connection){
		pool.returnObject(connection);
	}
}
