package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.CourseTeacher;

public interface CourseTeacherDao {

	public List<CourseTeacher> selectCourseTeacher(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public int insertCourseTeacher(CourseTeacher courseTeacher);
	
	public int updateCourseTeacher(CourseTeacher courseTeacher);
	
	public int deleteCourseTeacher(@Param("id")String id);
}
