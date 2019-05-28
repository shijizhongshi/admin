package com.ola.qh.entity;

import java.util.Date;

public class QuestionBankAsk {
	
	private String id;
	
	private String questionAsk;//问题
	
	private String questionAnswer;//答案
	
	private String courseTypeSubclassName;//子专业名称
	
	private Date addtime;//添加时间
	
	private Date updatetime;//更新时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestionAsk() {
		return questionAsk;
	}

	public void setQuestionAsk(String questionAsk) {
		this.questionAsk = questionAsk;
	}

	public String getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
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
