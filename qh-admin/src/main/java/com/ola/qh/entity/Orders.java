package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orders {

	private String id;
	
	private String userId;
	private String muserId;////
	
	private String orderno;
	
	private String ordersType;////0:药品订单  1:课程订单 2:服务的订单
	
	private BigDecimal payaccount;/////实际支付金额
	
	private String ordersStatus;/////订单的状态
	
	private String ordersOldStatus;/////老的订单状态
	
	private String address;
	
	private String receiver;
	
	private String mobile;
	
	private String expressNo;////快递单号
	
	private String paytype;///支付方式
	
	private String payname;////支付方式名称
	
	private Date addtime;
	
	private Date updatetime;
	
	private Date deliveredtime;
	
	private Date paidtime;
	private List<OrdersProduct> product = new ArrayList<OrdersProduct>();
	
	private int count;////总共多少件商品
	
	private String leaveMessage;/////商家的留言
	
	private String sex;/////性别
	
	private String paymentType;////团购还是预定
	
	private Date presetTime;////预定的时间
	
	private BigDecimal freight;////运费
	
	private String qrcodes;/////服务店铺的二维码
	
	private int classStatus;////1:购买的全套课程
	
	private String showtime;
	
	private String userMobile;////对应用户的手机号
	
	
	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public int getClassStatus() {
		return classStatus;
	}

	public void setClassStatus(int classStatus) {
		this.classStatus = classStatus;
	}

	public String getQrcodes() {
		return qrcodes;
	}

	public void setQrcodes(String qrcodes) {
		this.qrcodes = qrcodes;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
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

	public String getMuserId() {
		return muserId;
	}

	public void setMuserId(String muserId) {
		this.muserId = muserId;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	
	public String getOrdersType() {
		return ordersType;
	}

	public void setOrdersType(String ordersType) {
		this.ordersType = ordersType;
	}

	public BigDecimal getPayaccount() {
		return payaccount;
	}

	public void setPayaccount(BigDecimal payaccount) {
		this.payaccount = payaccount;
	}

	public String getOrdersStatus() {
		return ordersStatus;
	}

	public void setOrdersStatus(String ordersStatus) {
		this.ordersStatus = ordersStatus;
	}

	public String getOrdersOldStatus() {
		return ordersOldStatus;
	}

	public void setOrdersOldStatus(String ordersOldStatus) {
		this.ordersOldStatus = ordersOldStatus;
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

	public List<OrdersProduct> getProduct() {
		return product;
	}

	public void setProduct(List<OrdersProduct> product) {
		this.product = product;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getPayname() {
		return payname;
	}

	public void setPayname(String payname) {
		this.payname = payname;
	}

	public Date getDeliveredtime() {
		return deliveredtime;
	}

	public void setDeliveredtime(Date deliveredtime) {
		this.deliveredtime = deliveredtime;
	}

	public Date getPaidtime() {
		return paidtime;
	}

	public void setPaidtime(Date paidtime) {
		this.paidtime = paidtime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getLeaveMessage() {
		return leaveMessage;
	}

	public void setLeaveMessage(String leaveMessage) {
		this.leaveMessage = leaveMessage;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Date getPresetTime() {
		return presetTime;
	}

	public void setPresetTime(Date presetTime) {
		this.presetTime = presetTime;
	}
	
	
}
