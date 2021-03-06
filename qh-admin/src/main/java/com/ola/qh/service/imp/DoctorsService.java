package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.DoctorsDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.entity.Doctors;
import com.ola.qh.entity.User;
import com.ola.qh.service.IDoctorsService;
import com.ola.qh.util.Results;
@Service
public class DoctorsService implements IDoctorsService{

	@Autowired
	private DoctorsDao doctorsDao;
	@Autowired
	private UserDao userDao;
	
	@Override 
	public List<Doctors> selectDoctors(int islimit,String name,String offices,String isvirtual,int pageNo,int pageSize) {
		
		return doctorsDao.selectDoctors(islimit, name, offices, isvirtual, pageNo, pageSize);
	}

	@Transactional
	@Override
	public Results<String> updateDoctors(String id, int islimit,int isrecommend,Date updatetime) {
		
		Results<String> results=new Results<String>();
		try {
			
		doctorsDao.updateDoctors(id, islimit, isrecommend,updatetime);
		String userId=doctorsDao.selectUserId(id);
		if(islimit==1){
			User u =new User();
			u.setId(userId);
			u.setIsdoctor("1");
		    userDao.updateUser(u);
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

	@Override
	public int insertDoctors(Doctors doctors) {
		
		return doctorsDao.insertDoctors(doctors);
	}
	
	@Override
	public int deleteDoctors(String id) {
		
		return doctorsDao.deleteDoctors(id);
	}

	@Override
	public int selectcount(int islimit,String name) {
		
		return doctorsDao.selectcount(islimit,name);
	}
}
