package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.ShopDrug;

public interface ShopDrugDao {
	
	public int updateDrug(@Param("id") String id,
			@Param("status")int status,
			@Param("ishot")int ishot,
			@Param("updatetime") Date updatetime
			);
	
	public List<ShopDrug> selectDrugList(
			@Param("shopName") String shopName,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize,
			@Param("ishot")int ishot);

}
