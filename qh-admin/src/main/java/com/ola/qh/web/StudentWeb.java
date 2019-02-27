package com.ola.qh.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/student")
public class StudentWeb {

	@RequestMapping("/management")
	public String management(){
		return "student/management";
	}
	
		@RequestMapping("/classbuy")
		public String classbuy(){
			return "student/classbuy";
		}
		@RequestMapping("/coursebuy")
		public String coursebuy(){
			return "student/coursebuy";
		}
		
		
	
}
