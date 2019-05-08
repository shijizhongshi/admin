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
	public String section(@RequestParam(name="chapterId") String chapterId,
			@RequestParam(name="chapterName") String chapterName,HttpServletRequest request){
		request.getSession().setAttribute("chapterId", chapterId);
		request.getSession().setAttribute("chapterName", chapterName);
		return "courses/course-jie";
	}

	//////修改章页面里边的参数~~
	@RequestMapping(value="/chapter",method=RequestMethod.GET)
	public String chapter(
			@RequestParam(name="courseId")String courseId,
			@RequestParam(name="typeId")String typeId,
			@RequestParam(name="courseTypeName")String courseTypeName,
			@RequestParam(name="courseTypeSubclassName")String courseTypeSubclassName,
			HttpServletRequest request){
		request.getSession().setAttribute("courseId", courseId);
		request.getSession().setAttribute("typeId", typeId);
		request.getSession().setAttribute("courseTypeName", courseTypeName);
		request.getSession().setAttribute("courseTypeSubclassName", courseTypeSubclassName);
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
	@RequestMapping(value="/grade-template",method=RequestMethod.GET)
	public String gradetemplate(){
		return "courses/grade-template";
	}
	@RequestMapping(value="/audition",method=RequestMethod.GET)
	public String audition(){
		return "courses/audition";
	}

	@RequestMapping(value="/teacher",method=RequestMethod.GET)
	public String teacher(){
		return "courses/teacher";
	}
	@RequestMapping("/live")
	public String Live(){
		return "courses/live";
	}
	@RequestMapping("/toWhite")
	public String toWhite(@RequestParam(name="liveId")String liveId,
			@RequestParam(name="liveName")String liveName,
			HttpServletRequest request){
		request.getSession().setAttribute("liveId", liveId);
		request.getSession().setAttribute("liveName", liveName);
		return "courses/line-white";
	}
	@RequestMapping(value="/faceteache",method=RequestMethod.GET)
	public String faceteache(){
		return "courses/face-teache";
	}
}
