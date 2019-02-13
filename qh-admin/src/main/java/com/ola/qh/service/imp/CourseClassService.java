package com.ola.qh.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.CourseClassDao;
import com.ola.qh.dao.CourseClassTeacherDao;
import com.ola.qh.dao.CourseDao;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseClass;
import com.ola.qh.entity.CourseClassTeacher;
import com.ola.qh.entity.CourseTeacher;
import com.ola.qh.service.ICourseClassService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class CourseClassService implements ICourseClassService {

	@Autowired
	private CourseClassDao courseClassDao;
	@Autowired
	private CourseClassTeacherDao courseClassTeacherDao;
	@Autowired
	private CourseDao courseDao;

	@Override
	public List<CourseClass> selectCourseClass(String id, int pageNo, int pageSize,String courseTypeName,String courseTypeSubclassName,String className) {

		List<CourseClass> list = courseClassDao.selectCourseClass(id, pageNo, pageSize, courseTypeName, courseTypeSubclassName,className);
		for (CourseClass courseClass : list) {
			///查课程   
			Course c=new Course();
			c.setClassId(courseClass.getId());
			List<Course> clist = courseDao.courseList(c);
			courseClass.setListCourse(clist);
			///查老师
			List<CourseClassTeacher> ctlist=courseClassTeacherDao.selectCourseClassTeacher(courseClass.getId(), null);
			List<CourseTeacher> tlist=new ArrayList<CourseTeacher>();
			for (CourseClassTeacher courseClassTeacher : ctlist) {
				CourseTeacher t=new CourseTeacher();
				t.setId(courseClassTeacher.getTeacherId());
				t.setName(courseClassTeacher.getTname());
				tlist.add(t);
			}
			courseClass.setListTeacher(tlist);
		}
		return list;
				
	}

	@Transactional
	@Override
	public Results<String> insertCourseClass(CourseClass courseClass) {

		Results<String> results = new Results<String>();

		try {

			courseClass.setId(KeyGen.uuid());
			///////专业名称已经存在了班级给他提示
			int count = courseClassDao.selectCourseClassCount(courseClass.getCourseTypeName(), courseClass.getCourseTypeSubclassName(),courseClass.getClassName());
			
			if(count>0){
				results.setStatus("1");
				results.setMessage("该专业的班级名称已经存在");
				return results;
			}
			List<CourseTeacher> teacher = courseClass.getListTeacher();

			for (CourseTeacher courseTeacher : teacher) {

				CourseClassTeacher courseClassTeacher = new CourseClassTeacher();
				courseClassTeacher.setId(KeyGen.uuid());
				courseClassTeacher.setClassId(courseClass.getId());
				courseClassTeacher.setAddtime(new Date());
				courseClassTeacher.setTeacherId(courseTeacher.getId());
				courseClassTeacherDao.insertCourseClassTeacher(courseClassTeacher);

			}
			List<Course> course = courseClass.getListCourse();
			for (Course courseNew : course) {

				Course c=new Course();
				c.setClassId(courseClass.getId());
				c.setId(courseNew.getId());
				c.setUpdatetime(new Date());
				courseDao.updateCourese(c);

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
				if (courseClassTeacher1 == null || courseClassTeacher1.size()==0) {

					CourseClassTeacher courseClassTeacherNew = new CourseClassTeacher();
					courseClassTeacherNew.setId(KeyGen.uuid());
					courseClassTeacherNew.setClassId(courseClass.getId());
					courseClassTeacherNew.setAddtime(new Date());
					courseClassTeacherNew.setTeacherId(courseTeacher.getId());
					courseClassTeacherDao.insertCourseClassTeacher(courseClassTeacherNew);
				}
			}

			List<Course> course = courseDao.existCourseList(courseClass.getId());
			List<Course> courseset = courseClass.getListCourse();

			for (int i = 0; i < course.size(); i++) {

				int j = 0;
				for (; j < courseset.size(); j++) {

					if (course.get(i).getClassId().equals(courseset.get(j).getId())) {

						break;

					}

				}
				if (j == courseset.size()) {

					courseDao.updateClass(courseClass.getId());

				}
			}
			for (Course courseinsert : courseset) {
				/*Course exist = courseDao.existCourse(courseinsert.getId());
				if (exist.getClassId() == null) {*/

					Course courseOn = new Course();
					courseOn.setId(courseinsert.getId());
					courseOn.setClassId(courseClass.getId());
					courseDao.updateCourese(courseOn);
				/*}*/
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

	@Override
	public int selectCourseClassCount(String courseTypeName, String courseTypeSubclassName) {
		// TODO Auto-generated method stub
		return courseClassDao.selectCourseClassCount(courseTypeName, courseTypeSubclassName,null);
	}

}
