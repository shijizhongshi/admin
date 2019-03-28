package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.AdminMenus;
import com.ola.qh.entity.AdminRoleMenu;

public interface AdminRoleMenusDao {

	public List<AdminMenus> listmenu(@Param("names")String names,@Param("id")String id);
	
	public List<AdminMenus> listsubmenu(@Param("menuId")String menuId,@Param("id")String id);
	
	public int insertRoleMenu(AdminRoleMenu arm);
	
	public int updateRoleMenu(AdminRoleMenu arm);
	
	public int deleteRoleMenu(@Param("roleId")String roleId,@Param("menuId")String menuId);
	
	public List<AdminRoleMenu> listRoleMenu(@Param("roleId")String roleId,@Param("menuId")String menuId);
}
