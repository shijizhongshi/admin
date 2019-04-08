package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserWithdraw;

public interface IUserWithdrawService {

	public List<UserWithdraw> selectUserWithdraw(String mobile,String payStatus,String fromdate,String todate,int pageNo,int pageSize);
	
	public int selectUserWithdrawCount(String mobile,String payStatus,String fromdate,String todate);
	
	public void agreeWithdraw()throws Exception;
	
	
}
