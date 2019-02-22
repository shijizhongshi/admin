package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.ShopDrug;

public interface ShopDrugDao {
	
	public List<ShopDrug> selectDrugList(@Param("drugName")String drugName,@Param("categoryName")String categoryName,@Param("categorySubname")String categorySubname,
			@Param("islimits")String islimits,@Param("shopName")String shopName,
			@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("shopId")String shopId);
 
	
	public int selectDrugCount(@Param("drugName")String drugName,@Param("categoryName")String categoryName,@Param("categorySubname")String categorySubname,
			@Param("islimits")String islimits,@Param("shopName")String shopName);
	
	public int updateDrug(@Param("id")String id,@Param("istimes")String istimes,@Param("ishot")String ishot,@Param("islimits")String islimits,@Param("isrecommend")String isrecommend,
			@Param("issales")String issales,@Param("approvalTime")Date approvalTime,@Param("updatetime")Date updatetime);
}
