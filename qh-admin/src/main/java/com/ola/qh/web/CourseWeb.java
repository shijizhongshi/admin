package com.ola.qh.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web/course")
public class CourseWeb{
	
	
	@RequestMapping(value="/section",method=RequestMethod.GET)
	public String section(@RequestParam(name="chapterId") String chapterId,HttpServletRequest request){
		request.getSession().setAttribute("chapterId", chapterId);
		return "courses/course-jie";
	}

	@RequestMapping(value="/chapter",method=RequestMethod.GET)
	public String chapter(){
		return "courses/course-zhang";
	}
	
	@RequestMapping(value="/course",method=RequestMethod.GET)
	public String courses(){
		return "courses/course";
	}
}