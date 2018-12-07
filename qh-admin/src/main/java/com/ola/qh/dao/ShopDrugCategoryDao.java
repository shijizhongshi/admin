package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.ShopDrugCategory;

public interface ShopDrugCategoryDao {

	public List<ShopDrugCategory> selectShopDrugCategory();
	
	public int insertShopDrugCategory(ShopDrugCategory shopDrugCategory);
	
	public int updateShopDrugCategory(ShopDrugCategory shopDrugCategory);
	
	public int deleteShopDrugCategory(@Param("id")String id);
}
