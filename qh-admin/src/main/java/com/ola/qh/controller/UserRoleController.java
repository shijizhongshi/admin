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
	@RequestMapping(value = "/selectList",method = RequestMethod.GET)
	public Results<List<UserRole>> single (@RequestParam(name = "pageNo",required = true) Integer pageNo,
			@RequestParam(name = "pageSize",required = true)Integer pageSize,@RequestParam(name = "username",required = false)String username,
			@RequestParam(name = "nickname",required = false)String nickname,@RequestParam(name = "category",required = false)String category) {
		Results<List<UserRole>> results = new Results<List<UserRole>>();
		
		results = userRoleService.select(pageNo, pageSize, username, nickname, category);
		
		return results;
	}
	/**
	 * 修改
	 * @param userRole
	 * @return
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public Results<UserRole> update(@RequestBody @Valid UserRole userRole,BindingResult valid) {
		Results<UserRole> results = new Results<UserRole>();
		if (valid.hasErrors()) {
			results.setStatus("1");
			results.setMessage("填写信息不完整");
			
			return results;
		}
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
