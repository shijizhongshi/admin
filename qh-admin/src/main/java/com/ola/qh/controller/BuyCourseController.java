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
import com.ola.qh.util.Patterns;
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
			result.setMessage("开课的时候填写信息不完整");
			return result;
		}
		
		return buyCourseService.openCourse(oc);
	}
	/**
	 * 查看用户是否已经开通了id的课程
	 * <p>Title: existOpen</p>  
	 * <p>Description: </p>  
	 * @param courseId
	 * @param classId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/existOpen")
	public Results<String> existOpen(@RequestParam(name="courseId",required=false)String courseId,
			@RequestParam(name="classId",required=false)String classId,
			@RequestParam(name="userId",required=true)String userId){
		int count = buyCourseService.existOpenCourse(courseId, userId, classId);
		Results<String> result=new Results<String>();
		if(count==0){
			//////说明没有开过这个课
			result.setStatus("0");
		}else{
			//////说明已经开过课了
			result.setStatus("1");
			result.setMessage("这个用户的这个课程已经开通过了,请进行核实开通其他的课程");
		}
		return result;
	}
	
	
	
	/**
	 * 查询所有用户的购买记录
	 * <p>Title: buyRecord</p>  
	 * <p>Description: </p>  
	 * @param bcd
	 * @return
	 */
	@RequestMapping(value="/record",method=RequestMethod.GET)
	public Results<List<UserBuyCourse>> buyRecord(
			@RequestParam(name="nicknameORmobile",required=false)String nicknameORmobile,
			@RequestParam(name="fromdate",required=false)String fromdate,
			@RequestParam(name="todate",required=false)String todate,
			@RequestParam(name="businessId",required=false)String businessId,
			@RequestParam(name="types",required=true)int types,
			@RequestParam(name="page",required=true)int page,
			@RequestParam(name="classId",required=false)String classId,
			@RequestParam(name="courseId",required=false)String courseId){
		
		BuyCourseDomain bcd=new BuyCourseDomain();
		bcd.setBusinessId(businessId);
		bcd.setFromdate(fromdate);
		bcd.setNicknameORmobile(nicknameORmobile);
		int pageSize=Patterns.pageSize;
		int pageNo=(page-1)*pageSize;
		bcd.setPageNo(pageNo);
		bcd.setPageSize(pageSize);
		bcd.setTodate(todate);
		bcd.setTypes(types);
		bcd.setClassId(classId);
		bcd.setCourseId(courseId);
		return buyCourseService.buyRecord(bcd);
	}
	
	
	
	@RequestMapping("/remove/student")
	public Results<String> removeStudent(
			@RequestParam(name="classId",required=false)String classId,
			@RequestParam(name="courseId",required=false)String courseId){
		
		Results<String> result=new Results<String>();
		int count=buyCourseService.updateBuy(classId, courseId);
		if(count!=0){
			result.setStatus("0");
			return result;
		}
		result.setStatus("1");
		result.setMessage("移除学员失败~");
		return result;
		
	}
	
	@RequestMapping("/existCourseId")
	public Results<String> existCourseId(@RequestParam(name="productId",required=true)List<String> productId){
		
		return buyCourseService.existCourseId(productId);
		
	}
	
}
