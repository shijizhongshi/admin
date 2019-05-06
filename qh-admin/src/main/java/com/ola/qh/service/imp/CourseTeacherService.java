
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
}
