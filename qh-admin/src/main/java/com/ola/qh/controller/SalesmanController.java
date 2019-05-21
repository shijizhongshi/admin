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

import com.ola.qh.entity.Salesman;
import com.ola.qh.service.ISalesmanService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/salesman")
public class SalesmanController {

	@Autowired
	private ISalesmanService salesmanService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<Salesman>> SalesmanList(@RequestParam(name="name",required=false)String name,
			@RequestParam(name="mobile",required=false)String mobile,@RequestParam(name="address",required=false)String address,
			@RequestParam(name="pageNo",required=true)int pageNo,@RequestParam(name="pageSize",required=true)int pageSize){
		
		return salesmanService.SalesmanList(name, mobile, address, pageNo, pageSize);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertSalesman(@RequestBody @Valid Salesman salesman,BindingResult valid){
		
		Results<String> results=new Results<String>();
		if(valid.hasErrors()){
			results.setStatus("1");
			results.setMessage("信息不足");
			return results;
		}
		return salesmanService.insertSalesman(salesman);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateSalesman(@RequestBody Salesman salesman){
		
		return salesmanService.updateSalesman(salesman);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteSalesman(@RequestParam(name="id",required=true)String id,
			@RequestParam(name="types",required=true)int types){
		
		return salesmanService.deleteSalesman(id,types);
	}
}
