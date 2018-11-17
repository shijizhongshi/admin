package com.ola.qh.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserDao;
import com.ola.qh.service.IUserService;
@Service
public class UserService implements IUserService{

	@Autowired
	private UserDao userDao;

	@Override
	public int updateUser(int userrole, String id) {
		
		return userDao.updateUser(userrole, id);
	}
	
	

}
