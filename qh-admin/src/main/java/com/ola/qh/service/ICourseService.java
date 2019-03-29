package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;
import com.ola.qh.util.Results;

public interface ICourseService {

    public List<CourseType> courseTypeList();
	
	public int insertCourseType(String courseTypeName,String courseTypeId);
	
	public int updateCourseType(String courseTypeName,String id,String courseTypeId);
	
	public List<CourseTypeSubclass> courseTypeSubclassList(String courseTypeId);
	
	public int courseCount(String courseTypeName,String courseTypeSubclassName,String courseName);
		
	public List<Course> courseList(Course course);
	
	public int insertUpdateCourse(Course course);
	
	public int deleteCourse(String id);

	public Results<List<CourseTypeSubclass>> selectCourseTypeSubclassNameAll();
	
	
}
