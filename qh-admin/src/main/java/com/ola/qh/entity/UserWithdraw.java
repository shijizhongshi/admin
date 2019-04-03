package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class UserWithdraw {

private String id;
	
	@NotEmpty(message="用户ID不能为空")
	private String userId;
	
	@NotNull
	private int types;///1支付宝   2:微信
	
	private String aliaccount;//支付宝账号
	
	private String realname;
	
	private String openId;////微信提现的重要参数
	
	private String weixinnickname;
	
	private String weixinheadimg;
	
	private BigDecimal money;
	
	private int payStatus;//审核是否通过
	
	private String payMessage;//审核失败的原因
	
	private Date addtime;
	
	private Date updatetime;
	
	private int status;
	
	private String mobile;
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWeixinnickname() {
		return weixinnickname;
	}

	public void setWeixinnickname(String weixinnickname) {
		this.weixinnickname = weixinnickname;
	}

	public String getWeixinheadimg() {
		return weixinheadimg;
	}

	public void setWeixinheadimg(String weixinheadimg) {
		this.weixinheadimg = weixinheadimg;
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
	
	
	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}

	public String getAliaccount() {
		return aliaccount;
	}

	public void setAliaccount(String aliaccount) {
		this.aliaccount = aliaccount;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
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