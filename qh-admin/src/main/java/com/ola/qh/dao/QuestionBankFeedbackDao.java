package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.QuestionBankFeedback;

public interface QuestionBankFeedbackDao {

	public List<QuestionBankFeedback> feedbackList(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("status")int status);
	
	public int feedbackCount(@Param("status")int status);
	
	public int updateFeedback(@Param("id")String id,@Param("status")int status);
	
	public int deleteFeedback(@Param("id")String id);
	
	
}
