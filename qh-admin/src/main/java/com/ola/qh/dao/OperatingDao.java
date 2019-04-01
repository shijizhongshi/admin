package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Operating;

public interface OperatingDao {

	public List<Operating> operatingList(@Param("userRoleCategory")String userRoleCategory,@Param("userRoleNickname")String userRoleNickname,
			@Param("operatingScope")String operatingScope,@Param("operatingStatus")String operatingStatus,
			@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public int operatingCount(@Param("userRoleCategory")String userRoleCategory,@Param("userRoleNickname")String userRoleNickname,
			@Param("operatingScope")String operatingScope,@Param("operatingStatus")String operatingStatus);
	
	public int insertOperating(Operating operating);
	
	public int deleteOperating(@Param("id")String id);
}
