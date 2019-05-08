package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.entity.CourseLiveCheck;
import com.ola.qh.entity.PlayLog;
import com.ola.qh.entity.QuestionBank;
import com.ola.qh.entity.UserEnterLeaveActions;
import com.ola.qh.service.ICourseLineWhiteService;
import com.ola.qh.service.ICourseSubclassService;
import com.ola.qh.service.IQuestionBankService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.Results;


@RestController
@RequestMapping("/api/questionbank")
public class QuestionBankController {

	@Autowired
	private IQuestionBankService questionBankService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ICourseSubclassService courseSubclassService;
	@Autowired
	private ICourseLineWhiteService courseLineWhiteService;

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
	public Results<List<CourseLiveCheck>> liveVerifyList(
			@RequestParam(name = "fromdate", required = false) String fromdate,
			@RequestParam(name = "todate", required = false) String todate,
			@RequestParam(value = "pageNo", required = true) int pageNo,
			@RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "roomId", required = false) String roomId,
			@RequestParam(value = "courseTypeSubclassName", required = false) String courseTypeSubclassName) {
		Results<List<CourseLiveCheck>> results = new Results<>();

		results = questionBankService.selectLiveVerifyList(fromdate, todate, pageNo, pageSize, mobile, roomId,
				courseTypeSubclassName);

		return results;
	}

	/**
	 * cc点播 cc视频接口 三合一
	 * 
	 * @param videoId
	 * @param mobile
	 * @param date
	 * @param numPerPage
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/video", method = RequestMethod.GET)
	public Results<List<PlayLog>> test(@RequestParam(value = "videoId", required = false) String videoId,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "date", required = true) String date,
			@RequestParam(value = "numPerPage", required = false) String numPerPage,
			@RequestParam(value = "page", required = false) String page) {
		Results<List<PlayLog>> results = new Results<List<PlayLog>>();
		results = questionBankService.ccVideo(videoId,mobile,date,numPerPage,page);
		
		return results;
	}

	/**
	 * cc直播 获取观看视频接口
	 * 
	 * @param liveId
	 * @param pageindex
	 * @param pagenum
	 * @return
	 */
	@RequestMapping(value = "liveAccess", method = RequestMethod.GET)
	public Results<List<UserEnterLeaveActions>> liveAccess(
			@RequestParam(value = "notToEnter", required = false) String notToEnter,
			@RequestParam(value = "liveId", required = true) String liveId,
			@RequestParam(value = "pageindex") String pageindex, @RequestParam(value = "pagenum") String pagenum) {
		Results<List<UserEnterLeaveActions>> results = new Results<List<UserEnterLeaveActions>>();
		
		results = questionBankService.liveAccess(notToEnter,liveId,pagenum,pageindex);
		
		return results;
	}

}
