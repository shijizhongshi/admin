package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.QuestionCategory;

public interface IQuestionCategoryService {

	public List<QuestionCategory> selectCategory(String courseTypeSubclassName);
	
	public int updateCategory(QuestionCategory qc);
}
