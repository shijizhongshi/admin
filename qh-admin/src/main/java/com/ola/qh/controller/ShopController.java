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
	public Results<List<Shop>> listShop(@RequestParam(name="shopType",required=true)int shopType,
			@RequestParam(name="address",required=false)String address,
			@RequestParam(name="shopName",required=false)String shopName,
			@RequestParam(name="isrecommend",required=false)String isrecommend,
			@RequestParam(name="islimits",required=true)int islimits,
			@RequestParam(name="pageNo",required=true)int pageNo,
			@RequestParam(name="pageSize",required=true)int pageSize){
		
		
		return shopService.selectShopList(address, shopName, isrecommend, shopType, islimits, pageNo, pageSize);
		
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
			@RequestParam(name="userId",required=true)String userId,
			@RequestParam(name="shopType",required=true)String shopType,
			@RequestParam(name="islimits",required=true)int islimits,
			@RequestParam(name="isrecommend",required=true)int isrecommend){
		
		return shopService.updateShop(id,userId,islimits,isrecommend,shopType);
		
		
	}
	@RequestMapping("/shopcount")
	public Results<String> selectCount(@RequestParam(name="address",required=false)String address,
			@RequestParam(name="shopName",required=false)String shopName,
			@RequestParam(name="isrecommend",required=false)String isrecommend,
			@RequestParam(name="islimits",required=true)int islimits,
			@RequestParam(name="shopType",required=true)int shopType){

		Results<String> results = new Results<String>();

		int count=shopService.selectCount(address, shopName, isrecommend, islimits, shopType);

		results.setCount(count);
		results.setStatus("0");
		return results;

	}
}
