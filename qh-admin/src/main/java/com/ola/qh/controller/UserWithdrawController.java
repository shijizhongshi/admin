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
import com.ola.qh.entity.UserWithdraw;
import com.ola.qh.service.IUserWithdrawService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/userwithdraw")
public class UserWithdrawController {

	@Autowired
	private IUserWithdrawService userWithdrawService;
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<List<UserWithdraw>> selectUserWithdrawHistory(@RequestParam(name = "id", required = false) String id,
			@RequestParam(name = "page", required = true) int page) {

		Results<List<UserWithdraw>> results = new Results<List<UserWithdraw>>();

		int pageSize=Patterns.pageSize;
		int pageNo=(page-1)*pageSize;
		List<UserWithdraw> select = userWithdrawService.selectUserWithdraw(id, pageNo, pageSize);

		
				
		if (select== null) {
			results.setMessage("显示错误");
			results.setStatus("1");
			return results;
			
		}
		results.setData(select);
		results.setStatus("0");
		return results;
	}
	
}
