package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.LivePay;

public interface LivePayDao {

	public List<LivePay> livePayList(@Param("status")String status,@Param("livename")String livename,@Param("startTime")String startTime,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("types")int types);
	
	public int livePayCount(@Param("status")String status,@Param("livename")String livename,@Param("startTime")String startTime);
}
