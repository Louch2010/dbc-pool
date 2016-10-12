package com.louch2010.dbc.pool.constants;

/** 
  * @Description: 常量
  * @author: luocihang
  * @date: 2016年10月12日 下午6:43:13
  * @version: V1.0 
  * @see：
  */
public interface Constant {
	
	public interface POOLCONFIG_DEFAULT{
		String POOLNAME = "";
		int MAXCONNECTION = 10;
		int MINCONNECTION = 1;
		int IDLEALIVETIMESECOND = 60;
		boolean DEBUG = true;
	}
}
