package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.ShopServeDao;
import com.ola.qh.dao.ShopServeImgDao;
import com.ola.qh.entity.ShopServe;
import com.ola.qh.entity.ShopServeImg;
import com.ola.qh.service.IShopServeService;
import com.ola.qh.util.Results;

@Service
public class ShopServeService implements IShopServeService {

	@Autowired
	private ShopServeDao shopServeDao;
	
	@Autowired
	private ShopServeImgDao shopServeImgDao;
	
	@Transactional
	@Override
	public Results<List<ShopServe>> selectShopServe(String shopName, String serveName, String serveType,int pageNo,int pageSize,String serveStatus) {
		
		Results<List<ShopServe>> results=new Results<List<ShopServe>>();
		
		try {
			
			List<ShopServe> list=shopServeDao.selectShopServe(shopName, serveName, serveType,pageNo,pageSize,serveStatus);
			for (ShopServe shopServe : list) {
				
				if(shopServe.getId()==null){
					
					shopServe.setImglist(null);
				}
				List<ShopServeImg> listimg=shopServeImgDao.selectServeImg(shopServe.getId());
				shopServe.setImglist(listimg);
			}
			
		results.setData(list);	
		results.setStatus("0");
		return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

	@Override
	public int updateShopServe(String id,String ishot, String serveStatus,Date updatetime) {
		
		return shopServeDao.updateShopServe(id,ishot, serveStatus,updatetime);
	}

	
	@Transactional
	@Override
	public Results<String> deleteShopServe(String id) {
		
		Results<String> results=new Results<String>();
		
		try {
			
			shopServeDao.deleteShopServe(id);
			shopServeImgDao.deleteServeImg(id);
			
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

	
}
