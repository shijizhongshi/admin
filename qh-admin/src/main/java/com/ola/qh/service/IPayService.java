package com.ola.qh.service;

import java.util.Map;

public interface IPayService {

	public Map<String,String>  aliOrdersRefund(String extranceno,double money)throws Exception;
	
	public Map<String, String> wxOrderRefund(String extranceno,int totalfee,int refundfee) throws Exception;
}
