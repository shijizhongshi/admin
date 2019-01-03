package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.QuestionCategoryDao;
import com.ola.qh.entity.QuestionCategory;
import com.ola.qh.service.IQuestionCategoryService;

@Service
public class QuestionCategoryService implements IQuestionCategoryService{

	@Autowired
	private QuestionCategoryDao questionCategoryDao;

	@Override
	public List<QuestionCategory> selectCategory(String courseTypeSubclassName) {
		
		return questionCategoryDao.selectCategory(courseTypeSubclassName);
	}

	@Override
	public int updateCategory(QuestionCategory qc) {
		
		return questionCategoryDao.updateCategory(qc);
	}
	
	
}
