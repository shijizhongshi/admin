package com.ola.qh.dao;

import org.apache.ibatis.annotations.Param;

public interface UserDao {

	public int updateUser(@Param("userrole")int userrole,@Param("id")String id);
	
}
