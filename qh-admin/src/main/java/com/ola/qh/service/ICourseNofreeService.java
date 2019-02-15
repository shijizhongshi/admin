package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.CourseLineShow;
import com.ola.qh.entity.CourseNofree;

public interface ICourseNofreeService {

	public List<CourseNofree> selectCourseNofree(String courseTypeName,String courseTypeSubclassName,int pageNo,int pageSize,String teachers,String courseName);
	
	public int insertCourseNofree(CourseNofree courseNofree);
	
	public int updateCourseNofree(CourseNofree courseNofree);
	
	public int deleteCourseNofree(String id);
	
	public int insertLive(CourseLineShow cl);
	
	
	public List<CourseLineShow> selectLiveList(int pageNo,int pageSize,String courseTypeName,String courseTypeSubclassName,String liveName);
	
	public int selectLiveListCount(String courseTypeName,String courseTypeSubclassName,String liveName);
	
	public int updateLive(CourseLineShow cl);
	
	public CourseLineShow singleLive(String id);
	
	public int deleteLive(String id);
}
