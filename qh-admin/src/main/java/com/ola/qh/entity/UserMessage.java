package com.ola.qh.entity;

import java.util.Date;

public class UserMessage{

	private String id;
	
	private String userId;
	
	private int types;/////1:订单类型的消息  2:服务类型的消息 3:课程订单
	
	private int subType;///1;购物订单  2;销售订单
	
	private String title;
	
	private String describe;
	
	private int headStatus;
	
	private String ordersId;
	
	private String patientId;
	
	private Date addtime;
	

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public int getHeadStatus() {
		return headStatus;
	}

	public void setHeadStatus(int headStatus) {
		this.headStatus = headStatus;
	}

	public String getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	
	
}
