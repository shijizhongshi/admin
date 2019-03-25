package com.ola.qh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/student")
public class studentWeb {

	@RequestMapping("/management")
	public String management() {
		return "student/management";
	}

	@RequestMapping("/Online-course")
	public String online() {
		return "student/Online-course";
	}

	@RequestMapping("/student-course")
	public String student() {
		return "student/student-course";
	}
	
	@RequestMapping("/studentinfo")
	public String studentinfo () {
		return "student/studentinfo";
	}

}
