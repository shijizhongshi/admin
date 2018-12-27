package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Doctors;
import com.ola.qh.util.Results;

public interface IDoctorsService {

	public List<Doctors> selectDoctors(int islimit);
	
	public Results<String> updateDoctors(String id,int islimit,int isrecommend);
}
