package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class CourseNofree {

	private String id;
	
	@NotEmpty(message="专业不能为空")
	private String courseTypeName;//专业
	
	@NotEmpty(message="子专业不能为空")
	private String courseTypeSubclassName;//子专业
	
	@NotEmpty(message="资源类别不能为空")
	private String courseResourceType;//资源类别
	
	@NotEmpty(message="资源用途不能为空")
	private String courseUseDifference;//资源用途
	
	@NotEmpty(message="课程名称不能为空")
	private String courseName;//课程名称
	
	@NotEmpty(message="老师不能为空")
	private String teachers;//老师
	
	private int palyTime;//播放时间
	
	private String describes;//描述
	
	private String aliyunId;//阿里云id
	
	@NotEmpty(message="视频的id不能为空")
	private String videoId;
	@NotEmpty(message="视频地址不能为空")
	private String videoUrl;
	
	private int isremmend;//1:推荐
	
	@NotEmpty(message="图片不能为空")
	private String imgUrl;
	
	private Date addtime;
	
	private Date updatetime;

	
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

	public String getCourseResourceType() {
		return courseResourceType;
	}

	public void setCourseResourceType(String courseResourceType) {
		this.courseResourceType = courseResourceType;
	}

	public String getCourseUseDifference() {
		return courseUseDifference;
	}

	public void setCourseUseDifference(String courseUseDifference) {
		this.courseUseDifference = courseUseDifference;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	public int getPalyTime() {
		return palyTime;
	}

	public void setPalyTime(int palyTime) {
		this.palyTime = palyTime;
	}

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}

	public String getAliyunId() {
		return aliyunId;
	}

	public void setAliyunId(String aliyunId) {
		this.aliyunId = aliyunId;
	}

	public int getIsremmend() {
		return isremmend;
	}

	public void setIsremmend(int isremmend) {
		this.isremmend = isremmend;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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
