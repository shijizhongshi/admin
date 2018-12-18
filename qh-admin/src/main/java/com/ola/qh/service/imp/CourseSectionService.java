package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseSectionDao;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.service.ICourseSectionService;

@Service
public class CourseSectionService implements ICourseSectionService{

	@Autowired
	private CourseSectionDao courseSectionDao;
	
	@Override
	public List<CourseSection> selectCourseSection(int pageNo, int pageSize) {
		
		return courseSectionDao.selectCourseSection(pageNo, pageSize);
	}

	@Override
	public int insertCourseSection(CourseSection courseSection) {
		
		return courseSectionDao.insertCourseSection(courseSection);
	}

	@Override
	public int updateCourseSection(CourseSection courseSection) {
		
		return courseSectionDao.updateCourseSection(courseSection);
	}

	@Override
	public int deleteCourseSection(String id) {
		
		return courseSectionDao.deleteCourseSection(id);
	}

}
