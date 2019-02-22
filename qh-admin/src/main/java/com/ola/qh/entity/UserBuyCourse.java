package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * 
* @ClassName: UserBuyCourse  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author guoyuxue  
* @date 2018年11月29日  
*
 */
public class UserBuyCourse {

	private String userId;///用户的id
	
	private String id;
	
	private String courseId;
	
	private String courseName;////
	
	private BigDecimal courseDiscountPrice;////课程的折扣价
	
	private BigDecimal coursePrice;////课程的原价
	
	private String courseImgUrl;////课程的图片
	
	private String classId;
	
	private String ordersId;
	
	private Date addtime;///
	
	private String payType;///线上支付或者是线下支付的
	
	private String realname;/////购买的学员
	
	private String remarks;/////购买课程的简称
	
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public BigDecimal getCourseDiscountPrice() {
		return courseDiscountPrice;
	}

	public void setCourseDiscountPrice(BigDecimal courseDiscountPrice) {
		this.courseDiscountPrice = courseDiscountPrice;
	}

	public BigDecimal getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(BigDecimal coursePrice) {
		this.coursePrice = coursePrice;
	}

	public String getCourseImgUrl() {
		return courseImgUrl;
	}

	public void setCourseImgUrl(String courseImgUrl) {
		this.courseImgUrl = courseImgUrl;
	}

	public String getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	
	
}
