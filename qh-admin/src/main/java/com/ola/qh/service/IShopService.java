package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Shop;
import com.ola.qh.util.Results;

public interface IShopService {

    public Results<List<Shop>> selectShopList(String address,String shopName,String isrecommend,int shopType,int islimits,int pageNo,int pageSize);
	
	public Shop selectShopSingle(String id);
	
	public Results<String> updateShop(String id,String userId,int islimits,int isrecommend,String shopType);
	
	public String selectCount(String shopType);
}
