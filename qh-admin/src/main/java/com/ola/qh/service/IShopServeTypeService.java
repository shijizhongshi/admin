package com.ola.qh.service;

import java.util.Date;
import java.util.List;

import com.ola.qh.entity.ShopServeType;

public interface IShopServeTypeService {

	public List<ShopServeType> selectShopServeType();
	
	public int insertShopServeType(String name,Date addtime,String id);
	
	public int updateShopServeType(String name,Date updatetime,String id);
	
	public int deleteShopServeType(String id);

	
}
