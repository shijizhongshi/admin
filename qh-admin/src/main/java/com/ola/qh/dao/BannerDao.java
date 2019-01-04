package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Banner;

public interface BannerDao {

	public List<Banner> selectBanner(@Param("type")int type,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);
	
	public int selectBannerCount(@Param("type")int type);
	
	public int saveBanner(Banner banner);
	
	public int updateBanner(Banner banner);
	
	public int deleteBanner(String id);
}
