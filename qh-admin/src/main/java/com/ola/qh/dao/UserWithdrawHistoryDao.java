package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserWithdrawHistory;

public interface UserWithdrawHistoryDao {

	public List<UserWithdrawHistory> selectUserWithdrawHistory(@Param("userId")String userId,@Param("pageNo")int page,@Param("pageSize")int zupageSize);
	
	public int updateUserWithdrawHistory(UserWithdrawHistory userwithdrawhistory);
	
	
}
