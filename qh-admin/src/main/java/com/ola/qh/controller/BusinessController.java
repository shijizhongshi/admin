package com.ola.qh.controller;

import java.util.List;

import javax.validation.Valid;

import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Business;
import com.ola.qh.service.IBusinessService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/business")
public class BusinessController {

	@Autowired
	private IBusinessService businessService;
	
	@RequestMapping(value="/saveupdate",method=RequestMethod.POST)
	public Results<String> save(@RequestBody @Valid Business b,BindingResult valid){
		Results<String> result=new Results<String>();
		if(b.getId()==null || "".equals(b.getId())){
			///////如果id不存的话说明是保存  
			/////如果用户存在的话说明修改加盟商的信息
			if(valid.hasErrors()){
				result.setStatus("1");
				result.setMessage("加盟商信息填写不完整");
				return result;
			}
		}
		
		return businessService.save(b);
	}
	
	/**
	 * status==3停用的状态
	 * <p>Title: closeBusiness</p>  
	 * <p>Description: </p>  
	 * @param businessId
	 * @param status
	 * @return
	 */
	@RequestMapping("/closeBusiness")
	public Results<String> closeBusiness(@RequestParam(name="businessId",required=true)String businessId){
		
		Business b=new Business();
		b.setId(businessId);
		b.setStatus("3");
		return businessService.save(b);
	}
	
	/**
	 * id必须
	 * payaccount:实际支付金额
	 * account:兑换课程金额
	 * @param b
	 * @return
	 */
	@RequestMapping(value="/tocharge",method=RequestMethod.POST)
	public Results<String> businessCharge(@RequestBody Business b){
		return businessService.charge(b);
	}
	
	
	@RequestMapping("/list")
	public Results<List<Business>> businessList(
			@RequestParam(name="name",required=false)String name,
			@RequestParam(name="address",required=false)String address,
			@RequestParam(name="fromdate",required=false)String fromdate,
			@RequestParam(name="todate",required=false)String todate,
			@RequestParam(name="page",required=false)int page,
			@RequestParam(name="expireOrders",required=false)String expireOrders,
			@RequestParam(name="superOrders",required=false)String superOrders){
		
		Results<List<Business>> result=new Results<List<Business>>();
		int pageSize=Patterns.pageSize;
		int pageNo=(page-1)*pageSize;
		List<Business> list = businessService.list(name, address, fromdate, todate, pageNo, pageSize,expireOrders,superOrders);
		result.setCount(businessService.selectListCount(name, address, fromdate, todate));
		result.setStatus("0");
		result.setData(list);
		return result;
	} 
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> delete(@RequestParam(name="id",required=true)String id){
		Results<String> result=new Results<String>();
		businessService.delete(id);
		result.setStatus("0");
		return result;
	}
	
	
	
}
