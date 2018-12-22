package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Doctors;

public interface IDoctorsService {

	public List<Doctors> selectDoctors(int islimit);
	
	public int updateDoctors(String id,int islimit);
}
