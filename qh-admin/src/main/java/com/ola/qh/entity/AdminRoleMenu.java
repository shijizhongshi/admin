package com.ola.qh.entity;

import java.util.Date;

public class AdminRoleMenu {

	private String id;
	
	private String roleId;
	
	private String menuId;
	
	private String submenuId;
	
	private Date addtime;
	
	private Date updatetime;

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getSubmenuId() {
		return submenuId;
	}

	public void setSubmenuId(String submenuId) {
		this.submenuId = submenuId;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	} 
	
	
}
