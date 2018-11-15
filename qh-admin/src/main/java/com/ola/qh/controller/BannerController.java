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

import com.google.zxing.Result;
import com.ola.qh.entity.Banner;
import com.ola.qh.service.IBannerService;
import com.ola.qh.service.imp.BannerService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

/**
 * 
 * 
 * @ClassName:
 * @Description:
 * @author guozihan
 * @date
 *
 */
@RestController
@RequestMapping("api/banner")
public class BannerController {
	@Autowired
	private IBannerService bannerService;

	@RequestMapping(value = "/selectlist", method = RequestMethod.GET)
	public Results<List<Banner>> selectBanner(@RequestParam(name = "type", required = true) int type) {

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
		if (success > 0) {
			results.setStatus("0");
			return results;
		}
		results.setMessage("图片插入有误");
		results.setStatus("1");
		return results;

	}

	@RequestMapping(value = "/updateBanner", method = RequestMethod.POST)
	public Results<String> updateBanner(@RequestBody Banner banner) {

		Results<String> results = new Results<String>();

		int success = bannerService.updateBanner(banner);
		if (success > 0) {
			results.setStatus("0");
			return results;
		}
		results.setMessage("图片信息修改有误");
		results.setStatus("1");
		return results;

	}

	@RequestMapping(value = "/deleteBanner", method = RequestMethod.GET)
	public Results<String> deleteBanner(@RequestParam(name = "id", required = true) String id) {

		Results<String> results = new Results<String>();

		int success = bannerService.deleteBanner(id);
		if (success > 0) {
			results.setStatus("0");
			return results;
		}
		results.setMessage("图片删除失败");
		results.setStatus("1");
		return results;

	}

}
