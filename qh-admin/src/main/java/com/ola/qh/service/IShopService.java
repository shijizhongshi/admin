package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Shop;
import com.ola.qh.util.Results;

public interface IShopService {

    public Results<List<Shop>> selectShopList(String address,String shopName,String isrecommend,int shopType,int islimits,int pageNo,int pageSize);
	
	public Shop selectShopSingle(String id);
	
	public int updateShop(String id,int islimits,int isrecommend);
	
	public String selectCount(String shopType);
}
