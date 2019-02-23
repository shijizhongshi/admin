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

import com.ola.qh.entity.BuyCourseDomain;
import com.ola.qh.entity.OpenCourse;
import com.ola.qh.entity.UserBuyCourse;
import com.ola.qh.service.IBuyCourseService;
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

	@Autowired
	private IBuyCourseService buyCourseService;
	///////开课
	@RequestMapping(value="/open/course",method=RequestMethod.POST)
	public Results<String> openCourse(@RequestBody @Valid OpenCourse oc,BindingResult valid){
		
		Results<String> result=new Results<String>();
		if(valid.hasErrors()){
			result.setStatus("1");
			result.setMessage("开课的时候填写文章不完整");
			return result;
		}
		
		return buyCourseService.openCourse(oc);
	}
	/**
	 * 
	 * @param businessId
	 * @return
	 */
	@RequestMapping(value="/record",method=RequestMethod.POST)
	public Results<List<UserBuyCourse>> buyRecord(@RequestBody BuyCourseDomain bcd){
		
		return buyCourseService.buyRecord(bcd);
	}
	
	
	
}
