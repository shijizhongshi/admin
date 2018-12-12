package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.CommentText;

public interface CommentTextDao {

	public List<CommentText> selectCommentText();
	
	public int updateCommentText(CommentText commentText);
	
	public int insertCommentText(CommentText commentText);
	
	public int deleteCommentText(@Param("id")String id);
}
