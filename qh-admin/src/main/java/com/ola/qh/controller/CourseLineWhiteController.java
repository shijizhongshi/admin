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

import com.ola.qh.entity.CourseLineWhite;
import com.ola.qh.service.ICourseLineWhiteService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/LineWhite")
public class CourseLineWhiteController {

	@Autowired
	private ICourseLineWhiteService courseLineWhiteService;
	
	@RequestMapping(value = "/improtExcel", method = RequestMethod.POST, consumes = "multipart/form-data")
	public Results<String> improtExcel(@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "liveId") String liveId) throws Exception {

		return courseLineWhiteService.importExcel(file, liveId);
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<CourseLineWhite>> lineWhiteList(@RequestParam(name="liveId",required=false)String liveId,
			@RequestParam(name="username",required=false)String username,
			@RequestParam(name="pageNo",required=true)int pageNo,@RequestParam(name="pageSize",required=true)int pageSize){
		
		return courseLineWhiteService.lineWhiteList(liveId,username, pageNo, pageSize);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertLineWhite(@RequestBody @Valid CourseLineWhite courseLineWhite,BindingResult valid){
		
		Results<String> results=new Results<String>();
		if(valid.hasErrors()){
			results.setMessage("信息不足");
			results.setStatus("1");
			return results;
		}
		return courseLineWhiteService.insertLineWhite(courseLineWhite);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateLineWhite(@RequestBody CourseLineWhite courseLineWhite){
		
		return courseLineWhiteService.updateLineWhite(courseLineWhite);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteLineWhite(@RequestParam(name="id",required=true)String id){
		
		Results<String> results=new Results<String>();
		
		int delete=courseLineWhiteService.deleteLineWhite(id, null);
		if(delete<=0){
			
			results.setStatus("1");
			return results;
		}
		
		results.setStatus("0");
		return results;
	}
	
	@RequestMapping(value="/deleteAll",method=RequestMethod.GET)
	public Results<String> deleteAll(@RequestParam(name="liveId",required=true)String liveId){
		
		Results<String> results=new Results<String>();
		
		int delete=courseLineWhiteService.deleteLineWhite(null, liveId);
		if(delete<=0){
			
			results.setStatus("1");
			return results;
		}
		
		results.setStatus("0");
		return results;
	}
}
