package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Business;
import com.ola.qh.entity.BusinessBook;
import com.ola.qh.entity.BusinessBookHistory;

public interface BusinessDao {

	public int insert(Business b);
	
	public List<Business> selectList(@Param("name")String name,
			@Param("address")String address,@Param("fromdate")String fromdate,
			@Param("todate")String todate,@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);
	
	public int selectListCount(@Param("name")String name,
			@Param("address")String address,@Param("fromdate")String fromdate,
			@Param("todate")String todate);
	
	public int exist(@Param("name")String name);
	
	public int update(Business b);
	
	public Business single(@Param("id")String id,@Param("username")String username,@Param("password")String password);
	
	public int delete(@Param("id")String id);
	
	

	public int insertbook(BusinessBook bb);
	
	public BusinessBook singlebook(@Param("businessId")String businessId,
			@Param("salesName")String salesName);
	
	public int updatebook(BusinessBook bb);
	
	
	
	public int insertBookHistory(BusinessBookHistory bbh); 
	
	
	public int insertBusinessUser(@Param("id")String id,
			@Param("businessId")String businessId,
			@Param("userId")String userId);
	
	public String singleBusinessUser(@Param("businessId")String businessId,
			@Param("userId")String userId);
	
	public int deleteBusinessUser(@Param("businessId")String businessId);
}
