package com.louch2010.dbc.pool.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.louch2010.dbc.pool.DBConnectionPool;

public class TestPool {
	public static void main(String[] args) throws Exception {
		final DBConnectionPool pool = new DBConnectionPool("E:/workspaces/dbc-pool/target/classes/jdbc.properties");
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				public void run() {
					try {
						Connection conn = pool.getConnection();
						Statement stat = conn.createStatement();
						ResultSet result = stat.executeQuery("select * from person");
						while(result.next()){
							//String name = result.getString("name");
							//int age = result.getInt("age");
							//System.out.println(name + " = " + age);
						}
						System.out.println("执行成功，线程名：" + Thread.currentThread().getName());
						Thread.sleep(10000);
						pool.releaseConnection(conn);
					} catch (Exception e) {
						System.out.println("执行失败，线程名：" + Thread.currentThread().getName());
					}
				}
			}, "thread-" + i).start();;
		}
	}
}
