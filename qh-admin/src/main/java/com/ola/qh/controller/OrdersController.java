package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Orders;
import com.ola.qh.service.IOrdersService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

	@Autowired
	private IOrdersService ordersService;
	
	@RequestMapping("/list")
	public Results<List<Orders>> list(@RequestParam(name="page",required=true)int page,
			@RequestParam(name="ordersType",required=false)String ordersType,
			@RequestParam(name="mobile",required=false)String mobile,
			@RequestParam(name="todate",required=false)String todate,
			@RequestParam(name="fromdate",required=false)String fromdate){
		
		Results<List<Orders>> result=new Results<List<Orders>>();
		int pageNo=(page-1)*Patterns.pageSize;
		int pageSize=Patterns.pageSize;
		
		List<Orders> list = ordersService.list(pageNo, pageSize, ordersType, mobile, todate, fromdate);
		int count = ordersService.listCount(ordersType, mobile, todate, fromdate);
		result.setCount(count);
		result.setStatus("0");
		result.setData(list);
		return result;
	}
}
