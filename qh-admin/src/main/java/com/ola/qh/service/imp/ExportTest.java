package com.ola.qh.service.imp;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ola.qh.entity.ExportExcel;
import com.ola.qh.entity.InputExportExcel;
@RestController
public class ExportTest {
	
	@ResponseBody
	public void exportTest(InputExportExcel inputExportExcel,
			HttpServletRequest  request,HttpServletResponse response){
		
		ExportExcel export=new ExportExcel();
		

		String fileName=export.fileName(inputExportExcel.getTypes());
		
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
			prsSch.exportExcel(inputExportExcel,inputExportExcel.getTypes(),request, response, jarr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


}
