package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.DoctorPatient;

public interface DoctorPatientDao {

	public List<DoctorPatient> selectDoctorPatient(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("title")String title);
	
	public int deleteDoctorPatient(@Param("id")String id);
}
