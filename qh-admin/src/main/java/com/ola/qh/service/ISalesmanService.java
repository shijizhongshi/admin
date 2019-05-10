package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Salesman;
import com.ola.qh.util.Results;

public interface ISalesmanService {

	public Results<List<Salesman>> SalesmanList(String name,String mobile,String address,int pageNo,int pageSize);
	
	public Results<String> insertSalesman(Salesman salesman);
	
	public Results<String> updateSalesman(Salesman salesman);
	
	public Results<String> deleteSalesman(String id);
}
