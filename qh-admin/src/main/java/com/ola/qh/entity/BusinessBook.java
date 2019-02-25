package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BusinessBook {

	private String id;
	
	private String businessId;
	
	private BigDecimal account;////总的开课金额
	
	private BigDecimal usedaccount;///已经用的开课金额
	
	private BigDecimal surplusaccount;/////剩余的开课金额
	
	private BigDecimal payaccount;///实际支付钱的总额
	
	private String salesName;/////销售人员
	
	private Date updatetime;
	
	private Date addtime;
	
	

	public BigDecimal getPayaccount() {
		return payaccount;
	}

	public void setPayaccount(BigDecimal payaccount) {
		this.payaccount = payaccount;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getUsedaccount() {
		return usedaccount;
	}

	public void setUsedaccount(BigDecimal usedaccount) {
		this.usedaccount = usedaccount;
	}

	public BigDecimal getSurplusaccount() {
		return surplusaccount;
	}

	public void setSurplusaccount(BigDecimal surplusaccount) {
		this.surplusaccount = surplusaccount;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	
	
	
}
