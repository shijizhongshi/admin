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

	//////修改章页面里边的参数~~
	@RequestMapping(value="/chapter",method=RequestMethod.GET)
	public String chapter(
			@RequestParam(name="courseId")String courseId,
			@RequestParam(name="courseTypeName")String courseTypeName,
			@RequestParam(name="courseTypeSubclassName")String courseTypeSubclassName){
		return "courses/course-zhang";
	}
	
	@RequestMapping(value="/course",method=RequestMethod.GET)
	public String courses(){
		return "courses/course";
	}
	@RequestMapping(value="/grade",method=RequestMethod.GET)
	public String grade(){
		return "courses/grade";
	}
}
