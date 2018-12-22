package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.CourseNofree;

public interface ICourseNofreeService {

	public List<CourseNofree> selectCourseNofree(String courseTypeName,String courseTypeSubclassName,int pageNo,int pageSize);
	
	public int insertCourseNofree(CourseNofree courseNofree);
	
	public int updateCourseNofree(CourseNofree courseNofree);
	
	public int deleteCourseNofree(String id);
}
