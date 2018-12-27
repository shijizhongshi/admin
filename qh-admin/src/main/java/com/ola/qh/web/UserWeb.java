package com.ola.qh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserWeb {

	@RequestMapping(value="/course",method=RequestMethod.GET)
	public String UserWeb(){
		return "course";
	}
	

	@RequestMapping(value="/coursee",method=RequestMethod.GET)
	public String UserWebq(){
		return "coursee";
	}
	
	
	
}
