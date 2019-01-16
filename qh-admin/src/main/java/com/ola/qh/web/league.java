package com.ola.qh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/league")
public class league {

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
	@RequestMapping("/xiangmu")
	public String xiangmu(){
		return "league/xiangmu";
	}
}
