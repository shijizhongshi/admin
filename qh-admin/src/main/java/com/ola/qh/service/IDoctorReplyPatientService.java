package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.DoctorReplyPatient;

public interface IDoctorReplyPatientService {

	public List<DoctorReplyPatient> selectDoctorReplyPatient(String patientId);
	
	public int deleteDoctorReplyPatient(String id,String patientId);
}
