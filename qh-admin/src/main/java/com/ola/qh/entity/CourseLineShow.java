package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class CourseLineShow {

	private String id;
	@NotEmpty
	private String courseTypeName;
	@NotEmpty
	private String courseTypeSubclassName;
	@NotEmpty
	private String liveName;

	private String outLinks;
	@NotEmpty
	private String imgUrl;

	private String isremmend;//// 1是推荐

	private String isshow;/// 1:可见

	private Date addtime;

	private Date updatetime;

	@NotEmpty
	private String liveRoomId;

	private String liveId;

	private String liveBackId;

	public String getLiveRoomId() {
		return liveRoomId;
	}

	public void setLiveRoomId(String liveRoomId) {
		this.liveRoomId = liveRoomId;
	}

	public String getLiveId() {
		return liveId;
	}

	public void setLiveId(String liveId) {
		this.liveId = liveId;
	}

	public String getLiveBackId() {
		return liveBackId;
	}

	public void setLiveBackId(String liveBackId) {
		this.liveBackId = liveBackId;
	}

	public String getOutLinks() {
		return outLinks;
	}

	public void setOutLinks(String outLinks) {
		this.outLinks = outLinks;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
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

	public String getLiveName() {
		return liveName;
	}

	public void setLiveName(String liveName) {
		this.liveName = liveName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getIsremmend() {
		return isremmend;
	}

	public void setIsremmend(String isremmend) {
		this.isremmend = isremmend;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

}
