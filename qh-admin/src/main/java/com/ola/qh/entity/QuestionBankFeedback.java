package com.ola.qh.entity;

import java.util.Date;

public class QuestionBankFeedback {

	private String id;
	
	private String userId;
	
	private String bankId;
	
	private String types;
	
	private String content;
	
	private QuestionBank bank;
	
	private int status;
	
	private Date addtime;
	
	private Date updatetime;
	
	private String showtime;
	
	private String name;
	
	private String nickname;
	
	private String courseTypeSubclassName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public QuestionBank getBank() {
		return bank;
	}

	public void setBank(QuestionBank bank) {
		this.bank = bank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCourseTypeSubclassName() {
		return courseTypeSubclassName;
	}

	public void setCourseTypeSubclassName(String courseTypeSubclassName) {
		this.courseTypeSubclassName = courseTypeSubclassName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
