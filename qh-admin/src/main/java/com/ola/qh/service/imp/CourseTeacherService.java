
package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseTeacherDao;
import com.ola.qh.entity.CourseTeacher;
import com.ola.qh.service.ICourseTeacherService;

@Service
public class CourseTeacherService implements ICourseTeacherService{

	@Autowired
	private CourseTeacherDao courseTeacherDao;

	@Override
	public List<CourseTeacher> selectCourseTeacher(int pageNo, int pageSize) {
		
		return courseTeacherDao.selectCourseTeacher(pageNo, pageSize);
	}

	@Override
	public int insertCourseTeacher(CourseTeacher courseTeacher) {
		
		return courseTeacherDao.insertCourseTeacher(courseTeacher);
	}

	@Override
	public int updateCourseTeacher(CourseTeacher courseTeacher) {
		
		return courseTeacherDao.updateCourseTeacher(courseTeacher);
	}

	@Override
	public int deleteCourseTeacher(String id) {
		
		return courseTeacherDao.deleteCourseTeacher(id);
	}
	
	
	
}
