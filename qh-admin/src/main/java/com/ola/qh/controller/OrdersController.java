package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersProduct;
import com.ola.qh.service.IOrdersService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

	@Autowired
	private IOrdersService ordersService;
	
	@RequestMapping("/list")
	public Results<List<Orders>> list(
			@RequestParam(name="page",required=true)int page,
			@RequestParam(name="ordersType",required=false)String ordersType,
			@RequestParam(name="mobile",required=false)String mobile,
			@RequestParam(name="recommendTeacher",required=false)String recommendTeacher,
			@RequestParam(name="orderno",required=false)String orderno,
			@RequestParam(name="ordersStatus",required=false)String ordersStatus,
			@RequestParam(name="todate",required=false)String todate,
			@RequestParam(name="fromdate",required=false)String fromdate){
		
		Results<List<Orders>> result=new Results<List<Orders>>();
		int pageNo=(page-1)*Patterns.pageSize;
		int pageSize=Patterns.pageSize;
		
		List<Orders> list = ordersService.list(pageNo, pageSize, ordersType, mobile, todate, fromdate,orderno,ordersStatus,recommendTeacher);
		int count = ordersService.listCount(ordersType, mobile, todate, fromdate, orderno, ordersStatus, recommendTeacher);
		result.setCount(count);
		result.setStatus("0");
		result.setData(list);
		return result;
	}
	
	
	/**
	 * 查出订单对应的商品信息
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/product/list")
	public Results<List<OrdersProduct>> ordersProductList(@RequestParam(name="orderId",required=true)String orderId){
		Results<List<OrdersProduct>> results=new Results<List<OrdersProduct>>();
		List<OrdersProduct> list = ordersService.productList(orderId);
		results.setStatus("0");
		results.setData(list);
		return results;
	}
	
}
