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

import com.ola.qh.entity.SalesmanSecond;
import com.ola.qh.service.ISalesmanSecondService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/salesmanSecond")
public class SalesmanSecondController {

	@Autowired
	private ISalesmanSecondService SalesmanSecondService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<SalesmanSecond>> SecondList(@RequestParam(name="salesmanId",required=false)String salesmanId,
			@RequestParam(name="mobile",required=false)String mobile,@RequestParam(name="pageNo",required=true)int pageNo,
			@RequestParam(name="pageSize",required=true)int pageSize){
		
		return SalesmanSecondService.SecondList(salesmanId,mobile, pageNo, pageSize);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertSecond(@RequestBody @Valid SalesmanSecond salesmanSecond,BindingResult valid){
		
		Results<String> results=new Results<String>();
		if(valid.hasErrors()){
			results.setStatus("1");
			return results;
		}
		return SalesmanSecondService.insertSecond(salesmanSecond);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public Results<String> updateClient(@RequestParam(name="salesmanId",required=true)String salesmanId,
			@RequestParam(name="mobile",required=false)String mobile,@RequestParam(name="salesmanIdNew",required=false)String salesmanIdNew){
		
		return SalesmanSecondService.updateSecond(salesmanId, mobile, salesmanIdNew);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteSecond(@RequestParam(name="salesmanId",required=false)String salesmanId,
			@RequestParam(name="id",required=false)String id){
		
		return SalesmanSecondService.deleteSecond(salesmanId, id);
	}
}
