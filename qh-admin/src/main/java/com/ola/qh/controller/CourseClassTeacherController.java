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

import com.ola.qh.entity.CourseClassTeacher;
import com.ola.qh.service.ICourseClassTeacherService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/classteacher")
public class CourseClassTeacherController {

	@Autowired
	private ICourseClassTeacherService courseClassTeacherService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<CourseClassTeacher>> selectCourseClassTeacher(@RequestParam(name="classId",required=false)String classId,
			@RequestParam(name="teacherId",required=false)String teacherId,@RequestParam(name="page",required=true)int page){
		
		Results<List<CourseClassTeacher>> results=new Results<List<CourseClassTeacher>>();
		
		int pageSize = Patterns.pageSize;
		int pageNo = (page - 1) * pageSize;
		
		List<CourseClassTeacher> list=courseClassTeacherService.selectCourseClassTeacher(classId, teacherId, pageNo, pageSize);
		
		if(list==null || list.size()==0){
			
			results.setMessage("信息不存在");
			results.setStatus("1");
			return results;
			
		}
		results.setData(list);
		results.setStatus("0");
		return results;
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertCourseClassTeacher(@RequestBody @Valid CourseClassTeacher courseClassTeacher,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		courseClassTeacher.setId(KeyGen.uuid());
		courseClassTeacher.setAddtime(new Date());
		int insert=courseClassTeacherService.insertCourseClassTeacher(courseClassTeacher);
		
		if(insert==0){
			
			results.setStatus("1");
			return results;
			
		}
		
		results.setStatus("0");
		return results;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteCourseClassTeacher(@RequestParam(name="classId",required=true)String classId,
			@RequestParam(name="teacherId",required=true)String teacherId){
		
		Results<String> results=new Results<String>();
		
		int delete=courseClassTeacherService.deleteCourseClassTeacher(classId, teacherId);
		
		if(delete==0){
			
			results.setStatus("1");
			return results;
			
		}
		results.setStatus("0");
		return results;
	}
}
