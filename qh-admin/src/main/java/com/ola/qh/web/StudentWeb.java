package com.ola.qh.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web/student")
public class StudentWeb {

	@RequestMapping("/management")
	public String management() {
		return "student/management";
	}

	@RequestMapping("/classbuy")
	public String classbuy(@RequestParam(name="classId") String classId,HttpServletRequest request) {
		request.getSession().setAttribute("classId", classId);
		return "student/classbuy";
	}

	@RequestMapping("/coursebuy")
	public String coursebuy(@RequestParam(name="courseId") String courseId,HttpServletRequest request) {
		request.getSession().setAttribute("courseId",courseId);
		return "student/coursebuy";
	}
	
	@RequestMapping("/studentinfo")
	public String studentinfo () {
		return "student/studentinfo";
	}

	@RequestMapping("/videorecord")
	public String videoRecord () {
		return "student/videorecord";
	}
}
