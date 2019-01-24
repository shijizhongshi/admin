package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.ShopDrugDao;
import com.ola.qh.entity.ShopDrug;
import com.ola.qh.service.IShopDrugService;

@Service
public class ShopDrugService implements IShopDrugService {

	@Autowired
	private ShopDrugDao shopDrugDao;
	
	@Override
	public List<ShopDrug> selectDrugList(String drugName, String categoryName, String categorySubname,
			 String islimits,String shopName,int pageNo,int pageSize) {
		
		return shopDrugDao.selectDrugList(drugName, categoryName, categorySubname, islimits, shopName, pageNo, pageSize);
	}

	@Override
	public int updateDrug(String id,String istimes,String ishot,String islimits,String isrecommend,String issales,Date approvalTime,Date updatetime) {
		
		return shopDrugDao.updateDrug(id, istimes, ishot, islimits, isrecommend, issales,approvalTime, updatetime);
	}

	

}
