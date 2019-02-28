package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class OpenCourse {

	@NotNull
	private int types;////1:报班   2:报课程 3:报直播
	
	@Valid
	@Size(min=1)
	private List<String> productId=new ArrayList<String>();/////班级或者是课程id
	
	private String businessId;/////是加盟商开课的
	
	private String adminName;/////是哪个账号开课的呢
	
	private String salesName;/////销售人员姓名
	
	private String salesMobile;////销售人员的电话
	
	private BigDecimal account;/////兑换课程的总金额
	
	private BigDecimal payaccount;////实际支付金额
	
	@NotEmpty
	private String userId;
	@NotNull
	private int courseWays;////1:销售人员开课 2:加盟商开课
	
	

	public int getCourseWays() {
		return courseWays;
	}

	public void setCourseWays(int courseWays) {
		this.courseWays = courseWays;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getPayaccount() {
		return payaccount;
	}

	public void setPayaccount(BigDecimal payaccount) {
		this.payaccount = payaccount;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}

	public List<String> getProductId() {
		return productId;
	}

	public void setProductId(List<String> productId) {
		this.productId = productId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public String getSalesMobile() {
		return salesMobile;
	}

	public void setSalesMobile(String salesMobile) {
		this.salesMobile = salesMobile;
	}
}
