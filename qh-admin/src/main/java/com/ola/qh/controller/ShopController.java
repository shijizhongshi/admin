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

import com.ola.qh.entity.Shop;
import com.ola.qh.service.IShopService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
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
	
	
	
	
	/**
	 * 保存临时店铺的信息
	 * <p>Title: insertShortShop</p>  
	 * <p>Description: </p>  
	 * @param shop
	 * @param valid
	 * @return
	 */
	@RequestMapping(value="/saveUpdateShort",method=RequestMethod.POST)
	public Results<String> insertShortShop(@RequestBody @Valid Shop shop,BindingResult valid){
		
		Results<String> result=new Results<String>();
		if(shop.getId()!=null && !"".equals(shop.getId())){
			shopService.updateShortShop(shop);
		}else{
			if(valid.hasErrors()){
				result.setStatus("1");
				result.setMessage("临时店铺信息不完整~");
				return result;
			}
			shop.setId(KeyGen.uuid());
			shop.setAddtime(new Date());
			shopService.insertShortShop(shop);
		}
		
		result.setStatus("0");
		return result;
	}
	
	/**
	 * 删除临时店铺
	 * <p>Title: deleteShortShop</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteshort",method=RequestMethod.GET)
	public Results<String> deleteShortShop(@RequestParam(name="id",required=true)String id){
		Results<String> result=new Results<String>();
		shopService.deleteShortShop(id);
		result.setStatus("0");
		return result;
	}
	/**
	 * 临时店铺的集合
	 * <p>Title: listshortShop</p>  
	 * <p>Description: </p>  
	 * @param page
	 * @param shopType
	 * @return
	 */
	@RequestMapping("/listshort")
	public Results<List<Shop>> listshortShop(@RequestParam(name="page",required=true)int page){
		
		Results<List<Shop>> result=new Results<List<Shop>>();
		int pageSize=Patterns.pageSize;
		int pageNo=(page-1)*pageSize;
		List<Shop> list = shopService.listShortShop(0, pageNo, pageSize);
		int count = shopService.listShortShopCount(0);
		result.setCount(count);
		result.setStatus("0");
		result.setData(list);
		return result;
	}
}
