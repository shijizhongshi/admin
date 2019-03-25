package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.AdminMenus;

public interface AdminRoleMenusDao {

	public List<AdminMenus> listmenu(@Param("names")String names);
	
	public List<AdminMenus> listsubmenu(@Param("menuId")String menuId);
}
