package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.AddressPcd;

public interface IAddressPcdService {

	public List<AddressPcd> selectProvince();
	
	public List<AddressPcd> selectCity(int provinceId);
	
	public List<AddressPcd> selectDistrict(int cityId);
}
