package com.ola.qh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserWeb {

	@RequestMapping(value="/course",method=RequestMethod.GET)
	public String UserWeb(){
		return "courses/course";
	}
	@RequestMapping("/questionBank")
	public String questionBank(){
		return "questionBank";
	}
	

	@RequestMapping(value="/coursee",method=RequestMethod.GET)
	public String UserWebq(){
		return "courses/coursee";
	}

	@RequestMapping(value="/chapter-manage",method=RequestMethod.GET)
	public String UserWebw(){
		return "courses/chapter-manage";
	}
	@RequestMapping(value="/web/login",method=RequestMethod.GET)
	public String UserWebload(){
		return "login";
	}
	@RequestMapping(value="/web/loginout",method=RequestMethod.GET)
	public String UserLoginout(){
		return "login";
	}
		
	
}
