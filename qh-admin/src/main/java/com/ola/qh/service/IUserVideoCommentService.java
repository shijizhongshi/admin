package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserVideoComment;
import com.ola.qh.util.Results;

public interface IUserVideoCommentService {

	public Results<List<UserVideoComment>> videoCommentList(int pageNo,int pageSize,String vid);
	
	public Results<List<UserVideoComment>> videoCommentsList(int pageNo,int pageSize,String vid,String id);
	
	public Results<String> videoCommentDelete(String vid,String id,String commentid);
}
