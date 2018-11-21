package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.service.ICourseSubclassService;
import com.ola.qh.util.Results;
/**
 * 
* @ClassName: CourseSubclassController  
* @Description: 课程的章和课程的节的处理  
* @author guoyuxue  
* @date 2018年11月20日  
*
 */
@RestController
@RequestMapping("/api/course/subclass")
public class CourseSubclassController {

	@Autowired
	private ICourseSubclassService courseSubclassService;
	/**
	 * 通过课程的id查对应的章的id
	 * <p>Title: ListCourseChapter</p>  
	 * <p>Description: </p>  
	 * @param courseId
	 * @return
	 */
	@RequestMapping("/courseChapterList")
	public Results<List<CourseChapter>> ListCourseChapter(@RequestParam(name="courseId",required=true)String courseId){
		
		Results<List<CourseChapter>> result = new Results<List<CourseChapter>>();
		
		result.setData(courseSubclassService.courseChapterList(courseId));
		result.setStatus("0");
		return result;
	}
	
	/**
	 * 通过章的id查出对应的节的列表
	 * <p>Title: listCourseSection</p>  
	 * <p>Description: </p>  
	 * @param courseChapterId
	 * @return
	 */
	@RequestMapping("/courseSectionList")
	public Results<List<CourseSection>> listCourseSection(@RequestParam(name="courseChapterId",required=true)String courseChapterId){
		
		Results<List<CourseSection>> result = new Results<List<CourseSection>>();
		
		result.setData(courseSubclassService.courseSectionList(courseChapterId));
		result.setStatus("0");
		return result;
	}
}
