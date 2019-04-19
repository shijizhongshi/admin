package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.QuestionBankDao;
import com.ola.qh.dao.QuestionSubcategoryDao;
import com.ola.qh.entity.QuestionSubCategory;
import com.ola.qh.service.IQuestionSubcategoryService;
import com.ola.qh.util.Results;

@Service
public class QuestionSubcategoryService implements IQuestionSubcategoryService{

	@Autowired
	private QuestionSubcategoryDao questionSubcategoryDao;
	
	@Autowired
	private QuestionBankDao questionBankDao;

	@Transactional
	@Override
	public Results<List<QuestionSubCategory>> selectQuestionSubCategory(int pageNo,int pageSize,String categoryId) {
		
		Results<List<QuestionSubCategory>> results=new Results<List<QuestionSubCategory>>();
		try {
			
		List<QuestionSubCategory> list=questionSubcategoryDao.selectQuestionSubCategory(pageNo, pageSize, categoryId);
		
		int count=questionSubcategoryDao.countSubCategory(categoryId);
				
		for (QuestionSubCategory questionSubCategory : list) {
			
			int subcount=questionBankDao.countQuestionBank(questionSubCategory.getId());
			questionSubCategory.setCount(subcount);
			
		}
		
		results.setData(list);
		results.setCount(count);
		results.setStatus("0");
		return results;
		
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
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
