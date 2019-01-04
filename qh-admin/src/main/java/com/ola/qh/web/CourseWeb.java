package com.ola.qh.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web/course")
public class CourseWeb{
	
	
	@RequestMapping(value="/subnav",method=RequestMethod.GET)
	public String UserWebsubnav(@RequestParam(name="type") int type,HttpServletRequest request){
		request.getSession().setAttribute("type", type);
		return "courses/subnav";
	}
	@RequestMapping(value="/course-jie",method=RequestMethod.GET)
	public String UserWebq(@RequestParam(name="chapterId") String chapterId,HttpServletRequest request){
		request.getSession().setAttribute("chapterId", chapterId);
		return "courses/course-jie";
	}

	@RequestMapping(value="/course-zhang",method=RequestMethod.GET)
	public String UserWebw(){
		return "courses/course-zhang";
	}
	
	@RequestMapping(value="/course",method=RequestMethod.GET)
	public String UserWeb(){
		return "courses/course";
	}

@RequestMapping(value="/newtest",method=RequestMethod.GET)
public String newtest(){
	return "courses/new";
}
}