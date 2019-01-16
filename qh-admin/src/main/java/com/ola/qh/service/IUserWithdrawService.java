package com.ola.qh.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserWithdraw;
import com.ola.qh.util.Results;

public interface IUserWithdrawService {

	public List<UserWithdraw> selectUserWithdraw(String mobile,String payStatus,String fromdate,String todate,int pageNo,int pageSize);
	
	public int selectUserWithdrawCount(String mobile,String payStatus,String fromdate,String todate);
	
	public void agreeWithdraw()throws Exception;
	
	
}
