package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.CourseClassTemplate;

public interface ICourseClassTemplateService {

	public List<CourseClassTemplate> selectCourseClassTemplate(String id,int pageNo,int pageSize);
	
	public int insertCourseClassTemplate(CourseClassTemplate courseClassTemplate);
	
	public int updateCourseClassTemplate(CourseClassTemplate courseClassTemplate);
	
	public int deleteCourseClassTemplate(String id);
}
