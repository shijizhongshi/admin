package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.ShopDao;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopImg;
import com.ola.qh.service.IShopService;
import com.ola.qh.util.Results;
@Service
public class ShopService implements IShopService{

	@Autowired
	private ShopDao shopDao;
	
	@Transactional
	@Override
	public Results<List<Shop>> selectShopList(String address,String shopName,String isrecommend,int shopType,int islimits,int pageNo, int pageSize) {
		
		Results<List<Shop>> results=new Results<List<Shop>>();
		
		try {
			
			List<Shop> listAll=shopDao.selectShopList(address, shopName, isrecommend, shopType, islimits, pageNo, pageSize);
		
			for (Shop shop : listAll) {
				List<ShopImg> imgList=shopDao.selectImgList(shop.getId(), 1);
				shop.setImgList(imgList);
				
				List<ShopImg> environmentImgList=shopDao.selectImgList(shop.getId(), 2);
				shop.setEnvironmentImgList(environmentImgList);
			}
			results.setData(listAll);
			results.setStatus("0");
			return results;
		
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
		
	}

	@Override
	public Shop selectShopSingle(String id) {
		// TODO Auto-generated method stub
		return shopDao.selectShopSingle(id,null,null);
	}

	@Override
	public int updateShop(String id, int islimits,int isrecommend) {
		// TODO Auto-generated method stub
		return shopDao.updateShop(id, islimits,isrecommend);
	}

}
