package com.ola.qh.service.imp;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.DoctorPatientDao;
import com.ola.qh.dao.DoctorPatientImgDao;
import com.ola.qh.dao.DoctorReplyPatientDao;
import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.entity.DoctorPatientImg;
import com.ola.qh.entity.DoctorReplyPatient;
import com.ola.qh.service.IDoctorPatientService;
import com.ola.qh.util.Results;

@Service
public class DoctorPatientService implements IDoctorPatientService{

	@Autowired
	private DoctorPatientDao doctorPatientDao;
	
	@Autowired
	private DoctorPatientImgDao doctorPatientImgDao;
	
	@Autowired
	private DoctorReplyPatientDao doctorReplyPatientDao;
	
	
	@Override
	public List<DoctorPatient> selectDoctorPatient(int pageNo, int pageSize, String title) {
		
		return doctorPatientDao.selectDoctorPatient(pageNo, pageSize, title);
		
	}
	@Transactional
	@Override
	public Results<DoctorPatient> singleDoctorPatient(String id) {
		
		Results<DoctorPatient> results=new Results<DoctorPatient>();
		
		try {
			
		DoctorPatient single=doctorPatientDao.singleDoctorPatient(id);
		
		
			List<DoctorPatientImg> imgList=doctorPatientImgDao.selectPatientImg(single.getId());
			single.setListimg(imgList);
			
			List<DoctorReplyPatient> replyList=doctorReplyPatientDao.selectDoctorReplyPatient(single.getId());
			for (DoctorReplyPatient doctorReplyPatient : replyList) {
				SimpleDateFormat sf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				doctorReplyPatient.setShowtime(sf1.format(doctorReplyPatient.getAddtime()));
			}
			single.setListreply(replyList);
			
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			single.setShowtime(sf.format(single.getAddtime()));
		
		results.setStatus("0");
		results.setData(single);
		return results;
		
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}
	
	@Transactional
	@Override
	public Results<String> deleteDoctorPatient(String id) {
		
		Results<String> results=new Results<String>();
		
		try {
			
		doctorPatientDao.deleteDoctorPatient(id);
		doctorPatientImgDao.deletePatientImg(id, null);
		doctorReplyPatientDao.deleteDoctorReplyPatient(null, id);
		
		results.setStatus("0");
		return results;
	
	} catch (Exception e) {
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		results.setStatus("1");
		return results;
	}
	

	}
	@Override
	public int DoctorPatientCount(String title) {
		
		return doctorPatientDao.DoctorPatientCount(title);
	}
	
}
