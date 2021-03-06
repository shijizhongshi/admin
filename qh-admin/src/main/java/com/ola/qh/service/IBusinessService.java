package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Business;
import com.ola.qh.util.Results;

public interface IBusinessService {

	public Results<String> save(Business b);
	
	public Results<String> charge(Business b);
	
	public List<Business> list(String name,String address,String fromdate,
			String todate,int pageNo,int pageSize,String expireOrders,String superOrders);
	
	public int delete(String id);
	
	public int selectListCount(String name,String address,String fromdate,
			String todate);
}
