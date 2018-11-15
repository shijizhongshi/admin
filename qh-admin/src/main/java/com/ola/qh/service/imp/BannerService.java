package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.BannerDao;
import com.ola.qh.entity.Banner;
import com.ola.qh.service.IBannerService;

@Service
public class BannerService implements IBannerService {

	@Autowired
	private BannerDao bannerDao;

	@Override
	public List<Banner> selectBanner(int type) {
		
		return bannerDao.selectBanner(type);
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

	

	
	
	

}
