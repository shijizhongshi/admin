package com.ola.qh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.service.IUserService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/updateuser", method = RequestMethod.GET)
	public Results<String> updateUser(@RequestParam(name = "userrole", required = true) int userrole,
			@RequestParam(name = "id", required = true) String id) {

		Results<String> results = new Results<String>();

		int user = userService.updateUser(userrole, id);
		if (user <= 0) {
			results.setMessage("更改异常");
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}

}
