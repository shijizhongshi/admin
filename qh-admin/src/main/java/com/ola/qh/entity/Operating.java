package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class Operating {

	private String id;
	
	@NotEmpty
	private String userRoleUsername;//////////管理员昵称
	
	@NotEmpty
	private String operatingScope;////////改动区域
	
	@NotEmpty
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

	public String getUserRoleUsername() {
		return userRoleUsername;
	}

	public void setUserRoleUsername(String userRoleUsername) {
		this.userRoleUsername = userRoleUsername;
	}
	
	
}
