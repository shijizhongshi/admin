package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.CourseClassTeacher;

public interface CourseClassTeacherDao {

	public List<CourseClassTeacher> selectCourseClassTeacher(@Param("classId")String classId,@Param("teacherId")String teacherId);
	
	public int insertCourseClassTeacher(CourseClassTeacher courseClassTeacher);
	
	public int deleteCourseClassTeacher(@Param("classId")String classId,@Param("teacherId")String teacherId);
}
