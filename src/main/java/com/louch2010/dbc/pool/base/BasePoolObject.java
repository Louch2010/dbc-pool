package com.louch2010.dbc.pool.base;

import java.util.Date;

import com.louch2010.dbc.pool.constants.Constant;

/** 
  * @Description: 池对象，对T进行扩展
  * @author: luocihang
  * @date: 2016年10月13日 上午10:12:11
  * @version: V1.0 
  * @see：
  */
public class BasePoolObject<T> {
	private final T object;
	private final Date createTime;
	private volatile int status;
	private volatile long borrowedCount;
	private volatile Date lastBorrowTime;
	private volatile Date lastReturnTime;
	
	public BasePoolObject(T object){
		this.object = object;
		this.createTime = new Date();
		this.borrowedCount = 0;
		this.status = Constant.POOL_OBJECT_STATUS.INIT;
	}

	public T getObject() {
		return object;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getBorrowedCount() {
		return borrowedCount;
	}

	public void setBorrowedCount(long borrowedCount) {
		this.borrowedCount = borrowedCount;
	}

	public Date getLastBorrowTime() {
		return lastBorrowTime;
	}

	public void setLastBorrowTime(Date lastBorrowTime) {
		this.lastBorrowTime = lastBorrowTime;
	}

	public Date getLastReturnTime() {
		return lastReturnTime;
	}

	public void setLastReturnTime(Date lastReturnTime) {
		this.lastReturnTime = lastReturnTime;
	}
}
