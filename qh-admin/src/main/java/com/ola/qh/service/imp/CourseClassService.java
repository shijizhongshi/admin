package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.CourseClassDao;
import com.ola.qh.dao.CourseClassTeacherDao;
import com.ola.qh.entity.CourseClass;
import com.ola.qh.entity.CourseClassTeacher;
import com.ola.qh.entity.CourseTeacher;
import com.ola.qh.service.ICourseClassService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class CourseClassService implements ICourseClassService{

	@Autowired
	private CourseClassDao courseClassDao;
	@Autowired
	private CourseClassTeacherDao courseClassTeacherDao;
	@Override
	public List<CourseClass> selectCourseClass(String id, int pageNo, int pageSize) {
		
		return courseClassDao.selectCourseClass(id, pageNo, pageSize);
	}

	@Transactional
	@Override
	public Results<String> insertCourseClass(CourseClass courseClass) {
		
		Results<String> results=new Results<String>();
		
		try {
			
			
			List<CourseTeacher> teacher = courseClass.getListTeacher();
			for (CourseTeacher courseTeacher : teacher) {
				
					CourseClassTeacher courseClassTeacher = new CourseClassTeacher();
					courseClassTeacher.setId(KeyGen.uuid());
					courseClassTeacher.setClassId(courseClass.getId());
					courseClassTeacher.setAddtime(new Date());
					courseClassTeacher.setTeacherId(courseTeacher.getId());
					courseClassTeacherDao.insertCourseClassTeacher(courseClassTeacher);
				
			}
			courseClass.setAddtime(new Date());
			courseClassDao.insertCourseClass(courseClass);
			
			results.setStatus("0");
			return results;
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

	@Transactional
	@Override
	public Results<String> updateCourseClass(CourseClass courseClass) {

		Results<String> results = new Results<String>();

		try {

			courseClass.setUpdatetime(new Date());
			courseClassDao.updateCourseClass(courseClass);

			List<CourseClassTeacher> courseClassTeacher = courseClassTeacherDao.selectCourseClassTeacher(courseClass.getId(), null);
			List<CourseTeacher> teacher = courseClass.getListTeacher();

			for (int i = 0; i < courseClassTeacher.size(); i++) {

				int j = 0;
				for (; j < teacher.size(); j++) {

					if (courseClassTeacher.get(i).getTeacherId().equals(teacher.get(j).getId())) {

						break;

					}

				}
				if (j == teacher.size()) {

					courseClassTeacherDao.deleteCourseClassTeacher(courseClass.getId(),
							courseClassTeacher.get(i).getTeacherId());

				}
			}
			for (CourseTeacher courseTeacher : teacher) {
				List<CourseClassTeacher> courseClassTeacher1 = courseClassTeacherDao.selectCourseClassTeacher(courseClass.getId(), courseTeacher.getId());
				if (courseClassTeacher1 == null) {

					CourseClassTeacher courseClassTeacherNew = new CourseClassTeacher();
					courseClassTeacherNew.setId(KeyGen.uuid());
					courseClassTeacherNew.setClassId(courseClass.getId());
					courseClassTeacherNew.setAddtime(new Date());
					courseClassTeacherNew.setTeacherId(courseTeacher.getId());
					courseClassTeacherDao.insertCourseClassTeacher(courseClassTeacherNew);
				}
			}

			results.setStatus("0");
			return results;

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

	@Override
	public int deleteCourseClass(String id) {
		
		return courseClassDao.deleteCourseClass(id);
	}
	
	
}
