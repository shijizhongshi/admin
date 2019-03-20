package com.ola.qh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
	@RequestMapping(value = "/single",method = RequestMethod.GET)
	public Results<List<UserRole>> single (@RequestParam(name = "pageNo",required = false) Integer pageNo,
			@RequestParam(name = "pageSize",required = false)Integer pageSize) {
		Results<List<UserRole>> results = new Results<List<UserRole>>();
		
		results = userRoleService.select(pageNo,pageSize);
		
		return results;
	}
	/**
	 * 修改
	 * @param userRole
	 * @return
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
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
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	public Results<UserRole> insert(@RequestBody @Valid UserRole userRole,BindingResult valid) {
		Results<UserRole> results = new Results<UserRole>();
		if (valid.hasErrors()) {
			results.setStatus("1");
			results.setMessage("填写信息不完整！");
			
			return results;
		}
		results = userRoleService.insert(userRole);
		
		return results;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	public Results<UserRole> delete (@RequestParam(name = "id")String id) {
		Results<UserRole> results = new Results<UserRole>();
		results = userRoleService.deleteById(id);
		
		return results;
	}
	/**
	 * 查询角色类别
	 * @return
	 */
	@RequestMapping(value = "/selectCategory",method = RequestMethod.GET)
	public List<String> selectCategory () {
		List<String> list = userRoleService.selectCategory();
		
		return list;
	}
}
