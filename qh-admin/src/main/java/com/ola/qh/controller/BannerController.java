package com.ola.qh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Banner;
import com.ola.qh.service.IBannerService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

/**
 * 
 * 
 * @ClassName:BannerController
 * @Description:Banner增刪改查
 * @author guozihan
 * @date 2018年11月15日
 *
 */
@RestController
@RequestMapping("/api/banner")
public class BannerController {

	@Autowired
	private IBannerService bannerService;

	
	
	@RequestMapping(value = "/selectlist", method = RequestMethod.GET)
	public Results<List<Banner>> selectBanner(@RequestParam(name = "type", required = false) int type,
			@RequestParam(name = "pageNo", required = false) int pageNo,
			@RequestParam(name = "pageSize", required = false) int pageSize) {

		Results<List<Banner>> results = new Results<List<Banner>>();

		List<Banner> bannerlist = bannerService.selectBanner(type,pageNo,pageSize);
		int count = bannerService.selectBannerCount(type);	
		results.setData(bannerlist);
		results.setCount(count);
			results.setStatus("0");
			return results;


	}

	@RequestMapping(value = "/saveBanner", method = RequestMethod.POST)
	public Results<String> saveBanner(@RequestBody @Valid Banner banner, BindingResult valid) {

		Results<String> results = new Results<String>();

		if (valid.hasErrors()) {
			results.setMessage("图片信息填入不完整");
			results.setStatus("1");
			return results;
		}
		banner.setId(KeyGen.uuid());
		int success = bannerService.saveBanner(banner);
		if (success <= 0) {
			results.setMessage("图片插入有误");
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "/updateBanner", method = RequestMethod.POST)
	public Results<String> updateBanner(@RequestBody Banner banner) {

		Results<String> results = new Results<String>();

		int success = bannerService.updateBanner(banner);
		if (success <= 0) {

			results.setMessage("图片信息修改有误");
			results.setStatus("1");
			return results;

		}
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "/deleteBanner", method = RequestMethod.GET)
	public Results<String> deleteBanner(@RequestParam(name = "id", required = true) String id) {

		Results<String> results = new Results<String>();

		int success = bannerService.deleteBanner(id);
		if (success <= 0) {

			results.setMessage("图片删除失败");
			results.setStatus("1");
			return results;

		}
		results.setStatus("0");
		return results;
	}

}
