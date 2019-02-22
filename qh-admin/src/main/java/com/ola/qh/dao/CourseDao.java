package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;
public interface CourseDao {

	public List<CourseType> courseTypeList();
	
	public int insertCourseType(@Param("courseTypeName")String courseTypeName,@Param("id")String id);
	
	public int updateCourseType(@Param("courseTypeName")String courseTypeName,@Param("id")String id);
	
	
	
	public List<CourseTypeSubclass> courseTypeSubclassList(@Param("courseTypeId") String courseTypeId);
	
	public int insertCourseTypeSubclass(@Param("courseTypeSubclassName")String courseTypeName,
			@Param("id")String id,@Param("courseTypeId") String courseTypeId);
	
	public int updateCourseTypeSubclass(@Param("courseTypeSubclassName")String courseTypeName,
			@Param("id")String id,@Param("courseTypeId") String courseTypeId);
	
	public int deleteCourse(@Param("id")String id);
	
	public List<Course> courseList(Course course);
	
	public int insertCourse(Course course);
	
	public int courseCount(@Param("courseTypeName")String courseTypeName,@Param("courseTypeSubclassName")String courseTypeSubclassName ,@Param("courseName")String courseName);
	
	public int updateCourese(Course course);
	
	public Course existCourse(@Param("id")String id);
	
	public List<Course> existCourseList(@Param("classId")String classId);
	
	public int updateClass(@Param("classId")String classId);
	
}
