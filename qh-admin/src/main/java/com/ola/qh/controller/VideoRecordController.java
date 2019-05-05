package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.VideoRecord;
import com.ola.qh.service.IVideoRecordService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/videorecord")
public class VideoRecordController {

	@Autowired
	private IVideoRecordService videoRecordService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<VideoRecord>> VideoRecordList(@RequestParam(name="mobile",required=true)String mobile,
			@RequestParam(name="courseName",required=false)String courseName,@RequestParam(name="chapterName",required=false)String chapterName,
			@RequestParam(name="sectionName",required=false)String sectionName,@RequestParam(name="pageSize",required=true)int pageSize,
			@RequestParam(name="pageNo",required=true)int pageNo){
		
		return videoRecordService.VideoRecordList(mobile, courseName, chapterName, sectionName, pageNo, pageSize);
	}
	
	
}
