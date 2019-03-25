package com.ola.qh.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.BusinessDao;
import com.ola.qh.dao.UserRoleDao;
import com.ola.qh.entity.Business;
import com.ola.qh.entity.UserRole;
import com.ola.qh.service.IUserRoleService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class UserRoleService implements IUserRoleService {

	@Autowired
	private UserRoleDao UserRoleDao;
	@Autowired
	private BusinessDao BusinessDao;

	/**
	 * 查
	 */
	@Override
	public Results<List<UserRole>> select(Integer pageNo,Integer pageSize) {
		Results<List<UserRole>> results = new Results<List<UserRole>>();
		//查询  分页展示
		List<UserRole> list = UserRoleDao.select(pageNo,pageSize);
		for (UserRole userRole : list) {
			List<String> menus=new ArrayList<String>();
			if (userRole.getLimits().indexOf(",") >= 0) {
				menus = Arrays.asList(userRole.getLimits().split(","));
			}else {
				menus.add(userRole.getLimits());
			}
			userRole.setMenus(menus);
		}
		Integer count = UserRoleDao.selectCount();
		results.setStatus("0");
		results.setMessage("分页查询 成功");
		results.setData(list);
		results.setCount(count);
		
		return results;
	}

	/**
	 * 修改
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<UserRole> update(UserRole userRole) {
		Results<UserRole> results = new Results<UserRole>();
		try {
			// 修改操作
			List<String> limitsList=userRole.getMenus();
			String limits=null;
			for (String menus : limitsList) {
				if(limits==null){
					limits=menus;
				}else{
					limits=limits+","+menus;
				}
			}
			
			userRole.setUpdatetime(new Date());
			userRole.setLimits(limits);
			Integer count = UserRoleDao.updateUserRole(userRole);
			if (count == 1) {
				results.setStatus("0");
				results.setMessage("修改成功");

				return results;
			}else {
				results.setStatus("1");
				results.setMessage("修改失败");
				
				return results;
			}
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("修改失败");

			return results;
		}
	}

	/**
	 * 添加
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<UserRole> insert(UserRole userRole) {
		Results<UserRole> results = new Results<UserRole>();
		try {
			// 第一判断 账号名不能为admin
			if (userRole.getUsername().equals("admin")) {
				results.setStatus("1");
				results.setMessage("用户名不能为admin！");

				return results;
			}
			// 第二判断 不能与加盟商账号冲突
			Business business = BusinessDao.single(null, userRole.getUsername(), null);
			if (business != null) {
				results.setStatus("1");
				results.setMessage("与加盟商账号冲突，请重新输入！");

				return results;
			}
			// 第三判断 username不能与本身表中重复
			// 根据username字段查询
			UserRole userRoles = UserRoleDao.selectByUsername(userRole.getUsername());
			if (userRoles == null) {
				List<String> limitsList=userRole.getMenus();
				String limits=null;
				for (String menus : limitsList) {
					if(limits==null){
						limits=menus;
					}else{
						limits=limits+","+menus;
					}
				}
				userRole.setLimits(limits);
				userRole.setAddtime(new Date());
				userRole.setId(KeyGen.uuid());
				UserRoleDao.saveUserRole(userRole);
				results.setStatus("0");
				results.setMessage("添加成功");

				return results;
			}
			results.setStatus("1");
			results.setMessage("添加失败");

			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("添加失败");

			return results;
		}
	}

	/**
	 * 删除
	 */
	@Override
	public Results<UserRole> deleteById(String id) {
		Results<UserRole> results = new Results<UserRole>();
		Integer count = UserRoleDao.deleteById(id);
		if (count == 1) {
			results.setStatus("0");
			results.setMessage("删除成功");

			return results;
		}
		results.setStatus("1");
		results.setMessage("删除失败");

		return results;
	}

	/**
	 * 查询用户类别
	 */
	@Override
	public List<String> selectCategory() {
		List<String> list = UserRoleDao.selectCategory();

		return list;
	}
}
