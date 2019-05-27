package com.ola.qh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/marketing")
public class Marketing {

	@RequestMapping("/questionbankH5")
	public String courseOrders() {
		return "marketing/questionbankH5";
	}
	
	@RequestMapping("/liveVerify")
	public String liveVerify() {
		return "marketing/liveVerify";
	}
	
	@RequestMapping("/studyRecord")
	public String studyRecord() {
		return "marketing/studyRecord";
	}
	
	@RequestMapping("/liveAccess")
	public String liveAccess() {
		return "marketing/liveAccess";
	}
	@RequestMapping("/livepay")
	public String livePay() {
		return "marketing/LivePay";
	}
}
