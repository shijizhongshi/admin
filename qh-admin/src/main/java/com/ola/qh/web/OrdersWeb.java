package com.ola.qh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/orders")
public class OrdersWeb {

	@RequestMapping("/courselist")
	public String courseOrders(){
		return "orders/course_orders";
	}
	@RequestMapping("/today-bargain")
	public String todaybargain(){
		return "orders/today-bargain";
	}
	@RequestMapping("/list")
	public String orders(){
		return "orders/orders";
	}
	}
