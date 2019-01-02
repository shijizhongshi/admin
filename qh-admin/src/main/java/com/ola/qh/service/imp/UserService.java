package com.ola.qh.service.imp;

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
	public int updateUser(String userrole, String id) {
		
		User user=new User();
		user.setId(id);
		user.setUserrole(userrole);
		return userDao.updateUser(user);
	}

	@Override
	public Results<String> adminLogin(String username, String password) {
		String un = userDao.adminLogin(username, password);
		Results<String> result=new Results<String>();
		if(un!=null){
			result.setStatus("0");
			result.setData(un);
			return result;
		}else{
			result.setStatus("1");
			result.setMessage("用户名或者密码错误");
			return result;
		}
	}
	
	

}
