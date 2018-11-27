package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.ShopDrug;
import com.ola.qh.service.IShopDrugService;
import com.ola.qh.util.Results;

/**
 * 药品的修改和查看
* @ClassName: ShopDrugController  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author guoyuxue  
* @date 2018年11月27日  
*
 */
@RestController
@RequestMapping("/api/shopDrug")
public class ShopDrugController {

	@Autowired
	private IShopDrugService shopDrugService;
	
	@RequestMapping("/update")
	public Results<String> updateShopDrug(
			@RequestParam(name="id",required=true)String id,
			@RequestParam(name="status",required=false)int status,
			@RequestParam(name="ishot",required=false)int ishot){
		
	return	shopDrugService.updateDrug(id, status, ishot);
	}
	
	@RequestMapping("/list")
	public Results<List<ShopDrug>> listShopDrug(
			@RequestParam(name="shopName",required=false)String shopName,
			@RequestParam(name="pageNo",required=true)int pageNo,
			@RequestParam(name="pageSize",required=true)int pageSize,
			@RequestParam(name="ishot",required=false)int ishot){
		
		return shopDrugService.selectDrugList(shopName, pageNo, pageSize, ishot);
	}
}
