package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Banner;

public interface IBannerService {

	public List<Banner> selectBanner(String type);
}
