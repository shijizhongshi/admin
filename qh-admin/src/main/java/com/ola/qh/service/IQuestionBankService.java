package com.ola.qh.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.entity.QuestionBank;
import com.ola.qh.util.Results;

public interface IQuestionBankService {

	public Results<String> importExcel(MultipartFile file) throws Exception;
	
	public Results<List<QuestionBank>> selectQuestionBank(String subId);
	
	
}
