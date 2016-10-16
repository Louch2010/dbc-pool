package com.louch2010.dbc.pool.base;

import com.louch2010.dbc.pool.constants.Constant;

/** 
  * @Description: 资源配置
  * @author: luocihang
  * @date: 2016年10月12日 下午8:53:49
  * @version: V1.0 
  * @see：
  */
public class BasePoolConfig {
	// 连接池名称
	private String poolName = Constant.POOL_CONFIG_DEFAULT.POOL_NAME;
	// 最大资源数
	private int maxObjectNum = Constant.POOL_CONFIG_DEFAULT.MAX_CONNECT_NUM;
	// 最小资源数
	private int minObjectNum = Constant.POOL_CONFIG_DEFAULT.MIN_CONNECT_NUM;
	// 初始化资源数
	private int initObjectNum = Constant.POOL_CONFIG_DEFAULT.INIT_CONNECT_NUM;
	// 空闲线程存活时间
	private int idleAliveTimeSecond = Constant.POOL_CONFIG_DEFAULT.IDLE_ALIVE_TIMES_ECOND;
	// 获取资源时最大等待时间
	private int maxWaitTimeSecondForGetObject = Constant.POOL_CONFIG_DEFAULT.MAX_WAIT_TIME;
	// 获取之前先进行资源可用性检查
	private boolean checkBeforeBorrow = Constant.POOL_CONFIG_DEFAULT.CHECK_BEFORE_BORROW;
	// 还回之前先进行资源可用性检查
	private boolean checkBeforeReturn = Constant.POOL_CONFIG_DEFAULT.CHECK_BEFORE_RETURN;
	// 定时检查间隔时长
	private int checkObjectTimeSecond = Constant.POOL_CONFIG_DEFAULT.CHECK_INTERVAL;
	// 配置文件路径
	private String configFilePath;
	
	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public int getMaxObjectNum() {
		return maxObjectNum;
	}

	public void setMaxObjectNum(int maxObjectNum) {
		this.maxObjectNum = maxObjectNum;
	}

	public int getMinObjectNum() {
		return minObjectNum;
	}

	public void setMinObjectNum(int minObjectNum) {
		this.minObjectNum = minObjectNum;
	}

	public int getInitObjectNum() {
		return initObjectNum;
	}

	public void setInitObjectNum(int initObjectNum) {
		this.initObjectNum = initObjectNum;
	}

	public int getMaxWaitTimeSecondForGetObject() {
		return maxWaitTimeSecondForGetObject;
	}

	public void setMaxWaitTimeSecondForGetObject(int maxWaitTimeSecondForGetObject) {
		this.maxWaitTimeSecondForGetObject = maxWaitTimeSecondForGetObject;
	}

	public int getIdleAliveTimeSecond() {
		return idleAliveTimeSecond;
	}

	public void setIdleAliveTimeSecond(int idleAliveTimeSecond) {
		this.idleAliveTimeSecond = idleAliveTimeSecond;
	}

	public boolean isCheckBeforeBorrow() {
		return checkBeforeBorrow;
	}

	public void setCheckBeforeBorrow(boolean checkBeforeBorrow) {
		this.checkBeforeBorrow = checkBeforeBorrow;
	}

	public boolean isCheckBeforeReturn() {
		return checkBeforeReturn;
	}

	public void setCheckBeforeReturn(boolean checkBeforeReturn) {
		this.checkBeforeReturn = checkBeforeReturn;
	}

	public String getConfigFilePath() {
		return configFilePath;
	}

	public void setConfigFilePath(String configFilePath) {
		this.configFilePath = configFilePath;
	}

	public int getCheckObjectTimeSecond() {
		return checkObjectTimeSecond;
	}

	public void setCheckObjectTimeSecond(int checkObjectTimeSecond) {
		this.checkObjectTimeSecond = checkObjectTimeSecond;
	}
}
