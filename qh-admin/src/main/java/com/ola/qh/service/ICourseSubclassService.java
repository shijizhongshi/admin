package com.ola.qh.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.util.Results;

public interface ICourseSubclassService {

	public List<CourseChapter> courseChapterList(String courseId,String courseChapterName,int pageNo,int pageSize,String courseTypeName,String courseTypeSubclassName);
	
	public int courseChapterListCount(String courseId, String courseChapterName,String courseTypeName,String courseTypeSubclassName);

	public Results<String> courseChapterSaveUpdate(CourseChapter ccp);
	
	public int deleteChapter(String id);
	
	
	public List<CourseSection> courseSectionList(String courseChapterId,int pageNo,int pageSize);


	public int courseSectionListCount(String courseChapterId);
	
	public int deleteSerction(String id);
	
	public Results<String> courseSectionSaveUpdate(CourseSection cs);
	
	
	public Results<String> importExcel(MultipartFile file,String courseChapterId)throws Exception;
	
	
	
	public Results<String> sectionOrders(String id,int orders,String operateType,String tables,String comment);

	public CourseChapter selectNameAndCTSN(String videoid);
	
}
