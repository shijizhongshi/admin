package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserWithdraw;
import com.ola.qh.util.Results;

public interface IUserWithdrawService {

	public List<UserWithdraw> selectUserWithdraw(String id,int pageNo,int pageSize);
	
	
	public void agreeWithdraw()throws Exception;
	
	
}
