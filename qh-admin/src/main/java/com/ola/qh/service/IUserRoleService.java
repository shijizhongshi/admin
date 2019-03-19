package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserRole;
import com.ola.qh.util.Results;

public interface IUserRoleService {
	
	public Results<UserRole> selectById(String id);

	public Results<UserRole> update(UserRole userRole);

	public Results<UserRole> insert(UserRole userRole,String password);

	public Results<UserRole> deleteById(String id);

	public List<String> selectCategory();
}
