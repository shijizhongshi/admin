package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.ShopDrugSubcategoryDao;
import com.ola.qh.entity.ShopDrugSubcategory;
import com.ola.qh.service.IShopDrugSubcategoryService;

@Service
public class ShopDrugSubcategoryService implements IShopDrugSubcategoryService{

	@Autowired
	private ShopDrugSubcategoryDao shopDrugSubcategoryDao;

	@Override
	public List<ShopDrugSubcategory> selectShopDrugSubcategory(String categoryId,int pageNo,int pageSize) {
		
		return shopDrugSubcategoryDao.selectShopDrugSubcategory(categoryId,pageNo,pageSize);
	}

	@Override
	public int insertShopDrugSubcategory(ShopDrugSubcategory shopDrugSubcategory) {
		
		return shopDrugSubcategoryDao.insertShopDrugSubcategory(shopDrugSubcategory);
	}

	@Override
	public int updateShopDrugSubcategory(ShopDrugSubcategory shopDrugSubcategory) {
		
		return shopDrugSubcategoryDao.updateShopDrugSubcategory(shopDrugSubcategory);
	}

	@Override
	public int deleteShopDrugSubcategory(String id) {
		
		return shopDrugSubcategoryDao.deleteShopDrugSubcategory(id);
	}

	@Override
	public int selectShopDrugSubcount(String categoryId) {
		
		return shopDrugSubcategoryDao.selectShopDrugSubcount(categoryId);
	}

	
	
	

}
