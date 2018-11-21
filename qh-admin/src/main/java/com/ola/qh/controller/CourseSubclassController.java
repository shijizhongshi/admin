package com.ola.qh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.service.ICourseSubclassService;
import com.ola.qh.util.Patterns;
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
	 * page==0的时候表示全查
	 * <p>Title: ListCourseChapter</p>  
	 * <p>Description: </p>  
	 * @param courseId
	 * @return
	 */
	@RequestMapping("/courseChapterList")
	public Results<List<CourseChapter>> ListCourseChapter(@RequestParam(name="courseId",required=false)String courseId,@RequestParam(name="page",required=false)int page){
		
		Results<List<CourseChapter>> result = new Results<List<CourseChapter>>();
		int pageNo=(page-1)*Patterns.pageSize;
		int pageSize;
		if(page==0){
			pageSize=0;
		}else{
			pageSize=Patterns.pageSize;
		}
		result.setData(courseSubclassService.courseChapterList(courseId,pageNo,pageSize));
		result.setStatus("0");
		return result;
	}
	
	
	/**
	 * 章的添加或者修改
	 * <p>Title: chapterSaveUpdate</p>  
	 * <p>Description: </p>  
	 * @param ccp
	 * @param valid
	 * @return
	 */
	@RequestMapping("/courseChapter/saveUpdate")
	public Results<String> chapterSaveUpdate(@RequestBody @Valid CourseChapter ccp,BindingResult valid){
		 Results<String> result = new  Results<String>();
		 if(ccp.getId()==null || "".equals(ccp.getId())){
			 if(valid.hasErrors()){
				 result.setStatus("1");
				 result.setMessage("章的信息不完整");
				 return result;
			 }
		 }
		 return  courseSubclassService.courseChapterSaveUpdate(ccp);
	}
	
	/**
	 * 通过章的id查出对应的节的列表
	 * <p>Title: listCourseSection</p>  
	 * <p>Description: </p>  
	 * @param courseChapterId
	 * @return
	 */
	@RequestMapping("/courseSectionList")
	public Results<List<CourseSection>> listCourseSection(@RequestParam(name="courseChapterId",required=true)String courseChapterId,@RequestParam(name="page",required=false,defaultValue="0")int page){
		
		Results<List<CourseSection>> result = new Results<List<CourseSection>>();
		int pageNo=(page-1)*Patterns.pageSize;
		int pageSize;
		if(page==0){
			pageSize=0;
		}else{
			pageSize=Patterns.pageSize;
		}
		result.setData(courseSubclassService.courseSectionList(courseChapterId,pageNo,pageSize));
		result.setStatus("0");
		return result;
	}
	
	
	/**
	 * 节的添加或者修改
	 * <p>Title: sectionSaveUpdate</p>  
	 * <p>Description: </p>  
	 * @param cs
	 * @param valid
	 * @return
	 */
	@RequestMapping("/courseSection/saveUpdate")
	public Results<String> sectionSaveUpdate(@RequestBody @Valid CourseSection cs,BindingResult valid){
		 Results<String> result = new  Results<String>();
		 if(cs.getId()==null || "".equals(cs.getId())){
			 if(valid.hasErrors()){
				 result.setStatus("1");
				 result.setMessage("节的信息不完整");
				 return result;
			 }
		 }
		 return  courseSubclassService.courseSectionSaveUpdate(cs);
	}
}
