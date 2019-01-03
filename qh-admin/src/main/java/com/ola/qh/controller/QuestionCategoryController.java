package com.ola.qh.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.QuestionCategory;
import com.ola.qh.service.IQuestionCategoryService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/questionCategory")
public class QuestionCategoryController {

	@Autowired
	private IQuestionCategoryService questionCategoryService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<QuestionCategory>> selectCategory(@RequestParam(name="courseTypeSubclassName",required=true)String courseTypeSubclassName){
		
		Results<List<QuestionCategory>> results=new Results<List<QuestionCategory>>();
		
		List<QuestionCategory> list=questionCategoryService.selectCategory(courseTypeSubclassName);
		
		if(list==null ||list.size()==0){
			
			results.setStatus("1");
			return results;
		}
		results.setData(list);
		results.setStatus("0");
		return results;
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateCategory(@RequestBody QuestionCategory qc){
		
		Results<String> results=new Results<String>();
		
		if(qc.getId()==null){
			results.setMessage("缺少用户id");
			results.setStatus("1");
			return results;
		}
		qc.setUdpatetime(new Date());
		int update=questionCategoryService.updateCategory(qc);
		
		if(update==0){
			
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}
}
