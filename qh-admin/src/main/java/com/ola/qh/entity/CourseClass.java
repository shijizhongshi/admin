package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class CourseClass {

	private String id;
	
	@NotEmpty(message="专业不能为空")
	private String courseTypeName;//专业
	
	@NotEmpty(message="子专业不能为空")
	private String courseTypeSubclassName;//子专业
	
	private String templateId;//模板的id
	
	@NotEmpty(message="班级名称不能为空")
	private String className;
	@NotNull
	private BigDecimal classPrice;//课程价格
	@NotNull
	private BigDecimal classDiscountPrice;////课程优惠价格
	
	@NotEmpty(message="年份不能为空")
	private String classYear;//年份
	
	@NotEmpty(message="课程主讲人不能为空")
	private String courseLecturer;//课程主讲人
	
	@NotNull
	private int allTime;//所有的课时
	
	private String grade;//显示级别
	
	@NotNull
	private int isremmend;//1推荐 0:不推荐
	
	@NotNull
	private int isshow;//1:显示  0:关闭
	
	private String imgUrl;//班级图片
	
	private String properPeople;//适宜人群
	
	private String promises;//班级承诺
	
	private String features;//特色
	
	private String introduce;//介绍
	
	private String detail;//班级详情
	
	private Date addtime;//
	
	private Date updatetime;//
	
	private String maxdoudou;/////最多用的豆豆数
	
	private List<CourseTeacher> listTeacher=new ArrayList<CourseTeacher>();//
	
	private List<Course> listCourse=new ArrayList<Course>();//
	
	private String isbuy;///1:已经买过  0:没有买过

	private int orders;///序号
	
	public String getIsbuy() {
		return isbuy;
	}

	public void setIsbuy(String isbuy) {
		this.isbuy = isbuy;
	}

	public String getMaxdoudou() {
		return maxdoudou;
	}

	public void setMaxdoudou(String maxdoudou) {
		this.maxdoudou = maxdoudou;
	}

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

	public List<CourseTeacher> getListTeacher() {
		return listTeacher;
	}

	public void setListTeacher(List<CourseTeacher> listTeacher) {
		this.listTeacher = listTeacher;
	}

	public List<Course> getListCourse() {
		return listCourse;
	}

	public void setListCourse(List<Course> listCourse) {
		this.listCourse = listCourse;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	
	

	
}
