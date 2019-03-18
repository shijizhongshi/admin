package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.QuestionAnswer;
import com.ola.qh.entity.QuestionBank;
import com.ola.qh.entity.QuestionUnit;

public interface QuestionBankDao {

	public int insertQuestionBank(QuestionBank qb);
	
	public int insertQuestionAnswer(QuestionAnswer qa);
	
	public int insertQuestionUnit(QuestionBank qb);
	
	public List<QuestionBank> selectQuestionBank(@Param("subId")String subId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public QuestionBank singleQuestionBank(@Param("id")String id);
	
	public int countQuestionBank(@Param("subId")String subId);
	
	public List<QuestionAnswer> selectQuestionAnswer(String bankUnitId);
	
	public List<QuestionUnit> selectQuestionUnit(String bankId);
	
	public int deleteQuestionBank(String id);
	
	public int deleteQuestionAnswer(String bankUnitId);
	
	public int deleteQuestionUnit(String bankId);
	
	public int updateQuestionBank(QuestionBank questionBank);
	
	public int updateQuestionAnswer(QuestionAnswer questionAnswer);
	
	public int updateQuestionUnit(QuestionUnit questionUnit);
}
