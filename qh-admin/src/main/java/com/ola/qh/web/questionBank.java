package com.ola.qh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/questionBank")
public class questionBank {

	@RequestMapping("/questionChapter")
	public String questionChapter(){
		return "questionBank/questionChapter";
	}
	@RequestMapping("/questionJie")
	public String questionJie(){
		return "questionBank/questionJie";
	}
	@RequestMapping("/examination")
	public String examination(){
		return "questionBank/examination";
	}
	@RequestMapping("/examination-element")
	public String element(){
		return "questionBank/examination-element";
	}
	@RequestMapping("/ feedback")
	public String  feedback(){
		return "questionBank/ feedback";
	}
	}
