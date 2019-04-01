package com.ola.qh.entity;

import java.util.Date;

public class Operating {

	private String id;
	
	private String userRoleId;///////管理员id
	
	private String userRoleNickname;//////////管理员昵称
	
	private String userRoleCategory;///////管理员类型
	
	private String operatingScope;////////改动区域
	
	private String operatingStatus;////////进行的操作
	
	private String operatingUser;////////被该动人
	
	private Date addtime;
	
	private String showtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserRoleCategory() {
		return userRoleCategory;
	}

	public void setUserRoleCategory(String userRoleCategory) {
		this.userRoleCategory = userRoleCategory;
	}

	public String getOperatingScope() {
		return operatingScope;
	}

	public void setOperatingScope(String operatingScope) {
		this.operatingScope = operatingScope;
	}

	public String getOperatingStatus() {
		return operatingStatus;
	}

	public void setOperatingStatus(String operatingStatus) {
		this.operatingStatus = operatingStatus;
	}

	public String getOperatingUser() {
		return operatingUser;
	}

	public void setOperatingUser(String operatingUser) {
		this.operatingUser = operatingUser;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public String getUserRoleNickname() {
		return userRoleNickname;
	}

	public void setUserRoleNickname(String userRoleNickname) {
		this.userRoleNickname = userRoleNickname;
	}
	
	
}
