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

import com.ola.qh.entity.CourseClass;
import com.ola.qh.service.ICourseClassService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/courseclass")
public class CourseClassController {

	@Autowired
	private ICourseClassService courseClassService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<CourseClass>> selectCourseClass(@RequestParam(name="id",required=false)String id,
			@RequestParam(name="page")int page){
		
		Results<List<CourseClass>> results=new Results<List<CourseClass>>();
		
		int pageSize=Patterns.pageSize;
		int pageNo=(page-1)*pageSize;
		
		List<CourseClass> list=courseClassService.selectCourseClass(id, pageNo, pageSize);
		
		if(list==null || list.size()==0){
			
			results.setStatus("1");
			results.setMessage("课程信息不存在");
			return results;
		}
		results.setStatus("0");
		results.setData(list);
		return results;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Results<String> insertCourseClass(@RequestBody @Valid CourseClass courseClass,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		courseClass.setAddtime(new Date());
		courseClass.setId(KeyGen.uuid());
		int insert=courseClassService.insertCourseClass(courseClass);
		
		if(insert==0){
			
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateCourseClass(@RequestBody CourseClass courseClass){
		
		Results<String> results=new Results<String>();
		
		courseClass.setUpdatetime(new Date());
		int update=courseClassService.updateCourseClass(courseClass);
		
		if(update==0){
			
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
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
