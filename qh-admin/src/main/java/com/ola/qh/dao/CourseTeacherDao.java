package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.CourseTeacher;

public interface CourseTeacherDao {

	public List<CourseTeacher> selectCourseTeacher(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize,
			@Param("courseTypeName") String courseTypeName,
			@Param("courseTypeSubclassName") String courseTypeSubclassName, @Param("teacherName") String teacherName);

	public int selectCourseTeacherCount(@Param("courseTypeName") String courseTypeName,
			@Param("courseTypeSubclassName") String courseTypeSubclassName, @Param("teacherName") String teacherName);

	public CourseTeacher selectCourseTeacherDetails(@Param("id") String id);

	public List<CourseTeacher> selectName(@Param("id") String id);

	public List<CourseTeacher> selectNameFromType(@Param("courseTypeNames") String courseTypeNames,
			@Param("courseTypeSubclassNames") String courseTypeSubclassNames);

	public int insertCourseTeacher(CourseTeacher courseTeacher);

	public int updateCourseTeacher(CourseTeacher courseTeacher);

	public int deleteCourseTeacher(@Param("id") String id);

	// 上移下移
	public int selectOrder(@Param("type") String type, @Param("orders") int orders);

	public int updateOrders(@Param("id") String id, @Param("originalOrder") int originalOrder,
			@Param("orders") int orders);
}
