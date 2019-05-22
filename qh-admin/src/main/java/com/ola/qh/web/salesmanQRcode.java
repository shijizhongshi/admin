package com.ola.qh.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web/salesmanQRcode")
public class salesmanQRcode {
	@RequestMapping("/register")
	public String  register(){
		return "salesmanQRcode/register";
	}
}
