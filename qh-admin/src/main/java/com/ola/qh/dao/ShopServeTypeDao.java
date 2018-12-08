package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.ShopServeType;

public interface ShopServeTypeDao {

	public List<ShopServeType> selectShopServeType();
	
	public int insertShopServeType(@Param("name")String name,@Param("addtime")Date addtime,@Param("id")String id);
	
	public int updateShopServeType(@Param("name")String name,@Param("updatetime")Date addtime,@Param("id")String id);
	
	public int deleteShopServeType(@Param("id")String id);
}
