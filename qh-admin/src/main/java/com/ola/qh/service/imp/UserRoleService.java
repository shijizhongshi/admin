package com.ola.qh.service.imp;

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
	public Results<UserRole> selectById(String id) {
		Results<UserRole> results = new Results<UserRole>();
		UserRole userRole = UserRoleDao.single(id, null);
		results.setMessage("查询成功");
		results.setStatus("0");
		results.setData(userRole);

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
			// 首先判断账户是否存在
			if (userRole.getUsername() == null) {
				results.setStatus("1");
				results.setMessage("该账户不存在，请核对后重新输入");

				return results;
			}
			// 修改操作
			userRole.setUpdatetime(new Date());
			Integer count = UserRoleDao.update(userRole);
			if (count == 1) {
				results.setStatus("0");
				results.setMessage("修改成功");

				return results;
			}

			return results;
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
	public Results<UserRole> insert(UserRole userRole, String password) {
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
			// 第三 判断username不能与本身表中重复
			String username = userRole.getUsername();
			Integer count = UserRoleDao.selectUsername(username);
			if (true) {
				// 判断账号密码有效性(两次输入一致)
				if (!password.equals(userRole.getPassword())) {
					results.setStatus("1");
					results.setMessage("两次密码输入不一致  请核对！");

					return results;
				}
				userRole.setAddtime(new Date());
				userRole.setId(KeyGen.uuid());
				UserRoleDao.saveUserRole(userRole);
				results.setStatus("0");
				results.setMessage("添加成功");

				return results;
			}
			return null;
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
