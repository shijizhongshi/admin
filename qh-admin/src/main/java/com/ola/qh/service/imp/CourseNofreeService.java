package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseNofreeDao;
import com.ola.qh.entity.CourseNofree;
import com.ola.qh.service.ICourseNofreeService;

@Service
public class CourseNofreeService implements ICourseNofreeService {

	@Autowired
	private CourseNofreeDao courseNofreeDao;
	
	@Override
	public List<CourseNofree> selectCourseNofree(String id, int pageNo, int pageSize) {
		
		return courseNofreeDao.selectCourseNofree(id, pageNo, pageSize);
	}

	@Override
	public int insertCourseNofree(CourseNofree courseNofree) {
		
		return courseNofreeDao.insertCourseNofree(courseNofree);
	}

	@Override
	public int updateCourseNofree(CourseNofree courseNofree) {
		
		return courseNofreeDao.updateCourseNofree(courseNofree);
	}

	@Override
	public int deleteCourseNofree(String id) {
		
		return courseNofreeDao.deleteCourseNofree(id);
	}

}
