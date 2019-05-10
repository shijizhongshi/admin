package com.ola.qh.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web/salesman")
public class SalesmanWeb {

	@RequestMapping(value="/salesmans",method=RequestMethod.GET)
	public String salesmans(){
		return "salesman/salesmans";
	}
	
	@RequestMapping(value="/salesmanClient",method=RequestMethod.GET)
	public String salesmanClient(@RequestParam(name="salesmanId",required=true)String salesmanId,HttpServletRequest request){
		request.getSession().setAttribute("salesmanId", salesmanId);
		return "salesman/salesmanClient";
	}
}
