package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
* @ClassName: CourseController  
* @Description: 课程类别和课程集合的列表
* @author guoyuxue  
* @date 2018年11月19日  
*
 */

import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;
import com.ola.qh.service.ICourseService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	private ICourseService courseService;

	/**
	 * 大类别的集合
	 * <p>Title: listCourseType</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	@RequestMapping("/courseTypeList")
	public Results<List<CourseType>> listCourseType() {

		Results<List<CourseType>> result = new Results<List<CourseType>>();
		List<CourseType> list = courseService.courseTypeList();
		result.setData(list);
		result.setStatus("0");
		return result;

	}

	/**
	 * 子类别的集合
	 * <p>Title: listCourseTypeSubclass</p>  
	 * <p>Description: </p>  
	 * @param courseTypeId
	 * @return
	 */
	@RequestMapping("/courseTypeSubclassList")
	public Results<List<CourseTypeSubclass>> listCourseTypeSubclass(
			@RequestParam(name = "courseTypeId", required = true) String courseTypeId) {

		Results<List<CourseTypeSubclass>> result = new Results<List<CourseTypeSubclass>>();
		List<CourseTypeSubclass> list = courseService.courseTypeSubclassList(courseTypeId);
		result.setData(list);
		result.setStatus("0");
		return result;

	}

	/**
	 * 
	 * <p>
	 * Title: listCourse
	 * </p>
	 * <p>
	 * 多条件的查询,是否精品课程,按照大类别.小类别查询课程
	 * </p>
	 * 首页传精品课程page=1 其他的情况则是page是变量
	 * 
	 * @param page
	 * @param courseTypeName
	 * @param courseTypeSubclassName
	 * @param courseExcellent
	 * @return
	 */
	@RequestMapping("/courseList")
	public Results<List<Course>> listCourse(
			@RequestParam(name = "page", required = true) int page,
			@RequestParam(name = "courseTypeName", required = false) String courseTypeName,
			@RequestParam(name = "courseTypeSubclassName", required = false) String courseTypeSubclassName,
			@RequestParam(name = "courseExcellent", required = false,defaultValue="0") int courseExcellent) {

		Results<List<Course>> result = new Results<List<Course>>();

		Course course = new Course();
		course.setCourseTypeName(courseTypeName);
		course.setCourseTypeSubclassName(courseTypeSubclassName);
		course.setCourseExcellent(courseExcellent);
		int pageNo = (page - 1) * Patterns.pageSize;
		course.setPageNo(pageNo);
		course.setPageSize(Patterns.pageSize);
		result.setData(courseService.courseList(course));
		result.setStatus("0");
		return result;
	}
	
	

}
