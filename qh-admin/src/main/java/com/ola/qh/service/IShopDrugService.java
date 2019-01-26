package com.ola.qh.service;

import java.util.Date;
import java.util.List;



import com.ola.qh.entity.ShopDrug;

public interface IShopDrugService {

	public List<ShopDrug> selectDrugList(String drugName,String categoryName,String categorySubname,
			String islimits,String shopName,int pageNo,int pageSize,String shopId);
	
	public int updateDrug(String id,String istimes,String ishot,String islimits,String isrecommend,String issales,Date approvalTime,Date updatetime);
	
}

