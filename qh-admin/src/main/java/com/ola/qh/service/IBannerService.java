package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Banner;
/**
 * 
 * 
* @ClassName: IBannerService
* @Description:  IBannerService接口
* @author guozihan
* @date   2018年11月15日
*
 */
public interface IBannerService {

	public List<Banner> selectBanner(int type);
	
	public int saveBanner(Banner banner);
	
	public int updateBanner(Banner banner);
	
	public int deleteBanner(String id);
}
