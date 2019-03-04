package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.CourseClass;
import com.ola.qh.util.Results;

public interface ICourseClassService {

	public List<CourseClass> selectCourseClass(String id,int pageNo,int pageSize,String courseTypeName,String courseTypeSubclassName,String className);
	
	public List<CourseClass> listCourseClass(String courseTypeName,String courseTypeSubclassName);
	
	public int selectCourseClassCount(String courseTypeName,String courseTypeSubclassName,String className);
	
	public Results<String> insertCourseClass(CourseClass courseClass);
	
	public Results<String> updateCourseClass(CourseClass courseClass);
	
	public int deleteCourseClass(String id);
}
