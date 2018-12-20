package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class CourseClassTemplate {

	private String id;
	
	@NotEmpty(message="模板名称不能为空")
	private String templateName;//;模板的名称
	
	@NotEmpty(message="班级名称不能为空")
	private String className;//班级名称
	
	@NotNull
	private BigDecimal classPrice;//课程价格
	
	private BigDecimal classDiscountPrice;//课程优惠价格
	
	@NotEmpty(message="课程图片不能为空")
	private String classUrl;
	
	@NotEmpty(message="适合人群不能为空")
	private String properPeople;//适合人群
	
	private String promises;//班级承诺
	
	private String features;//班级特色
	
	private String introduce;//班级介绍
	
	private String detail;//班级详情
	
	private Date addtime;
	
	private Date updatetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
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

	public String getClassUrl() {
		return classUrl;
	}

	public void setClassUrl(String classUrl) {
		this.classUrl = classUrl;
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
