package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.QuestionBankFeedback;
import com.ola.qh.service.IQuestionBankFeedbackService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/feedback")
public class QuestionBankFeedbackController {

	@Autowired
	private IQuestionBankFeedbackService questionBankFeedbackService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<QuestionBankFeedback>> feedbacklist(@RequestParam(name="pageNo",required=true)int pageNo,
			@RequestParam(name="pageSize",required=true)int pageSize,@RequestParam(name="status",required=true)int status,
			@RequestParam(name="nickname",required=false)String nickname,@RequestParam(name="courseTypeSubclassName",required=false)String courseTypeSubclassName,
			@RequestParam(name="name",required=false)String name){
		
		
				return questionBankFeedbackService.feedbackList(pageNo, pageSize, status, nickname, courseTypeSubclassName, name);
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public Results<String> updateFeedback(@RequestParam(name="id",required=true)String id,@RequestParam(name="status",required=true)int status){
		
		Results<String> results=new Results<String>();
		
		int update=questionBankFeedbackService.updateFeedback(id, status);
		if(update<=0){
			
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteFeedback(@RequestParam(name="id",required=true)String id){
		
		Results<String> results=new Results<String>();
		
		int delete=questionBankFeedbackService.deleteFeedback(id);
		
		if(delete<=0){
			
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}
}
