package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.ShopDrugSubcategory;

public interface ShopDrugSubcategoryDao {
	
	public List<ShopDrugSubcategory> selectShopDrugSubcategory(@Param("categoryId")String categoryId);
	
	public int insertShopDrugSubcategory(ShopDrugSubcategory shopDrugSubcategory);
	
	public int updateShopDrugSubcategory(ShopDrugSubcategory shopDrugSubcategory);
	
	public int deleteShopDrugSubcategory(@Param("id")String id);
	
	public int deleteAllShopDrugSubcategory(@Param("categoryId")String categoryId);
}
