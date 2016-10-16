package com.louch2010.dbc.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.louch2010.dbc.pool.base.BasePoolFactory;
import com.louch2010.dbc.pool.base.BasePoolObject;
import com.louch2010.dbc.pool.constants.Constant;
import com.louch2010.dbc.pool.utils.Logger;

/** 
  * @Description: 连接工厂
  * @author: luocihang
  * @date: 2016年10月12日 下午8:24:41
  * @version: V1.0 
  * @see：
  */
public class DBConnectionFactory implements BasePoolFactory<Connection>{
	
	private DBConnectionPool pool;
	private Logger logger = new Logger();
	
	public DBConnectionFactory(DBConnectionPool pool){
		this.pool = pool;
	}

	/**
	  *description : 创建资源
	  *@param      : @return
	  *@param      : @throws Exception
	  *@see        : 
	  *modified    : 1、2016年10月13日 下午4:00:28 由 luocihang 创建 
	  *			   
	  */ 
	public BasePoolObject<Connection> makeObject() throws Exception {
		String url = pool.getUrl();
		String user = pool.getUsername();
		String password = pool.getPassword();
		Connection conn = DriverManager.getConnection(url, user, password);
		DBConnection proxy = new DBConnection(pool, conn);
		return new BasePoolObject<Connection>(proxy);
	}

	/**
	  *description : 销毁资源
	  *@param      : @param p
	  *@param      : @throws Exception
	  *@see        : 
	  *modified    : 1、2016年10月13日 下午4:00:19 由 luocihang 创建 
	  *			   
	  */ 
	public void destroyObject(BasePoolObject<Connection> p) throws Exception {
		DBConnection proxy = (DBConnection)p.getObject();
		proxy.destroy();
	}

	/**
	  *description : 校验资源可用性
	  *@param      : @param p
	  *@param      : @return
	  *@see        : 
	  *modified    : 1、2016年10月13日 下午4:00:11 由 luocihang 创建 
	  *			   
	  */ 
	public boolean validateObject(BasePoolObject<Connection> p) {
		String sql = "";
		if(pool.getDbType().equals(Constant.DB_TYPE.MYSQL)){
			sql = "select now()";
		}else if(pool.getDbType().equals(Constant.DB_TYPE.ORACLE)){
			sql = "select systimestamp from dual";
		}else if(pool.getDbType().equals(Constant.DB_TYPE.DB2)){
			sql = "values(current timestamp)";
		}
		Connection conn = p.getObject();
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			if(result != null){
				return true;
			}
		} catch (Exception e) {
			logger.error("检查资源可用性失败", e);
		}
		return false;
	}
}
