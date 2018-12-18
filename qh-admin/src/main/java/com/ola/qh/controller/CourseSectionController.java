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

import com.ola.qh.entity.CourseSection;
import com.ola.qh.service.imp.CourseSectionService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/coursesection")
public class CourseSectionController {
	
	@Autowired
	private CourseSectionService courseSectionService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<CourseSection>> selectCourseSection(@RequestParam(name="page",required=true)int page){
		
		Results<List<CourseSection>> results=new Results<List<CourseSection>>();
		
		int pageSize = Patterns.pageSize;
		int pageNo = (page - 1) * pageSize;
		
		List<CourseSection> list=courseSectionService.selectCourseSection(pageNo, pageSize);
		
		if(list==null || list.size()==0){
			
			results.setStatus("1");
			results.setMessage("当前没有试听课程");
			return results;
		}
		results.setStatus("0");
		results.setData(list);
		return results;
		
		
	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Results<String> insertCourseSection(@RequestBody @Valid CourseSection courseSection,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		courseSection.setId(KeyGen.uuid());
		courseSection.setAddtime(new Date());
		int insert=courseSectionService.insertCourseSection(courseSection);
		
		if(insert==0){
			
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateCourseSection(@RequestBody CourseSection courseSection){
		
		Results<String> results=new Results<String>();
		
		if(courseSection.getId()==null || "".equals(courseSection.getId())){
			
			results.setStatus("1");
			results.setMessage("缺少id");
			return results;
		}
		courseSection.setUpdatetime(new Date());
		int update=courseSectionService.updateCourseSection(courseSection);
		
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
		
		int delete=courseSectionService.deleteCourseSection(id);
		
		if(delete==0){
			
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
		
		
	}
}
