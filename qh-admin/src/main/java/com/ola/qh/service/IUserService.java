package com.ola.qh.service;

import org.apache.ibatis.annotations.Param;

public interface IUserService {
	
	public int updateUser(@Param("userrole")int userrole,@Param("id")String id);
}
