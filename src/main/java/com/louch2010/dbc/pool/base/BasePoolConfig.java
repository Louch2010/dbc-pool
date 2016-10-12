package com.louch2010.dbc.pool.base;

/** 
  * @Description: 资源配置
  * @author: luocihang
  * @date: 2016年10月12日 下午8:53:49
  * @version: V1.0 
  * @see：
  */
public class BasePoolConfig {
	// 最大资源数
	private int maxConnection;
	// 最小资源数
	private int minConnection;
	// 初始化资源数
	private int initConnection;
	// 空闲线程存活时间
	private int idleAliveTimeSecond;
	// 获取资源时最大等待时间
	private int maxWaitTimeSecondForGetConnection;
	// 获取之前先进行资源可用性检查
	private boolean checkBeforeBorrow;
	// 还回之前先进行资源可用性检查
	private boolean checkBeforeReturn;
	// 配置文件路径
	private String configFilePath;

	public int getMaxConnection() {
		return maxConnection;
	}

	public void setMaxConnection(int maxConnection) {
		this.maxConnection = maxConnection;
	}

	public int getMinConnection() {
		return minConnection;
	}

	public void setMinConnection(int minConnection) {
		this.minConnection = minConnection;
	}

	public int getInitConnection() {
		return initConnection;
	}

	public void setInitConnection(int initConnection) {
		this.initConnection = initConnection;
	}

	public int getIdleAliveTimeSecond() {
		return idleAliveTimeSecond;
	}

	public void setIdleAliveTimeSecond(int idleAliveTimeSecond) {
		this.idleAliveTimeSecond = idleAliveTimeSecond;
	}

	public int getMaxWaitTimeSecondForGetConnection() {
		return maxWaitTimeSecondForGetConnection;
	}

	public void setMaxWaitTimeSecondForGetConnection(
			int maxWaitTimeSecondForGetConnection) {
		this.maxWaitTimeSecondForGetConnection = maxWaitTimeSecondForGetConnection;
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

}
