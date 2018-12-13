package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class UserWithdrawHistory {

	@NotEmpty(message="ID不能为空")
	private String id;
	
	@NotEmpty(message="用户ID不能为空")
	private String userId;
	
	@NotEmpty(message="提现的方式不能为空")
	private String withdrawTypes;/////支付宝或者是微信
	
	@NotEmpty(message="真实姓名不能为空")
	private String realname;
	
	@NotNull
	private BigDecimal money;
	
	@NotNull
	private int payStatus;//审核是否通过
	
	private String payMessage;//审核失败的原因
	
	private Date addtime;
	
	private Date updatetime;
	
	private int status;
	
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

	public String getWithdrawTypes() {
		return withdrawTypes;
	}

	public void setWithdrawTypes(String withdrawTypes) {
		this.withdrawTypes = withdrawTypes;
	}

	
	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayMessage() {
		return payMessage;
	}

	public void setPayMessage(String payMessage) {
		this.payMessage = payMessage;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	


	
	
	
}