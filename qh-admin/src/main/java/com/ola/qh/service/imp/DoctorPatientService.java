package com.ola.qh.service.imp;

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
	
	
	@Transactional
	@Override
	public Results<List<DoctorPatient>> selectDoctorPatient(int pageNo, int pageSize, String title) {
		
		Results<List<DoctorPatient>> results=new Results<List<DoctorPatient>>();
		
		//try {
			
		List<DoctorPatient> list=doctorPatientDao.selectDoctorPatient(pageNo, pageSize, title);
		
		for (DoctorPatient doctorPatient : list) {
			List<DoctorPatientImg> imgList=doctorPatientImgDao.selectPatientImg(doctorPatient.getId());
			doctorPatient.setListimg(imgList);
			
			List<DoctorReplyPatient> replyList=doctorReplyPatientDao.selectDoctorReplyPatient(doctorPatient.getId());
			doctorPatient.setListreply(replyList);
		}
		results.setStatus("0");
		results.setData(list);
		return results;
		
		/*} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}*/
		
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
}
