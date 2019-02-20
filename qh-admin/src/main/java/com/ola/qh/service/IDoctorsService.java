package com.ola.qh.service;

import java.util.Date;
import java.util.List;

import com.ola.qh.entity.Doctors;
import com.ola.qh.util.Results;

public interface IDoctorsService {

	public List<Doctors> selectDoctors(int islimit,String name,String offices,int isvirtual,int pageNo,int pageSize);
	
	public Results<String> updateDoctors(String id,int islimit,int isrecommend,Date updatetime);
	
	public int insertDoctors(Doctors doctors);
	
	public int deleteDoctors(String id);
	
	public int selectcount(int islimit,String name);
}
