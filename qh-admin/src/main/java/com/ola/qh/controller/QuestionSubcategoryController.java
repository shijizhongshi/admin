package com.ola.qh.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.ola.qh.entity.QuestionSubCategory;
import com.ola.qh.service.IQuestionSubcategoryService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/questionsubcategory")
public class QuestionSubcategoryController {

	@Autowired
	private IQuestionSubcategoryService questionSubCategoryService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<QuestionSubCategory>> selectQuestionSubCategory(@RequestParam(name="pageNo",required=true)int pageNo
			,@RequestParam(name="pageSize",required=true)int pageSize,@RequestParam(name="categoryId",required=true)String categoryId){
		
		return questionSubCategoryService.selectQuestionSubCategory(pageNo, pageSize, categoryId);
		
	}
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertQuestionSubCategory(@RequestBody @Valid QuestionSubCategory questionSubCategory,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		if(valid.hasErrors()){
			results.setMessage("信息填写不完整，请检查");
			results.setStatus("1");
			return results;
		}
		
		questionSubCategory.setId(KeyGen.uuid());
		if(questionSubCategory.getAddtime()==null){
			questionSubCategory.setAddtime(new Date());
		}
		
		int insert=questionSubCategoryService.insertQuestionSubCategory(questionSubCategory);
		
		
		if(insert==0){
			
			results.setStatus("1");
			return results;
		}
		
		results.setStatus("0");
		return results;
		
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateQuestionSubCategory(@RequestBody QuestionSubCategory questionSubCategory){
		
		Results<String> results=new Results<String>();
		
		if(questionSubCategory.getId()==null){
			
			results.setStatus("1");
			return results;
		}
		
		questionSubCategory.setUpdatetime(new Date());
		int update=questionSubCategoryService.updateQuestionSubCategory(questionSubCategory);
		
		if(update==0){
			
			results.setStatus("1");
			return results;
		}
		
		results.setStatus("0");
		return results;
		
	}
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteQuestionSubCategory(@RequestParam(name="id",required=false)String id,
			@RequestParam(name="categoryId",required=false)String categoryId){
		
		return questionSubCategoryService.deleteQuestionSubCategory(id, categoryId);
	}
}
