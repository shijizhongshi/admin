package com.ola.qh.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;

public interface ICourseService {

    public List<CourseType> courseTypeList();
	
	public int insertCourseType(String courseTypeName,String courseTypeId);
	
	public int updateCourseType(String courseTypeName,String id,String courseTypeId);
	
	public List<CourseTypeSubclass> courseTypeSubclassList(String courseTypeId);
	
	
		
	public List<Course> courseList(Course course);
	
	public int insertUpdateCourse(Course course);
	
	
}