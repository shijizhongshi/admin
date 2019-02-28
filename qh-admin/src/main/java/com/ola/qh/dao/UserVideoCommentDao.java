package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserVideoComment;

public interface UserVideoCommentDao {

	public List<UserVideoComment> videoCommentList(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,
			@Param("vid")String vid);
	
	public List<UserVideoComment> videoCommentsList(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,
			@Param("vid")String vid,@Param("id")String id);
	
	public int videoCommentCount(@Param("vid")String vid);
	
	public int videoCommentsCount(@Param("vid")String vid,@Param("id")String id);
	
	public int videoCommentDelete(@Param("vid")String vid,@Param("id")String id,@Param("commentid")String commentid);
}
