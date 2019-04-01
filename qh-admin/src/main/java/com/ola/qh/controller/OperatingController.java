package com.ola.qh.controller;

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

import com.ola.qh.entity.Operating;
import com.ola.qh.service.IOperatingService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/operating")
public class OperatingController {

	@Autowired
	private IOperatingService operatingService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<Operating>> operatingList(@RequestParam(name="userRoleCategory",required=false)String userRoleCategory,
			@RequestParam(name="userRoleUsername",required=false)String userRoleUsername,
			@RequestParam(name="operatingScope",required=false)String operatingScope,
			@RequestParam(name="operatingStatus",required=false)String operatingStatus,
			@RequestParam(name="pageNo",required=true)int pageNo,@RequestParam(name="pageSize",required=true)int pageSize){
		
			return operatingService.operatingList(userRoleCategory,userRoleUsername, operatingScope, operatingStatus, pageNo, pageSize);
		}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertOperating(@RequestBody @Valid Operating operating,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		if(valid.hasErrors()){
			results.setStatus("1");
			return results;
		}
		operating.setAddtime(new Date());
		operating.setId(KeyGen.uuid());
		int insert=operatingService.insertOperating(operating);
		
		if(insert<=0){
			
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
		}
	
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteOperating(@RequestParam(name="id",required=true)String id){
		
		Results<String> results=new Results<String>();
		
		int delete=operatingService.deleteOperating(id);
		
		if(delete<=0){
			
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
		}
	}
