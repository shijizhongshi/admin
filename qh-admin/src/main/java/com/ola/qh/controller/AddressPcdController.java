package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.AddressPcd;
import com.ola.qh.service.IAddressPcdService;
import com.ola.qh.util.Results;

/**
 * 
 * 
* @ClassName: AddressPcdController
* @Description:  省份、城市、区县的查询
* @author guozihan
* @date   2018年11月30日
*
 */
@RestController
@RequestMapping("/api/AddressPcd")
public class AddressPcdController {

	@Autowired
	private IAddressPcdService addresspcdservice;

	@RequestMapping(value = "/selectprovince", method = RequestMethod.GET)
	public Results<List<AddressPcd>> selectProvince() {

		
		
		Results<List<AddressPcd>> results = new Results<List<AddressPcd>>();

		List<AddressPcd> province = addresspcdservice.selectProvince();

		if (province != null && province.size() != 0) {
			results.setData(province);
			results.setStatus("0");
			return results;

		}
		results.setMessage("省份显示失败");
		results.setStatus("1");
		return results;
	}

	@RequestMapping(value = "/selectcity", method = RequestMethod.GET)
	public Results<List<AddressPcd>> selectCity(@RequestParam(name = "provinceId",required=true) int provinceId) {

		Results<List<AddressPcd>> results = new Results<List<AddressPcd>>();

		List<AddressPcd> city = addresspcdservice.selectCity(provinceId);
		if (city != null && city.size() != 0) {
			results.setData(city);
			results.setStatus("0");
			return results;

		}
		results.setMessage("城市显示失败");
		results.setStatus("1");
		return results;
	}

	@RequestMapping(value = "/selectdistrict", method = RequestMethod.GET)
	public Results<List<AddressPcd>> selectDistrict(@RequestParam(name = "cityId",required=true) int cityId) {

		Results<List<AddressPcd>> results = new Results<List<AddressPcd>>();

		List<AddressPcd> district = addresspcdservice.selectDistrict(cityId);
		if (district != null && district.size() != 0) {
			results.setData(district);
			results.setStatus("0");
			return results;

		}
		results.setMessage("区县显示失败");
		results.setStatus("1");
		return results;
	}

}
