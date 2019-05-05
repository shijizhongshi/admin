
package com.ola.qh.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.CourseClassTeacherDao;
import com.ola.qh.dao.CourseTeacherDao;
import com.ola.qh.entity.CourseTeacher;
import com.ola.qh.service.ICourseTeacherService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class CourseTeacherService implements ICourseTeacherService {

	@Autowired
	private CourseTeacherDao courseTeacherDao;
	@Autowired
	private CourseClassTeacherDao courseClassTeacherDao;

	@Override
	public List<CourseTeacher> selectCourseTeacher(int pageNo, int pageSize, String courseTypeName,
			String courseTypeSubclassName, String teacherName) {
		List<CourseTeacher> list = courseTeacherDao.selectCourseTeacher(pageNo, pageSize, courseTypeName,
				courseTypeSubclassName, teacherName);
		for (CourseTeacher courseTeacher : list) {
			List<String> typename = new ArrayList<String>();
			if (courseTeacher.getCourseTypeSubclassNames().indexOf(",") > 0) {
				String[] typenames = courseTeacher.getCourseTypeSubclassNames().split(",");
				typename = Arrays.asList(typenames);
			} else {
				typename.add(courseTeacher.getCourseTypeSubclassNames());
			}
			courseTeacher.setTypename(typename);

			List<String> names = new ArrayList<String>();
			if (courseTeacher.getCourseTypeNames().indexOf(",") > 0) {
				String[] tnames = courseTeacher.getCourseTypeNames().split(",");
				names = Arrays.asList(tnames);
			} else {
				names.add(courseTeacher.getCourseTypeNames());
			}
			courseTeacher.setNames(names);
		}
		return list;
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

		Results<String> results = new Results<String>();

		try {
			courseTeacher.setId(KeyGen.uuid());
			courseTeacher.setAddtime(new Date());
			List<String> list = courseTeacher.getTypename();
			String typename = "";
			for (String string : list) {
				if ("".equals(typename)) {
					typename = string;
				} else {
					typename = typename + "," + string;
				}
			}

			String names = "";
			for (String string : courseTeacher.getNames()) {
				if ("".equals(names)) {
					names = string;
				} else {
					names = names + "," + string;
				}
			}
			// 添加教师信息的同时 赋值给
			Integer maxOrders = courseTeacherDao.selectMaxOrders();
			Integer orders = null;
			if (maxOrders != null) {
				orders = maxOrders + 1;
			} else {
				orders = 1;
			}
			courseTeacher.setOrders(orders);
			courseTeacher.setCourseTypeNames(names);
			courseTeacher.setCourseTypeSubclassNames(typename);
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
		List<String> list = courseTeacher.getTypename();
		if (list != null && list.size() != 0) {
			String typename = "";
			for (String string : list) {
				if ("".equals(typename)) {
					typename = string;
				} else {
					typename = typename + "," + string;
				}
			}
			courseTeacher.setCourseTypeSubclassNames(typename);
		}

		if (courseTeacher.getNames() != null && courseTeacher.getNames().size() != 0) {
			String names = "";
			for (String string : courseTeacher.getNames()) {
				if ("".equals(names)) {
					names = string;
				} else {
					names = names + "," + string;
				}
			}
			courseTeacher.setCourseTypeNames(names);
		}

		return courseTeacherDao.updateCourseTeacher(courseTeacher);
	}

	@Transactional
	public int deleteCourseTeacher(String id) {
		int num = courseTeacherDao.deleteCourseTeacher(id);

		courseClassTeacherDao.deleteCourseClassTeacher(null, id);
		return num;
	}

	@Override
	public int selectCourseTeacherCount(String courseTypeName, String courseTypeSubclassName, String teacherName) {
		// TODO Auto-generated method stub
		return courseTeacherDao.selectCourseTeacherCount(courseTypeName, courseTypeSubclassName, teacherName);
	}

	@Override
	public Results<String> courseTeacherOrders(String id, int orders, String operateType) {
		Results<String> results = new Results<String>();
		if ("down".equals(operateType)) { // 下移
			// 获取下一条记录iorder
			int nextOrder = courseTeacherDao.selectOrder(operateType, orders);
			// 修改下一条的为当前值
			courseTeacherDao.updateOrders(null, nextOrder, orders);
			// 修改自己的排序为下一条
			courseTeacherDao.updateOrders(id, 0, nextOrder);
			results.setData(String.valueOf(nextOrder));
		}
		if ("up".equals(operateType)) { // 上移
			// 获取上一条记录iorder
			int previousOrder = courseTeacherDao.selectOrder(operateType, orders);
			// 修改上一条的为当前值
			courseTeacherDao.updateOrders(null, previousOrder, orders);
			// 修改自己的排序为上一条
			courseTeacherDao.updateOrders(id, 0, previousOrder);
			results.setData(String.valueOf(previousOrder));
		}
		results.setStatus("0");

		return results;
	}

}
