package com.ola.qh.entity;

import java.util.Date;

/**
 * 
* @ClassName: CourseChapter  
* @Description: 课程章的实体类
* @author guoyuxue  
* @date 2018年11月19日  
*
 */
public class CourseChapter {
	
	private String id;
	
	private String courseId;///课程的id
	
	private String courseChapterName;//课程章的名称
	
	private String courseTypeName;////课程类别的名称
	
	private String courseTypeSubclassName;///课程子类别的名称
	
	private String courseLecturer;////课程的主讲人
	
	private String courseChapterCategory;////课程的章类别
	
	private int ispay;////1:付费的章节  0:免费的章节
	
	private Date addtime;
	
	private Date updatetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseChapterName() {
		return courseChapterName;
	}

	public void setCourseChapterName(String courseChapterName) {
		this.courseChapterName = courseChapterName;
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

	public String getCourseLecturer() {
		return courseLecturer;
	}

	public void setCourseLecturer(String courseLecturer) {
		this.courseLecturer = courseLecturer;
	}

	public String getCourseChapterCategory() {
		return courseChapterCategory;
	}

	public void setCourseChapterCategory(String courseChapterCategory) {
		this.courseChapterCategory = courseChapterCategory;
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
