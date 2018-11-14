package com.ola.qh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Banner;
import com.ola.qh.service.imp.BannerService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("api/banner")
public class BannerController {
	@Autowired
	private BannerService bannerService;

	@RequestMapping(value="/selectlist",method = RequestMethod.GET)
	public Results<List<Banner>> selectBanner(@RequestParam(name="type",required=true)String type){

		Results<List<Banner>> results = new Results<List<Banner>>();

		List<Banner> bannerlist = bannerService.selectBanner(type);
		if (bannerlist != null && bannerlist.size() != 0) {

			results.setData(bannerService.selectBanner(type));
			results.setStatus("0");
			return results;

		}

		results.setMessage("图片为空");
		results.setStatus("1");
		return results;
	}
}
