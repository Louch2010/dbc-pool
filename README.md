# dbc-pool
java数据库连接池database connection pool，支持集成spring，支持的数据有库：Oracle、MySQL、DB2
## 一、java示例
### 1、配置文件
```
#数据库驱动
dbc.driver=com.mysql.jdbc.Driver
#数据库url
dbc.url=jdbc:mysql://localhost:3306/test
#数据库用户名
dbc.username=root
#数据库密码
dbc.password=123456
#是否为调试模式
dbc.debug=true
#资源池名称
dbc.pool.name=test_pool
#资源池最大资源数
dbc.pool.connect.max=5
#资源池最小资源数
dbc.pool.connect.min=2
#资源池初始时化资源数
dbc.pool.connect.init=2
#资源池定时检测时间（秒）
dbc.pool.connect.check.interval=60
#资源池空闲资源存活时间（秒）
dbc.pool.connect.idleAliveTime=30
#获取资源时最大等待时间（秒）
dbc.pool.connect.maxWaitTime=3
#从资源池获取资源时否是检测资源的可用性
dbc.pool.connect.check.borrow=true
#向资源池还回资源时否是检测资源的可用性
dbc.pool.connect.check.return=false
```
### 2、java代码
```
DBConnectionPool pool = new DBConnectionPool("classpath:jdbc.properties");
Connection conn = pool.getConnection();
Statement stat = conn.createStatement();
ResultSet result = stat.executeQuery("select * from person");
while(result.next()){
		String name = result.getString("name");
		int age = result.getInt("age");
		System.out.println(name + " = " + age);
}
conn.close();
```
## 二、集成spring示例
### 1、使用单独配置文件
```
<bean id="dataSource" class="com.louch2010.dbc.pool.DBConnectionPool" init-method="init" destroy-method="destory">
	<!--配置文件路径 -->
        <property name="configFilePath" value="classpath:jdbc.properties"/>
</bean>
```

### 2、直接在spring配置中文件配置
```
<bean id="dataSource" class="com.louch2010.dbc.pool.DBConnectionPool" init-method="init" destroy-method="destory">
	<!-- 数据库驱动 -->
        <property name="driver" value="com.mysql.jdbc.Driver" />
	<!-- 数据库url -->
        <property name="url" value="jdbc:mysql://localhost:3306/test" />
	<!-- 数据库用户名 -->
        <property name="username" value="root" />
	<!-- 数据库密码 -->
        <property name="password" value="123456" />
	<!-- 是否为调试模式 -->
	<property name="debug" value="true" />
	<!-- 资源池名称 -->
	<property name="poolName" value="" />
	<!-- 资源池最大资源数 -->
	<property name="maxObjectNum" value="" />
	<!-- 资源池最小资源数 -->
	<property name="minObjectNum" value="" />
	<!-- 资源池初始时化资源数 -->
	<property name="initObjectNum" value="" />
	<!-- 资源池空闲资源存活时间（秒） -->
	<property name="idleAliveTimeSecond" value="30" />
	<!-- 获取资源时最大等待时间（秒） -->
	<property name="maxWaitTimeSecondForGetObject" value=""3 />
	<!-- 从资源池获取资源时否是检测资源的可用性 -->
	<property name="checkBeforeBorrow" value="" />
	<!-- 向资源池还回资源时否是检测资源的可用性 -->
	<property name="checkBeforeReturn" value="true" />
	<!-- 资源池定时检测时间（秒） -->
	<property name="checkObjectTimeSecond" value="60" />
</bean>
```

