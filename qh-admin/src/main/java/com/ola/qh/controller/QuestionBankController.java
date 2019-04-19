package com.ola.qh.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.entity.CourseLiveCheck;
import com.ola.qh.entity.QuestionBank;
import com.ola.qh.service.IQuestionBankService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.weixin.handler.Requests;

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

	/**
	 * h5题库展示页面
	 * 
	 * @param page                   分页
	 * @param realname               姓名
	 * @param courseTypeSubclassName 专业名
	 * @param status                 用户属性
	 * @return
	 */
	@RequestMapping(value = "/questionbankList", method = RequestMethod.GET)
	public Results<List<QuestionBank>> questionbankList(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "realname", required = false) String realname,
			@RequestParam(value = "courseTypeSubclassName", required = false) String courseTypeSubclassName,
			@RequestParam(value = "status", required = false) String status) {
		Results<List<QuestionBank>> results = new Results<>();

		results = questionBankService.selectQuestionBankList(realname, courseTypeSubclassName, status, page);

		return results;
	}

	/**
	 * 直播验证数据
	 * 
	 * @param page                   分页
	 * @param mobile                 手机号
	 * @param roomId                 房间号
	 * @param courseTypeSubclassName 专业名
	 * @return
	 */
	@RequestMapping(value = "/liveVerifyList", method = RequestMethod.GET)
	public Results<List<CourseLiveCheck>> liveVerifyList(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "roomId", required = false) String roomId,
			@RequestParam(value = "courseTypeSubclassName", required = false) String courseTypeSubclassName) {
		Results<List<CourseLiveCheck>> results = new Results<>();

		results = questionBankService.selectLiveVerifyList(page, mobile, roomId, courseTypeSubclassName);

		return results;
	}

	/**
	 * 测试
	 * @throws IOException 
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Results<String> test(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "videoId", required = false) String videoId,
			@RequestParam(value = "date", required = true) String date,
			@RequestParam(value = "page", required = false) Integer page) throws IOException {
		Results<String> results = new Results<>();
		// 数据 传参
		Map<String, String> mapss = new HashMap<>();
		mapss.put("userid", userId);
		mapss.put("videoId", videoId);
		mapss.put("date", date);

		Results<byte[]> rbody = Requests.get(Patterns.test, null, mapss);
		System.out.println("rbody = " + rbody);
		// 类型转换 出问题？
		byte[] bytess = rbody.getData();
		//测试转换类型 
		//byte[]转换成char[]
		String aString = bytess.toString();
		String bString = new String(bytess);
		
		
		
		results.setData(bString);
		return results;
	}
}
