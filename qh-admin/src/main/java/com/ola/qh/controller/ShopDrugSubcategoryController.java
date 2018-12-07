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

import com.ola.qh.entity.ShopDrugSubcategory;
import com.ola.qh.service.IShopDrugSubcategoryService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/shopdrugsubcategory")
public class ShopDrugSubcategoryController {

	@Autowired
	private IShopDrugSubcategoryService shopDrugSubcategoryService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<ShopDrugSubcategory>> selectShopDrugSubcategory(String categoryId){
		
		Results<List<ShopDrugSubcategory>> results=new Results<List<ShopDrugSubcategory>>();
		
		List<ShopDrugSubcategory> list=shopDrugSubcategoryService.selectShopDrugSubcategory(categoryId);
		
		if(list==null || list.size()==0){
			
			results.setMessage("未添加子分类");
			results.setStatus("1");
			return results;
		}
		
		results.setData(list);
		results.setStatus("0");
		return results;
		
	}
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertShopDrugSubcategory(@RequestBody @Valid ShopDrugSubcategory shopDrugSubcategory,BindingResult valid){
		
		Results<String> results=new Results<String>();
		if (valid.hasErrors()) {
			results.setMessage("信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}
		shopDrugSubcategory.setId(KeyGen.uuid());
		shopDrugSubcategory.setAddtime(new Date());
		int insert=shopDrugSubcategoryService.insertShopDrugSubcategory(shopDrugSubcategory);
		
		if(insert==0){
			
			results.setMessage("添加出错");
			results.setStatus("1");
			return results;
		}
		
		results.setStatus("0");
		return results;
		
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateShopDrugSubcategory(@RequestBody ShopDrugSubcategory shopDrugSubcategory){
		
		Results<String> results=new Results<String>();
		
		int update=shopDrugSubcategoryService.updateShopDrugSubcategory(shopDrugSubcategory);
		
		if(update==0){
			
			results.setMessage("修改出错");
			results.setStatus("1");
			return results;
		}
		
		results.setStatus("0");
		return results;
		
	}
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteShopDrugSubcategory(@RequestParam(name="id",required=true)String id){
		
		Results<String> results=new Results<String>();
		
		int delete=shopDrugSubcategoryService.deleteShopDrugSubcategory(id);
		
		if(delete==0){
			
			results.setMessage("删除出错");
			results.setStatus("1");
			return results;
		}
		
		results.setStatus("0");
		return results;
		
	}
}
