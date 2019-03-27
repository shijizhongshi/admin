package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserRole;

public interface UserRoleDao {

	public UserRole single(@Param("id") String id, @Param("username") String username,
			@Param("password") String password);

	public Integer updateUserRole(UserRole userRole);

	public void saveUserRole(UserRole userRole);

	public Integer deleteById(String id);

	public List<String> selectCategory();

	public UserRole selectByUsername(@Param("username") String username);

	public List<UserRole> select(@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize,
			@Param("username") String username,@Param("nickname") String nickname,@Param("category") String category);

	public Integer selectCount(@Param("username") String username,@Param("nickname") String nickname,@Param("category") String category);

}
