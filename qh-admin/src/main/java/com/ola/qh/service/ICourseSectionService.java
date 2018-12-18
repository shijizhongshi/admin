package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.CourseSection;

public interface ICourseSectionService {

	public List<CourseSection> selectCourseSection(int pageNo,int pageSize);
	
	public int insertCourseSection(CourseSection courseSection);
	
	public int updateCourseSection(CourseSection courseSection);
	
	public int deleteCourseSection(String id);
}
