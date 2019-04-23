package com.ola.qh.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ola.qh.entity.CourseLiveCheck;
import com.ola.qh.service.imp.PaperMgrExcel;
@RestController
@RequestMapping("api/excel")
public class ExportTest {
	
	@ResponseBody
	@RequestMapping(value="/exportTest",method=RequestMethod.POST)
	public void exportTest(@RequestBody List<CourseLiveCheck> courseLiveList,HttpServletRequest  request,HttpServletResponse response){
		String fileName = "导出";  
        response.reset();   
		response.setContentType("application/octet-stream;charset=utf-8");  
		try {
			response.setHeader("Content-Disposition", "attachment;filename="  
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
