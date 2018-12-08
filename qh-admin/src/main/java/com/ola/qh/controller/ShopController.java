package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Shop;
import com.ola.qh.service.IShopService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/shop")
public class ShopController {
	
	@Autowired
	private IShopService shopService;
	
	
	@RequestMapping("/shopList")
	public Results<List<Shop>> listShop(
			@RequestParam(name="pageNo",required=true)int pageNo,
			@RequestParam(name="pageSize",required=true)int pageSize){
		
		Results<List<Shop>> result = new Results<List<Shop>>();
		result.setData(shopService.selectShopList(pageNo, pageSize));
		result.setStatus("0");
		return result;
		
	}
	
	
	@RequestMapping("/shopSingle")
	public Results<Shop> singleShop(@RequestParam(name="id",required=true)String id){
		
		Results<Shop> result = new Results<Shop>();
		result.setData(shopService.selectShopSingle(id));
		result.setStatus("0");
		return result;
		
	}
	
	
	@RequestMapping("/updateShop")
	public Results<String> singleShop(
			@RequestParam(name="id",required=true)String id,
			@RequestParam(name="islimits",required=true)int islimits,
			@RequestParam(name="isrecommend",required=true)int isrecommend){
		
		Results<String> result = new Results<String>();
		int num = shopService.updateShop(id, islimits,isrecommend);
		if(num>0){
			result.setStatus("0");
			return result;
		}
		result.setStatus("1");
		result.setMessage("审核失败~");
		return result;
		
	}

}
