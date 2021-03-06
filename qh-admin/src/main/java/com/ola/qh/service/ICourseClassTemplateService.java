package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.CourseClassTemplate;

public interface ICourseClassTemplateService {

	public List<CourseClassTemplate> selectCourseClassTemplate(String id,String templateName,int pageNo,int pageSize);
	
	public int selectTemplateCount(String templateName);
	
	public int insertCourseClassTemplate(CourseClassTemplate courseClassTemplate);
	
	public int updateCourseClassTemplate(CourseClassTemplate courseClassTemplate);
	
	public int deleteCourseClassTemplate(String id);
}
