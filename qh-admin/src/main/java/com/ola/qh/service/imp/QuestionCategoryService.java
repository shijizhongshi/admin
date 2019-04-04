package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.QuestionCategoryDao;
import com.ola.qh.dao.QuestionSubcategoryDao;
import com.ola.qh.entity.QuestionCategory;
import com.ola.qh.service.IQuestionCategoryService;
import com.ola.qh.util.Results;

@Service
public class QuestionCategoryService implements IQuestionCategoryService{

	@Autowired
	private QuestionCategoryDao questionCategoryDao;

	@Autowired
	private QuestionSubcategoryDao questionSubcategoryDao;
	
	@Transactional
	@Override
	public Results<List<QuestionCategory>> selectCategory(int pageNo,int pageSize,String courseTypeSubclassName,String types) {
		
		
		Results<List<QuestionCategory>> results=new Results<List<QuestionCategory>>();
		
		try {
			
		List<QuestionCategory> list=questionCategoryDao.selectCategory(pageNo,pageSize,courseTypeSubclassName,types);
		
		int countcate=questionCategoryDao.countCategory(courseTypeSubclassName, types);
		for (QuestionCategory questionCategory : list) {
			
			int countsub=questionSubcategoryDao.countSubCategory(questionCategory.getId());
			questionCategory.setCount(countsub);
			
		}
		
		
		results.setCount(countcate);
		results.setData(list);
		results.setStatus("0");
		return results;
		
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
		
	}

	@Override
	public int updateCategory(QuestionCategory qc) {
		
		return questionCategoryDao.updateCategory(qc);
	}

	@Override
	public int insertCategory(QuestionCategory qc) {
		
		return questionCategoryDao.insertCategory(qc);
	}

	@Transactional
	@Override
	public Results<String> deleteCategory(String id) {
		
		Results<String> results=new Results<String>();
		
		try {
			
		questionCategoryDao.deleteCategory(id);
		
		questionSubcategoryDao.deleteQuestionSubCategory(null, id);
		
		
		results.setStatus("0");
		return results;
		
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}
	
	
}
