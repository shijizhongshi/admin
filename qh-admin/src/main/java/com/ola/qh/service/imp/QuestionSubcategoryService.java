package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.QuestionSubcategoryDao;
import com.ola.qh.entity.QuestionSubCategory;
import com.ola.qh.service.IQuestionSubcategoryService;

@Service
public class QuestionSubcategoryService implements IQuestionSubcategoryService{

	@Autowired
	private QuestionSubcategoryDao questionSubcategoryDao;

	@Override
	public List<QuestionSubCategory> selectQuestionSubCategory(String categoryId) {
		
		return questionSubcategoryDao.selectQuestionSubCategory(categoryId);
	}

	@Override
	public int insertQuestionSubCategory(QuestionSubCategory questionSubCategory) {
		
		return questionSubcategoryDao.insertQuestionSubCategory(questionSubCategory);
	}

	@Override
	public int updateQuestionSubCategory(QuestionSubCategory questionSubCategory) {
		
		return questionSubcategoryDao.updateQuestionSubCategory(questionSubCategory);
	}

	@Override
	public int deleteQuestionSubCategory(String id,String categoryId) {
		
		return questionSubcategoryDao.deleteQuestionSubCategory(id,categoryId);
	}
	
}
