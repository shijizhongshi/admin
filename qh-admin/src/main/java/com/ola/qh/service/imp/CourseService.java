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
import com.ola.qh.entity.CourseTypeSubclassNames;
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
			return courseDao.insertCourseTypeSubclass(courseTypeName, id, courseTypeId,null);
		} else {
			return courseDao.insertCourseType(courseTypeName, id, null);
		}

	}

	@Override
	public int updateCourseType(String courseTypeName, String id, String courseTypeId) {
		// TODO Auto-generated method stub
		if (courseTypeId != null && !"".equals(courseTypeId)) {
			return courseDao.updateCourseTypeSubclass(courseTypeName, id, courseTypeId,null);
		} else {
			return courseDao.updateCourseType(courseTypeName, id,null);
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
			Integer ordersMax = courseSubclassDao.selectMaxOrder("course");
			if (ordersMax != null) {
				course.setOrders(ordersMax.intValue() + 1);
			} else {
				course.setOrders(1);
			}
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

	@Override
	public Results<List<CourseTypeSubclass>> selectCourseTypeSubclass(String courseTypeId) {
		Results<List<CourseTypeSubclass>> results = new Results<List<CourseTypeSubclass>>();
		List<CourseTypeSubclass> list = courseDao.courseTypeSubclassList(courseTypeId);

		results.setStatus("0");
		results.setData(list);

		return results;
	}

	@Override
	public Results<String> insertCourseTypeName(String courseTypeName, String imgUrl) {
		Results<String> results = new Results<String>();
		String id = KeyGen.uuid();
		Integer countInteger = courseDao.insertCourseType(courseTypeName, id, imgUrl);
		if (countInteger == 1) {
			results.setStatus("0");

			return results;
		}
		results.setStatus("1");
		results.setMessage("添加分类失败");

		return results;
	}

	@Override
	public Results<String> deleteCourseType(String id) {
		Results<String> results = new Results<String>();
		//根据一级ID查询二级集合
		List<CourseTypeSubclass> list = courseDao.courseTypeSubclassList(id);
		for (CourseTypeSubclass courseTypeSubclass : list) {
			//根据二级ID查询三级集合
			List<CourseTypeSubclassNames> namesList = courseDao.select(courseTypeSubclass.getId());
			for (CourseTypeSubclassNames courseTypeSubclassNames : namesList) {
				//根据三级ID删除三级类别
				courseDao.delete(courseTypeSubclassNames.getId());
			}
			//根据二级ID删除二级集合
			courseDao.deleteCourseTypeSubclass(courseTypeSubclass.getId());
		}
		//根据一级ID删除一级类别
		Integer count = courseDao.deleteCourseType(id);
		if (count == 1) {
			results.setStatus("0");

			return results;
		}
		results.setStatus("1");
		results.setMessage("删除失败");

		return results;
	}

	@Override
	public Results<String> insertCourseTypeSubclassName(String courseTypeId, String courseTypeSubclassName,String imgUrl) {

		Results<String> results = new Results<String>();
		// 生成一个ID
		String id = KeyGen.uuid();
		Integer countInteger = courseDao.insertCourseTypeSubclass(courseTypeSubclassName, id, courseTypeId,imgUrl);
		if (countInteger == 1) {
			results.setStatus("0");

			return results;
		}
		results.setStatus("1");
		results.setMessage("添加分类失败");

		return results;
	}

	@Override
	public Results<String> updateCourseTypeSubclassName(String courseTypeSubclassId, String courseTypeSubclassName,String imgUrl) {
		Results<String> results = new Results<String>();
		Integer count = courseDao.updateCourseTypeSubclass(courseTypeSubclassName, courseTypeSubclassId, null,imgUrl);
		if (count == 1) {
			results.setStatus("0");

			return results;
		}
		results.setStatus("1");
		results.setMessage("修改失败");

		return results;
	}

	@Override
	public Results<String> deleteCourseTypeSubclass(String courseTypeSubclassId) {
		Results<String> results = new Results<String>();
		//根据二级ID查询出它下面的三级类别集合
		List<CourseTypeSubclassNames> list = courseDao.select(courseTypeSubclassId);
		for (CourseTypeSubclassNames courseTypeSubclassNames : list) {
			//根据三级ID删除三级类别
			courseDao.delete(courseTypeSubclassNames.getId());
		}
		//根据二级ID删除二级类别
		Integer count = courseDao.deleteCourseTypeSubclass(courseTypeSubclassId);
		if (count == 1) {
			results.setStatus("0");

			return results;
		}
		results.setStatus("1");
		results.setMessage("删除失败");

		return results;
	}

	@Override
	public Results<String> insertThree(String courseTypeSubclassId,String courseTypeSubclassName, String miniSubclassName) {
		Results<String> results = new Results<String>();
		String id = KeyGen.uuid();
		Integer count = courseDao.insert(id, courseTypeSubclassId,courseTypeSubclassName, miniSubclassName);
		if (count == 1) {
			results.setStatus("0");

			return results;
		}
		results.setStatus("1");
		results.setMessage("添加失败");

		return results;
	}

	@Override
	public Results<List<CourseTypeSubclassNames>> selectThree(String courseTypeSubclassId) {
		Results<List<CourseTypeSubclassNames>> results = new Results<List<CourseTypeSubclassNames>>();
		List<CourseTypeSubclassNames> list = courseDao.select(courseTypeSubclassId);
		results.setStatus("0");
		results.setData(list);

		return results;
	}

	@Override
	public Results<String> updateThree(String miniId, String miniSubclassName) {
		Results<String> results = new Results<String>();
		Integer count = courseDao.update(miniId, miniSubclassName);
		if (count == 1) {
			results.setStatus("0");

			return results;
		}
		results.setStatus("1");
		results.setMessage("修改失败");

		return results;
	}

	@Override
	public Results<String> deleteThree(String miniId) {
		Results<String> results = new Results<String>();
		Integer count = courseDao.delete(miniId);
		if (count == 1) {
			results.setStatus("0");

			return results;
		}
		results.setStatus("1");
		results.setMessage("删除失败");

		return results;
	}

	@Override
	public Results<String> updateOne(String id, String courseTypeName,String imgUrl) {
		Results<String> results = new Results<String>();
		Integer count = courseDao.updateCourseType(courseTypeName, id,imgUrl);
		if (count == 1) {
			results.setStatus("0");

			return results;
		}
		results.setStatus("1");
		results.setMessage("修改一级类别失败");

		return results;
	}
}
