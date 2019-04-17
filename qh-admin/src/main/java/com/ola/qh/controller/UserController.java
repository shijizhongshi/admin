package com.ola.qh.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.User;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/user")
public class UserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/updateuser", method = RequestMethod.GET)
	public Results<String> updateUser(@RequestParam(name = "isdisabled", required = true) String isdisabled,
			@RequestParam(name = "id", required = true) String id) {

		Results<String> results = new Results<String>();

		int user = userService.updateUser(isdisabled, id);
		if (user <= 0) {
			results.setMessage("更改异常");
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<List<User>> selectUser(@RequestParam(name = "page", required = true) int page,
			@RequestParam(name = "mobile", required = false) String mobile,
			@RequestParam(name = "nickname", required = false) String nickname,
			@RequestParam(name = "userrole", required = false) String userrole) {

		Results<List<User>> results = new Results<List<User>>();

		int pageSize = Patterns.pageSize;
		int pageNo = (page - 1) * pageSize;
		results = userService.selectUser(pageNo, pageSize, mobile, nickname, userrole);

		return results;
	}

	@RequestMapping(value = "/saveupdate", method = RequestMethod.POST)
	public Results<String> saveUser(@RequestBody @Valid User user, BindingResult valid) {
		Results<String> result = new Results<String>();
		if (user.getId() == null || "".equals(user.getId())) {
			if (valid.hasErrors()) {
				result.setStatus("1");
				result.setMessage("学员信息不完整~");
				return result;
			}
		}

		return userService.saveUsers(user);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Results<String> deleteUser(@RequestParam(name = "id", required = true) String id) {
		Results<String> result = new Results<String>();
		userService.deleteUser(id);
		result.setStatus("0");
		return result;
	}

	@RequestMapping("/select/student")
	public Results<List<User>> selectStudent(@RequestParam(name = "fromdate", required = false) String fromdate,
			@RequestParam(name = "todate", required = false) String todate,
			@RequestParam(name = "realnameORmobile", required = false) String realnameORmobile,
			@RequestParam(name = "status", required = false) String status,
			@RequestParam(name = "page", required = true) int page) {

		Results<List<User>> result = new Results<List<User>>();
		int pageSize = Patterns.pageSize;
		int pageNo = (page - 1) * pageSize;
		List<User> list = userService.selectStudent(fromdate, todate, realnameORmobile, status, pageNo, pageSize);
		// sql语句暂时无法处理检索时查询数量 后续想到实现方法再改
		if (realnameORmobile == null) {
			result.setCount(userService.selectStudentCount(fromdate, todate, realnameORmobile, status));
		}
		result.setStatus("0");
		result.setData(list);
		return result;

	}
	/**
	 * 单播推送页面
	 * 
	 * @param title
	 * @param content
	 * @param sex
	 * @param courseTypeSubclassName
	 * @param userrole
	 * @param isdoctor
	 * @param birthday
	 * @return
	 */
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public Results<List<User>> send(@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "content", required = false) String content,
			@RequestParam(name = "sex", required = false) String sex,
			@RequestParam(name = "courseTypeSubclassName", required = false) String courseTypeSubclassName,
			@RequestParam(name = "userrole", required = false) String userrole,
			@RequestParam(name = "isdoctor", required = false) String isdoctor,
			@RequestParam(name = "birthday", required = false) String birthday) {
		Results<List<User>> results = new Results<>();

		results = userService.send(title, content, sex, courseTypeSubclassName, userrole, isdoctor,
				birthday);

		return results;
	}
}
