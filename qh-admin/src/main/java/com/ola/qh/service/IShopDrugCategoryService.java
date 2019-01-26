package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.ShopDrugCategory;
import com.ola.qh.util.Results;

public interface IShopDrugCategoryService {
	
	public List<ShopDrugCategory> selectShopDrugCategory();
	
	public String selectShopDrugCategoryName(String categoryName);
	
	public int insertShopDrugCategory(ShopDrugCategory shopDrugCategory);
	
	public int updateShopDrugCategory(ShopDrugCategory shopDrugCategory);
	
	public Results<String> deleteShopDrugCategory(String id);
}
