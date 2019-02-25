package com.ola.qh.service.imp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestParam;

import com.ola.qh.dao.UserBookDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserBook;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
@Service
public class UserService implements IUserService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserBookDao userbookDao;
	

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

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<String> saveUsers(User user) {

		Results<String> result = new Results<String>();
		try {
			if(user.getId()!=null && !"".equals(user.getId())){
				//////编辑学员信息
				userDao.updateUser(user);
				result.setStatus("0");
				return result;
			}else{
				int count=userDao.selectUserCount(user.getMobile(), null, null);
				if (count!=0) {
					result.setStatus("1");
					result.setMessage("手机号已存在");
					return result;
				}
				user.setAddtime(new Date());
				user.setId(KeyGen.uuid());
				userDao.insertUser(user);
				// userDao.loginUser(user.getMobile(), user.getPassword());

				UserBook userbook = new UserBook();
				userbook.setId(KeyGen.uuid());
				userbook.setUserId(user.getId());
				userbook.setCanWithdraw(BigDecimal.ZERO);
				userbook.setAddtime(new Date());
				userbookDao.saveUserBook(userbook);
				
				result.setStatus("0");
				return result;
			}
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("保存失败");
			return result;
		}

	}

	@Override
	public int deleteUser(String id) {
		// TODO Auto-generated method stub
		return userDao.delete(id);
	}
	
	
	

}
