package com.ola.qh.dao;

import java.util.List;

import com.ola.qh.entity.Banner;

public interface BannerDao {

	public List<Banner> selectBanner(String type);
	
	
}
