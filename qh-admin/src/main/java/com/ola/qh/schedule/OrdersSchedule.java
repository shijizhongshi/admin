package com.ola.qh.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ola.qh.service.IOrdersService;

@Component
public class OrdersSchedule {

	@Autowired
	private IOrdersService ordersService;
	
	@Scheduled(cron="0 0 1 ? * *")//1点执行)
	public void autoComfirmOrders(){
		////7天自动确认收货
		ordersService.comfirmOrders();
		
	}
}
