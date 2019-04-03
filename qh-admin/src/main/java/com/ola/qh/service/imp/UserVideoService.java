package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.UserVideoCommentDao;
import com.ola.qh.dao.UserVideoDao;
import com.ola.qh.entity.UserVideo;
import com.ola.qh.service.IUserVideoService;
import com.ola.qh.util.Results;

@Service
public class UserVideoService implements IUserVideoService{

	@Autowired
	private UserVideoDao userVideoDao;
	
	@Autowired
	private UserVideoCommentDao userVideoCommentDao;

	@Transactional
	@Override
	public Results<List<UserVideo>> videoList(int pageNo, int pageSize, String videoName) {
		
		Results<List<UserVideo>> results=new Results<List<UserVideo>>();
		
		try {
			
		List<UserVideo> videoList=userVideoDao.videoList(pageNo, pageSize, videoName);
		int count=userVideoDao.videoCount( videoName);
		for (UserVideo userVideo : videoList) {
			int videoCount=userVideoCommentDao.videoCommentCount(userVideo.getId());
			userVideo.setCommentNumber(videoCount);
			
			
		}
		
		results.setStatus("0");
		results.setData(videoList);
		results.setCount(count);
		return results;
		
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

	@Transactional
	@Override
	public Results<String> deleteVideo(String id) {
		
			Results<String> results=new Results<String>();
			
			try {
				
				userVideoDao.deleteVideo(id);
				userVideoCommentDao.videoCommentDelete(id, null,null);
				
				results.setStatus("0");
				return results;
			} catch (Exception e) {
				
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				results.setStatus("1");
				return results;
			}
			
	}
}
