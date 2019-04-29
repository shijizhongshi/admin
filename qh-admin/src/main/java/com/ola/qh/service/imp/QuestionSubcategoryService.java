package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.QuestionBankDao;
import com.ola.qh.dao.QuestionSubcategoryDao;
import com.ola.qh.entity.QuestionBank;
import com.ola.qh.entity.QuestionSubCategory;
import com.ola.qh.entity.QuestionUnit;
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

	@Transactional
	@Override
	public Results<String> deleteQuestionSubCategory(String id,String categoryId) {
		
		Results<String> results=new Results<String>();
		
		try {
			
		if(categoryId==null){
		List<QuestionBank> listbank = questionBankDao.selectQuestionBank(id, 0, 0);
		
		for (QuestionBank questionBank : listbank) {
			questionBankDao.deleteQuestionBank(questionBank.getId());
			questionBankDao.deleteQuestionAnswer(questionBank.getId());
			List<QuestionUnit> listunit = questionBankDao.selectQuestionUnit(questionBank.getId());

			for (QuestionUnit questionUnit : listunit) {

				questionBankDao.deleteQuestionUnit(questionUnit.getId());
				questionBankDao.deleteQuestionAnswer(questionUnit.getId());
			}
		}
		}else{
				List<QuestionSubCategory> list=questionSubcategoryDao.selectQuestionSubCategory(0, 0, categoryId);
			
				for (QuestionSubCategory questionSubCategory : list) {
				
			
				List<QuestionBank> listbank = questionBankDao.selectQuestionBank(questionSubCategory.getId(), 0, 0);
			
				for (QuestionBank questionBank : listbank) {
					questionBankDao.deleteQuestionBank(questionBank.getId());
					questionBankDao.deleteQuestionAnswer(questionBank.getId());
					List<QuestionUnit> listunit = questionBankDao.selectQuestionUnit(questionBank.getId());

					for (QuestionUnit questionUnit : listunit) {

						questionBankDao.deleteQuestionUnit(questionUnit.getId());
						questionBankDao.deleteQuestionAnswer(questionUnit.getId());
					}
				}
			}
		}
		questionSubcategoryDao.deleteQuestionSubCategory(id,categoryId);
		
		results.setStatus("0");
		return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}
	
}
