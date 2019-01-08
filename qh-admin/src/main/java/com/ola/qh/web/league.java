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
	
}
