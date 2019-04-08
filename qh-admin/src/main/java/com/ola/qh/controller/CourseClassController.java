package com.ola.qh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseClass;
import com.ola.qh.service.IBuyCourseService;
import com.ola.qh.service.ICourseClassService;
import com.ola.qh.service.ICourseService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/courseclass")
public class CourseClassController {

	@Autowired
	private ICourseClassService courseClassService;
	
	@Autowired
	private IBuyCourseService buyCourseService;
	
	@Autowired
	private ICourseService courseService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<CourseClass>> selectCourseClass(@RequestParam(name="id",required=false)String id,
			@RequestParam(name="page")int page,
			@RequestParam(name="courseTypeName")String courseTypeName,
			@RequestParam(name="courseTypeSubclassName")String courseTypeSubclassName,
			@RequestParam(name="className",required=false)String className){
		
		Results<List<CourseClass>> results=new Results<List<CourseClass>>();
		
		int pageSize=Patterns.pageSize;
		int pageNo=(page-1)*pageSize;
		
		List<CourseClass> list=courseClassService.selectCourseClass(id, pageNo, pageSize, courseTypeName, courseTypeSubclassName,className);
		results.setCount(courseClassService.selectCourseClassCount(courseTypeName, courseTypeSubclassName,className));
		results.setStatus("0");
		results.setData(list);
		return results;
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<CourseClass>> selectCourseClass(
			@RequestParam(name="courseTypeName")String courseTypeName,
			@RequestParam(name="courseTypeSubclassName")String courseTypeSubclassName,
			@RequestParam(name="userId")String userId
			){
		
		Results<List<CourseClass>> results=new Results<List<CourseClass>>();
		List<CourseClass> list=courseClassService.listCourseClass(null,courseTypeName, courseTypeSubclassName);
		for (CourseClass courseClass : list) {
			int count = buyCourseService.existOpenCourse(null, userId, courseClass.getId());
			if(count==0){
				
				
				int courseCount=0;
				Course c=new Course();
				c.setClassId(courseClass.getId());
				c.setPageNo(0);
				c.setPageSize(0);
				List<Course> courseList=courseService.courseList(c);
				for (Course course : courseList) {
					courseCount+=buyCourseService.existOpenCourse(course.getId(), userId,null);
				}
				if(courseCount==courseList.size() && courseCount!=0){
					courseClass.setIsbuy("1");
				}else{
					courseClass.setIsbuy("0");
				}
			}else{
				courseClass.setIsbuy("1");
			}
		}
		results.setStatus("0");
		results.setData(list);
		return results;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Results<String> insertCourseClass(@RequestBody @Valid CourseClass courseClass,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		if (valid.hasErrors()) {
			results.setMessage("信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}
		
		return courseClassService.insertCourseClass(courseClass);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateCourseClass(@RequestBody CourseClass courseClass){
		
		
		return courseClassService.updateCourseClass(courseClass);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> updateCourseClass(@RequestParam(name="id",required=true)String id){
		
		Results<String> results=new Results<String>();
		
		int delete=courseClassService.deleteCourseClass(id);
		
		if(delete==0){
			
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}
}
