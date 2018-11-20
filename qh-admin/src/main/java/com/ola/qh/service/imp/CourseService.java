package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseDao;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;
import com.ola.qh.service.ICourseService;
/**
 * 
* @ClassName: CourseService  
* @Description: 类别的查询和课程的查询  
* @author guoyuxue  
* @date 2018年11月19日  
*
 */
@Service
public class CourseService implements ICourseService{

	
	@Autowired
	private CourseDao courseDao;
	
	@Override
	public List<CourseType> courseTypeList() {
		// TODO Auto-generated method stub
		return courseDao.courseTypeList();/////大类别的查询
	}

	@Override
	public List<CourseTypeSubclass> courseTypeSubclassList(String courseTypeId) {
		// TODO Auto-generated method stub
		return courseDao.courseTypeSubclassList(courseTypeId);////子类别的查询
	}

	@Override
	public List<Course> courseList(Course course) {
		// TODO Auto-generated method stub
		return courseDao.courseList(course);
	}

	

}
