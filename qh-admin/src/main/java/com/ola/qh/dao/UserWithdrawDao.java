package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserWithdraw;

public interface UserWithdrawDao {

	public List<UserWithdraw> selectUserWithdraw(@Param("id")String id,@Param("pageNo")int page,@Param("pageSize")int zupageSize);
	
	public String checkedAccountBook(String userId);/////对账用的
	
	public int updateChecked(String id,String payStatus,String payMessage,Date updatetime);
	
	public List<UserWithdraw> selectListNoCheck(@Param("types")int types);
}
