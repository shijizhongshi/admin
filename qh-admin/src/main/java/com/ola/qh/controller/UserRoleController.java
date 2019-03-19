package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.UserRole;
import com.ola.qh.service.IUserRoleService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/userRole")
public class UserRoleController {

	@Autowired
	private IUserRoleService userRoleService;
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "single",method = RequestMethod.GET)
	public Results<UserRole> single (@RequestParam(name = "id",required = false) String id) {
		Results<UserRole> results = new Results<UserRole>();
		
		results = userRoleService.selectById(id);
		
		return results;
	}
	/**
	 * 修改
	 * @param userRole
	 * @return
	 */
	@RequestMapping(value = "update",method = RequestMethod.POST)
	public Results<UserRole> update(@RequestBody UserRole userRole) {
		Results<UserRole> results = new Results<UserRole>();
		results = userRoleService.update(userRole);
		
		return results;
	} 
	/**
	 * 添加
	 * @param userRole
	 * @return
	 */
	@RequestMapping(value = "insert",method = RequestMethod.POST)
	public Results<UserRole> insert(@RequestBody UserRole userRole,@RequestParam(name="password" ,required = true) String password) {
		Results<UserRole> results = new Results<UserRole>();
		results = userRoleService.insert(userRole,password);
		
		return results;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete",method = RequestMethod.GET)
	public Results<UserRole> delete (@RequestParam(name = "id",required = true) String id) {
		Results<UserRole> results = new Results<UserRole>();
		results = userRoleService.deleteById(id);
		
		return results;
	}
	@RequestMapping(value = "selectCategory",method = RequestMethod.GET)
	public List<String> selectCategory () {
		List<String> list = userRoleService.selectCategory();
		
		return list;
	}
}
