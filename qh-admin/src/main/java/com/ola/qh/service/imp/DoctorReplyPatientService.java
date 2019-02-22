package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.DoctorReplyPatientDao;
import com.ola.qh.entity.DoctorReplyPatient;
import com.ola.qh.service.IDoctorReplyPatientService;
@Service
public class DoctorReplyPatientService implements IDoctorReplyPatientService{

	@Autowired
	private DoctorReplyPatientDao doctorReplyPatientDao;;
	
	@Override
	public List<DoctorReplyPatient> selectDoctorReplyPatient(String patientId) {
		
		return doctorReplyPatientDao.selectDoctorReplyPatient(patientId);
	}

	@Override
	public int deleteDoctorReplyPatient(String id, String patientId) {
		
		return doctorReplyPatientDao.deleteDoctorReplyPatient(id, patientId);
	}

}
