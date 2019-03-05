package com.ola.qh.service.imp;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.ShopDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopImg;
import com.ola.qh.entity.User;
import com.ola.qh.service.IShopService;
import com.ola.qh.util.Results;
@Service
public class ShopService implements IShopService{

	@Autowired
	private ShopDao shopDao;
	@Autowired
	private UserDao userDao;
	
	
	
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
				
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				shop.setShowtime(sf.format(shop.getAddtime()));
			}
			int count=shopDao.selectCount(address, shopName, isrecommend, islimits, shopType);
			results.setData(listAll);
			results.setCount(count);
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

	@Transactional
	@Override
	public Results<String> updateShop(String id,String userId, int islimits,int isrecommend,String shopType) {
		// TODO Auto-generated method stub
		Results<String> result=new Results<String>();
		
		User user = userDao.singleUser(userId);
		int userrole=0;
		if("0".equals(user.getUserrole())){
			///////说明用户当前是普通的角色
			if("1".equals(shopType)){
				//////服务店铺
				userrole=1;
			}else if("2".equals(shopType)){
				/////商城店铺
				userrole=2;
			}else{
				result.setStatus("1");
				result.setMessage("店铺类型不对");
				return result;
			}
		}else if("1".equals(user.getUserrole())){
			/////说明用户本身就是个服务店铺
			if("2".equals(shopType)){
				/////商城店铺
				userrole=3;
			}else{
				result.setStatus("1");
				result.setMessage("店铺类型不对");
				return result;
			}
		}else if("2".equals(user.getUserrole())){
		    /////说明用户本身就是个商城店铺
			if("1".equals(shopType)){
				/////服务店铺
				userrole=3;
			}else{
				result.setStatus("1");
				result.setMessage("店铺类型不对");
				return result;
			}
			
		}else{
			result.setStatus("1");
			result.setMessage("请检查该用户");
			return result;
		}
		User newUser=new User();
		newUser.setId(userId);
		newUser.setUserrole(userrole+"");
		userDao.updateUser(newUser);
		shopDao.updateShop(id, islimits,isrecommend);
		
		result.setStatus("0");
		return result;
	}

	@Override
	public int selectCount(String address, String shopName, String isrecommend, int islimits, int shopType) {
		// TODO Auto-generated method stub
		return shopDao.selectCount(address, shopName, isrecommend, islimits, shopType);
	}

	@Override
	public int insertShortShop(Shop shop) {
		// TODO Auto-generated method stub
		return shopDao.insertShortShop(shop);
	}

	@Override
	public List<Shop> listShortShop(int shopType, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return shopDao.listShortShop(shopType, pageNo, pageSize);
	}

	@Override
	public int updateShortShop(Shop shop) {
		// TODO Auto-generated method stub
		return shopDao.updateShortShop(shop);
	}

	@Override
	public int deleteShortShop(String id) {
		// TODO Auto-generated method stub
		return shopDao.deleteShortShop(id);
	}

	@Override
	public int listShortShopCount(int shopType) {
		// TODO Auto-generated method stub
		return shopDao.listShortShopCount(shopType);
	}

}
