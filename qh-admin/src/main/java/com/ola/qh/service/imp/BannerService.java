package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.BannerDao;
import com.ola.qh.entity.Banner;
import com.ola.qh.service.IBannerService;

/**
 * 
 * 
* @ClassName: BannerService
* @Description:  BannerService接口实现类
* @author guozihan
* @date   2018年11月15日
*
 */
@Service
public class BannerService implements IBannerService {

	@Autowired
	private BannerDao bannerDao;

	@Override
	public List<Banner> selectBanner(int type,int pageNo,int pageSize) {
		
		return bannerDao.selectBanner(type,pageNo,pageSize);
	}

	@Override
	public int saveBanner(Banner banner) {
		
		return bannerDao.saveBanner(banner);
	}

	@Override
	public int updateBanner(Banner banner) {
		
		return bannerDao.updateBanner(banner);
	}

	@Override
	public int deleteBanner(String id) {
		
		return bannerDao.deleteBanner(id);
	}

	@Override
	public int selectBannerCount(int type) {
		// TODO Auto-generated method stub
		return bannerDao.selectBannerCount(type);
	}

	

	
	
	

}
