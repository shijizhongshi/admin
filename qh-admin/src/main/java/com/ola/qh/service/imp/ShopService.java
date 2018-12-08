package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.ShopDao;
import com.ola.qh.entity.Shop;
import com.ola.qh.service.IShopService;
@Service
public class ShopService implements IShopService{

	@Autowired
	private ShopDao shopDao;
	
	
	@Override
	public List<Shop> selectShopList(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return shopDao.selectShopList(pageNo, pageSize);
	}

	@Override
	public Shop selectShopSingle(String id) {
		// TODO Auto-generated method stub
		return shopDao.selectShopSingle(id);
	}

	@Override
	public int updateShop(String id, int islimits,int isrecommend) {
		// TODO Auto-generated method stub
		return shopDao.updateShop(id, islimits,isrecommend);
	}

}
