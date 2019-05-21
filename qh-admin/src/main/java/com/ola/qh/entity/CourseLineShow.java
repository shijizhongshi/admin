package com.ola.qh.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class CourseLineShow {

	private String id;
	@NotEmpty
	private String courseTypeName;
	@NotEmpty
	private String courseTypeSubclassName;
	@NotEmpty
	private String liveName;//直播name

	private String outLinks;
	@NotEmpty
	private String imgUrl;// 课程图片

	private String headImgUrl;// 主讲老师头像

	private String isremmend;//// 1是推荐

	private String isshow;/// 1:可见

	private Date addtime;//精确的开始时间

	private Date updatetime;

	@NotEmpty
	private String liveRoomId;//房间ID

	private String liveId;//直播ID

	private String liveBackId;//回放ID

	private String teacherId;// 老师id  

	private String lecturer;// 主讲老师

	@NotNull
	private Date starttime;// 手动输入的直播开始时间
	@NotNull
	private Date stoptime;// 手动输入的直播结束时间

	private Integer isopen;// 是否是公开课 ，0 不公开，1 公开

	public Date getStoptime() {
		return stoptime;
	}

	public void setStoptime(Date stoptime) {
		this.stoptime = stoptime;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Integer getIsopen() {
		return isopen;
	}

	public void setIsopen(Integer isopen) {
		this.isopen = isopen;
	}

	public String getLecturer() {
		return lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

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
