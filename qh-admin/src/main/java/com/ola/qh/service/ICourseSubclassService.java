package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseSection;

public interface ICourseSubclassService {

	public List<CourseChapter> courseChapterList(String courseId);
	
	public List<CourseSection> courseSectionList(String courseChapterId);

}
