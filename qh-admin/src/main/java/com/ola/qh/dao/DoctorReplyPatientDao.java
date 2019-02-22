package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.DoctorReplyPatient;

public interface DoctorReplyPatientDao {

	public List<DoctorReplyPatient> selectDoctorReplyPatient(@Param("patientId")String patientId);
	
	public int deleteDoctorReplyPatient(@Param("id")String id,@Param("patientId")String patientId);
}
