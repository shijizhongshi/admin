package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Shop;

public interface IShopService {

    public List<Shop> selectShopList(int shopType,int pageNo,int pageSize);
	
	public Shop selectShopSingle(String id);
	
	public int updateShop(String id,int islimits,int isrecommend);
}
