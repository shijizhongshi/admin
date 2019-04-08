package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ola.qh.dao.QuestionBankDao;
import com.ola.qh.dao.QuestionBankFeedbackDao;
import com.ola.qh.entity.QuestionAnswer;
import com.ola.qh.entity.QuestionBank;
import com.ola.qh.entity.QuestionBankFeedback;
import com.ola.qh.entity.QuestionUnit;
import com.ola.qh.service.IQuestionBankFeedbackService;
import com.ola.qh.util.Results;

@Service
public class QuestionBankFeedbackService implements IQuestionBankFeedbackService{

	@Autowired
	private QuestionBankFeedbackDao questionBankFeedbackDao;

	@Autowired
	private QuestionBankDao questionBankDao;
	
	@Transactional
	@Override
	public Results<List<QuestionBankFeedback>> feedbackList(int pageNo,int pageSize,int status,String nickname,String courseTypeSubclassName,String name) {
		
		Results<List<QuestionBankFeedback>> results=new Results<List<QuestionBankFeedback>>();
		
		//try {
			
		
		List<QuestionBankFeedback> list=questionBankFeedbackDao.feedbackList(pageNo, pageSize, status,nickname,courseTypeSubclassName, name);
		
		for (QuestionBankFeedback questionBankFeedback : list) {
			QuestionBank bank=questionBankDao.singleQuestionBank(questionBankFeedback.getBankId());
			
			List<QuestionAnswer> listanswer=questionBankDao.selectQuestionAnswer(questionBankFeedback.getBankId());
				
			bank.setAnswer(listanswer);
				
			List<QuestionUnit> listunit=questionBankDao.selectQuestionUnit(questionBankFeedback.getBankId());
				
			for (QuestionUnit questionUnit : listunit) {
					
				List<QuestionAnswer> listanswerunit=questionBankDao.selectQuestionAnswer(questionUnit.getId());
					
				questionUnit.setUnitAnswer(listanswerunit);
			}
			bank.setUnit(listunit);
			
			questionBankFeedback.setBank(bank);
				
		}
		
		int count=questionBankFeedbackDao.feedbackCount(status);
		
		results.setCount(count);
		results.setData(list);
		results.setStatus("0");
		return results;
		
		/*} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}*/
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
