package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersProduct;

public interface OrdersDao {

	public List<Orders> autoComfirmOrders(); 
	
	public List<OrdersProduct> selectByOid(@Param("orderId")String orderId,
			@Param("statusCode")String statusCode);
	
	public int updateOrders(@Param("ordersStatus")String ordersStatus,
			@Param("ordersOldStatus")String ordersOldStatus,
			@Param("updatetime")Date updatetime,
			@Param("oid")String oid);
	
	public int updateOrdersProduct(@Param("statusCode")String statusCode,
			@Param("statusName")String statusName,
			@Param("oldStatusCode")String oldStatusCode,
			@Param("updatetime")Date updatetime,
			@Param("id")String id);
	/////////////////////////////以上是自动走定时任务的需求//////////////
	
	////////////////////////////订单/////////////////////////////////
	public List<Orders> ordersList(
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize,
			@Param("ordersType")String ordersType,
			@Param("mobile")String mobile,
			@Param("todate")String todate,
			@Param("fromdate")String fromdate);
	
	public int ordersListCount(
			@Param("ordersType")String ordersType,
			@Param("mobile")String mobile,
			@Param("todate")String todate,
			@Param("fromdate")String fromdate);
	
	
	
	//////////////////////订单的产品/////////////////////////////////
	
	public int selectByOrderIdCount(@Param("orderId")String orderId);
	
	public List<OrdersProduct> selectByOrderId(@Param("orderId")String orderId,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);
}
