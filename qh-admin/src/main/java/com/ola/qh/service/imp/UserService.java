package com.ola.qh.service.imp;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.AdminRoleMenusDao;
import com.ola.qh.dao.BusinessDao;
import com.ola.qh.dao.UserBookDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.dao.UserLoginDao;
import com.ola.qh.dao.UserRoleDao;
import com.ola.qh.entity.AdminMenus;
import com.ola.qh.entity.AdminRoleMenu;
import com.ola.qh.entity.Business;
import com.ola.qh.entity.BusinessBook;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserBook;
import com.ola.qh.entity.UserLogin;
import com.ola.qh.entity.UserRole;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserBookDao userbookDao;
	@Autowired
	private UserLoginDao userLoginDao;
	@Autowired
	private BusinessDao businessDao;
	@Autowired
	private UserRoleDao UserRoleDao;
	@Autowired
	private AdminRoleMenusDao adminRoleMenusDao;

	@Override
	public int updateUser(String isdisabled, String id) {

		User user = new User();
		user.setId(id);
		user.setIsdisabled(isdisabled);
		user.setUpdatetime(new Date());
		return userDao.updateUser(user);
	}

	@Override
	public Results<String> adminLogin(String username, String password, HttpServletRequest request) {
		Results<String> result = new Results<String>();
		if ("admin".equals(username)) {
			String un = userDao.adminLogin(username, password);
			if (un != null) {
				result.setStatus("0");
				result.setData(un);
				request.getSession().setAttribute("surplusaccount", 0);
				request.getSession().setAttribute("username", un);
				/* request.getSession().setAttribute("admin",true); */
				request.getSession().setAttribute("jiamengshang", false);
				request.getSession().setAttribute("isrole", "1");
				return result;
			} else {
				result.setStatus("1");
				result.setMessage("用户名或者密码错误");
				return result;
			}
		} else {
			Business b = businessDao.single(null, username, password);
			if (b != null) {
				BusinessBook bb = businessDao.singlebook(b.getId(), null);
				if (bb != null) {
					request.getSession().setAttribute("surplusaccount", bb.getSurplusaccount());
				}
				request.getSession().setAttribute("username", b.getUsername());
				request.getSession().setAttribute("jiamengshang", true);
				request.getSession().setAttribute("isrole", "2");
				/* request.getSession().setAttribute("admin",false); */
				result.setData("2");
				result.setStatus("0");
				return result;
			} else {
				/////// 其他身份的用户登录
				UserRole ur = UserRoleDao.single(null, username, password);
				if (ur != null) {
					request.getSession().setAttribute("surplusaccount", 0);
					request.getSession().setAttribute("username", ur.getUsername());
					request.getSession().setAttribute("jiamengshang", false);
					request.getSession().setAttribute("isrole", "3");
					result.setStatus("0");
					result.setData("3");
					return result;
				}

				result.setStatus("1");
				result.setMessage("用户名或者密码不对");
				return result;
			}

		}

	}

	@Override
	public Results<List<AdminMenus>> adminisLogin(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Results<List<AdminMenus>> results = new Results<List<AdminMenus>>();
		List<AdminMenus> list = new ArrayList<AdminMenus>();
		Object obj = request.getSession().getAttribute("isrole");
		if (obj != null) {

			String roles = String.valueOf(obj);
			if ("3".equals(roles)) {
				////// 3:权限小的系统用户(按照选择权限展示菜单)
				UserRole ur = UserRoleDao.single(null, String.valueOf(request.getSession().getAttribute("username")),
						null);
				if (ur != null) {
					List<AdminRoleMenu> menuList = adminRoleMenusDao.listRoleMenu(ur.getId(), null);
					for (AdminRoleMenu adminRoleMenu : menuList) {
						AdminMenus menu = new AdminMenus();
						List<AdminMenus> newmenus = adminRoleMenusDao.listmenu(null, adminRoleMenu.getMenuId());
						menu = newmenus.get(0);
						List<String> submenuId = new ArrayList<String>();
						if (adminRoleMenu.getSubmenuId().indexOf(",") >= 0) {
							submenuId = Arrays.asList(adminRoleMenu.getSubmenuId().split(","));
						} else {
							submenuId.add(adminRoleMenu.getSubmenuId());
						}
						List<AdminMenus> sublist = new ArrayList<AdminMenus>();
						for (String subId : submenuId) {
							List<AdminMenus> newsubmenus = adminRoleMenusDao.listsubmenu(null, subId);
							sublist.add(newsubmenus.get(0));

						}
						menu.setList(sublist);
						list.add(menu);
					}

				} else {
					results.setStatus("1");
					results.setMessage("该账号不存在");
					return results;
				}

			} else if ("1".equals(roles)) {
				////// 查全部的菜单
				list = adminRoleMenusDao.listmenu(null, null);
				for (AdminMenus adminMenus : list) {
					adminMenus.setList(adminRoleMenusDao.listsubmenu(adminMenus.getId(), null));
				}

			} else if ("2".equals(roles)) {
				///// 只查学员管理
				list = adminRoleMenusDao.listmenu("学员信息管理", null);
				for (AdminMenus adminMenus : list) {
					adminMenus.setList(adminRoleMenusDao.listsubmenu(adminMenus.getId(), null));
				}
			}
			results.setStatus("0");
			results.setData(list);
			return results;
		}
		results.setStatus("1");
		return results;

	}

	@Override
	public Results<List<User>> selectUser(int pageNo, int pageSize, String mobile, String nickname, String userrole) {
		Results<List<User>> results = new Results<List<User>>();

		List<User> list = userDao.selectUser(pageNo, pageSize, mobile, nickname, userrole);
		// 如果根据查询条件没有查询出用户 给页面一条提示
		if (list.size() == 0) {
			results.setStatus("0");
			results.setMessage("根据条件查询为空");

			return results;
		}
		for (User user : list) {
			// 循环遍历 在user_buy_course表中查询是否存在
			Integer count = userDao.selectCountByUserId(user.getId());
			if (count == 0) {
				// 查不到 非学员
				user.setIsStudent(0);
			} else {
				// user_buy_course表中有记录 是学员
				user.setIsStudent(1);
			}
		}
		int count = userDao.selectUserCount(mobile, nickname, userrole);
		results.setStatus("0");
		results.setData(list);
		results.setCount(count);

		return results;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<String> saveUsers(User user) {

		Results<String> result = new Results<String>();
		try {
			Pattern p = Pattern.compile(Patterns.INTERNAL_MOBILE_PATTERN);
			Matcher m = p.matcher(user.getMobile());
			if (!m.matches()) {
				result.setStatus("1");
				result.setMessage("手机号格式不对");
				return result;
			}
			if (user.getId() != null && !"".equals(user.getId())) {
				////// 编辑学员信息
				Integer count = userDao.updateUser(user);
				if (count != 0) {
					result.setStatus("0");
					return result;
				} else {
					result.setStatus("1");
					result.setMessage("修改失败");

					return result;
				}
			} else {
				int count = userDao.selectUserCount(user.getMobile(), null, null);
				if (count != 0) {
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
		List<User> list = userDao.selectStudent(fromdate, todate, realnameORmobile, status, pageNo, pageSize);
		for (User user : list) {
			UserLogin ul = userLoginDao.selectUserLogin(user.getId());
			if (ul != null) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (ul.getUpdatetime() != null) {
					user.setLogintime(sf.format(ul.getUpdatetime()));
				} else {
					user.setLogintime(sf.format(ul.getAddtime()));
				}
			} else {
				user.setLogintime("用户还没有登录过");
			}

		}
		return list;
	}

	@Override
	public int selectStudentCount(String fromdate, String todate, String realnameORmobile, String status) {
		// TODO Auto-generated method stub
		return userDao.selectStudentCount(fromdate, todate, realnameORmobile, status);

	}

	@Override
	public List<AdminMenus> listmenu() {
		// TODO Auto-generated method stub
		List<AdminMenus> list = adminRoleMenusDao.listmenu(null, null);
		for (AdminMenus adminMenus : list) {
			adminMenus.setList(adminRoleMenusDao.listsubmenu(adminMenus.getId(), null));
		}
		return list;
	}

}
