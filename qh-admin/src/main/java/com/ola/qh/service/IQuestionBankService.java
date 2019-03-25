package com.ola.qh.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.entity.QuestionBank;
import com.ola.qh.util.Results;

public interface IQuestionBankService {

	public Results<String> importExcel(MultipartFile file,String subId) throws Exception;
	
	public Results<List<QuestionBank>> selectQuestionBank(String subId,int pageNo,int pageSize);
	
	public Results<String> updateQuestionBank(QuestionBank questionBank);
	
	public Results<String> deleteQuestionBank(String id);
	
	
}
