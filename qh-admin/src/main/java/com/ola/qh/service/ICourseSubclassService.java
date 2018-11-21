package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.util.Results;

public interface ICourseSubclassService {

	public List<CourseChapter> courseChapterList(String courseId,int pageNo,int pageSize);
	
	public Results<String> courseChapterSaveUpdate(CourseChapter ccp);
	
	
	public List<CourseSection> courseSectionList(String courseChapterId,int pageNo,int pageSize);

	public Results<String> courseSectionSaveUpdate(CourseSection cs);
}
