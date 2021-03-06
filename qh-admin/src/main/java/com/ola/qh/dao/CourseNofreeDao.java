package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.CourseLineShow;
import com.ola.qh.entity.CourseNofree;

public interface CourseNofreeDao {

	public List<CourseNofree> selectCourseNofree(@Param("courseTypeName")String courseTypeName,@Param("courseTypeSubclassName")String courseTypeSubclassName,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("teachers")String teachers,@Param("courseName")String courseName);
	
	public int insertCourseNofree(CourseNofree courseNofree);

	public int selectCourseNofreeCount(@Param("courseTypeName")String courseTypeName,@Param("courseTypeSubclassName")String courseTypeSubclassName,@Param("teachers")String teachers,@Param("courseName")String courseName);
	
	public int updateCourseNofree(CourseNofree courseNofree);
	
	public int deleteCourseNofree(@Param("id")String id);
	
	
	public int insertLive(CourseLineShow cl);
	
	public List<CourseLineShow> selectLiveList(@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize,
			@Param("courseTypeName")String courseTypeName,
			@Param("courseTypeSubclassName")String courseTypeSubclassName,
			@Param("liveName")String liveName);
	
	public int selectLiveListCount(@Param("courseTypeName")String courseTypeName,
			@Param("courseTypeSubclassName")String courseTypeSubclassName,@Param("liveName")String liveName);
	
	public int updateLive(CourseLineShow cl);
	
	public CourseLineShow singleLive(String id);
	
	public int deleteLive(String id);
}
