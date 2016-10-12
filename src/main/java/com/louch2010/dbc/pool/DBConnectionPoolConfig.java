package com.louch2010.dbc.pool;

import com.louch2010.dbc.pool.base.BasePoolConfig;

/**
 * @Description: 连接池配置
 * @author: luocihang
 * @date: 2016年10月12日 下午6:41:02
 * @version: V1.0
 * @see：
 */
public class DBConnectionPoolConfig extends BasePoolConfig{
	// 连接池名称
	private String poolName;
	// 驱动
	private String driver;
	// 数据库连接url
	private String url;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 是否为dubug模式
	private boolean debug;

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
