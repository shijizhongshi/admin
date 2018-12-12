package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CommentTextDao;
import com.ola.qh.entity.CommentText;
import com.ola.qh.service.ICommentTextService;

@Service
public class CommentTextService implements ICommentTextService{

	@Autowired 
	private CommentTextDao commentTextDao;

	@Override
	public List<CommentText> selectCommentText() {
		
		return commentTextDao.selectCommentText();
	}

	@Override
	public int updateCommentText(CommentText commentText) {
		
		return commentTextDao.updateCommentText(commentText);
	}

	@Override
	public int insertCommentText(CommentText commentText) {
		
		return commentTextDao.insertCommentText(commentText);
	}

	@Override
	public int deleteCommentText(String id) {
		
		return commentTextDao.deleteCommentText(id);
	}
	
}
