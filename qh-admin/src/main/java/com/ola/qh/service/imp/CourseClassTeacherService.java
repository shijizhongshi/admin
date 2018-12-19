package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseClassTeacherDao;
import com.ola.qh.entity.CourseClassTeacher;
import com.ola.qh.service.ICourseClassTeacherService;

@Service
public class CourseClassTeacherService implements ICourseClassTeacherService{

	@Autowired
	private CourseClassTeacherDao courseClassTeacherDao;

	@Override
	public List<CourseClassTeacher> selectCourseClassTeacher(String classId, String teacherId, int pageNo,
			int pageSize) {
		
		return courseClassTeacherDao.selectCourseClassTeacher(classId, teacherId, pageNo, pageSize);
	}

	@Override
	public int insertCourseClassTeacher(CourseClassTeacher courseClassTeacher) {
		
		return courseClassTeacherDao.insertCourseClassTeacher(courseClassTeacher);
	}

	@Override
	public int deleteCourseClassTeacher(String classId,String teacherId) {
		
		return courseClassTeacherDao.deleteCourseClassTeacher(classId,teacherId);
	}
}
