package com.ola.qh.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/super")
public class superAdmin {


	
		@RequestMapping("/superAdmin")
		public String superadmin(){
			return "super/super-admin";
		}
		
	
}
