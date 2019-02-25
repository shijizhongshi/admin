package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
* @ClassName: CourseSection  
* @Description: 课程节的实体类 
* @author guoyuxue  
* @date 2018年11月19日  
*
 */
public class CourseSection {

	private String id;
	
	@NotEmpty(message="章的id不能为空")
	private String courseChapterId;//////一章多个节
	@NotEmpty(message="节的名称不能为空")
	private String sectionName;
	
	private String isshow;
	
	private String aliyunId;
	@NotEmpty(message="视频id不能为空")
	private String videoId;////保利威返回的视频的id
	
	private String videoUrl;////保利威返回的视频路径
	
	private Date addtime;
	
	private Date updatetime;
	
	private int sectionOrders;///第1节 第2节

	public int getSectionOrders() {
		return sectionOrders;
	}

	public void setSectionOrders(int sectionOrders) {
		this.sectionOrders = sectionOrders;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	
	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	public String getAliyunId() {
		return aliyunId;
	}

	public void setAliyunId(String aliyunId) {
		this.aliyunId = aliyunId;
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

	public String getCourseChapterId() {
		return courseChapterId;
	}

	public void setCourseChapterId(String courseChapterId) {
		this.courseChapterId = courseChapterId;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	
	
}
