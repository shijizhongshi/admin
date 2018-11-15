package com.ola.qh.dao;

import java.util.List;

import com.ola.qh.entity.Banner;

public interface BannerDao {

	public List<Banner> selectBanner(int type);
	
	public int saveBanner(Banner banner);
	
	public int updateBanner(Banner banner);
	
	public int deleteBanner(String id);
}
