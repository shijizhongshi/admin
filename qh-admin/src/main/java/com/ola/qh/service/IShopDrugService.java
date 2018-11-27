package com.ola.qh.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.ShopDrug;
import com.ola.qh.util.Results;

public interface IShopDrugService {

	public Results<String> updateDrug(String id,int status,int ishot);
	
	public Results<List<ShopDrug>> selectDrugList(String shopName,int pageNo,int pageSize,int ishot);
}
