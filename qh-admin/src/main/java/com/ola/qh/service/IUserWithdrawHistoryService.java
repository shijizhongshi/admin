package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserWithdrawHistory;
import com.ola.qh.util.Results;

public interface IUserWithdrawHistoryService {

	public List<UserWithdrawHistory> selectUserWithdrawHistory(String id,int pageNo,int pageSize);
	
	public Results<String> updateUserWithdrawHistory(UserWithdrawHistory userwithdrawhistory);
	
	
}
