package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseNofreeDao;
import com.ola.qh.entity.CourseLineShow;
import com.ola.qh.entity.CourseNofree;
import com.ola.qh.service.ICourseNofreeService;
import com.ola.qh.util.KeyGen;

@Service
public class CourseNofreeService implements ICourseNofreeService {

	@Autowired
	private CourseNofreeDao courseNofreeDao;
	
	@Override
	public List<CourseNofree> selectCourseNofree(String courseTypeName, String courseTypeSubclassName, int pageNo,
			int pageSize) {
		
		return courseNofreeDao.selectCourseNofree(courseTypeName, courseTypeSubclassName, pageNo, pageSize);
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

	@Override
	public int insertLive(CourseLineShow cl) {
		// TODO Auto-generated method stub
		cl.setId(KeyGen.uuid());
		cl.setAddtime(new Date());
		return courseNofreeDao.insertLive(cl);
	}

	@Override
	public List<CourseLineShow> selectLiveList(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return courseNofreeDao.selectLiveList(pageNo, pageSize);
	}

	@Override
	public int updateLive(CourseLineShow cl) {
		// TODO Auto-generated method stub
		cl.setAddtime(new Date());
		return courseNofreeDao.updateLive(cl);
	}

	@Override
	public CourseLineShow singleLive(String id) {
		// TODO Auto-generated method stub
		return courseNofreeDao.singleLive(id);
	}

	@Override
	public int deleteLive(String id) {
		// TODO Auto-generated method stub
		return courseNofreeDao.deleteLive(id);
	}

	

}
