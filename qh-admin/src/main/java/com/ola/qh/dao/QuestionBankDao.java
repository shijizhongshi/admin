package com.ola.qh.dao;

import com.ola.qh.entity.QuestionAnswer;
import com.ola.qh.entity.QuestionBank;

public interface QuestionBankDao {

	public int insertQuestionBank(QuestionBank qb);
	
	public int insertQuestionAnswer(QuestionAnswer qa);
	
	public int insertQuestionUnit(QuestionBank qb);
}
