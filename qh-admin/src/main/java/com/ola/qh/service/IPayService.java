package com.ola.qh.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.util.Results;

public interface IPayService {

	public Map<String,String>  aliOrdersRefund(String extranceno,double money)throws Exception;
	
	public Map<String, String> wxOrderRefund(String extranceno,int totalfee,int refundfee) throws Exception;
}
