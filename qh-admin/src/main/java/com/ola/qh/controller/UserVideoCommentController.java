package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.UserVideoComment;
import com.ola.qh.service.IUserVideoCommentService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/videocomment")
public class UserVideoCommentController {

	@Autowired
	private IUserVideoCommentService userVideoCommentService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<UserVideoComment>> videoCommentList(@RequestParam(name="pageNo",required=true)int pageNo,
			@RequestParam(name="pageSize",required=true)int pageSize,@RequestParam(name="vid",required=true)String vid){
		
			return userVideoCommentService.videoCommentList(pageNo, pageSize, vid);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> videoCommentDelete(@RequestParam(name="id",required=false)String id,@RequestParam(name="commentid",required=false)String commentid){
		
		return userVideoCommentService.videoCommentDelete(null, id, commentid);
	}
	
	@RequestMapping(value="/conmmentlist",method=RequestMethod.GET)
	public Results<List<UserVideoComment>> videoCommentDelete(@RequestParam(name="pageNo",required=true)int pageNo,
			@RequestParam(name="pageSize",required=true)int pageSize,@RequestParam(name="vid",required=true)String vid
			,@RequestParam(name="id",required=true)String id){
		
		return userVideoCommentService.videoCommentsList(pageNo, pageSize, vid, id);
	}
}
