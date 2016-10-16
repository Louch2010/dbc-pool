package com.louch2010.dbc.pool.constants;

/** 
  * @Description: 常量
  * @author: luocihang
  * @date: 2016年10月12日 下午6:43:13
  * @version: V1.0 
  * @see：
  */
public interface Constant {
	//池默认配置
	public interface POOL_CONFIG_DEFAULT{
		String POOL_NAME = "default-dbc-pool-";
		int MAX_CONNECT_NUM = 10;
		int MIN_CONNECT_NUM = 1;
		int INIT_CONNECT_NUM = 3;
		int IDLE_ALIVE_TIMES_ECOND = 60;
		int MAX_WAIT_TIME = 3;
		boolean CHECK_BEFORE_BORROW = false;
		boolean CHECK_BEFORE_RETURN = false;
		int CHECK_INTERVAL = 60;
		boolean DEBUG = true;
		int LOGIN_TIMEOUT = 10;
	}
	//池资源状态
	public interface POOL_OBJECT_STATUS{
		//初始
		int INIT = 1;
		//空闲
		int IDLE = 2;
		//已分配
		int ALLOCATED = 3;
		//无效、不可用
		int INVALID = 4;
	}
	//数据库类型
	public interface DB_TYPE{
		String MYSQL = "mysql";
		String ORACLE = "oracle";
		String DB2 = "db2";
	}
}
