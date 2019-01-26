package com.ola.qh.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.ShopDrug;
import com.ola.qh.service.IShopDrugService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/shopDrug")
public class ShopDrugController {

	@Autowired
	private IShopDrugService shopDrugService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<ShopDrug>> selectDrugList(@RequestParam(name="drugName",required=false)String drugName,
			@RequestParam(name="categoryName",required=false)String categoryName,@RequestParam(name="categorySubname",required=false)String categorySubname,
			@RequestParam(name="islimits",required=false)String islimits,@RequestParam(name="shopName",required=false)String shopName,
			@RequestParam(name="pageNo",required=false)int pageNo,
			@RequestParam(name="pageSize",required=false)int pageSize,@RequestParam(name="shopId",required=false)String shopId){
		
		
		Results<List<ShopDrug>> results=new Results<List<ShopDrug>>();
		
		List<ShopDrug> list=shopDrugService.selectDrugList(drugName, categoryName, categorySubname, islimits ,shopName,pageNo,pageSize,shopId);
		
		results.setData(list);
		results.setStatus("0");
		return results;
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public Results<String> updateDrug(@RequestParam(name="id",required=true)String id,@RequestParam(name="ishot",required=false)String ishot,
			@RequestParam(name="islimits",required=false)String islimits,@RequestParam(name="istimes",required=false)String istimes,
			@RequestParam(name="isrecommend",required=false)String isrecommend,@RequestParam(name="issales",required=false)String issales
			){
		
		
		Results<String> results=new Results<String>();
		
		if(islimits!=null){
			int update=shopDrugService.updateDrug(id, istimes, ishot, islimits, isrecommend, issales,new Date(),new Date());
			
			if(update<=0){
				
				results.setStatus("1");
				results.setMessage("审核出错");
				return results;
			}
			
			results.setStatus("0");
			return results;
			
		}
		int update=shopDrugService.updateDrug(id, istimes, ishot, islimits, isrecommend, issales,null,new Date());
				
		if(update<=0){
			results.setStatus("1");
			return results;
		}
		
		results.setStatus("0");
		return results;
	}
}
