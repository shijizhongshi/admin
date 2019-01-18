package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrdersPayment {

	private String id;
	
	private String userId;
	
	private String muserId;
	
	private String ordersId;
	
	private int ordersType;/////0:药品的订单   1;课程的订单
	
	private BigDecimal money;
	
	private int done;
	
	private String paytypeCode;
	
	private String paytypeName;
	
	private String extransno;//////商户的订单编号
	
	private Date addtime;
	
	private String subjectTitle;////购买时的描述
	
	private String bodyDetail;////购买时的详细介绍

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

	public String getMuserId() {
		return muserId;
	}

	public void setMuserId(String muserId) {
		this.muserId = muserId;
	}

	public String getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
	}

	public String getPaytypeCode() {
		return paytypeCode;
	}

	public void setPaytypeCode(String paytypeCode) {
		this.paytypeCode = paytypeCode;
	}

	
	public String getPaytypeName() {
		return paytypeName;
	}

	public void setPaytypeName(String paytypeName) {
		this.paytypeName = paytypeName;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getExtransno() {
		return extransno;
	}

	public void setExtransno(String extransno) {
		this.extransno = extransno;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public String getBodyDetail() {
		return bodyDetail;
	}

	public void setBodyDetail(String bodyDetail) {
		this.bodyDetail = bodyDetail;
	}

	public int getOrdersType() {
		return ordersType;
	}

	public void setOrdersType(int ordersType) {
		this.ordersType = ordersType;
	}
	
}
