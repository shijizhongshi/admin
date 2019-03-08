package com.ola.qh.service.imp;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.QuestionBankFeedbackDao;
import com.ola.qh.entity.QuestionBankFeedback;
import com.ola.qh.service.IQuestionBankFeedbackService;
import com.ola.qh.util.Results;

@Service
public class QuestionBankFeedbackService implements IQuestionBankFeedbackService{

	@Autowired
	private QuestionBankFeedbackDao questionBankFeedbackDao;

	
	@Transactional
	@Override
	public Results<List<QuestionBankFeedback>> feedbackList(int pageNo,int pageSize,int status) {
		
		Results<List<QuestionBankFeedback>> results=new Results<List<QuestionBankFeedback>>();
		
		try {
			
		
		List<QuestionBankFeedback> list=questionBankFeedbackDao.feedbackList(pageNo, pageSize, status);
		
		for (QuestionBankFeedback questionBankFeedback : list) {
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			questionBankFeedback.setShowtime(sf.format(questionBankFeedback.getAddtime()));
		}
		
		int count=questionBankFeedbackDao.feedbackCount(status);
		
		results.setCount(count);
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
	public int updateFeedback(String id,int status) {
		
		return questionBankFeedbackDao.updateFeedback(id,status);
	}

	@Override
	public int deleteFeedback(String id) {
		
		return questionBankFeedbackDao.deleteFeedback(id);
	}
	
	

}
