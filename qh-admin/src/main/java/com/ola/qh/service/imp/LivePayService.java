package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.LivePayDao;
import com.ola.qh.entity.LivePay;
import com.ola.qh.service.ILivePayService;
import com.ola.qh.util.Results;

@Service
public class LivePayService implements ILivePayService{

	@Autowired
	private LivePayDao livePayDao;

	@Transactional
	@Override
	public Results<List<LivePay>> livePayList(String status,String livename,String startTime,int pageNo,int pageSize) {
		
		Results<List<LivePay>> results=new Results<List<LivePay>>();
		
		try {
			
			List<LivePay> list=livePayDao.livePayList(status,livename,startTime, pageNo, pageSize,1);
			
			int count=livePayDao.livePayCount(status,livename,startTime);
			
			results.setData(list);
			results.setCount(count);
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}
	
	
}
