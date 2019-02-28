package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.AddressPcdDao;
import com.ola.qh.entity.AddressPcd;
import com.ola.qh.service.IAddressPcdService;

/**
 * 
 * 
* @ClassName: AddressPcdService
* @Description:  省份、城市、区县的查询
* @author guozihan
* @date   2018年11月30日
*
 */
@Service
public class AddressPcdService implements IAddressPcdService {

	@Autowired
	private AddressPcdDao addresspcddao;
	
	@Override
	public List<AddressPcd> selectProvince() {
		
		return addresspcddao.selectProvince();
	}

	@Override
	public List<AddressPcd> selectCity(int provinceId) {
		return addresspcddao.selectCity(provinceId);
	}

	@Override
	public List<AddressPcd> selectDistrict(int cityId) {
		
		return addresspcddao.selectDistrict(cityId);
	}

}
