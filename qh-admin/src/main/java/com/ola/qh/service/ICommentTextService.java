package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.CommentText;

public interface ICommentTextService {
	
	public List<CommentText> selectCommentText();
	
	public int updateCommentText(CommentText commentText);
	
	public int insertCommentText(CommentText commentText);
	
	public int deleteCommentText(String id);
}
