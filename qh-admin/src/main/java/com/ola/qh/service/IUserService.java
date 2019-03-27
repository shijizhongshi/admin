package com.ola.qh.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ola.qh.entity.AdminMenus;

import com.ola.qh.entity.User;
import com.ola.qh.util.Results;

public interface IUserService {
	
	public Results<String> saveUsers(User user);
	
	public List<User> selectUser(int pageNo,int pageSize,String mobile,String nickname,String userrole);
	
	public int selectUserCount(String mobile,String nickname,String userrole);
	
	public int updateUser(String isdisabled,String id);
	
	public int deleteUser(String id);
	
	public Results<String> adminLogin(String username,String password,HttpServletRequest request);
	
	public Results<List<AdminMenus>> adminisLogin(HttpServletRequest request);
	
	
	public List<User> selectStudent(String fromdate,String todate,String realnameORmobile,String status,
			int pageNo,int pageSize);
	
	public int selectStudentCount(String fromdate,String todate,
			String realnameORmobile,String status);
	
	public List<AdminMenus> listmenu();
}
