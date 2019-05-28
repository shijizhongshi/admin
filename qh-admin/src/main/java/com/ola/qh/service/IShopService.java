package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Shop;
import com.ola.qh.util.Results;

public interface IShopService {

    public Results<List<Shop>> selectShopList(String address,String shopName,String isrecommend,int shopType,int islimits,int pageNo,int pageSize);
	
	public Shop selectShopSingle(String id);
	
	public Results<String> updateShop(String id,String userId,int islimits,int isrecommend,String shopType);
	
	public int selectCount(String address,String shopName,String isrecommend,int islimits,int shopType);
	
	
	
	/////临时店铺的添加
	public int insertShortShop(Shop shop);
	
	public List<Shop> listShortShop(int shopType,int pageNo,int pageSize);
	
	public int listShortShopCount(int shopType);
	
	public int updateShortShop(Shop shop);
	
	public int deleteShortShop(String id);
}
