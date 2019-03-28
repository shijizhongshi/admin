package com.ola.qh.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web/questionBank")
public class QuestionBankWeb {

	@RequestMapping("/questionChapter")
	public String questionChapter(){
		return "questionBank/questionChapter";
	}
	@RequestMapping(value="/questionJie",method=RequestMethod.GET)
	public String questionJie(@RequestParam(name="id",required=true)String id,
			@RequestParam(name="name",required=true)String name,HttpServletRequest request){
		
		request.getSession().setAttribute("id", id);
		request.getSession().setAttribute("name", name);
		return "questionBank/questionJie";
	}
	@RequestMapping("/examination")
	public String examination(){
		return "questionBank/examination";
	}
	@RequestMapping("/examination-element")
	public String element(@RequestParam(name="id",required=true)String id,
			@RequestParam(name="name",required=true)String name,HttpServletRequest request){
		request.getSession().setAttribute("id", id);
		request.getSession().setAttribute("name", name);
		return "questionBank/examination-element";
	}
	@RequestMapping("/feedback")
	public String  feedback(){
		return "questionBank/feedback";
	}
	}
