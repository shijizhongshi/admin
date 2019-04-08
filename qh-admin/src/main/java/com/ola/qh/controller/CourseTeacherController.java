package com.ola.qh.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.CourseTeacher;
import com.ola.qh.service.ICourseTeacherService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/courseteacher")
public class CourseTeacherController {

	@Autowired
	private ICourseTeacherService courseTeacherService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<CourseTeacher>> selectCourseSection(
			@RequestParam(name="page",required=true)int page,
			@RequestParam(name="courseTypeName",required=false)String courseTypeName,
			@RequestParam(name="courseTypeSubclassName",required=false)String courseTypeSubclassName,
			@RequestParam(name="teacherName",required=false)String teacherName){
		
		Results<List<CourseTeacher>> results=new Results<List<CourseTeacher>>();
		int pageSize = 0;
		int pageNo =0;
		if(page!=0){
			pageSize = Patterns.pageSize;
		    pageNo = (page - 1) * pageSize;
		}
		
		
		List<CourseTeacher> list=courseTeacherService.selectCourseTeacher(pageNo, pageSize,courseTypeName,courseTypeSubclassName,teacherName);
		
		results.setCount(courseTeacherService.selectCourseTeacherCount(courseTypeName, courseTypeSubclassName, teacherName));
		results.setStatus("0");
		results.setData(list);
		return results;
		
		
	}
	
	@RequestMapping(value="/selectdetails",method=RequestMethod.GET)
	public Results<CourseTeacher> selectCourseTeacherDetails(@RequestParam(name="id",required=true)String id){
		
		Results<CourseTeacher> results=new Results<CourseTeacher>();
		
		CourseTeacher courseTeacher=courseTeacherService.selectCourseTeacherDetails(id);
		
		if(courseTeacher==null){
			
			results.setStatus("1");
			results.setMessage("当前没有教师信息");
			return results;
		}
		results.setStatus("0");
		results.setData(courseTeacher);
		return results;
		
		
	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Results<String> insertCourseSection(@RequestBody @Valid CourseTeacher courseTeacher,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		if (valid.hasErrors() || courseTeacher.getTypename().size()==0) {
			results.setMessage("信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}
		return courseTeacherService.insertCourseTeacher(courseTeacher);
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateCourseSection(@RequestBody CourseTeacher courseTeacher){
		
		Results<String> results=new Results<String>();
		
		if(courseTeacher.getId()==null || "".equals(courseTeacher.getId())){
			
			results.setStatus("1");
			results.setMessage("缺少id");
			return results;
		}
		
		
		
		
		
		courseTeacher.setUpdatetime(new Date());
		int update=courseTeacherService.updateCourseTeacher(courseTeacher);
		
		if(update==0){
			
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
		
	}
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String>deleteCourseSection(@RequestParam(name="id",required=true)String id){
		
		Results<String> results=new Results<String>();
		
		int delete=courseTeacherService.deleteCourseTeacher(id);
		
		if(delete==0){
			
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
		
		
	}
	
	@RequestMapping(value="/selectname",method=RequestMethod.GET)
	public Results<List<CourseTeacher>>selectName(@RequestParam(name="id",required=true)String id){
		
		Results<List<CourseTeacher>> results=new Results<List<CourseTeacher>>();
		
		List<CourseTeacher> selectName=courseTeacherService.selectName(id);
		
		if(selectName==null){
			
			results.setStatus("1");
			results.setMessage("教师信息不存在");
			return results;
		}
		results.setStatus("0");
		results.setData(selectName);
		return results;
		
		
	}
	
	
}
