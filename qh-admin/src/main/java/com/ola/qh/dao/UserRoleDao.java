package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserRole;

public interface UserRoleDao {

	public UserRole single(@Param("id") String id,@Param("username")String username,@Param("password")String password);

	public Integer update(UserRole userRole);

	public void saveUserRole(UserRole userRole);

	public Integer deleteById(String id);

	public List<String> selectCategory();

	public Integer selectUsername(String username);

}
