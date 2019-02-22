package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.CourseClassTemplate;

public interface CourseClassTemplateDao {

	public List<CourseClassTemplate> selectCourseClassTemplate(@Param("id")String id,@Param("templateName")String templateName,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public int selectTemplateCount(@Param("templateName")String templateName);
	
	public int insertCourseClassTemplate(CourseClassTemplate courseClassTemplate);
	
	public int updateCourseClassTemplate(CourseClassTemplate courseClassTemplate);
	
	public int deleteCourseClassTemplate(@Param("id")String id);
}
