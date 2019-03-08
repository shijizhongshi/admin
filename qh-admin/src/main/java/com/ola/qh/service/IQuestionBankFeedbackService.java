package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.QuestionBankFeedback;
import com.ola.qh.util.Results;

public interface IQuestionBankFeedbackService {

	public Results<List<QuestionBankFeedback>> feedbackList(int pageNo,int pageSize,int status);
	
	public int updateFeedback(String id,int status);
	
	public int deleteFeedback(String id);
}
