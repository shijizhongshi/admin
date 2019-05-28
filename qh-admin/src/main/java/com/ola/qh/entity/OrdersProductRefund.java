package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrdersProductRefund {

	private String id;
	private String userId;
	private String ordersProductId;////退款订单的产品的ID
	private String refundReason;////退款理由
	
	private String refundExplain;/////退款说明
	private BigDecimal refundMoney;/////退款金额
	
	private Date addtime;////退款时间

	private String statusCode;
	
	private Date updatetime;
	
	private String showtime;
	
	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
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

	public String getOrdersProductId() {
		return ordersProductId;
	}

	public void setOrdersProductId(String ordersProductId) {
		this.ordersProductId = ordersProductId;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getRefundExplain() {
		return refundExplain;
	}

	public void setRefundExplain(String refundExplain) {
		this.refundExplain = refundExplain;
	}

	public BigDecimal getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	
}
