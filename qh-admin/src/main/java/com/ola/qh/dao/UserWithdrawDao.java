package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserWithdraw;

public interface UserWithdrawDao {

	public List<UserWithdraw> selectUserWithdraw(@Param("id")String id,@Param("pageNo")int page,@Param("pageSize")int zupageSize);
	
	public int updateUserWithdraw(UserWithdraw userwithdrawhistory);
	
	
}
