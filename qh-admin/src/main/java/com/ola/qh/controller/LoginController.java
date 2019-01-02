package com.ola.qh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
			@RequestParam(name="password",required=true)String password){
		
		return userService.adminLogin(username, password);
	}
}
