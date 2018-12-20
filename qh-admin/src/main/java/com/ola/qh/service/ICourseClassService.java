package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.CourseClass;

public interface ICourseClassService {

	public List<CourseClass> selectCourseClass(String id,int pageNo,int pageSize);
	
	public int insertCourseClass(CourseClass courseClass);
	
	public int updateCourseClass(CourseClass courseClass);
	
	public int deleteCourseClass(String id);
}
