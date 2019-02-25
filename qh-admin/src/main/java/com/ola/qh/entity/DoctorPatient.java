package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DoctorPatient {

	private String id;
	
	private String userId;
	
	private String category;////类别   经验分享   问题解答
	
	private String title;////标题
	
	private int likes;////点赞
	
	private String describes;////问题描述
	
	private String publisher;/////发布人信息
	
	private String publisherHeadImg;/////发布人的头像
	
	private String publisherPosition;/////发布人的职位
	
	private Date addtime;
	
	private Date updatetime;
	
	private String showtime;
	
	private List<DoctorPatientImg> listimg=new ArrayList<DoctorPatientImg>();
	
	private List<DoctorReplyPatient> listreply=new ArrayList<DoctorReplyPatient>();

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublisherHeadImg() {
		return publisherHeadImg;
	}

	public void setPublisherHeadImg(String publisherHeadImg) {
		this.publisherHeadImg = publisherHeadImg;
	}

	public String getPublisherPosition() {
		return publisherPosition;
	}

	public void setPublisherPosition(String publisherPosition) {
		this.publisherPosition = publisherPosition;
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

	public List<DoctorPatientImg> getListimg() {
		return listimg;
	}

	public void setListimg(List<DoctorPatientImg> listimg) {
		this.listimg = listimg;
	}

	public List<DoctorReplyPatient> getListreply() {
		return listreply;
	}

	public void setListreply(List<DoctorReplyPatient> listreply) {
		this.listreply = listreply;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
	
	
}
