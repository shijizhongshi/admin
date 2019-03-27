package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.List;

public class AdminMenus {

	private String id;
	
	private String category;
	
	private String names;
	
	private String menuId;
	
	private String outLinks;
	
	private List<AdminMenus> list=new ArrayList<AdminMenus>();
	
	
	private List<AdminMenus> adminSubMenus=new ArrayList<AdminMenus>();
	
	
	public List<AdminMenus> getAdminSubMenus() {
		return adminSubMenus;
	}

	public void setAdminSubMenus(List<AdminMenus> adminSubMenus) {
		this.adminSubMenus = adminSubMenus;
	}

	public String getOutLinks() {
		return outLinks;
	}

	public void setOutLinks(String outLinks) {
		this.outLinks = outLinks;
	}

	public List<AdminMenus> getList() {
		return list;
	}

	public void setList(List<AdminMenus> list) {
		this.list = list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}
