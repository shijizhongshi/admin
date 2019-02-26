package com.ola.qh.service.imp;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestParam;

import com.ola.qh.dao.BusinessDao;
import com.ola.qh.dao.UserBookDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.dao.UserLoginDao;
import com.ola.qh.entity.Business;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserBook;
import com.ola.qh.entity.UserLogin;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
@Service
public class UserService implements IUserService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserBookDao userbookDao;
	@Autowired
	private UserLoginDao userLoginDao;
	@Autowired
	private BusinessDao businessDao;

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
		Results<String> result=new Results<String>();
		if("admin".equals(username)){
			String un = userDao.adminLogin(username, password);
			if(un!=null){
				result.setStatus("0");
				result.setData(un);
				request.getSession().setAttribute("username", un);
				request.getSession().setAttribute("usernameId", "0");
				request.getSession().setAttribute("admin",true);
				request.getSession().setAttribute("jiamengshang",false);
				return result;
			}else{
				result.setStatus("1");
				result.setMessage("用户名或者密码错误");
				return result;
			}
		}else{
			Business b = businessDao.single(null,username,password);
			if(b!=null){
				request.getSession().setAttribute("username", b.getUsername());
				request.getSession().setAttribute("usernameId",b.getId());
				request.getSession().setAttribute("jiamengshang",true);
				request.getSession().setAttribute("admin",false);
				result.setStatus("0");
				return result;
			}else{
				result.setStatus("1");
				result.setMessage("用户名或者密码不对");
				return result;
			}
			
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
			Pattern p=Pattern.compile(Patterns.INTERNAL_MOBILE_PATTERN);
			Matcher m = p.matcher(user.getMobile());
			if(!m.matches()){
				result.setStatus("1");
				result.setMessage("手机号格式不对");
				return result;
			}
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

	@Override
	public List<User> selectStudent(String fromdate, String todate, String realnameORmobile, String status, int pageNo,
			int pageSize) {
		List<User> list=userDao.selectStudent(fromdate, todate, realnameORmobile, status, pageNo, pageSize);
		for(User user : list) {
			UserLogin ul = userLoginDao.selectUserLogin(user.getId());
			if(ul!=null){
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(ul.getUpdatetime()!=null){
					user.setLogintime(sf.format(ul.getUpdatetime()));
				}else{
					user.setLogintime(sf.format(ul.getAddtime()));
				}
			}else{
				user.setLogintime("用户还没有登录过");
			}
			
		}
		return list;
	}
	
	
	

}
