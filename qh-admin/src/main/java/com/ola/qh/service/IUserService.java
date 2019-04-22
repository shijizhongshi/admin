package com.ola.qh.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.AdminMenus;
import com.ola.qh.entity.User;
import com.ola.qh.util.Results;

public interface IUserService {
	
	public Results<String> saveUsers(User user);
	
	public Results<List<User>> selectUser(int pageNo,int pageSize,String mobile,String nickname,String userrole);
	
	public int updateUser(String isdisabled,String id);
	
	public int deleteUser(String id);
	
	public Results<String> adminLogin(String username,String password,HttpServletRequest request);
	
	public Results<List<AdminMenus>> adminisLogin(HttpServletRequest request);
	
	
	public List<User> selectStudent(String fromdate,String todate,String realnameORmobile,String status,
			int pageNo,int pageSize);
	
	public int selectStudentCount(String fromdate,String todate,
			String realnameORmobile,String status);
	
	public List<AdminMenus> listmenu();

	public Results<List<User>> send(String title, String content, String sex, String courseTypeSubclassName, String userrole,
			String isdoctor, String birthday);

	public String selectIdByMobile(String mobile);

	public String selectNameById(String userid);
}
