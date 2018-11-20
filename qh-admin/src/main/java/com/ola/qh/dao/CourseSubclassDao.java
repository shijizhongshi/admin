package com.ola.qh.dao;

import java.util.List;

import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseSection;

public interface CourseSubclassDao {

	public List<CourseChapter> courseChapterList(String courseId);
	
	public List<CourseSection> courseSectionList(String courseChapterId);
}
