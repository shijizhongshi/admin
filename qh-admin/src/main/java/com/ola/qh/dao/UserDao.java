package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.User;

public interface UserDao {

	public int updateUser(User user);
	
	public List<User> selectUser(@Param("mobile")String mobile,@Param("nickname")String nickname,@Param("userrole")String userrole);
	
	public int updateFavorite(@Param("productId")String productId);
	
	public String adminLogin(@Param("username")String username,@Param("password")String password);
	
}
