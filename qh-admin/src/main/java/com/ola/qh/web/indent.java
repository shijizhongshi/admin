package com.ola.qh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/indent")
public class indent {

	@RequestMapping("/dingdan")
	public String dingdan(){
		return "indent/dingdan";
	}
	@RequestMapping("/today-bargain")
	public String todaybargain(){
		return "indent/today-bargain";
	}
	}
