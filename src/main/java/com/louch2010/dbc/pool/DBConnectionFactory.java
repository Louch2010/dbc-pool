package com.louch2010.dbc.pool;

import java.sql.Connection;

import com.louch2010.dbc.pool.base.BasePoolFactory;
import com.louch2010.dbc.pool.base.BasePoolObject;

/** 
  * @Description: 连接工厂
  * @author: luocihang
  * @date: 2016年10月12日 下午8:24:41
  * @version: V1.0 
  * @see：
  */
public class DBConnectionFactory implements BasePoolFactory<Connection>{
	
	private DBConnectionPoolConfig config;
	
	public DBConnectionFactory(DBConnectionPoolConfig config){
		this.config = config;
	}

	public BasePoolObject<Connection> makeObject() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void destroyObject(BasePoolObject<Connection> p) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean validateObject(BasePoolObject<Connection> p) {
		// TODO Auto-generated method stub
		return false;
	}

	public void activateObject(BasePoolObject<Connection> p) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
