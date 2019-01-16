package com.ola.qh.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Doctors;
import com.ola.qh.service.IDoctorsService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/doctors")
public class DoctorsController {

	@Autowired
	private IDoctorsService doctorsService;
	
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<Doctors>> selectDoctors(@RequestParam(name="islimit",required=false)int islimit,@RequestParam(name="isvirtual",required=false)int isvirtual,
			@RequestParam(name="pageNo",required=true)int pageNo,@RequestParam(name="pageSize",required=true)int pageSize){
		
		Results<List<Doctors>> results=new Results<List<Doctors>>();
		List<Doctors> list=doctorsService.selectDoctors(islimit, isvirtual, pageNo, pageSize);
		if(list==null || list.size()==0){
			
			results.setMessage("没有医生信息");
			results.setStatus("1");
			return results;
		}
		
		results.setData(list);
		results.setStatus("0");
		return results;
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public Results<String> updateDoctors(@RequestParam(name="islimit",required=false)int islimit,
			@RequestParam(name="isrecommend",required=false)int isrecommend,
			@RequestParam(name="id",required=true)String id){
		
		Date updatetime=new Date();
		return doctorsService.updateDoctors(id, islimit, isrecommend, updatetime);
				
		
	}
}
