package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Orders;

public interface IOrdersService {

	public void comfirmOrders();///订单确认收货
	
	public List<Orders> list(int pageNo,int pageSize,String ordersType,String mobile,
			String todate,String fromdate,String orderno,String ordersStatus,String recommendTeacher);
	
	public int listCount(String ordersType,String mobile,
			String todate,String fromdate,String orderno,String ordersStatus,String recommendTeacher);
}
