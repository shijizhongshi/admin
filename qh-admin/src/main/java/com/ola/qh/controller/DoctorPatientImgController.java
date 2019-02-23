package com.ola.qh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.service.IDoctorPatientImgService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/patientimg")
public class DoctorPatientImgController {

	@Autowired
	private IDoctorPatientImgService doctorPatientImgService;
	
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> selectPatientImg(@RequestParam(name="patientId",required=false)String patientId, 
			@RequestParam(name="id",required=false)String id){
		
		Results<String> results=new Results<String>();
		
		if(patientId==null && id==null){
			results.setStatus("1");
			results.setMessage("信息不足");
			return results;
		}
		
		doctorPatientImgService.deletePatientImg(patientId, id);
		
		results.setStatus("0");
		return results;
		
		
		
	}
	
}
