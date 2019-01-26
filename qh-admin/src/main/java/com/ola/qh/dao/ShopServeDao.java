package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.ShopServe;

public interface ShopServeDao {

	public List<ShopServe> selectShopServe(@Param("shopName")String shopName,@Param("serveName")String serveName,@Param("serveType")String serveType,@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize,@Param("serveStatus")String serveStatus,@Param("shopId")String shopId);
	
	public int updateShopServe(@Param("id")String id,@Param("ishot")String ishot,@Param("serveStatus")String serveStatus,@Param("updatetime")Date updatetime);
	
	public int deleteShopServe(@Param("id")String id);
}
