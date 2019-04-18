package com.ola.qh.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.CourseClassDao;
import com.ola.qh.dao.CourseClassTeacherDao;
import com.ola.qh.dao.CourseDao;
import com.ola.qh.dao.CourseSubclassDao;
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
	@Autowired
	private CourseSubclassDao courseSubclassDao;
	
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
			int ordersMax = courseSubclassDao.selectMaxOrder("c");
			courseClass.setOrders(ordersMax+1);
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
				///////之前没有课程
				if(courseNew.getClassId()!=null && courseNew.getClassId()!=""){
					c.setClassId(courseNew.getClassId()+","+courseClass.getId());
				}else{
					c.setClassId(courseClass.getId());
				}
				
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

					if (course.get(i).getId().equals(courseset.get(j).getId())) {

						break;

					}

				}
				if (j == courseset.size()) {
					
					String idlist=course.get(i).getClassId();
					String str=null;
					if(idlist.contains(courseClass.getId())){
						if(idlist.contains(",")){
							List<String> ids=Arrays.asList(idlist.split(","));
							List<String> arrList = new ArrayList<String>(ids);
							arrList.remove(courseClass.getId());
							str = StringUtils.join(arrList, ",");
						}
						
						////////修改课程的classId为多个的
						courseDao.updateClass(course.get(i).getId(),str);
					}

				}
			}
			for (Course courseinsert : courseset) {
				/*Course exist = courseDao.existCourse(courseinsert.getId());
				if (exist.getClassId() == null) {*/

					
					if(!courseinsert.getClassId().contains(courseClass.getId())){
						Course courseOn = new Course();
						courseOn.setId(courseinsert.getId());
						courseOn.setClassId(courseinsert.getClassId()+","+courseClass.getId());
						courseDao.updateCourese(courseOn);
					};
					
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
	public int selectCourseClassCount(String courseTypeName, String courseTypeSubclassName,String className) {
		// TODO Auto-generated method stub
		return courseClassDao.selectCourseClassCount(courseTypeName, courseTypeSubclassName,className);
	}

	@Override
	public List<CourseClass> listCourseClass(String id,String courseTypeName, String courseTypeSubclassName) {
		// TODO Auto-generated method stub
		return courseClassDao.selectCourseClass(id, 0, 0, courseTypeName, courseTypeSubclassName, null);
	}

}
