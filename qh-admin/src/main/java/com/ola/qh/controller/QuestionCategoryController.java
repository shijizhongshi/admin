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

import com.ola.qh.entity.QuestionCategory;
import com.ola.qh.service.IQuestionCategoryService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/questionCategory")
public class QuestionCategoryController {

	@Autowired
	private IQuestionCategoryService questionCategoryService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<QuestionCategory>> selectCategory(@RequestParam(name="pageNo",required=true)int pageNo,
			@RequestParam(name="pageSize",required=true)int pageSize,
			@RequestParam(name="courseTypeSubclassName",required=true)String courseTypeSubclassName,
			@RequestParam(name="types",required=true)String types){
		
		return questionCategoryService.selectCategory(pageNo,pageSize,courseTypeSubclassName,types);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertCategory(@RequestBody @Valid QuestionCategory qc,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		qc.setId(KeyGen.uuid());
		qc.setAddtime(new Date());
		int insert=questionCategoryService.insertCategory(qc);
		
		if(insert==0){
			
			results.setStatus("1");
			results.setMessage("添加失败");
			return results;
		}
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
		qc.setUpdatetime(new Date());
		int update=questionCategoryService.updateCategory(qc);
		
		if(update==0){
			
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}
	
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteCategory(@RequestParam(name="id",required=true)String id){
		
		return questionCategoryService.deleteCategory(id);
	}
}
