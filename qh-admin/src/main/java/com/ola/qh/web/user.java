package com.ola.qh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/user")
public class  user {

	@RequestMapping("/tixian")
	public String fuwu(){
		return " user/tixian";
	}
	@RequestMapping("/userinfo")
	public String shangpin(){
		return " user/userinfo";
	}
	@RequestMapping("/user-dingdan")
	public String userdingdan(){
		return " user/user-dingdan";
	}
	
}
