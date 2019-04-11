package com.ola.qh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public Results<List<CourseChapter>> ListCourseChapter(
			@RequestParam(name="courseId",required=false)String courseId,
			@RequestParam(name="courseChapterName",required=false)String courseChapterName,
			@RequestParam(name="pageNo",required=true)int pageNo,
			@RequestParam(name="pageSize",required=true)int pageSize,
			@RequestParam(name="courseTypeName",required=false)String courseTypeName,
			@RequestParam(name="courseTypeSubclassName",required=false)String courseTypeSubclassName){
		
		Results<List<CourseChapter>> result = new Results<List<CourseChapter>>();
		
		result.setData(courseSubclassService.courseChapterList(courseId,courseChapterName,pageNo,pageSize,courseTypeName,courseTypeSubclassName));
		result.setCount(courseSubclassService.courseChapterListCount(courseId,courseChapterName, courseTypeName, courseTypeSubclassName));
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
	
	@RequestMapping("/courseChapter/delete")
	public Results<String> chapterdelete(@RequestParam("chapterId")String chapterId){
		 Results<String> result = new  Results<String>();
		 int num = courseSubclassService.deleteChapter(chapterId);
		 if(num>0){
			 result.setStatus("0");
			 return result;
		 }
		 result.setStatus("1");
		 result.setMessage("删除失败");
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
	public Results<List<CourseSection>> listCourseSection(@RequestParam(name="courseChapterId",required=true)String courseChapterId,@RequestParam(name="page",required=false)int page){
		
		Results<List<CourseSection>> result = new Results<List<CourseSection>>();
		int pageNo=(page-1)*Patterns.pageSize;
		int pageSize;
		
			pageSize=Patterns.pageSize;
		
		result.setData(courseSubclassService.courseSectionList(courseChapterId,pageNo,pageSize));
		result.setCount(courseSubclassService.courseSectionListCount(courseChapterId));
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
	
	
	
	@RequestMapping(value="/upload/section",method=RequestMethod.POST,consumes = "multipart/form-data")
	public Results<String> uploadSection(@RequestParam(name="file",required=true) MultipartFile file,
			@RequestParam(name="courseChapterId",required=true)String courseChapterId) throws Exception{
		
		return courseSubclassService.importExcel(file, courseChapterId);
	}
	
	
	
	@RequestMapping("/section/delete")
	public Results<String> sectiondelete(@RequestParam("sectionId")String sectionId){
		 Results<String> result = new  Results<String>();
		 int num = courseSubclassService.deleteSerction(sectionId);
		 if(num>0){
			 result.setStatus("0");
			 return result;
		 }
		 result.setStatus("1");
		 result.setMessage("删除失败");
		 return result;
	}
	
	
	@RequestMapping("/sectionOrders")
	public Results<String> sectionOrders(@RequestParam(name="id",required=true)String id,
			@RequestParam(name="orders",required=true)int orders,
			@RequestParam(name="operateType",required=true)String operateType,
			@RequestParam(name="tables",required=true)String tables,
			@RequestParam(name="comment",required=true)String comment){
		return courseSubclassService.sectionOrders(id, orders, operateType,tables,comment);
	}
}
