package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Business {

	private String id;
	
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@NotEmpty
	private String logo;
	@NotNull
	private String mobile;
	@NotEmpty
	private String address;
	@NotNull
	private BigDecimal payaccount;/////实际支付的金额
	@NotNull
	private BigDecimal account;////实际课程的金额 
	
	private BigDecimal surplusaccount;
	
	private String status;////0:正常的状态
	
	private Date addtime;
	@NotNull
	private String name;////加盟商的名称
	@NotNull
	private String principal;////加盟商的负责人
	
	private Date expireTime;/////过期时间(用定时任务定位过期时间)
	
	private Date updatetime;
	
	

	public BigDecimal getSurplusaccount() {
		return surplusaccount;
	}

	public void setSurplusaccount(BigDecimal surplusaccount) {
		this.surplusaccount = surplusaccount;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
}
