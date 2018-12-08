package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserWithdrawHistory;

public interface IUserWithdrawHistoryService {

	public List<UserWithdrawHistory> selectUserWithdrawHistory(String userId,int pageNo,int pageSize);
	
	public int updateUserWithdrawHistory(UserWithdrawHistory userwithdrawhistory);
	
	
}
