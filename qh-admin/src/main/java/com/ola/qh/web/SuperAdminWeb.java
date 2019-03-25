package com.ola.qh.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/super")
public class SuperAdminWeb {


	
		@RequestMapping("/superAdmin")
		public String superadmin(){
			return "super/super-admin";
		}
		
	
}
