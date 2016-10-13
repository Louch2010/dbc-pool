package com.louch2010.dbc.pool.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.louch2010.dbc.pool.DBConnectionPool;

public class TestPool {
	public static void main(String[] args) throws Exception {
		DBConnectionPool pool = new DBConnectionPool("E:/workspaces/dbc-pool/target/classes/jdbc.properties");
		Connection conn = pool.getConnection();
		Statement stat = conn.createStatement();
		ResultSet result = stat.executeQuery("select * from person");
		while(result.next()){
			String name = result.getString("name");
			int age = result.getInt("age");
			System.out.println(name + " = " + age);
		}
	}
}
