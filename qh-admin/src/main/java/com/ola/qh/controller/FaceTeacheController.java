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

import com.ola.qh.entity.FaceTeache;
import com.ola.qh.service.IFaceTeacheService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/faceTeache")
public class FaceTeacheController {

	@Autowired
	private IFaceTeacheService faceTeacheService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<FaceTeache>> faceTeacheList(@RequestParam(name="courseTypeSubclassName",required=false)String courseTypeSubclassName,
			@RequestParam(name="teacherName",required=false)String teacherName,@RequestParam(name="courseName",required=false)String courseName,
			@RequestParam(name="pageNo",required=true)int pageNo,@RequestParam(name="pageSize",required=true)int pageSize){
		
		return faceTeacheService.faceTeacheList(courseTypeSubclassName, teacherName, courseName, pageNo, pageSize);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertFaceTeache(@RequestBody @Valid FaceTeache faceTeache,BindingResult valid){
		
		Results<String> results=new Results<String>();
		if(valid.hasErrors()){
			results.setStatus("1");
			results.setMessage("信息不足~");
			return results;
		}
		return faceTeacheService.insertFaceTeache(faceTeache);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> faceTeacheList(@RequestBody FaceTeache faceTeache){
		
		return faceTeacheService.updateFaceTeache(faceTeache);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteFaceTeache(@RequestParam(name="id",required=true)String id){
		
		return faceTeacheService.deleteFaceTeache(id);
	}
}
