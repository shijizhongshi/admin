package com.ola.qh.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.ShopServe;
import com.ola.qh.service.IShopServeService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/shopserve")
public class ShopServeController {

	@Autowired
	private IShopServeService shopServeService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<ShopServe>> selectShopServe(@RequestParam(name="shopName",required=false)String shopName,
			@RequestParam(name="serveName",required=false)String serveName,@RequestParam(name="serveType",required=false)String serveType,
			@RequestParam(name = "pageNo", required = false) int pageNo,
			@RequestParam(name = "pageSize", required = false) int pageSize){
		
		
		return shopServeService.selectShopServe(shopName, serveName, serveType,pageNo,pageSize);
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public Results<String> selectShopServe(@RequestParam(name="id",required=true)String id,
			@RequestParam(name="ishot",required=false)String ishot,@RequestParam(name="serveStatus",required=false)String serveStatus){
		
		Results<String> results=new Results<String>();
		shopServeService.updateShopServe(id, ishot, serveStatus,new Date());
		
		results.setStatus("0");
		return results;
		
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteShopServe(@RequestParam(name="id",required=true)String id){
		
		
		return shopServeService.deleteShopServe(id);
		
	}
}
