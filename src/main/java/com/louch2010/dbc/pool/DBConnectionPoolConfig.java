package com.louch2010.dbc.pool;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.louch2010.dbc.pool.base.BasePoolConfig;
import com.louch2010.dbc.pool.constants.Constant;
import com.louch2010.dbc.pool.utils.IOUtil;
import com.louch2010.dbc.pool.utils.StringUtil;

/**
 * @Description: 连接池配置
 * @author: luocihang
 * @date: 2016年10月12日 下午6:41:02
 * @version: V1.0
 * @see：
 */
public class DBConnectionPoolConfig extends BasePoolConfig{
	// 驱动
	private String driver;
	// 数据库连接url
	private String url;
	// 用户名
	private String username;
	// 密码
	private String password;
	//数据库类型
	private String dbType;
	// 是否为dubug模式
	private boolean debug;
	//登录超时时间
	private int loginTimeout;
	
	/**
	  *description : 
	  *@return     : void
	  *modified    : 1、2016年10月16日 下午7:18:55 由 luocihang 创建 	   
	  */ 
	protected void initConfig(){
		String configFilePath = super.getConfigFilePath();
		//如果配置文件不为空的话，则加载配置
		if(!StringUtil.isEmpty(configFilePath)){
			if(StringUtil.isEmpty(configFilePath)){
				throw new RuntimeException("配置文件地址为空，初始化配置失败！");
			}
			setConfigFilePath(configFilePath);
			//加载配置文件
			if(configFilePath.toLowerCase().startsWith("classpath:")){
				loadPropertiesFromClassPath(configFilePath.substring("classpath:".length()));
			}else{			
				loadProperties();
			}
		}
		modification();
	}
	
	/**
	  *description : 从classpath加载
	  *@param      : @param propfile
	  *@return     : void
	  *modified    : 1、2016年10月16日 下午12:31:49 由 luocihang 创建 	   
	  */ 
	private void loadPropertiesFromClassPath(String propfile){
		String[] classPaths = System.getProperty("java.class.path", "classes").split(System.getProperty("path.separator", ";"));
		File pfile = null;
		for (int i = 0; i <= classPaths.length; i++) {
			if(i == classPaths.length){//尝试从项目的classpath加载
				try {
					String path = this.getClass().getResource("/").getPath() + propfile;
					pfile = new File(path);
				} catch (Exception e) {}
			}else{
				pfile = new File(classPaths[i] + "/" + propfile);
			}
	        if (pfile.exists()) {
	            break;
	        }
		}
		if(pfile != null && pfile.exists()){
			setConfigFilePath(pfile.getAbsolutePath());
			loadProperties();
		}else{
			throw new RuntimeException("加载配置文件失败！配置文件：" + this.getConfigFilePath());
		}
	}
	
	/**
	  *description : 加载配置
	  *@param      : 
	  *@return     : void
	  *modified    : 1、2016年10月13日 下午5:18:23 由 luocihang 创建 	   
	  */ 
	private void loadProperties(){
		InputStream is = null;
		try {
			is = new FileInputStream(new File(this.getConfigFilePath()));
			Properties prop = new Properties();
		    prop.load(is);
		    setProperties(prop);
		} catch (Exception e) {
			throw new RuntimeException("加载配置文件失败！配置文件：" + this.getConfigFilePath(), e);
		} finally{
			IOUtil.closeQuietly(is);
		}
	}
	
	/**
	  *description : 设置属性
	  *@param      : @param prop
	  *@return     : void
	  *modified    : 1、2016年10月13日 下午5:18:32 由 luocihang 创建 	   
	  */ 
	private void setProperties(Properties prop){
		setPoolName(prop.getProperty("dbc.pool.name", Constant.POOL_CONFIG_DEFAULT.POOL_NAME));
		setMaxObjectNum(Integer.parseInt(prop.getProperty("dbc.pool.connect.max", "" +Constant.POOL_CONFIG_DEFAULT.MAX_CONNECT_NUM)));
		setMinObjectNum(Integer.parseInt(prop.getProperty("dbc.pool.connect.min", "" +Constant.POOL_CONFIG_DEFAULT.MIN_CONNECT_NUM)));
		setInitObjectNum(Integer.parseInt(prop.getProperty("dbc.pool.connect.init", "" +Constant.POOL_CONFIG_DEFAULT.INIT_CONNECT_NUM)));
		setIdleAliveTimeSecond(Integer.parseInt(prop.getProperty("dbc.pool.connect.idleAliveTime", "" +Constant.POOL_CONFIG_DEFAULT.IDLE_ALIVE_TIMES_ECOND)));
		setMaxWaitTimeSecondForGetObject(Integer.parseInt(prop.getProperty("dbc.pool.connect.maxWaitTime", "" +Constant.POOL_CONFIG_DEFAULT.MAX_WAIT_TIME)));
		setCheckBeforeBorrow(Boolean.parseBoolean(prop.getProperty("dbc.pool.connect.check.borrow", "" +Constant.POOL_CONFIG_DEFAULT.CHECK_BEFORE_BORROW)));
		setCheckBeforeReturn(Boolean.parseBoolean(prop.getProperty("dbc.pool.connect.check.return", "" +Constant.POOL_CONFIG_DEFAULT.CHECK_BEFORE_RETURN)));
		setCheckObjectTimeSecond(Integer.parseInt(prop.getProperty("dbc.pool.connect.check.interval", "" +Constant.POOL_CONFIG_DEFAULT.CHECK_INTERVAL)));
		setDriver(prop.getProperty("dbc.driver"));
		setUrl(prop.getProperty("dbc.url"));
		setUsername(prop.getProperty("dbc.username"));
		setPassword(prop.getProperty("dbc.password"));
		setDebug(Boolean.parseBoolean(prop.getProperty("dbc.debug", "" +Constant.POOL_CONFIG_DEFAULT.DEBUG)));
		setLoginTimeout(Integer.parseInt(prop.getProperty("dbc.logintTimeout", "" +Constant.POOL_CONFIG_DEFAULT.LOGIN_TIMEOUT)));
	}
	
	/**
	  *description : 修正
	  *@param      : 
	  *@return     : void
	  *modified    : 1、2016年10月16日 下午9:35:25 由 luocihang 创建 	   
	  */ 
	private void modification(){
		//设置数据库类型
		String[] buf = this.url.split(":");
        if (buf.length < 2) {
            return;
        }
        String dbf = buf[1];
        if (dbf.compareToIgnoreCase(Constant.DB_TYPE.ORACLE) == 0) {
            setDbType(Constant.DB_TYPE.ORACLE);
        } else if (dbf.compareToIgnoreCase(Constant.DB_TYPE.MYSQL) == 0) {
        	setDbType(Constant.DB_TYPE.MYSQL);
        } else if (dbf.compareToIgnoreCase(Constant.DB_TYPE.DB2) == 0) {
        	setDbType(Constant.DB_TYPE.DB2);
        } else{
        	throw new RuntimeException("不支持的数据库类型：" + dbf);
        }
        //校正（保证数据的合理性）
        if(getInitObjectNum() > getMinObjectNum()){
        	setInitObjectNum(getMinObjectNum());
        }
        if(getInitObjectNum() > getMaxObjectNum()){
        	setInitObjectNum(getMaxObjectNum());
        }
        if(getMinObjectNum() > getMaxObjectNum()){
        	setMaxObjectNum(getMaxObjectNum());
        }
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

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public int getLoginTimeout() {
		return loginTimeout;
	}

	public void setLoginTimeout(int loginTimeout) {
		this.loginTimeout = loginTimeout;
	}
}
