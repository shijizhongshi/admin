package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class ExamCalendar {

	private String id;
	
	@NotEmpty
	private String examName;
	
	@NotEmpty
	private String years;
	
	private String applyTime;
	
	private String cardTime;
	
	private String gradeTime;
	
	private String certificateTime;
	
	private String courseTypeSubclassName;
	
	private Date addtime;
	
	private Date updatetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getCardTime() {
		return cardTime;
	}

	public void setCardTime(String cardTime) {
		this.cardTime = cardTime;
	}

	public String getGradeTime() {
		return gradeTime;
	}

	public void setGradeTime(String gradeTime) {
		this.gradeTime = gradeTime;
	}

	public String getCertificateTime() {
		return certificateTime;
	}

	public void setCertificateTime(String certificateTime) {
		this.certificateTime = certificateTime;
	}

	public String getCourseTypeSubclassName() {
		return courseTypeSubclassName;
	}

	public void setCourseTypeSubclassName(String courseTypeSubclassName) {
		this.courseTypeSubclassName = courseTypeSubclassName;
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
