package com.ola.qh.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.ShopServeType;
import com.ola.qh.service.IShopServeTypeService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/shopservetype")
public class ShopServeTypeController {
	@Autowired
	private IShopServeTypeService shopServeTypeService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<List<ShopServeType>> selectShopServeType() {

		Results<List<ShopServeType>> results = new Results<List<ShopServeType>>();

		List<ShopServeType> list=shopServeTypeService.selectShopServeType();
		if (list != null && list.size() != 0) {

			results.setData(list);
			results.setStatus("0");
			return results;

		}

		results.setStatus("1");
		return results;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public Results<String> insertShopServeType(@RequestParam(name="name",required=true)String name) {

		Results<String> results = new Results<String>();

		Date addtime=new Date();
		String id=KeyGen.uuid();
		int insert=shopServeTypeService.insertShopServeType(name, addtime, id);
		
		if (insert <= 0) {
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public Results<String> updateShopServeType(@RequestParam(name="name",required=true)String name,
			@RequestParam(name="id",required=true)String id) {

		Results<String> results = new Results<String>();

		Date updatetime=new Date();
		int update=shopServeTypeService.updateShopServeType(name, updatetime, id);
		
		if (update <= 0) {
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Results<String> deleteBanner(@RequestParam(name = "id", required = true) String id) {

		Results<String> results = new Results<String>();

		int delete=shopServeTypeService.deleteShopServeType(id);
		if (delete <= 0) {

			results.setStatus("1");
			return results;

		}
		results.setStatus("0");
		return results;
	}

}

