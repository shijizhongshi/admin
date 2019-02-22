package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.DoctorPatientImg;

public interface IDoctorPatientImgService {

	
	public List<DoctorPatientImg> selectPatientImg(String patientId);
	
	public int deletePatientImg(String patientId,String id);
}
