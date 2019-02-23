package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.DoctorPatientImg;

public interface DoctorPatientImgDao {

	public List<DoctorPatientImg> selectPatientImg(@Param("patientId")String patientId);
	
	public int deletePatientImg(@Param("patientId")String patientId,@Param("id")String id);
}
