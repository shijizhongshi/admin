package com.ola.qh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.AdminMenus;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/admin")
public class LoginController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/login")
	public Results<String> login(
			@RequestParam(name="username",required=true)String username,
			@RequestParam(name="password",required=true)String password,
			HttpServletRequest request){
		
		return userService.adminLogin(username, password,request);
	}
	
	@RequestMapping("/islogin")
	public Results<List<AdminMenus>> islogin(HttpServletRequest request){
		
		return userService.adminisLogin(request);
	} 
	
	
	@RequestMapping("/menus")
	public Results<List<AdminMenus>> menus(){
		Results<List<AdminMenus>> result=new Results<List<AdminMenus>>();
		result.setStatus("0");
		result.setData(userService.listmenu());
		return result;
	} 
	
}
