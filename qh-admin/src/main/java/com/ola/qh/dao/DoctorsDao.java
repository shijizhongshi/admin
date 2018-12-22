package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Doctors;

public interface DoctorsDao {

	public List<Doctors> selectDoctors(@Param("islimit")int islimit);
	
	public int updateDoctors(@Param("id")String id,@Param("islimit")int islimit);
}
