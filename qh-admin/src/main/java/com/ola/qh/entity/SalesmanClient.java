package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

///////销售人员的客户
public class SalesmanClient {

	private String id;
	
	@NotEmpty
	private String mobile;
	
	private String salesmanId;
	
	private String salesmanIdNew;
	
	private Date addtime;
	
	private Date updatetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(String salesmanId) {
		this.salesmanId = salesmanId;
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

	public String getSalesmanIdNew() {
		return salesmanIdNew;
	}

	public void setSalesmanIdNew(String salesmanIdNew) {
		this.salesmanIdNew = salesmanIdNew;
	}
	
	
}
