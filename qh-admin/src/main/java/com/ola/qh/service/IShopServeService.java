package com.ola.qh.service;

import java.util.Date;
import java.util.List;

import com.ola.qh.entity.ShopServe;
import com.ola.qh.util.Results;

public interface IShopServeService {

	public Results<List<ShopServe>> selectShopServe(String shopName,String serveName,String serveType,int pageNo,int pageSize,String serveStatus,String shopId);
	
	public int selectShopServeCount(String shopName,String serveName,String serveType,String serveStatus);
	
	public int updateShopServe(String id,String ishot,String serveStatus,Date updatetime);
	
	public Results<String> deleteShopServe(String id);
}
