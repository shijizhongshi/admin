package com.ola.qh.dao;

import org.apache.ibatis.annotations.Param;

public interface UserDao {

	public int updateUser(@Param("userrole")int userrole,@Param("id")String id);
	
	public int updateIsdoctor(@Param("isdoctor")int isdoctor,@Param("id")String id);
	
	public int updateFavorite(@Param("productId")String productId);
	
}
