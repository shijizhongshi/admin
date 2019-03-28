package com.ola.qh.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Doctors;
import com.ola.qh.service.IDoctorsService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/doctors")
public class DoctorsController {

	@Autowired
	private IDoctorsService doctorsService;
	
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<Doctors>> selectDoctors(@RequestParam(name="islimit",required=false)int islimit,@RequestParam(name="isvirtual",required=false)String isvirtual,
			@RequestParam(name="name",required=false)String name,@RequestParam(name="offices",required=false)String offices,
			@RequestParam(name="pageNo",required=true)int pageNo,@RequestParam(name="pageSize",required=true)int pageSize){
		
		Results<List<Doctors>> results=new Results<List<Doctors>>();
		List<Doctors> list=doctorsService.selectDoctors(islimit, name, offices, isvirtual, pageNo, pageSize);
		for (Doctors doctors : list) {
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			doctors.setShowtime(sf.format(doctors.getAddtime()));
		}
		
		int count=doctorsService.selectcount(islimit, name);
				
		results.setData(list);
		results.setCount(count);
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
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertDoctors(@RequestBody @Valid Doctors doctors,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		if(valid.hasErrors()){
			
			results.setStatus("1");
			return results;
			
		}
		doctors.setId(KeyGen.uuid());
		doctors.setAddtime(new Date());
		doctorsService.insertDoctors(doctors);
		
		results.setStatus("0");
		return results;
		
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteDoctors(@RequestParam(name="id",required=true)String id){
		
		Results<String> results=new Results<String>();
		
		doctorsService.deleteDoctors(id);
		
		results.setStatus("0");
		return results;
		
	}
	
	@RequestMapping(value="/selectcount",method=RequestMethod.GET)
	public Results<String> selectcount(@RequestParam(name="name",required=false)String name,
			@RequestParam(name="islimit",required=false)int islimit){
		
		Results<String> results=new Results<String>();
		
		int count=doctorsService.selectcount(islimit,name);
		
		results.setCount(count);
		results.setStatus("0");
		return results;
		
	}
}
