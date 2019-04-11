package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.CourseDao;
import com.ola.qh.dao.CourseSubclassDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;
import com.ola.qh.service.ICourseService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

/**
 * 
 * @ClassName: CourseService
 * @Description: 类别的查询和课程的查询
 * @author guoyuxue
 * @date 2018年11月19日
 *
 */
@Service
public class CourseService implements ICourseService {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CourseSubclassDao courseSubclassDao;

	@Override
	public List<CourseType> courseTypeList() {
		// TODO Auto-generated method stub
		return courseDao.courseTypeList();///// 大类别的查询
	}

	@Override
	public int insertCourseType(String courseTypeName, String courseTypeId) {
		// TODO Auto-generated method stub
		String id = KeyGen.uuid();
		if (courseTypeId != null && !"".equals(courseTypeId)) {
			return courseDao.insertCourseTypeSubclass(courseTypeName, id, courseTypeId);
		} else {
			return courseDao.insertCourseType(courseTypeName, id);
		}

	}

	@Override
	public int updateCourseType(String courseTypeName, String id, String courseTypeId) {
		// TODO Auto-generated method stub
		if (courseTypeId != null && !"".equals(courseTypeId)) {
			return courseDao.updateCourseTypeSubclass(courseTypeName, id, courseTypeId);
		} else {
			return courseDao.updateCourseType(courseTypeName, id);
		}
	}

	@Override
	public List<CourseTypeSubclass> courseTypeSubclassList(String courseTypeId) {
		// TODO Auto-generated method stub
		return courseDao.courseTypeSubclassList(courseTypeId);//// 子类别的查询
	}

	@Override
	public List<Course> courseList(Course course) {
		// TODO Auto-generated method stub
		return courseDao.courseList(course);
	}

	@Transactional
	@Override
	public int insertUpdateCourse(Course course) {
		// TODO Auto-generated method stub
		try {

			if (course.getId() != null && !"".equals(course.getId())) {
				course.setUpdatetime(new Date());
				userDao.updateFavorite(course.getId());
				return courseDao.updateCourese(course);

			}
			int ordersMax = courseSubclassDao.selectMaxOrder("course");
			course.setOrders(ordersMax+1);
			course.setId(KeyGen.uuid());
			course.setAddtime(new Date());
			course.setUserId("1");
			return courseDao.insertCourse(course);

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return 0;
		}
	}

	@Override
	public int deleteCourse(String id) {
		// TODO Auto-generated method stub
		return courseDao.deleteCourse(id);
	}

	@Override
	public int courseCount(String courseTypeName, String courseTypeSubclassName, String courseName) {

		return courseDao.courseCount(courseTypeName, courseTypeSubclassName, courseName);
	}

	/**
	 * 推送页面 专业下拉列表
	 */
	@Override
	public Results<List<CourseTypeSubclass>> selectCourseTypeSubclassNameAll() {
		Results<List<CourseTypeSubclass>> results = new Results<>();
		List<CourseTypeSubclass> list = courseDao.selectCourseTypeSubclassNameAll();
		if (list == null) {
			results.setStatus("1");
			results.setMessage("下拉列表数据查询 失败！");

			return results;
		}
		results.setStatus("0");
		results.setData(list);

		return results;
	}
}
