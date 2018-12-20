package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseClassDao;
import com.ola.qh.entity.CourseClass;
import com.ola.qh.service.ICourseClassService;

@Service
public class CourseClassService implements ICourseClassService{

	@Autowired
	private CourseClassDao courseClassDao;

	@Override
	public List<CourseClass> selectCourseClass(String id, int pageNo, int pageSize) {
		
		return courseClassDao.selectCourseClass(id, pageNo, pageSize);
	}

	@Override
	public int insertCourseClass(CourseClass courseClass) {
		
		return courseClassDao.insertCourseClass(courseClass);
	}

	@Override
	public int updateCourseClass(CourseClass courseClass) {
		
		return courseClassDao.updateCourseClass(courseClass);
	}

	@Override
	public int deleteCourseClass(String id) {
		
		return courseClassDao.deleteCourseClass(id);
	}
	
	
}
