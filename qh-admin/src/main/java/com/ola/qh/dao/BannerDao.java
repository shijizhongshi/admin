package com.ola.qh.dao;

import java.util.List;

import com.ola.qh.entity.Banner;
/**
 * 
 * 
* @ClassName: BannerDao
* @Description:  BannerDAO接口
* @author guozihan
* @date   2018年11月15日
*
 */
public interface BannerDao {

	public List<Banner> selectBanner(int type);
	
	public int saveBanner(Banner banner);
	
	public int updateBanner(Banner banner);
	
	public int deleteBanner(String id);
}
