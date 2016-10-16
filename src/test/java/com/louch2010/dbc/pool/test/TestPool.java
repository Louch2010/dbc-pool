package com.louch2010.dbc.pool.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;

import com.louch2010.dbc.pool.DBConnectionPool;

public class TestPool {
	public static void main(String[] args) throws Exception {
		int count = 10;
		final CountDownLatch counter = new CountDownLatch(count);
		final DBConnectionPool pool = new DBConnectionPool("E:/workspaces/dbc-pool/target/classes/jdbc.properties");
		for (int i = 0; i < count; i++) {
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
						Thread.sleep(5000);
						conn.close();
					} catch (Exception e) {
						System.out.println("执行失败，线程名：" + Thread.currentThread().getName());
					} finally{
						counter.countDown();
					}
				}
			}, "thread-" + i).start();;
		}
		System.out.println("等待中...");
		counter.await();
		System.out.println("开始销毁...");
		pool.destory();
	}
}
