package com.ola.qh.entity;

import java.util.Date;

public class QuestionCategory {

	private String id;
	
	private String types;
	
	private String courseTypeName;
	
	private String courseTypeSubclassName;
	
	private String name;
	
	private String isshow;
	
	private Date addtime;
	
	private Date udpatetime;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getUdpatetime() {
		return udpatetime;
	}

	public void setUdpatetime(Date udpatetime) {
		this.udpatetime = udpatetime;
	}
	
	
}
