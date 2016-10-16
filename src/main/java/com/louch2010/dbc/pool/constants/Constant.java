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
		String MAX_CONNECT_NUM = "10";
		String MIN_CONNECT_NUM = "1";
		String INIT_CONNECT_NUM = "3";
		String IDLE_ALIVE_TIMES_ECOND = "60";
		String MAX_WAIT_TIME = "3";
		String CHECK_BEFORE_BORROW = "false";
		String CHECK_BEFORE_RETURN = "false";
		String CHECK_INTERVAL = "60";
		String DEBUG = "true";
		String LOGIN_TIMEOUT = "10";
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
