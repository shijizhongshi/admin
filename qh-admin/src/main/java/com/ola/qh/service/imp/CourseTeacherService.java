
package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.CourseTeacherDao;
import com.ola.qh.entity.CourseTeacher;
import com.ola.qh.service.ICourseTeacherService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class CourseTeacherService implements ICourseTeacherService{

	@Autowired
	private CourseTeacherDao courseTeacherDao;

	@Override
	public List<CourseTeacher> selectCourseTeacher(int pageNo, int pageSize,String courseTypeName,String courseTypeSubclassName) {
		
		return courseTeacherDao.selectCourseTeacher(pageNo, pageSize,courseTypeName,courseTypeSubclassName);
	}
	
	@Override
	public CourseTeacher selectCourseTeacherDetails(String id) {
		
		return courseTeacherDao.selectCourseTeacherDetails(id);
	}
	
	@Override
	public List<CourseTeacher> selectName(String id) {
		
		return courseTeacherDao.selectName(id);
	}

	@Transactional
	@Override
	public Results<String> insertCourseTeacher(CourseTeacher courseTeacher) {
		
		Results<String> results=new Results<String>();
		
		try {
			courseTeacher.setId(KeyGen.uuid());
			courseTeacher.setAddtime(new Date());
			courseTeacherDao.insertCourseTeacher(courseTeacher);
		
			results.setStatus("0");
			return results;
		
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
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
