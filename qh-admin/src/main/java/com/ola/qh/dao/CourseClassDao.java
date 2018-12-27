package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.CourseClass;

public interface CourseClassDao {

	public List<CourseClass> selectCourseClass(@Param("id")String id,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public int insertCourseClass(CourseClass courseClass);
	
	public int updateCourseClass(CourseClass courseClass);
	
	public int deleteCourseClass(@Param("id")String id);
	

}
