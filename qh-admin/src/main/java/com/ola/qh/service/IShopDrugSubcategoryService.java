package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.ShopDrugSubcategory;

public interface IShopDrugSubcategoryService {

	public List<ShopDrugSubcategory> selectShopDrugSubcategory(String categoryId,int pageNo,int pageSize);
	
	public int selectShopDrugSubcount(String categoryId);
	
	public int insertShopDrugSubcategory(ShopDrugSubcategory shopDrugSubcategory);
	
	public int updateShopDrugSubcategory(ShopDrugSubcategory shopDrugSubcategory);
	
	public int deleteShopDrugSubcategory(String id);
	
	
}
