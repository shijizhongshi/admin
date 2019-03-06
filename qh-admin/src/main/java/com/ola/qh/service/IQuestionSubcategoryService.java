package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.QuestionSubCategory;
import com.ola.qh.util.Results;

public interface IQuestionSubcategoryService {

	public Results<List<QuestionSubCategory>> selectQuestionSubCategory(int pageNo,int pageSize,String categoryId);
	
	public int insertQuestionSubCategory(QuestionSubCategory questionSubCategory);
	
	public int updateQuestionSubCategory(QuestionSubCategory questionSubCategory);
	
	public int deleteQuestionSubCategory(String id,String categoryId);
}
