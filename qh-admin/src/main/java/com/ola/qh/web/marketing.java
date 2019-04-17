package com.ola.qh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/marketing")
public class marketing {

	@RequestMapping("/questionbankH5")
	public String courseOrders() {
		return "marketing/questionbankH5";
	}
	
	@RequestMapping("/liveVerify")
	public String courseOrderss() {
		return "marketing/liveVerify";
	}

}
