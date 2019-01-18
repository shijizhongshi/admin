package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersProduct;
import com.ola.qh.util.Results;

public interface IOrdersService {

	public void comfirmOrders();///订单确认收货
	
	public List<Orders> list(int pageNo,int pageSize,String ordersType,String mobile,
			String todate,String fromdate,String orderno,String ordersStatus,String recommendTeacher,String receiver);
	
	public int listCount(String ordersType,String mobile,
			String todate,String fromdate,String orderno,String ordersStatus,String recommendTeacher,String receiver);
	
	public List<OrdersProduct> productList(String orderId);
	
	public Results<String> updateRefund(String ordersProductId, String statusCode);
	
	public Results<String> updateServeRefund(String ordersId,String statusCode);
	
	
}
