package com.ola.qh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/system")
public class SystemWeb {

	@RequestMapping("/banner")
	public String banner(){
		return "system/banner";
	}
	@RequestMapping("/news")
	public String zixun(){
		return "system/zixun";
	}

	@RequestMapping("/zhuanti")
	public String zhuanti(){
		return "system/zhuanti";
	}
	@RequestMapping("/fenlei")
	public String fenlei(){
		return "system/fenlei";
	}
	@RequestMapping("/superAdmin")
	public String superadmin(){
		return "system/super-admin";
	}
	
}
