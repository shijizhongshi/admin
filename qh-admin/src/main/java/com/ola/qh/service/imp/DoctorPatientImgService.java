package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.DoctorPatientImgDao;
import com.ola.qh.entity.DoctorPatientImg;
import com.ola.qh.service.IDoctorPatientImgService;
@Service
public class DoctorPatientImgService implements IDoctorPatientImgService{

	
	@Autowired
	private DoctorPatientImgDao doctorPatientImgDao;
	
	
	@Override
	public List<DoctorPatientImg> selectPatientImg(String patientId) {
		
		return doctorPatientImgDao.selectPatientImg(patientId);
	}

	@Override
	public int deletePatientImg(String patientId, String id) {
		
		return doctorPatientImgDao.deletePatientImg(patientId, id);
	}

}
