package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseSubclassDao;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.service.ICourseSubclassService;
@Service
public class CourseSubclassService implements ICourseSubclassService{

	@Autowired
	private CourseSubclassDao courseSubclassDao;
	
	@Override
	public List<CourseChapter> courseChapterList(String courseId) {
		// TODO Auto-generated method stub
		return courseSubclassDao.courseChapterList(courseId);
	}

	@Override
	public List<CourseSection> courseSectionList(String courseChapterId) {
		// TODO Auto-generated method stub
		return courseSubclassDao.courseSectionList(courseChapterId);
	}
	
}
