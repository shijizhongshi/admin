package com.ola.qh.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.UserDao;
import com.ola.qh.dao.VideoRecordDao;
import com.ola.qh.entity.VideoRecord;
import com.ola.qh.service.IVideoRecordService;
import com.ola.qh.util.Results;

@Service
public class VideoRecordService implements IVideoRecordService{

	@Autowired
	private VideoRecordDao videoRecordDao;
	@Autowired
	private UserDao userDao;
	
	@Transactional
	@Override
	public Results<List<VideoRecord>> VideoRecordList(String mobile, String courseName, String chapterName, String sectionName,int pageNo,int pageSize) {
		
		Results<List<VideoRecord>> results=new Results<List<VideoRecord>>();
		
		try {
			String userId=userDao.selectIdByMobile(mobile);
			
			List<VideoRecord> list=videoRecordDao.VideoRecordList(userId, courseName, chapterName, sectionName, pageNo, pageSize);
			int count=videoRecordDao.VideoRecordCount(userId, courseName, chapterName, sectionName);
			
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
