package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersPayment;

public interface OrdersDao {

	public List<Orders> autoComfirmOrders(); 
	
	public int updateOrders(@Param("ordersStatus")String ordersStatus,
			@Param("ordersOldStatus")String ordersOldStatus,
			@Param("updatetime")Date updatetime,
			@Param("oid")String oid);
	

	/////////////////////////////以上是自动走定时任务的需求//////////////
	
	////////////////////////////订单/////////////////////////////////
	
	public Orders singleOrders(@Param("id")String id);
	public List<Orders> ordersList(
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize,
			@Param("ordersType")String ordersType,
			@Param("mobile")String mobile,
			@Param("todate")String todate,
			@Param("fromdate")String fromdate,
			@Param("orderno")String orderno,
			@Param("ordersStatus")String ordersStatus, 
			@Param("recommendTeacher")String recommendTeacher,
			@Param("receiver")String receiver);
	
	public int ordersListCount(
			@Param("ordersType")String ordersType,
			@Param("mobile")String mobile,
			@Param("todate")String todate,
			@Param("fromdate")String fromdate,
			@Param("orderno")String orderno,
			@Param("ordersStatus")String ordersStatus, 
			@Param("recommendTeacher")String recommendTeacher,
			@Param("receiver")String receiver);
	
	
	
	public OrdersPayment singlePayment(@Param("orderId")String orderId);
	public List<OrdersPayment> listByExtransno(@Param("extransno")String extransno);
	
	
}
