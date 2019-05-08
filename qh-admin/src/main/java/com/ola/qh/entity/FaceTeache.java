package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class FaceTeache {

	private String id;
	
	@NotEmpty
	private String firstImg;//////主讲老师图片
	
	@NotEmpty
	private String teacherName;////主讲人
	
	@NotEmpty
	private String courseName;//////课程名称
	
	@NotEmpty
	private String courseTypeSubclassName;//////专业
	
	@NotEmpty
	private String detailAddress;///////详细地址
	
	@NotEmpty
	private String startTime;///////具体时间（排序用）
	
	@NotEmpty
	private String probablyAddress;/////省市
	
	@NotEmpty
	private String times;//////面授时间
	
	private Date addtime;
	
	private Date updatetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstImg() {
		return firstImg;
	}

	public void setFirstImg(String firstImg) {
		this.firstImg = firstImg;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseTypeSubclassName() {
		return courseTypeSubclassName;
	}

	public void setCourseTypeSubclassName(String courseTypeSubclassName) {
		this.courseTypeSubclassName = courseTypeSubclassName;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getProbablyAddress() {
		return probablyAddress;
	}

	public void setProbablyAddress(String probablyAddress) {
		this.probablyAddress = probablyAddress;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	
}
