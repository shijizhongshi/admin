package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CourseClass {

	private String id;
	
	private String courseTypeName;//专业
	
	private String courseTypeSubclassName;//子专业
	
	private String templateId;//模板的id
	
	private String className;//课程优惠价格
	
	private BigDecimal classPrice;//课程价格
	
	private BigDecimal classDiscountPrice;//
	
	private String classYear;//年份
	
	private String courseLecturer;//课程主讲人
	
	private int allTime;//所有的课时
	
	private String grade;//显示级别
	
	private int isremmend;//1推荐 0:不推荐
	
	private int isshow;//1:显示  0:关闭
	
	private String imgUrl;//班级图片
	
	private String properPeople;//适宜人群
	
	private String promises;//班级承诺
	
	private String features;//特色
	
	private String introduce;//介绍
	
	private String detail;//班级详情
	
	private Date addtime;//
	
	private Date updatetime;//

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public String getCourseTypeSubclassName() {
		return courseTypeSubclassName;
	}

	public void setCourseTypeSubclassName(String courseTypeSubclassName) {
		this.courseTypeSubclassName = courseTypeSubclassName;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public BigDecimal getClassPrice() {
		return classPrice;
	}

	public void setClassPrice(BigDecimal classPrice) {
		this.classPrice = classPrice;
	}

	public BigDecimal getClassDiscountPrice() {
		return classDiscountPrice;
	}

	public void setClassDiscountPrice(BigDecimal classDiscountPrice) {
		this.classDiscountPrice = classDiscountPrice;
	}

	public String getClassYear() {
		return classYear;
	}

	public void setClassYear(String classYear) {
		this.classYear = classYear;
	}

	public String getCourseLecturer() {
		return courseLecturer;
	}

	public void setCourseLecturer(String courseLecturer) {
		this.courseLecturer = courseLecturer;
	}

	public int getAllTime() {
		return allTime;
	}

	public void setAllTime(int allTime) {
		this.allTime = allTime;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getIsremmend() {
		return isremmend;
	}

	public void setIsremmend(int isremmend) {
		this.isremmend = isremmend;
	}

	public int getIsshow() {
		return isshow;
	}

	public void setIsshow(int isshow) {
		this.isshow = isshow;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getProperPeople() {
		return properPeople;
	}

	public void setProperPeople(String properPeople) {
		this.properPeople = properPeople;
	}

	public String getPromises() {
		return promises;
	}

	public void setPromises(String promises) {
		this.promises = promises;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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
	
	
}
