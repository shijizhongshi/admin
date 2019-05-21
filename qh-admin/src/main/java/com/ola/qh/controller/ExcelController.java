package com.ola.qh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.dao.QuestionBankDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.entity.CourseLiveCheck;
import com.ola.qh.entity.InputExportExcel;
import com.ola.qh.entity.PlayLog;
import com.ola.qh.entity.QuestionBank;
import com.ola.qh.entity.UserEnterLeaveActions;
import com.ola.qh.service.IQuestionBankService;
import com.ola.qh.service.imp.ExportTest;
import com.ola.qh.util.Results;
@RestController
public class ExcelController {
	
	@Autowired
	private QuestionBankDao questionBankDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private IQuestionBankService questionBankService;
/////////////////////////直播验证数据的集合
	@RequestMapping("/api/listLiveCheck")
	public  void listLiveCheck(@RequestParam(name="fromdate",required=false)String fromdate, @RequestParam(name="todate",required=false)String todate,
			@RequestParam(name="mobile",required=false)String mobile, @RequestParam(name="roomId",required=false)String roomId, 
			@RequestParam(name="courseTypeSubclassName",required=false)String courseTypeSubclassName,
			@RequestParam(name="types",required=true)int types,HttpServletRequest  request,HttpServletResponse response){
		
		ExportTest exportTest=new ExportTest();
		InputExportExcel inputExportExcel=new InputExportExcel();
		
		List<CourseLiveCheck> list = questionBankDao.liveVerifyList(fromdate, todate, 0, 0, mobile, roomId,
				courseTypeSubclassName);
		for (CourseLiveCheck courseLiveCheck : list) {
			// 根据mobile查询user 有数据说明是注册用户 赋值展示
			Integer count = userDao.selectUserCount(courseLiveCheck.getMobile(), null, null);
			if (count == 0) {
				courseLiveCheck.setIsRegister(0);
			} else {
				courseLiveCheck.setIsRegister(1);
			}
		}
		inputExportExcel.setListLiveCheck(list);
		inputExportExcel.setTypes(types);
		
		exportTest.exportTest(inputExportExcel, request, response);
	};
/////////////////////////H5题库集合
	@RequestMapping("/api/questionbankList")
	public  void questionbankList(
			@RequestParam(value = "realname", required = false) String realname,
			@RequestParam(value = "courseTypeSubclassName", required = false) String courseTypeSubclassName,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(name="types",required=true)int types,HttpServletRequest  request,HttpServletResponse response){
		
		ExportTest exportTest=new ExportTest();
		InputExportExcel inputExportExcel=new InputExportExcel();
		
		List<QuestionBank> list = questionBankDao.questionBankList(realname, courseTypeSubclassName, status, 0,
				0);
		inputExportExcel.setQuestionBank(list);
		inputExportExcel.setTypes(types);
		
		exportTest.exportTest(inputExportExcel, request, response);
	};
/////////////////////////学习记录集合
	@RequestMapping("api/video")
	public void test(@RequestParam(value = "videoId", required = false) String videoId,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "date", required = true) String date,
			@RequestParam(value = "numPerPage", required = false) int numPerPage,
			@RequestParam(value = "total", required = true) int total,
			@RequestParam(value = "types", required = true) int types,HttpServletRequest  request,HttpServletResponse response) {
		if(videoId.equals("null")){
			videoId=null;
		}
		if(mobile.equals("null")){
			mobile=null;
		}
		ExportTest exportTest=new ExportTest();
		InputExportExcel inputExportExcel=new InputExportExcel();
		if(total>0){
			int pageindex=total/numPerPage;
			Results<List<PlayLog>> results =new Results<List<PlayLog>>();
			List<PlayLog> playLog=new ArrayList<PlayLog>();
			for (int i = 0; i < pageindex+1; i++) {
				
				List<PlayLog> PlayLogAdd=new ArrayList<PlayLog>();
				String pageindexs=String.valueOf(i+1);
				String numPerPages=String.valueOf(numPerPage);
				results =questionBankService.ccVideo(videoId, mobile, date, numPerPages, pageindexs);
				PlayLogAdd=results.getData();
				playLog.addAll(PlayLogAdd);
			}
			inputExportExcel.setPlayLog(playLog);
			inputExportExcel.setTypes(types);
		}
		exportTest.exportTest(inputExportExcel, request, response);
	}
/////////////////////////直播访问记录
	@RequestMapping("/api/liveAccess")
	public void liveAccess(
			@RequestParam(value = "notToEnter", required = false) String notToEnter,
			@RequestParam(value = "liveId", required = true) String liveId,
			@RequestParam(value = "pagenum") int pagenum,
			@RequestParam(value = "types", required = true) int types,
			@RequestParam(value = "total", required = true) int total,HttpServletRequest  request,HttpServletResponse response) {
		
		ExportTest exportTest=new ExportTest();
		InputExportExcel inputExportExcel=new InputExportExcel();
		
		if(total>0){
			int pageindex=total/pagenum;
			Results<List<UserEnterLeaveActions>> results =new Results<List<UserEnterLeaveActions>>();
			List<UserEnterLeaveActions> userEnterLeaveActions=new ArrayList<UserEnterLeaveActions>();
			for (int i = 0; i < pageindex+1; i++) {
				
				List<UserEnterLeaveActions> userEnterLeaveActionsAdd=new ArrayList<UserEnterLeaveActions>();
				String pageindexs=String.valueOf(i+1);
				String pagenums=String.valueOf(pagenum);
				results =questionBankService.liveAccess(notToEnter, liveId, pagenums, pageindexs);
				userEnterLeaveActionsAdd=results.getData();
				userEnterLeaveActions.addAll(userEnterLeaveActionsAdd);
			}
			inputExportExcel.setUserEnterLeaveActions(userEnterLeaveActions);
			inputExportExcel.setTypes(types);
		}
		
		exportTest.exportTest(inputExportExcel, request, response);
	}
}
