package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.CourseNofree;

public interface CourseNofreeDao {

	public List<CourseNofree> selectCourseNofree(@Param("courseTypeName")String courseTypeName,@Param("courseTypeSubclassName")String courseTypeSubclassName,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public int insertCourseNofree(CourseNofree courseNofree);
	
	public int updateCourseNofree(CourseNofree courseNofree);
	
	public int deleteCourseNofree(@Param("id")String id);
}
