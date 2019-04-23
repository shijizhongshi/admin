package com.ola.qh.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ola.qh.dao.QuestionBankDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.entity.CourseLiveCheck;
import com.ola.qh.service.imp.PaperMgrExcel;
@RestController
@RequestMapping("/api/excel")
public class ExportTest {
	
	@Autowired
	private QuestionBankDao questionBankDao;
	@Autowired
	private UserDao userDao;
	
	@ResponseBody
	@RequestMapping(value="/exportTest",method=RequestMethod.GET)
	public void exportTest(@RequestParam(name = "fromdate", required = false) String fromdate,
			@RequestParam(name = "todate", required = false) String todate,
			@RequestParam(value = "pageNo", required = true) int pageNo,
			@RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "roomId", required = false) String roomId,
			@RequestParam(value = "courseTypeSubclassName", required = false) String courseTypeSubclassName,
			HttpServletRequest  request,HttpServletResponse response){
		
		List<CourseLiveCheck> courseLiveList = questionBankDao.liveVerifyList(fromdate,todate,pageNo, pageSize, mobile, roomId,
				courseTypeSubclassName);
		for (CourseLiveCheck courseLiveCheck : courseLiveList) {
			// 根据mobile查询user 有数据说明是注册用户 赋值展示
			Integer count = userDao.selectUserCount(courseLiveCheck.getMobile(), null, null);
			if (count == 0) {
				courseLiveCheck.setIsRegister(0);
			} else {
				courseLiveCheck.setIsRegister(1);
			}
		}
				;
		String fileName = "直播验证表格";  
        response.reset();   
		response.setContentType("application/octet-stream;charset=utf-8");  
		try {
			response.addHeader("Content-Disposition", "attachment;filename="  
			        + new String(fileName.getBytes(),"iso-8859-1") + ".xls");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
         
         
		PaperMgrExcel prsSch = new PaperMgrExcel();
		JSONObject param = new JSONObject();
		param.put("paperId", 111);
		param.put("paperName", "ceshi1");
		param.put("xkId", 1);
		JSONArray jarr = null;
		try {
			prsSch.exportExcel(courseLiveList,request, response, jarr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


}
