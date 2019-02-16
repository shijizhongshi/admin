package com.ola.qh.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ola.qh.entity.User;
import com.ola.qh.util.Results;

public interface IUserService {
	
	public List<User> selectUser(String mobile,String nickname,String userrole);
	
	public int updateUser(String isdisabled,String id);
	
	public Results<String> adminLogin(String username,String password,HttpServletRequest request);
}
