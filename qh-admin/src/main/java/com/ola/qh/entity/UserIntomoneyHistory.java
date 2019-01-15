package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserIntomoneyHistory {

private String id;
	
	private String userId;
	
	private BigDecimal money;
	
	private int intoStatus;//商城卖店铺里的商品所得
	
	private Date addtime;
	
	private Date updatetime;
	
	private String orderId;//订单的id
	
	private int status;//0:正常状态 1:用户删除之后的状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
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

	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getIntoStatus() {
		return intoStatus;
	}

	public void setIntoStatus(int intoStatus) {
		this.intoStatus = intoStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
