package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.CourseTeacher;
import com.ola.qh.util.Results;

public interface ICourseTeacherService {

	public List<CourseTeacher> selectCourseTeacher(int pageNo,int pageSize,String courseTypeName,String courseTypeSubclassName,String teacherName);
	
	public int selectCourseTeacherCount(
			String courseTypeName,
			String courseTypeSubclassName,
			String teacherName);
	
	public CourseTeacher selectCourseTeacherDetails(String id);
	
	public List<CourseTeacher> selectName(String id);
	
	public Results<String> insertCourseTeacher(CourseTeacher courseTeacher);
	
	public int updateCourseTeacher(CourseTeacher courseTeacher);
	
	public int deleteCourseTeacher(String id);

	public Results<String> courseTeacherOrders(String id, int orders, String operateType);
}
