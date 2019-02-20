package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserDao;
import com.ola.qh.entity.User;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.Results;
@Service
public class UserService implements IUserService{

	@Autowired
	private UserDao userDao;

	@Override
	public int updateUser(String isdisabled, String id) {
		
		User user=new User();
		user.setId(id);
		user.setIsdisabled(isdisabled);
		user.setUpdatetime(new Date());
		return userDao.updateUser(user);
	}

	@Override
	public Results<String> adminLogin(String username, String password,HttpServletRequest request) {
		String un = userDao.adminLogin(username, password);
		Results<String> result=new Results<String>();
		if(un!=null){
			result.setStatus("0");
			result.setData(un);
			request.getSession().setAttribute("username", un);
			return result;
		}else{
			result.setStatus("1");
			result.setMessage("用户名或者密码错误");
			return result;
		}
	}

	@Override
	public List<User> selectUser(int pageNo,int pageSize, String mobile, String nickname, String userrole) {
		// TODO Auto-generated method stub
		return userDao.selectUser(pageNo,pageSize,mobile, nickname, userrole);
	}

	@Override
	public int selectUserCount(String mobile, String nickname, String userrole) {
		// TODO Auto-generated method stub
		return userDao.selectUserCount(mobile, nickname, userrole);
	}

	
	

}
