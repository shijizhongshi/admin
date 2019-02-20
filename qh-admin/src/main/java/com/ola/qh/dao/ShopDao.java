package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopImg;

public interface ShopDao {

	public List<Shop> selectShopList(@Param("address")String address,@Param("shopName")String shopName,@Param("isrecommend")String isrecommend,@Param("shopType")int shopType,@Param("islimits")int islimits,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public Shop selectShopSingle(@Param("id")String id,@Param("userId")String userId,@Param("shopType")String shopType);
	
	public int updateShop(@Param("id")String id,@Param("islimits")int islimits,@Param("isrecommend")int isrecommend);
	
	public List<ShopImg> selectImgList(@Param("shopId")String shopId,@Param("subtype")int subtype);
	
	public int selectCount(@Param("address")String address,@Param("shopName")String shopName,@Param("isrecommend")String isrecommend,@Param("islimits")int islimits,@Param("shopType")int shopType);
}
