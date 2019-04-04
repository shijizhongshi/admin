package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ola.qh.util.Results;
import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.service.IDoctorPatientService;

@RestController
@RequestMapping(value="/api/patient")
public class DoctorPatientController {

	@Autowired
	private IDoctorPatientService doctorPatientService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<DoctorPatient>> selectDoctorPatient(@RequestParam(name="pageNo",required=true)int pageNo,
			@RequestParam(name="pageSize",required=true)int pageSize,
			@RequestParam(name="title" ,required=false)String title){
		
		Results<List<DoctorPatient>> results=new Results<List<DoctorPatient>>();
		
		List<DoctorPatient> list= doctorPatientService.selectDoctorPatient(pageNo, pageSize, title);
		
		int count=doctorPatientService.DoctorPatientCount(title);
			
		
		results.setStatus("0");
		results.setCount(count);
		results.setData(list);
		return results;
		
		
		
	}
	
	@RequestMapping(value="/single",method=RequestMethod.GET)
	public Results<DoctorPatient> singleDoctorPatient(@RequestParam(name="id" ,required=true)String id){
		
		return doctorPatientService.singleDoctorPatient( id);
		
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteDoctorPatient(@RequestParam(name="id",required=true)String id){
		
		return doctorPatientService.deleteDoctorPatient(id);
	}
	
}
