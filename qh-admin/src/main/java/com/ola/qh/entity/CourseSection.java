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
	
	private int isshow;
	@NotEmpty(message="视频连接不能为空")
	private String aliyunId;
	
	private Date addtime;
	
	private Date updatetime;

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

	public int getIsshow() {
		return isshow;
	}

	public void setIsshow(int isshow) {
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

	
	
}
