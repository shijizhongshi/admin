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
import com.ola.qh.entity.UserWithdrawHistory;
import com.ola.qh.service.IUserWithdrawHistoryService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/userwithdrawhistory")
public class UserWithdrawHistoryController {

	@Autowired
	private IUserWithdrawHistoryService userWithdrawHistoryService;
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<List<UserWithdrawHistory>> selectUserWithdrawHistory(@RequestParam(name = "id", required = false) String id,
			@RequestParam(name = "page", required = true) int page) {

		Results<List<UserWithdrawHistory>> results = new Results<List<UserWithdrawHistory>>();

		int pageSize=Patterns.pageSize;
		int pageNo=(page-1)*pageSize;
		List<UserWithdrawHistory> select = userWithdrawHistoryService.selectUserWithdrawHistory(id, pageNo, pageSize);

		
				
		if (select== null) {
			results.setMessage("显示错误");
			results.setStatus("1");
			return results;
			
		}
		results.setData(select);
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Results<String> updateUserWithdrawHistory(@RequestBody @Valid UserWithdrawHistory userwithdrawhistory,BindingResult valid) {

		Results<String> results = new Results<String>();

		if (valid.hasErrors()) {
			results.setMessage("信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}
		return userWithdrawHistoryService.updateUserWithdrawHistory(userwithdrawhistory);

	}
}
