package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.PlayBackDao;
import com.ola.qh.entity.PlayBack;
import com.ola.qh.service.IPlayBackService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class PlayBackService implements IPlayBackService{

	@Autowired
	private PlayBackDao playBackDao;

	@Transactional
	@Override
	public Results<List<PlayBack>> playBackList(String liveId,String playbackName,int pageNo,int pageSize) {
		
		Results<List<PlayBack>> results=new Results<List<PlayBack>>();
		
		try {
			
			List<PlayBack> list=playBackDao.playBackList(liveId,playbackName,pageNo,pageSize);
			int count=playBackDao.playBackCount(liveId, playbackName);
			
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
	public Results<String> insertPlayBack(PlayBack playBack) {


		Results<String> results=new Results<String>();
		
		try {
			
			playBack.setId(KeyGen.uuid());
			playBack.setAddtime(new Date());
			int add=playBackDao.insertPlayBack(playBack);
			
			if(add<=0){
				results.setMessage("添加失败");
				results.setStatus("1");
				return results;
			}
			
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
	public Results<String> updatePlayBack(PlayBack playBack) {


		Results<String> results=new Results<String>();
		
		try {
			
			playBack.setUpdatetime(new Date());
			int update=playBackDao.updatePlayBack(playBack);
			
			if(update<=0){
				results.setMessage("修改失败");
				results.setStatus("1");
				return results;
			}
			
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
	public Results<String> deletePlayBack(String id) {


		Results<String> results=new Results<String>();
		
		try {
			
			int delete=playBackDao.deletePlayBack(id);
			if(delete<=0){
				results.setMessage("删除失败");
				results.setStatus("1");
				return results;
			}
			
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

	@Override
	public int existPlayBack(String liveId) {
		
		return playBackDao.existPlayBack(liveId);
	}
}
