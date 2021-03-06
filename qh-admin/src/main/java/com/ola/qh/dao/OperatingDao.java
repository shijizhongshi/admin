package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Operating;

public interface OperatingDao {

	public List<Operating> operatingList(@Param("userRoleUsername")String userRoleUsername,
			@Param("operatingScope")String operatingScope,@Param("operatingStatus")String operatingStatus,
			@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("todate")String todate,
			@Param("fromdate")String fromdate);
	
	public int operatingCount(@Param("userRoleUsername")String userRoleUsername,
			@Param("operatingScope")String operatingScope,@Param("operatingStatus")String operatingStatus,@Param("todate")String todate,
			@Param("fromdate")String fromdate);
	
	public int insertOperating(Operating operating);
	
	public int deleteOperating(@Param("id")String id);
}
