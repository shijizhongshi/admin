package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class KnowledgeVideo {

	private String id;

	private String firstImage;// 第一帧图片
	@NotEmpty
	private String title;
	@NotEmpty
	private String videoId;

	private String times;

	private String courseTypeSubclassName;

	private String miniSubclassName;// 三级类别名称

	private int orders;

	private int status;////// 1同步到短视频0不同步

	private Date addtime;

	private Date updatetime;

	//@Valid
	//@Size(min = 1)
	private List<String> courseTypeSubclassNames = new ArrayList<String>();

	private List<String> CourseTypeNames = new ArrayList<String>();

	public String getMiniSubclassName() {
		return miniSubclassName;
	}

	public void setMiniSubclassName(String miniSubclassName) {
		this.miniSubclassName = miniSubclassName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getCourseTypeSubclassName() {
		return courseTypeSubclassName;
	}

	public void setCourseTypeSubclassName(String courseTypeSubclassName) {
		this.courseTypeSubclassName = courseTypeSubclassName;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
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

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<String> getCourseTypeSubclassNames() {
		return courseTypeSubclassNames;
	}

	public void setCourseTypeSubclassNames(List<String> courseTypeSubclassNames) {
		this.courseTypeSubclassNames = courseTypeSubclassNames;
	}

	public List<String> getCourseTypeNames() {
		return CourseTypeNames;
	}

	public void setCourseTypeNames(List<String> courseTypeNames) {
		CourseTypeNames = courseTypeNames;
	}

}
