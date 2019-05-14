package com.ola.qh.entity;

/**
 * 
 * @ClassName: CourseTypeSubclass
 * @Description: 所有课程的子类别
 * @author guoyuxue
 * @date 2018年11月19日
 *
 */
public class CourseTypeSubclass {

	private String id;

	private String courseTypeSubclassName;//// 子类别的名称

	private String courseTypeId;///// 大类别的名称

	private String imgUrl;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseTypeSubclassName() {
		return courseTypeSubclassName;
	}

	public void setCourseTypeSubclassName(String courseTypeSubclassName) {
		this.courseTypeSubclassName = courseTypeSubclassName;
	}

	public String getCourseTypeId() {
		return courseTypeId;
	}

	public void setCourseTypeId(String courseTypeId) {
		this.courseTypeId = courseTypeId;
	}

}
