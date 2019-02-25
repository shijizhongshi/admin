package com.ola.qh.entity;

import java.util.Date;

public class DoctorReplyPatient {

	private String id;
	
	private String patientId;
	
	private String userId;
	
	private String replyContent;////回复内容
	
	private int likes;
	
	private String replyName;/////回复用户的姓名
	
	private String replyHeadImg;////回复用户的头像
	
	private Date addtime;
	
	private Date updatetime;
	
	private String showtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getReplyName() {
		return replyName;
	}

	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}

	public String getReplyHeadImg() {
		return replyHeadImg;
	}

	public void setReplyHeadImg(String replyHeadImg) {
		this.replyHeadImg = replyHeadImg;
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
	
	
}
