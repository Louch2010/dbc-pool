package com.louch2010.dbc.pool.test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.louch2010.dbc.pool.base.BasePoolObject;

public class Test {
	public static void main(String[] args) {
		BasePoolObject<Integer> obj = new BasePoolObject<Integer>(3);
		System.out.println(obj.hashCode());
		System.out.println(System.identityHashCode(obj));
		new Timer().schedule(new TimerTask() {
			public void run() {
				System.out.println(new Date());;
			}
		}, 1 * 1000, 1* 1000);
	}
}
