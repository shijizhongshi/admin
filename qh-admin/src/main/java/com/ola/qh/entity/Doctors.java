package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class Doctors {

	private String id;
	
	private String userId;
	@NotEmpty
	private String name;
	@NotEmpty
	private String headImg;
	@NotEmpty
	private String sexs;
	@NotEmpty
	private String edu;
	@NotEmpty
	private String school;
	@NotEmpty
	private String hospital;
	@NotEmpty
	private String offices;
	@NotEmpty
	private String professional;////职称
	
	private String signs;
	@NotEmpty
	private String skilled;
	@NotEmpty
	private String address;
	@NotEmpty
	private String idcard;
	@NotEmpty
	private String frontIdcardImg;
	@NotEmpty
	private String reverseIdcardImg;
	@NotEmpty
	private String professionalImg;///职称的图片
	@NotEmpty
	private String practiceImg;////执业医师证的图片
	@NotEmpty
	private String elseImg;////其他的图片
	
	private Date addtime;
	
	private Date updatetime;
	
	private int islimit;
	
	private int isrecommend;
	
	private String isvirtual;
	
	private String showtime;

	public int getIslimit() {
		return islimit;
	}

	public void setIslimit(int islimit) {
		this.islimit = islimit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getSexs() {
		return sexs;
	}

	public void setSexs(String sexs) {
		this.sexs = sexs;
	}

	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getOffices() {
		return offices;
	}

	public void setOffices(String offices) {
		this.offices = offices;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getSigns() {
		return signs;
	}

	public void setSigns(String signs) {
		this.signs = signs;
	}

	public String getSkilled() {
		return skilled;
	}

	public void setSkilled(String skilled) {
		this.skilled = skilled;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getFrontIdcardImg() {
		return frontIdcardImg;
	}

	public void setFrontIdcardImg(String frontIdcardImg) {
		this.frontIdcardImg = frontIdcardImg;
	}

	public String getReverseIdcardImg() {
		return reverseIdcardImg;
	}

	public void setReverseIdcardImg(String reverseIdcardImg) {
		this.reverseIdcardImg = reverseIdcardImg;
	}

	public String getProfessionalImg() {
		return professionalImg;
	}

	public void setProfessionalImg(String professionalImg) {
		this.professionalImg = professionalImg;
	}

	public String getPracticeImg() {
		return practiceImg;
	}

	public void setPracticeImg(String practiceImg) {
		this.practiceImg = practiceImg;
	}

	public String getElseImg() {
		return elseImg;
	}

	public void setElseImg(String elseImg) {
		this.elseImg = elseImg;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public int getIsrecommend() {
		return isrecommend;
	}

	public void setIsrecommend(int isrecommend) {
		this.isrecommend = isrecommend;
	}

	public String getIsvirtual() {
		return isvirtual;
	}

	public void setIsvirtual(String isvirtual) {
		this.isvirtual = isvirtual;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
	
	
}
