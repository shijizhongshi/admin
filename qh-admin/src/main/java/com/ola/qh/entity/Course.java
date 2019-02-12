package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
/**
 * 
* @ClassName: Course  
* @Description: 课程的实体类
* @author guoyuxue  
* @date 2018年11月19日  
*
 */
public class Course {
	
	private String id;
	
	private String userId;////所属人课程的拥有者

	@NotEmpty(message="课程名称不能为空")
	private String courseName;//课程名称
	
	@NotEmpty(message="课程类别不能为空")
	private String courseTypeName;///课程类别的名称
	@NotEmpty(message="课程子类别不能为空")
	private String courseTypeSubclassName;////课程子类别的名称
	
	private int courseChapterSize;////课程的章节数
	
	private BigDecimal coursePrice;////课程的价格
	
	private BigDecimal courseDiscountPrice;////课程折扣价格
	
	private String maxdoudou;//////最大的折扣豆豆数
	
	private String courseYear;////课程年限
	
	private int courseShow;////课程是否展示
	
	private int courseExcellent;///是否是优秀课程
	@NotEmpty(message="资源类别不能为空")
	private String courseResourceType;/////课程资源类别
	@NotEmpty(message="用途类别不能为空")
	private String courseUseDifference;/////课程用途类别
	
	private String courseImg;////课程的图片
	
	private Date addtime;
	
	private Date updatetime;
	
	private int pageNo;////分页用的
	
	private int pageSize;
	
	private String classId;

	
	public String getMaxdoudou() {
		return maxdoudou;
	}

	public void setMaxdoudou(String maxdoudou) {
		this.maxdoudou = maxdoudou;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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


	public int getCourseChapterSize() {
		return courseChapterSize;
	}

	public void setCourseChapterSize(int courseChapterSize) {
		this.courseChapterSize = courseChapterSize;
	}

	public BigDecimal getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(BigDecimal coursePrice) {
		this.coursePrice = coursePrice;
	}

	public BigDecimal getCourseDiscountPrice() {
		return courseDiscountPrice;
	}

	public void setCourseDiscountPrice(BigDecimal courseDiscountPrice) {
		this.courseDiscountPrice = courseDiscountPrice;
	}

	public String getCourseYear() {
		return courseYear;
	}

	public void setCourseYear(String courseYear) {
		this.courseYear = courseYear;
	}

	
	public int getCourseShow() {
		return courseShow;
	}

	public void setCourseShow(int courseShow) {
		this.courseShow = courseShow;
	}

	public int getCourseExcellent() {
		return courseExcellent;
	}

	public void setCourseExcellent(int courseExcellent) {
		this.courseExcellent = courseExcellent;
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

	public String getCourseImg() {
		return courseImg;
	}

	public void setCourseImg(String courseImg) {
		this.courseImg = courseImg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	
	
}
