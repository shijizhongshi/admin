package com.ola.qh.service.imp;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.UserVideoCommentDao;
import com.ola.qh.entity.UserVideoComment;
import com.ola.qh.service.IUserVideoCommentService;
import com.ola.qh.util.Results;

@Service
public class UserVideoCommentService implements IUserVideoCommentService{

	@Autowired
	private UserVideoCommentDao userVideoCommentDao;

	@Transactional
	@Override
	public Results<List<UserVideoComment>> videoCommentList(int pageNo, int pageSize, String vid) {
		
				Results<List<UserVideoComment>> results=new Results<List<UserVideoComment>>();
				
				try {
					
					List<UserVideoComment> list=userVideoCommentDao.videoCommentList(pageNo, pageSize, vid);
					
					for (UserVideoComment userVideoComment : list) {
						
						SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						userVideoComment.setShowtime(sf.format(userVideoComment.getAddtime()));
						
					}
					
					int count=userVideoCommentDao.videoCommentCount(vid);
					 
					results.setCount(count);
					results.setData(list);
					results.setStatus("0");
					return results;
				} catch (Exception e) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					results.setStatus("1");
					return results;
				}
	}

	@Transactional
	@Override
	public Results<String> videoCommentDelete(String vid, String id, String commentid) {
		
		Results<String> results=new Results<String>();
		
		try {
			
		
		userVideoCommentDao.videoCommentDelete(null, id, null);
		userVideoCommentDao.videoCommentDelete(null, null, id);
		
		results.setStatus("0");
		return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

	@Transactional
	@Override
	public Results<List<UserVideoComment>> videoCommentsList(int pageNo, int pageSize, String vid, String id) {
		
		Results<List<UserVideoComment>> results=new Results<List<UserVideoComment>>();
		
		try {
			
			List<UserVideoComment> list=userVideoCommentDao.videoCommentsList(pageNo, pageSize, vid, id);
			
			for (UserVideoComment userVideoComment : list) {
				
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				userVideoComment.setShowtime(sf.format(userVideoComment.getAddtime()));
				
			}
			
			int count=userVideoCommentDao.videoCommentsCount(vid, id);
			 
			results.setCount(count);
			results.setData(list);
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

	
	
}
