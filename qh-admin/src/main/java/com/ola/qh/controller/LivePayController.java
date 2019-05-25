package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.LivePay;
import com.ola.qh.service.ILivePayService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/livepay")
public class LivePayController {

	@Autowired
	private ILivePayService livePayService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<LivePay>> livePayList(@RequestParam(name="status",required=true)String status,
			@RequestParam(name="livename",required=false)String livename,
			@RequestParam(name="startTime",required=false)String startTime,
			@RequestParam(name="pageNo",required=true)int pageNo,@RequestParam(name="pageSize",required=true)int pageSize){
		
		return livePayService.livePayList(status,livename,startTime, pageNo, pageSize);
	}
}
