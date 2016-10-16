# dbc-pool
java数据库连接池database connection pool，支持集成spring
## 一、java示例
### 1、配置文件
```
#数据库驱动
dbc.driver=com.mysql.jdbc.Driver
#url
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
dbc.pool.connect.check.return
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
