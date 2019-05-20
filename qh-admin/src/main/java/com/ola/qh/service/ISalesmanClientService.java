package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.SalesmanClient;
import com.ola.qh.util.Results;

public interface ISalesmanClientService {

	public Results<List<SalesmanClient>> ClientList(String salesmanId,String mobile,int pageNo,int pageSize);
	
	public Results<String> insertClient(SalesmanClient salesmanClient);
	
	public Results<String> updateClient(String salesmanId,String mobile,String salesmanIdNew);
	
	public Results<String> deleteClient(String salesmanId,String id);
}
