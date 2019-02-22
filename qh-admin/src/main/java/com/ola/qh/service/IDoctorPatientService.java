package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.util.Results;

public interface IDoctorPatientService {

	public Results<List<DoctorPatient>> selectDoctorPatient(int pageNo,int pageSize,String title);
	
	public Results<String> deleteDoctorPatient(String id);
}
