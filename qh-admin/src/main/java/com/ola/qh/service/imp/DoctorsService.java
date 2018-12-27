package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.DoctorsDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.entity.Doctors;
import com.ola.qh.service.IDoctorsService;
import com.ola.qh.util.Results;
@Service
public class DoctorsService implements IDoctorsService{

	@Autowired
	private DoctorsDao coctorsDao;
	@Autowired
	private UserDao userDao;
	
	@Override 
	public List<Doctors> selectDoctors(int islimit) {
		
		return coctorsDao.selectDoctors(islimit);
	}

	@Transactional
	@Override
	public Results<String> updateDoctors(String id, int islimit,int isrecommend) {
		
		Results<String> results=new Results<String>();
		try {
			
		coctorsDao.updateDoctors(id, islimit, isrecommend);
		String userId=coctorsDao.selectUserId(id);
		if(islimit==1){
		userDao.updateIsdoctor(islimit, userId);
		}
		else {
			userDao.updateIsdoctor(0, userId);
		}
		results.setStatus("0");
		return results;
		
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("修改失败");
		return results;
		}
	}
}
