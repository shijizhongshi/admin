package com.ola.qh.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.entity.CourseLineWhite;
import com.ola.qh.util.Results;

public interface ICourseLineWhiteService {
	
	public Results<String> importExcel(MultipartFile file,String liveId) throws Exception;

	public Results<List<CourseLineWhite>> lineWhiteList(String liveId,String mobile,int pageNo,int pageSize);
	
	public Results<String> insertLineWhite(CourseLineWhite courseLineWhite);
	
	public Results<String> updateLineWhite(CourseLineWhite courseLineWhite);
	
	public int deleteLineWhite(String id,String liveId);
}
