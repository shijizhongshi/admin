package com.ola.qh.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/student")
public class studentWeb {

	@RequestMapping("/management")
	public String management(){
		return "student/management";
	}
	
	
}
