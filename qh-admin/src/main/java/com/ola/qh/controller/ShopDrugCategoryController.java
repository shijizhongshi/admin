package com.ola.qh.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.ShopDrugCategory;
import com.ola.qh.service.IShopDrugCategoryService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/shopdrugcategory")
public class ShopDrugCategoryController {

	@Autowired
	private IShopDrugCategoryService shopDrugCategoryService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<ShopDrugCategory>> selectShopDrugCategory(){
		
		Results<List<ShopDrugCategory>> results=new Results<List<ShopDrugCategory>>();
		
		List<ShopDrugCategory> list=shopDrugCategoryService.selectShopDrugCategory();
		
		results.setData(list);
		results.setStatus("0");
		return results;
		
	}
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public Results<String> insertShopDrugCategory(@RequestParam(name="categoryName",required=true)String categoryName){
		
		Results<String> results=new Results<String>();
		
		ShopDrugCategory shopDrugCategory=new ShopDrugCategory();
		shopDrugCategory.setCategoryName(categoryName);
		shopDrugCategory.setId(KeyGen.uuid());
		shopDrugCategory.setAddtime(new Date());
		int insert=shopDrugCategoryService.insertShopDrugCategory(shopDrugCategory);
		
		if(insert==0){
			
			results.setMessage("添加出错");
			results.setStatus("1");
			return results;
		}
		
		results.setStatus("0");
		return results;
		
	}
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public Results<String> updateShopDrugCategory(@RequestParam(name="categoryName",required=true)String categoryName,
			@RequestParam(name="id",required=true)String id){
		
		Results<String> results=new Results<String>();
		
		ShopDrugCategory shopDrugCategory=new ShopDrugCategory();
		shopDrugCategory.setId(id);
		shopDrugCategory.setUpdatetime(new Date());
		int update=shopDrugCategoryService.updateShopDrugCategory(shopDrugCategory);
		
		if(update==0){
			
			results.setMessage("修改出错");
			results.setStatus("1");
			return results;
		}
		
		results.setStatus("0");
		return results;
		
	}
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteShopDrugCategory(@RequestParam(name="id",required=true)String id){
		

		return shopDrugCategoryService.deleteShopDrugCategory(id);
		
	}
}
