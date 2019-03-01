package com.ola.qh.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserWeb {

	
	@RequestMapping("/questionBank")
	public String questionBank(){
		return "questionBank";
	}
	

	@RequestMapping(value="/",method=RequestMethod.GET)
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
	
	@RequestMapping("/web/user/withdrawlist")
	public String fuwu(){
		return " user/withdrawlist";
	}
	@RequestMapping("/web/user/userinfo")
	public String shangpin(@RequestParam(name="nickname") String nickname,HttpServletRequest request){
		request.getSession().setAttribute("nickname", nickname);
		return " user/userinfo";
	}
	@RequestMapping("/web/user/business")
	public String franchisee(){
		return " user/business";
	}

}
