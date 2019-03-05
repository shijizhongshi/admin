package com.ola.qh.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web/league")
public class LeagueWeb {

	@RequestMapping("/fuwu")
	public String fuwu(){
		return "league/fuwu";
	}
	@RequestMapping("/shangpin")
	public String shangpin(){
		return "league/shangpin";
	}
	@RequestMapping("/yishi")
	public String yishi(){
		return "league/yishi";
	}
	@RequestMapping(value="/xiangmu",method=RequestMethod.GET)
	public String xiangmu(@RequestParam(name="shopId")String shopId,HttpServletRequest request){
		
			request.getSession().setAttribute("shopId", shopId);
			
		return "league/xiangmu";
	}
	
	
	@RequestMapping(value="/shortshop",method=RequestMethod.GET)
	public String shortShop(){
		return "league/short_shop";
	}
}
