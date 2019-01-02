package com.ola.qh.service;

import com.ola.qh.util.Results;

public interface IUserService {
	
	public int updateUser(String userrole,String id);
	
	public Results<String> adminLogin(String username,String password);
}
