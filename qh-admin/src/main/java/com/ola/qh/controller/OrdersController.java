package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersProduct;
import com.ola.qh.service.IOrdersService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

	@Autowired
	private IOrdersService ordersService;
	
	@RequestMapping("/list")
	public Results<List<Orders>> list(
			@RequestParam(name="pageSize",required=true)int pageSize,
			@RequestParam(name="pageNo",required=true)int pageNo,
			@RequestParam(name="ordersType",required=false)String ordersType,
			@RequestParam(name="mobile",required=false)String mobile,
			@RequestParam(name="recommendTeacher",required=false)String recommendTeacher,
			@RequestParam(name="orderno",required=false)String orderno,
			@RequestParam(name="ordersStatus",required=false)String ordersStatus,
			@RequestParam(name="receiver",required=false)String receiver,
			@RequestParam(name="todate",required=false)String todate,
			@RequestParam(name="fromdate",required=false)String fromdate){
		
		Results<List<Orders>> result=new Results<List<Orders>>();
		List<Orders> list = ordersService.list(pageNo, pageSize, ordersType, mobile, todate, fromdate,orderno,ordersStatus,recommendTeacher,receiver);
		int count = ordersService.listCount(ordersType, mobile, todate, fromdate, orderno, ordersStatus, recommendTeacher,receiver);
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
	/**
	 * 商品订单
	 * 管理员同意拒绝退款
	 * @param ordersProductId
	 * @param statusCode
	 * @return
	 */
	@RequestMapping("/update/product")
	public Results<String> updateProduct(@RequestParam(name="ordersProductId",required=true)String ordersProductId,
			@RequestParam(name="statusCode",required=true)String statusCode){
		
		return ordersService.updateRefund(ordersProductId, statusCode);
	}
	/**
	 * 服务订单
	 * 管理员同意或者是拒绝
	 * @param ordersId
	 * @param statusCode
	 * @return
	 */
	@RequestMapping("/update/serve")
	public Results<String> updateServe(@RequestParam(name="ordersId",required=true)String ordersId,
			@RequestParam(name="statusCode",required=true)String statusCode){
		return ordersService.updateServeRefund(ordersId, statusCode);
	}
	
	@RequestMapping("/exportExcel")
	public Results<String> exportExcel(
			@RequestParam(name="ordersType",required=false)String ordersType,
			@RequestParam(name="mobile",required=false)String mobile,
			@RequestParam(name="recommendTeacher",required=false)String recommendTeacher,
			@RequestParam(name="orderno",required=false)String orderno,
			@RequestParam(name="ordersStatus",required=false)String ordersStatus,
			@RequestParam(name="receiver",required=false)String receiver,
			@RequestParam(name="todate",required=false)String todate,
			@RequestParam(name="fromdate",required=false)String fromdate){
		
		
		return ordersService.exportExcel(ordersType, mobile, todate, fromdate, orderno, ordersStatus, recommendTeacher, receiver);
	}
	
	
}
