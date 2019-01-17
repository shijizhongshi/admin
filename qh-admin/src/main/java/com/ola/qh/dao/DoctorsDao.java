package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Doctors;

public interface DoctorsDao {

	public List<Doctors> selectDoctors(@Param("islimit")int islimit,@Param("name")String name,@Param("offices")String offices,@Param("isvirtual")int isvirtual,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public String selectUserId(@Param("id")String id);
	
	public int updateDoctors(@Param("id")String id,@Param("islimit")int islimit,@Param("isrecommend")int isrecommend,@Param("updatetime")Date updatetime);

	public int insertDoctors(Doctors doctors);
	
	public int deleteDoctors(@Param("id")String id);
}
