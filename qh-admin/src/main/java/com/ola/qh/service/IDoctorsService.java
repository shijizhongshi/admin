package com.ola.qh.service;

import java.util.Date;
import java.util.List;

import com.ola.qh.entity.Doctors;
import com.ola.qh.util.Results;

public interface IDoctorsService {

	public List<Doctors> selectDoctors(int islimit,int isvirtual,int pageNo,int pageSize);
	
	public Results<String> updateDoctors(String id,int islimit,int isrecommend,Date updatetime);
}
