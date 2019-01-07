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

}
