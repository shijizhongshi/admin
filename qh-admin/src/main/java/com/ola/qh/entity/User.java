package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class User {

	private String id;

	private String nickname;

	@NotEmpty(message = "密码不能为空")
	private String password;

	private String verification;

	private Date addtime;

	private String headimg;

	private String userrole;

	private String isdoctor;

	@NotEmpty(message = "手机号不能为空")
	private String mobile;

	private Date updatetime;

	private String isdisabled;

	private String address;///// 地址

	private String realname;///// 真实姓名

	private String logintime;//// 最后一次登录的时间

	private Integer isStudent;// 是否为学员 | 0：不是学员 | 1：是学员

	private String usertype;// service层拼接userrole和isdoctor字段 在页面展示

	private String birthday;// 用户生日

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public Integer getIsStudent() {
		return isStudent;
	}

	public void setIsStudent(Integer isStudent) {
		this.isStudent = isStudent;
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public String getIsdoctor() {
		return isdoctor;
	}

	public void setIsdoctor(String isdoctor) {
		this.isdoctor = isdoctor;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getIsdisabled() {
		return isdisabled;
	}

	public void setIsdisabled(String isdisabled) {
		this.isdisabled = isdisabled;
	}
}
