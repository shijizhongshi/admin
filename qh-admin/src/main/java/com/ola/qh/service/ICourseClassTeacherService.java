package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.CourseClassTeacher;

public interface ICourseClassTeacherService {

	public List<CourseClassTeacher> selectCourseClassTeacher(String classId,String teacherId,int pageNo,int pageSize);
	
	public int insertCourseClassTeacher(CourseClassTeacher courseClassTeacher);
	
	public int deleteCourseClassTeacher(String classId,String teacherId);
}
