package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.util.Results;

public interface IDoctorPatientService {

	public List<DoctorPatient> selectDoctorPatient(int pageNo,int pageSize,String title);
	
	public int DoctorPatientCount(String title);
	
	public Results<DoctorPatient> singleDoctorPatient(String id);
	
	public Results<String> deleteDoctorPatient(String id);
}
