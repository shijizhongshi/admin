package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.SalesmanSecond;
import com.ola.qh.util.Results;

public interface ISalesmanSecondService {

	public Results<List<SalesmanSecond>> SecondList(String salesmanId,String mobile,int pageNo,int pageSize);
	
	public Results<String> insertSecond(SalesmanSecond salesmanSecond);
	
	public Results<String> updateSecond(String salesmanId,String mobile,String salesmanIdNew);
	
	public Results<String> deleteSecond(String salesmanId,String id);
}
