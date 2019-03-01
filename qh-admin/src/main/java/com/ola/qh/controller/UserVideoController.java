package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.UserVideo;
import com.ola.qh.service.IUserVideoService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/uservideo")
public class UserVideoController {

	@Autowired
	private IUserVideoService userVideoService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<UserVideo>> videoList(@RequestParam(name="pageNo",required=true)int pageNo,
			@RequestParam(name="pageSize",required=true)int pageSize,@RequestParam(name="videoName",required=false)String videoName){
		
		
		
		return userVideoService.videoList(pageNo, pageSize, videoName);
		

	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteVideo(@RequestParam(name="id",required=true)String id){
		
		
		
		return userVideoService.deleteVideo(id);
		

	}
}
