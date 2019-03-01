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
	
	private String businessId;////加盟商的id
	
	private String nickname;
	
	private String realname;
	
	private String mobile;
	
	private String salesPeople;
	
	private int courseways;
	
	private String operatingName;////操作账号
	
	private String status;///0:正常   1:已关闭
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperatingName() {
		return operatingName;
	}

	public void setOperatingName(String operatingName) {
		this.operatingName = operatingName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSalesPeople() {
		return salesPeople;
	}

	public void setSalesPeople(String salesPeople) {
		this.salesPeople = salesPeople;
	}

	public int getCourseways() {
		return courseways;
	}

	public void setCourseways(int courseways) {
		this.courseways = courseways;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
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
