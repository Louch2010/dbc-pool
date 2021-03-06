package com.louch2010.dbc.pool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import javax.sql.DataSource;

import com.louch2010.dbc.pool.base.BasePool;
import com.louch2010.dbc.pool.base.BasePoolFactory;

public class DBConnectionPool extends DBConnectionPoolConfig implements DataSource, ObjectFactory{
	
	private BasePool<Connection> pool;
	private PrintWriter logger;
	
	public DBConnectionPool(String configFilePath){
		this.setConfigFilePath(configFilePath);
		this.init();
	}
	
	public DBConnectionPool(){
		
	}
	
	/**
	  *description : 初始化
	  *@param      : 
	  *@return     : void
	  *modified    : 1、2016年10月16日 上午11:01:47 由 luocihang 创建 	   
	  */ 
	public void init(){
		//初始化配置
		super.initConfig();
		//加载驱动
		this.loadDriver();
		//初始化工厂
		BasePoolFactory<Connection> factory = new DBConnectionFactory(this);
		//初始化池
		pool = new BasePool<Connection>(factory, this);
	}
	
	/**
	  *description : 销毁
	  *@param      : 
	  *@return     : void
	  *modified    : 1、2016年10月16日 上午11:18:41 由 luocihang 创建 	   
	  */ 
	public void destory(){
		if(pool != null){
			pool.destoryPool();
		}
	}
	
	/**
	  *description : 加载驱动
	  *@param      : 
	  *@return     : void
	  *modified    : 1、2016年10月13日 下午5:01:54 由 luocihang 创建 	   
	  */ 
	private void loadDriver(){
		try {
			Class.forName(this.getDriver());
		} catch (ClassNotFoundException e) {
			logger.println("加载数据库驱动失败！" + e.toString());
			throw new RuntimeException("加载数据库驱动失败！", e);
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
	
	/**
	  *description : 获取连接
	  *@param      : @return
	  *@param      : @throws SQLException
	  *@see        : 
	  *modified    : 1、2016年10月13日 下午4:41:27 由 luocihang 创建 
	  *			   
	  */ 
	public Connection getConnection() throws SQLException {
		//如果没有初始化，则需要执行初始化操作
		if(pool == null){
			synchronized (this) {
				if(pool == null){
					init();
				}
			}
		}
		try {
			return pool.borrowObject();
		} catch (Exception e) {
			//logger.println("获取连接失败！" + e);
			e.printStackTrace();
			throw new SQLException(e);
		}
	}
	
	/**
	  *description : 返还资源
	  *@param      : @param conn
	  *@return     : void
	  *modified    : 1、2016年10月14日 下午7:47:58 由 luocihang 创建 	   
	  */ 
	public void returnConnection(Connection conn){
		pool.returnObject(conn);
	}

	public Connection getConnection(String username, String password)
			throws SQLException {
		throw new UnsupportedOperationException("getConnection(username, password) is unsupported.");
	}

	public PrintWriter getLogWriter() throws SQLException {
		return logger;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		this.logger = out;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getObjectInstance(Object obj, Name name, Context nameCtx,
			Hashtable<?, ?> environment) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
