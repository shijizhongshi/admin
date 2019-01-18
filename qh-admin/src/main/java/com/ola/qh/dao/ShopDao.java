package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Shop;

public interface ShopDao {

	public List<Shop> selectShopList(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public Shop selectShopSingle(@Param("id")String id,@Param("userId")String userId,@Param("shopType")String shopType);
	
	public int updateShop(@Param("id")String id,@Param("islimits")int islimits,@Param("isrecommend")int isrecommend);
}
