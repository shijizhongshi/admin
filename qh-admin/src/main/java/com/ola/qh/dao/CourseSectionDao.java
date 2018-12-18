package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.CourseSection;

public interface CourseSectionDao {

	public List<CourseSection> selectCourseSection(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public int insertCourseSection(CourseSection courseSection);
	
	public int updateCourseSection(CourseSection courseSection);
	
	public int deleteCourseSection(@Param("id")String id);
}
