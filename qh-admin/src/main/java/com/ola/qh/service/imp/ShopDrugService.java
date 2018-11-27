package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.ShopDrugDao;
import com.ola.qh.entity.ShopDrug;
import com.ola.qh.service.IShopDrugService;
import com.ola.qh.util.Results;

@Service
public class ShopDrugService implements IShopDrugService{

	@Autowired
	private ShopDrugDao shopDrugDao;
	
	@Override
	public Results<String> updateDrug(String id, int status, int ishot) {
		// TODO Auto-generated method stub
		Results<String> result = new Results<String>();
		
		int num = shopDrugDao.updateDrug(id, status, ishot,new Date());
		if(num>0){
			result.setStatus("0");
			return result;
		}
		result.setStatus("1");
		result.setMessage("更新有误");
		return result;
	}

	@Override
	public Results<List<ShopDrug>> selectDrugList(String shopName, int pageNo, int pageSize, int ishot) {
		// TODO Auto-generated method stub
		Results<List<ShopDrug>> result = new Results<List<ShopDrug>>();
		List<ShopDrug> list = shopDrugDao.selectDrugList(shopName, pageNo, pageSize, ishot);
		result.setData(list);
		result.setStatus("0");
		return result;
	}

}
