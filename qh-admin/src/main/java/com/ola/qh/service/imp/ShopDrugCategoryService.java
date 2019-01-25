package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.ShopDrugCategoryDao;
import com.ola.qh.dao.ShopDrugSubcategoryDao;
import com.ola.qh.entity.ShopDrugCategory;
import com.ola.qh.service.IShopDrugCategoryService;
import com.ola.qh.util.Results;

@Service
public class ShopDrugCategoryService implements IShopDrugCategoryService{

	@Autowired
	private ShopDrugCategoryDao shopDrugCategoryDao;
	
	@Autowired
	private ShopDrugSubcategoryDao shopDrugSubcategoryDao;
	
	
	@Override
	public List<ShopDrugCategory> selectShopDrugCategory() {
		
		return shopDrugCategoryDao.selectShopDrugCategory();
	}

	@Override
	public int insertShopDrugCategory(ShopDrugCategory shopDrugCategory) {
		
		return shopDrugCategoryDao.insertShopDrugCategory(shopDrugCategory);
	}

	@Override
	public int updateShopDrugCategory(ShopDrugCategory shopDrugCategory) {
		
		return shopDrugCategoryDao.updateShopDrugCategory(shopDrugCategory);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<String> deleteShopDrugCategory(String id) {
		
		Results<String> results=new Results<String>();
		
		try {
			
		shopDrugCategoryDao.deleteShopDrugCategory(id);
		String categoryId=id;
		shopDrugSubcategoryDao.deleteAllShopDrugSubcategory(categoryId);
		
		results.setStatus("0");
		return results;
		
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("删除失败");
			return results;
		}
	}

	@Override
	public String selectShopDrugCategoryName(String categoryName) {
		
		return shopDrugCategoryDao.selectShopDrugCategoryName(categoryName);
	}

}
