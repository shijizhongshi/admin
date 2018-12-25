package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.DoctorsDao;
import com.ola.qh.entity.Doctors;
import com.ola.qh.service.IDoctorsService;
@Service
public class DoctorsService implements IDoctorsService{

	@Autowired
	private DoctorsDao coctorsDao;
	
	@Override
	public List<Doctors> selectDoctors(int islimit) {
		
		return coctorsDao.selectDoctors(islimit);
	}

	@Override
	public int updateDoctors(String id, int islimit) {
		
		return coctorsDao.updateDoctors(id, islimit);
	}

}
