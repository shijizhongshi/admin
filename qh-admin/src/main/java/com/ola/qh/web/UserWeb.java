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
	

	@RequestMapping(value="/course-jie",method=RequestMethod.GET)
	public String UserWebq(){
		return "courses/course-jie";
	}

	@RequestMapping(value="/course-zhang",method=RequestMethod.GET)
	public String UserWebw(){
		return "courses/course-zhang";
	}
	@RequestMapping(value="/grade",method=RequestMethod.GET)
	public String UserWebgrade(){
		return "courses/grade";
	}
	@RequestMapping(value="/web/login",method=RequestMethod.GET)
	public String UserWebload(){
		return "login";
	}
	@RequestMapping(value="/web/loginout",method=RequestMethod.GET)
	public String UserLoginout(){
		return "login";
	}
		
	@RequestMapping(value="/main",method=RequestMethod.GET)
	public String UserWebmain(){
		return "main";
	}
	
	
@RequestMapping(value="subnav",method=RequestMethod.GET)
public String UserWebsubnav(){
	return "courses/subnav";
}
}
