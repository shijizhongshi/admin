package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.entity.QuestionBank;
import com.ola.qh.service.IQuestionBankService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/questionbank")
public class QuestionBankController {

	@Autowired
	private IQuestionBankService questionBankService;

	/**
	 * 题库的上传
	 * <p>
	 * Title: improtExcel
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/improtExcel", method = RequestMethod.POST, consumes = "multipart/form-data")
	public Results<String> improtExcel(@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "subId") String subId) throws Exception {

		return questionBankService.importExcel(file, subId);
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<List<QuestionBank>> selectQuestionBank(@RequestParam(value = "subId", required = true) String subId,
			@RequestParam(value = "pageNo", required = true) int pageNo,
			@RequestParam(value = "pageSize", required = true) int pageSize) {

		return questionBankService.selectQuestionBank(subId, pageNo, pageSize);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Results<String> updateQuestionBank(@RequestBody QuestionBank questionBank) {

		return questionBankService.updateQuestionBank(questionBank);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Results<String> deleteQuestionBank(@RequestParam(value = "id", required = true) String id) {

		return questionBankService.deleteQuestionBank(id);
	}

	// H5题库管理展示页面
	@RequestMapping(value = "/questionbankList", method = RequestMethod.GET)
	public Results<List<QuestionBank>> questionbankList(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "realname", required = false) String realname,
			@RequestParam(value = "courseTypeSubclassName", required = false) String courseTypeSubclassName,
			@RequestParam(value = "status", required = false) String status) {
		Results<List<QuestionBank>> results = new Results<>();
		
		results = questionBankService.selectQuestionBankList(realname,courseTypeSubclassName,status,page);
		
		return results;
	}
}
