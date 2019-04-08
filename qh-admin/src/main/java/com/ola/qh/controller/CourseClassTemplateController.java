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

import com.ola.qh.entity.CourseClassTemplate;
import com.ola.qh.service.ICourseClassTemplateService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/classtemplate")
public class CourseClassTemplateController {

	@Autowired
	private ICourseClassTemplateService courseClassTemplateService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<CourseClassTemplate>> selectCourseClassTemplate(@RequestParam(name="id",required=false)String id,
			@RequestParam(name="templateName",required=false)String templateName,
			@RequestParam(name="page",required=true)int page){
		
		Results<List<CourseClassTemplate>> results=new Results<List<CourseClassTemplate>>();
		int pageSize=0;
		int pageNo=0;
		if(page!=0){
			pageSize=Patterns.pageSize;
			pageNo=(page-1)*pageSize;
		}
		
		
		List<CourseClassTemplate> list=courseClassTemplateService.selectCourseClassTemplate(id,templateName, pageNo, pageSize);
		int count=courseClassTemplateService.selectTemplateCount(templateName);
		
		results.setData(list);
		results.setStatus("0");
		results.setCount(count);
		return results;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Results<String> insertCourseClassTemplate(@RequestBody @Valid CourseClassTemplate courseClassTemplate,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		if (valid.hasErrors()) {
			results.setMessage("信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}
		
		courseClassTemplate.setAddtime(new Date());
		courseClassTemplate.setId(KeyGen.uuid());
		int insert=courseClassTemplateService.insertCourseClassTemplate(courseClassTemplate);
		
		if(insert==0){
			results.setStatus("1");
			return results;
			
		}
		results.setStatus("0");
		return results;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateCourseClassTemplate(@RequestBody CourseClassTemplate courseClassTemplate){
		
		Results<String> results=new Results<String>();
		
		int update=courseClassTemplateService.updateCourseClassTemplate(courseClassTemplate);
		
		if(update==0){
			results.setStatus("1");
			return results;
			
		}
		results.setStatus("0");
		return results;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteCourseClassTemplate(@RequestParam(name="id",required=true)String id){
		
		Results<String> results=new Results<String>();
		
		int delete=courseClassTemplateService.deleteCourseClassTemplate(id);
		
		if(delete==0){
			results.setStatus("1");
			return results;
			
		}
		results.setStatus("0");
		return results;
	}
}
