package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BusinessBookHistory {

	private String id;
	
	private int courseWays;/////1:销售人员开课 2:加盟商开课
	
	private String businessId;////加盟商的id
	
	private String salesPeople;/////销售人
	
	private String salesMobile;////销售电话
	
	private int types;/////1:进钱(加盟商)    2:出钱(加盟商或者销售人员)
	
	private BigDecimal payaccount;/////加盟商入驻实际支付金额
	
	private BigDecimal account;////开课课程的金额或者是加盟商入驻课程金额
	
	private String buyCourseId;///这个是开课记录的id
	
	private Date addtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCourseWays() {
		return courseWays;
	}

	public void setCourseWays(int courseWays) {
		this.courseWays = courseWays;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getSalesPeople() {
		return salesPeople;
	}

	public void setSalesPeople(String salesPeople) {
		this.salesPeople = salesPeople;
	}

	public String getSalesMobile() {
		return salesMobile;
	}

	public void setSalesMobile(String salesMobile) {
		this.salesMobile = salesMobile;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}

	public BigDecimal getPayaccount() {
		return payaccount;
	}

	public void setPayaccount(BigDecimal payaccount) {
		this.payaccount = payaccount;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public String getBuyCourseId() {
		return buyCourseId;
	}

	public void setBuyCourseId(String buyCourseId) {
		this.buyCourseId = buyCourseId;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
}
