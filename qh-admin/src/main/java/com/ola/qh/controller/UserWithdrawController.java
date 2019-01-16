package com.ola.qh.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
	public Results<List<UserWithdraw>> selectUserWithdrawHistory(
			@RequestParam(name="page", required = true) int page,
			@RequestParam(value="fromdate", required = false)String fromdate,
	        @RequestParam(value="todate", required = false) String todate,
			@RequestParam(name="mobile",required=false)String mobile,
			@RequestParam(name="payStatus",required=false)String payStatus) throws Exception {

		Results<List<UserWithdraw>> results = new Results<List<UserWithdraw>>();

		int pageSize=Patterns.pageSize;
		int pageNo=(page-1)*pageSize;
		List<UserWithdraw> select = userWithdrawService.selectUserWithdraw(mobile, payStatus,fromdate, todate, pageNo, pageSize);
		int count = userWithdrawService.selectUserWithdrawCount(mobile, payStatus, fromdate, todate);
		results.setCount(count);
		results.setData(select);
		results.setStatus("0");
		return results;
	}
	
}
