package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.SalesmanClient;

public interface SalesmanClientDao {

	public List<SalesmanClient> ClientList(@Param("salesmanId")String salesmanId,@Param("mobile")String mobile,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public SalesmanClient existClient(@Param("mobile")String mobile);
	
	public int ClientCount(@Param("salesmanId")String salesmanId,@Param("mobile")String mobile);
	
	public int insertClient(SalesmanClient salesmanClient);
	
	public int updateClient(SalesmanClient salesmanClient);
	
	public int deleteClient(@Param("salesmanId")String salesmanId,@Param("id")String id);
}
