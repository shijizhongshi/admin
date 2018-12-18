package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.CourseTeacher;

public interface ICourseTeacherService {

	public List<CourseTeacher> selectCourseTeacher(int pageNo,int pageSize);
	
	public int insertCourseTeacher(CourseTeacher courseTeacher);
	
	public int updateCourseTeacher(CourseTeacher courseTeacher);
	
	public int deleteCourseTeacher(String id);
}
