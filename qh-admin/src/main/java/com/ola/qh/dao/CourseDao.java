package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;
import com.ola.qh.entity.CourseTypeSubclassNames;
import com.ola.qh.entity.LiveMark;

public interface CourseDao {

	public List<CourseType> courseTypeList();

	public int insertCourseType(@Param("courseTypeName") String courseTypeName, @Param("id") String id,
			@Param("imgUrl") String imgUrl);

	public int updateCourseType(@Param("courseTypeName") String courseTypeName, @Param("id") String id,
			@Param("imgUrl") String imgUrl);

	public CourseType singleCourseType(@Param("id") String id);

	public List<CourseTypeSubclass> courseTypeSubclassList(@Param("courseTypeId") String courseTypeId);

	public CourseTypeSubclass singleCourseTypeSubclass(@Param("courseTypeSubclassName") String courseTypeSubclassName);

	public int insertCourseTypeSubclass(@Param("courseTypeSubclassName") String courseTypeName, @Param("id") String id,
			@Param("courseTypeId") String courseTypeId, @Param("imgUrl") String imgUrl);

	public int updateCourseTypeSubclass(@Param("courseTypeSubclassName") String courseTypeName, @Param("id") String id,
			@Param("courseTypeId") String courseTypeId, @Param("imgUrl") String imgUrl);

	public int deleteCourse(@Param("id") String id);

	public List<Course> courseList(Course course);

	public int insertCourse(Course course);

	public int courseCount(@Param("courseTypeName") String courseTypeName,
			@Param("courseTypeSubclassName") String courseTypeSubclassName, @Param("courseName") String courseName);

	public int updateCourese(Course course);

	public Course existCourse(@Param("id") String id);

	public List<Course> existCourseList(@Param("classId") String classId);

	public int updateClass(@Param("courseId") String courseId, @Param("classIds") String classIds);

	public List<CourseTypeSubclass> selectCourseTypeSubclassNameAll();

	public List<Course> selectByCourseTypeSubclassName(@Param("courseTypeSubclassName") String courseTypeSubclassName);

	public String maxId();

	public Integer deleteCourseType(@Param("id") String id);

	public String maxCourseTypeSubclassId();

	public Integer updateCourseTypeSubclassName(@Param("courseTypeSubclassId") String courseTypeSubclassId,
			@Param("courseTypeSubclassName") String courseTypeSubclassName);

	public Integer deleteCourseTypeSubclass(@Param("courseTypeSubclassId") String courseTypeSubclassId);

	public Integer insert(@Param("id") String id, @Param("courseTypeSubclassId") String courseTypeSubclassId,
			@Param("miniSubclassName") String miniSubclassName);

	public List<CourseTypeSubclassNames> select(@Param("courseTypeSubclassId") String courseTypeSubclassId);

	public Integer update(@Param("miniId") String miniId, @Param("miniSubclassName") String miniSubclassName);

	public Integer delete(@Param("id") String miniId);

	public List<Date> selectLiveShow(@Param("date") String date);

	public Integer updateStatus(@Param("userId") String userId);

	public List<LiveMark> selectStartTimeByHour(@Param("date") String date);
	
	public List<LiveMark> selectStartTimeByMin(@Param("format")String format);
	
}
