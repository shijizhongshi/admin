package com.ola.qh.service;

import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.util.Results;

public interface IQuestionBankService {

	public Results<String> importExcel(MultipartFile file) throws Exception;
}
