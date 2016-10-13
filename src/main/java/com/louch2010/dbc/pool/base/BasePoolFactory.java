package com.louch2010.dbc.pool.base;

/** 
  * @Description: 池工厂
  * @author: luocihang
  * @date: 2016年10月12日 下午8:42:51
  * @version: V1.0 
  * @see：
  */
public interface BasePoolFactory<T> {
	/**
	  *description : 创建对象
	  *@param      : @return
	  *@param      : @throws Exception
	  *@return     : BasePoolObject<T>
	  *modified    : 1、2016年10月12日 下午8:44:20 由 luocihang 创建 	   
	  */ 
	public BasePoolObject<T> makeObject() throws Exception;
	/**
	  *description : 销毁对象
	  *@param      : @param p
	  *@param      : @throws Exception
	  *@return     : void
	  *modified    : 1、2016年10月12日 下午8:54:41 由 luocihang 创建 	   
	  */ 
	public void destroyObject(BasePoolObject<T> p) throws Exception;
	/**
	  *description : 校验对象是否可用
	  *@param      : @param p
	  *@param      : @return
	  *@return     : boolean
	  *modified    : 1、2016年10月12日 下午8:54:53 由 luocihang 创建 	   
	  */ 
	public boolean validateObject(BasePoolObject<T> p);
}
