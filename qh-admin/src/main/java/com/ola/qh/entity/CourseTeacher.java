package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class CourseTeacher {

	private String id;
	
	@NotEmpty(message="讲师姓名不能为空")
	private String name;
	
	@NotEmpty(message="不能为空")
	private String isshow;
	
	@NotEmpty(message="是否推荐不能为空")
	private String isremmend;//0不推荐1推荐
	
	@NotEmpty(message="图片不能为空")
	private String imgUrl;
	
	private String prizes;
	
	@NotEmpty(message="特色不能为空")
	private String features;
	
	@NotEmpty(message="老师描述不能为空")
	private String describes;
	
	@NotEmpty(message="详细介绍不能为空")
	private String details;
	
	@NotEmpty(message="大类别不能为空")
	private String courseTypeNames;
	
	@NotEmpty(message="小类别不能为空")
	private String courseTypeSubclassNames;
	
	private String classId;
	
	private Date addtime;
	
	private Date updatetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getIsremmend() {
		return isremmend;
	}

	public void setIsremmend(String isremmend) {
		this.isremmend = isremmend;
	}

	public String getPrizes() {
		return prizes;
	}

	public void setPrizes(String prizes) {
		this.prizes = prizes;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getCourseTypeNames() {
		return courseTypeNames;
	}

	public void setCourseTypeNames(String courseTypeNames) {
		this.courseTypeNames = courseTypeNames;
	}

	public String getCourseTypeSubclassNames() {
		return courseTypeSubclassNames;
	}

	public void setCourseTypeSubclassNames(String courseTypeSubclassNames) {
		this.courseTypeSubclassNames = courseTypeSubclassNames;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	
}
