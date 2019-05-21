package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.SalesmanSecond;

public interface SalesmanSecondDao {

	public List<SalesmanSecond> SecondList(@Param("salesmanId")String salesmanId,@Param("mobile")String mobile,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public SalesmanSecond existSecond(@Param("mobile")String mobile);
	
	public int SecondCount(@Param("salesmanId")String salesmanId,@Param("mobile")String mobile);
	
	public int insertSecond(SalesmanSecond salesmanSecond);
	
	public int updateSecond(SalesmanSecond salesmanSecond);
	
	public int deleteSecond(@Param("salesmanId")String salesmanId,@Param("id")String id);
}
