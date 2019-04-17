package com.ola.qh.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.AdminRoleMenusDao;
import com.ola.qh.dao.BusinessDao;
import com.ola.qh.dao.UserRoleDao;
import com.ola.qh.entity.AdminMenus;
import com.ola.qh.entity.AdminRoleMenu;
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
	@Autowired
	private AdminRoleMenusDao adminRoleMenusDao;

	/**
	 * 查
	 */
	@Override
	public Results<List<UserRole>> select(Integer pageNo, Integer pageSize, String username, String nickname,
			String category) {
		Results<List<UserRole>> results = new Results<List<UserRole>>();
		// 查询 分页展示
		List<UserRole> list = UserRoleDao.select(pageNo, pageSize, username, nickname, category);
		for (UserRole userRole : list) {
			List<AdminMenus> menus = new ArrayList<AdminMenus>();
			/*
			 * if (userRole.getLimits().indexOf(",") >= 0) { menus =
			 * Arrays.asList(userRole.getLimits().split(",")); }else {
			 * menus.add(userRole.getLimits()); }
			 */
			List<AdminRoleMenu> menuList = adminRoleMenusDao.listRoleMenu(userRole.getId(), null);
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
				menus.add(menu);
			}

			userRole.setMenus(menus);
		}
		Integer count = UserRoleDao.selectCount(username, nickname, category);
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
			///// 传过来的数据
			List<AdminMenus> menuList = userRole.getMenus();
			///// 数据库本身就有的数据
			List<AdminRoleMenu> oldmenuList = adminRoleMenusDao.listRoleMenu(userRole.getId(), null);
			for (int i = 0; i < oldmenuList.size(); i++) {

				int j = 0;
				for (; j < menuList.size(); j++) {
					if (menuList.get(j).getId().equals(oldmenuList.get(i).getMenuId())) {
						//////// 做一个修改的处理
						if (menuList.get(j).getList() == null || menuList.get(j).getList().size() == 0) {
							results.setStatus("1");
							results.setMessage("请保证大菜单下至少有一个小菜单");
							return results;

						}
						String submenuId = null;
						for (AdminMenus submenu : menuList.get(j).getList()) {
							if (submenuId == null) {
								submenuId = submenu.getId();
							} else {
								submenuId = submenuId + "," + submenu.getId();
							}
						}
						if (!oldmenuList.get(i).getSubmenuId().equals(submenuId)) {
							AdminRoleMenu armupdate = new AdminRoleMenu();
							armupdate.setUpdatetime(new Date());
							armupdate.setId(oldmenuList.get(i).getId());
							armupdate.setSubmenuId(submenuId);
							adminRoleMenusDao.updateRoleMenu(armupdate);
						}

					}
				}
				if (j == menuList.size()) {
					//// 把数据库里的数据删掉
					adminRoleMenusDao.deleteRoleMenu(userRole.getId(), oldmenuList.get(i).getMenuId());
				}
			}

			for (AdminMenus am : menuList) {
				List<AdminRoleMenu> armlist = adminRoleMenusDao.listRoleMenu(userRole.getId(), am.getId());
				if (armlist == null || armlist.size() == 0) {
					///// 说明要塞值
					AdminRoleMenu arm = new AdminRoleMenu();
					arm.setId(KeyGen.uuid());
					arm.setMenuId(am.getId());
					arm.setAddtime(new Date());
					arm.setRoleId(userRole.getId());
					if (am.getList() == null || am.getList().size() == 0) {
						results.setStatus("1");
						results.setMessage("请保证大菜单下至少有一个小菜单");
						return results;
					}
					String submenuId = null;
					for (AdminMenus adminMenu : am.getList()) {
						if (submenuId == null) {
							submenuId = adminMenu.getId();
						} else {
							submenuId = submenuId + "," + adminMenu.getId();
						}
					}

					arm.setSubmenuId(submenuId);
					adminRoleMenusDao.insertRoleMenu(arm);
				}
			}

			userRole.setUpdatetime(new Date());
			// userRole.setLimits(limits);
			Integer count = UserRoleDao.updateUserRole(userRole);
			if (count == 1) {
				results.setStatus("0");
				results.setMessage("修改成功");

				return results;
			} else {
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
				List<AdminMenus> limitsList = userRole.getMenus();
				String menuId = null;

				String roleId = KeyGen.uuid();
				////// 大菜单的集合
				for (AdminMenus menus : limitsList) {
					String submenuId = null;
					menuId = menus.getId();

					////// 大菜单对应小菜单的集合
					if (menus.getList().size() == 0) {
						results.setStatus("1");
						results.setMessage("请保证大菜单下至少有一个小菜单");

						return results;
					}
					for (AdminMenus adminMenus : menus.getList()) {
						if (submenuId == null) {
							submenuId = adminMenus.getId();
						} else {
							submenuId = submenuId + "," + adminMenus.getId();
						}
					}
					AdminRoleMenu arm = new AdminRoleMenu();
					arm.setMenuId(menuId);
					arm.setSubmenuId(submenuId);
					arm.setAddtime(new Date());
					arm.setRoleId(roleId);
					arm.setId(KeyGen.uuid());
					adminRoleMenusDao.insertRoleMenu(arm);

				}
				// userRole.setLimits(limits);
				userRole.setAddtime(new Date());
				userRole.setId(roleId);
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
