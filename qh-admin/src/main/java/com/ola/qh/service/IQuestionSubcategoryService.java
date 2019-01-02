package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.QuestionSubCategory;

public interface IQuestionSubcategoryService {

	public List<QuestionSubCategory> selectQuestionSubCategory(String categoryId);
	
	public int insertQuestionSubCategory(QuestionSubCategory questionSubCategory);
	
	public int updateQuestionSubCategory(QuestionSubCategory questionSubCategory);
	
	public int deleteQuestionSubCategory(String id,String categoryId);
}
