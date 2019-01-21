package com.ola.qh.dao;

import java.util.List;

import com.ola.qh.entity.ShopServeImg;

public interface ShopServeImgDao {

	public List<ShopServeImg> selectServeImg(String serveId);
	
	public int deleteServeImg(String serveId);
}
