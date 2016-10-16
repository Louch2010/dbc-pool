package com.louch2010.dbc.pool.base;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class BasePoolQueue<T> {
	private LinkedList<T> list = new LinkedList<T>();
	/**
	  *description : 插入到尾部
	  *@param      : @param t
	  *@param      : @return
	  *@return     : boolean
	  *modified    : 1、2016年10月14日 下午12:07:02 由 luocihang 创建 	   
	  */ 
	public boolean offer(T t){
		synchronized (list) {
			//先将对象加入到尾部，再唤醒等待的线程
			list.addLast(t);
			list.notify();
			return true;
		}
	}
	
	/**
	  *description : 弹出队列头
	  *@param      : @return
	  *@return     : T
	  *modified    : 1、2016年10月14日 下午12:09:46 由 luocihang 创建 	   
	  */ 
	public T poll(){
		synchronized (list) {
			if(list.size() == 0){
				return null;
			}
			return list.removeFirst();
		}
	}
	
	/**
	  *description : 弹出队列头，如果队列为空，则等待指定时间
	  *@param      : @param time
	  *@param      : @param unit
	  *@param      : @return
	  *@return     : T
	  *modified    : 1、2016年10月14日 下午12:10:14 由 luocihang 创建 	   
	  */ 
	public T poll(long time, TimeUnit unit) throws InterruptedException{
		long wait = unit.toMillis(time);
		synchronized (list) {
			if(list.size() == 0 && wait > 0){
				list.wait(wait);
			}
			if(list.size() > 0){
				return list.removeFirst();
			}
		}
		return null;
	}
	
	/**
	  *description : 获取队列头
	  *@param      : @return
	  *@return     : T
	  *modified    : 1、2016年10月14日 下午12:11:25 由 luocihang 创建 	   
	  */ 
	public T peek(){
		synchronized (list) {
			if(list.size() == 0){
				return null;
			}
			return list.getFirst();
		}
	}
	
	/**
	  *description : 获取队列大小
	  *@param      : @return
	  *@return     : int
	  *modified    : 1、2016年10月14日 下午12:13:50 由 luocihang 创建 	   
	  */ 
	public int size(){
		synchronized (list) {			
			return list.size();
		}
	}
	
	/**
	  *description : 移除元素
	  *@param      : @param t
	  *@param      : @return
	  *@return     : boolean
	  *modified    : 1、2016年10月14日 下午12:14:11 由 luocihang 创建 	   
	  */ 
	public boolean remove(T t){
		synchronized (list) {
			if(list.contains(t)){
				list.remove(t);
				return true;
			}
		}
		return false;
	}
	
	/**
	  *description : 清空资源
	  *@param      : 
	  *@return     : void
	  *modified    : 1、2016年10月16日 上午11:22:36 由 luocihang 创建 	   
	  */ 
	public void clear(){
		synchronized(list){
			list.clear();
		}
	}
}
