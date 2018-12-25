package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.CourseClass;
import com.ola.qh.util.Results;

public interface ICourseClassService {

	public List<CourseClass> selectCourseClass(String id,int pageNo,int pageSize);
	
	public Results<String> insertCourseClass(CourseClass courseClass);
	
	public Results<String> updateCourseClass(CourseClass courseClass);
	
	public int deleteCourseClass(String id);
}
