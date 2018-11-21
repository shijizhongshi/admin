package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseDao;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;
import com.ola.qh.service.ICourseService;
import com.ola.qh.util.KeyGen;
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
	public int insertCourseType(String courseTypeName,String courseTypeId) {
		// TODO Auto-generated method stub
		String id=KeyGen.uuid();
		if(courseTypeId!=null && !"".equals(courseTypeId)){
			return courseDao.insertCourseTypeSubclass(courseTypeName, id, courseTypeId);
		}else{
			return courseDao.insertCourseType(courseTypeName, id);
		}
		
	}
	@Override
	public int updateCourseType(String courseTypeName, String id,String courseTypeId) {
		// TODO Auto-generated method stub
		if(courseTypeId!=null && !"".equals(courseTypeId)){
			return courseDao.updateCourseTypeSubclass(courseTypeName, id, courseTypeId);
		}else{
			return courseDao.updateCourseType(courseTypeName, id);
		}
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
	@Override
	public int insertUpdateCourse(Course course) {
		// TODO Auto-generated method stub
		if(course.getId()!=null && !"".equals(course.getId())){
			course.setUpdatetime(new Date());
			return courseDao.updateCourese(course);
		}
		course.setId(KeyGen.uuid());
		course.setAddtime(new Date());
		return courseDao.insertCourse(course);
	}

	

}
