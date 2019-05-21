package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Salesman;

public interface SalesmanDao {

	public List<Salesman> SalesmanList(@Param("name")String name,@Param("mobile")String mobile,@Param("address")String address,
			@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public int SalesmanCount(@Param("name")String name,@Param("mobile")String mobile,@Param("address")String address);
	
	public Salesman exist(@Param("mobile")String mobile);
	
	public int insertSalesman(Salesman salesman);
	
	public int updateSalesman(Salesman salesman);
	
	public int deleteSalesman(@Param("id")String id);
}
