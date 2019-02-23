package com.ola.qh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.util.Results;

/**
 * 线下购买课程(加盟商和销售人员的开课)
* @ClassName:BuyCourseController
* @Description:
* @author guoyuxue
* @date:2019年2月22日 
*
 */
@RestController
@RequestMapping("/api/btl")
public class BuyCourseController {

	///////开课
	public Results<String> openCourse(){
		return null;
	}
}
