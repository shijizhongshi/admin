package com.ola.qh.service;

import javax.servlet.http.HttpServletRequest;

import com.ola.qh.util.Results;

public interface IUserService {
	
	public int updateUser(String userrole,String id);
	
	public Results<String> adminLogin(String username,String password,HttpServletRequest request);
}
