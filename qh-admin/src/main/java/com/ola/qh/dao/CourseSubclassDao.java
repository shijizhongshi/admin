package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseSection;

public interface CourseSubclassDao {

	/////课程章的操作
	public List<CourseChapter> courseChapterList(
			@Param("courseId") String courseId,
			@Param("courseChapterName") String courseChapterName,
			@Param("pageNo") int pageNo,
			@Param("pageSize") int pageSize,
			@Param("courseTypeName")String courseTypeName,
			@Param("courseTypeSubclassName")String courseTypeSubclassName);
	
/////课程章的操作
	public int courseChapterListCount(
			@Param("courseId") String courseId,
			@Param("courseChapterName") String courseChapterName,
			@Param("courseTypeName")String courseTypeName,
			@Param("courseTypeSubclassName")String courseTypeSubclassName);
	
	public int insertCourseChapter(CourseChapter ccp);
	
	public int updateCourseChapter(CourseChapter ccp);
	
	public int deleteChapter(@Param("id")String id);
	
	//////课程节的操作
	public List<CourseSection> courseSectionList(
			@Param("courseChapterId") String courseChapterId,
			@Param("pageNo") int pageNo,
			@Param("pageSize") int pageSize);
	public int courseSectionListCount(@Param("courseChapterId")String courseChapterId);
	
	public int deleteSerction(@Param("id")String id);
	
	public int insertCourseSection(CourseSection sc);
	
	
	public int existSection(@Param("sectionName")String sectionName,@Param("courseChapterId")String courseChapterId);
	
	public int updateCourseSection(CourseSection sc);
	
	
	public int selectOrder(@Param("type")String type,@Param("orders")int orders);
	
	public int updateOrders(@Param("id")String id,@Param("originalOrder")int originalOrder,
			@Param("orders")int orders);
}
