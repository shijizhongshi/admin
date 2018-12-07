package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.ShopServeTypeDao;
import com.ola.qh.entity.ShopServeType;
import com.ola.qh.service.IShopServeTypeService;

@Service
public class ShopServeTypeService implements IShopServeTypeService {

	@Autowired
	private ShopServeTypeDao shopServeTypeDao;

	@Override
	public List<ShopServeType> selectShopServeType() {

		return shopServeTypeDao.selectShopServeType();
	}

	@Override
	public int insertShopServeType(String name,Date addtime,String id) {

		return shopServeTypeDao.insertShopServeType(name,addtime,id);
	}

	@Override
	public int updateShopServeType(String name,Date updatetime,String id) {

		return shopServeTypeDao.updateShopServeType(name,updatetime,id);
	}

	@Override
	public int deleteShopServeType(String id) {

		return shopServeTypeDao.deleteShopServeType(id);
	}

}
