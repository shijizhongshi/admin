package com.ola.qh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.SalesmanClient;
import com.ola.qh.service.ISalesmanClientService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/salesmanClient")
public class SalesmanClientController {

	@Autowired
	private ISalesmanClientService SalesmanClientService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<SalesmanClient>> ClientList(@RequestParam(name="salesmanId",required=false)String salesmanId,
			@RequestParam(name="mobile",required=false)String mobile,@RequestParam(name="pageNo",required=true)int pageNo,
			@RequestParam(name="pageSize",required=true)int pageSize){
		
		return SalesmanClientService.ClientList(salesmanId,mobile, pageNo, pageSize);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertClient(@RequestBody @Valid SalesmanClient salesmanClient,BindingResult valid){
		
		Results<String> results=new Results<String>();
		if(valid.hasErrors()){
			results.setStatus("1");
			return results;
		}
		return SalesmanClientService.insertClient(salesmanClient);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public Results<String> updateClient(@RequestParam(name="salesmanId",required=true)String salesmanId,
			@RequestParam(name="mobile",required=false)String mobile,@RequestParam(name="salesmanIdNew",required=false)String salesmanIdNew){
		
		return SalesmanClientService.updateClient(salesmanId, mobile, salesmanIdNew);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteClient(@RequestParam(name="salesmanId",required=false)String salesmanId,
			@RequestParam(name="id",required=false)String id){
		
		return SalesmanClientService.deleteClient(salesmanId, id);
	}
}
