package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.AddressPcd;

public interface AddressPcdDao {

	public List<AddressPcd> selectProvince();
	
	public List<AddressPcd> selectCity(@Param("provinceId")int provinceId);
	
	public List<AddressPcd> selectDistrict(@Param("cityId")int cityId);
	
	
}
