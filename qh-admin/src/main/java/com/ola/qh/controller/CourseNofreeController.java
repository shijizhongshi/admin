package com.ola.qh.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.CourseNofree;
import com.ola.qh.entity.Polyv;
import com.ola.qh.service.ICourseNofreeService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.MD5;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/coursenofree")
public class CourseNofreeController {

	@Autowired
	private ICourseNofreeService courseNofreeService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<CourseNofree>> selectCourseNofree(@RequestParam(name="courseTypeName",required=false)String courseTypeName,
			@RequestParam(name="courseTypeSubclassName",required=false)String courseTypeSubclassName,
			@RequestParam(name="teachers",required=false)String teachers,@RequestParam(name="courseName",required=false)String courseName,
			@RequestParam(name="page",required=true)int page){
		
		Results<List<CourseNofree>> results=new Results<List<CourseNofree>>();
		
		int pageSize=Patterns.pageSize;
		int pageNo=(page-1)*pageSize;
		
		List<CourseNofree> list=courseNofreeService.selectCourseNofree(courseTypeName, courseTypeSubclassName, pageNo, pageSize, teachers, courseName);
		
		if(list==null || list.size()==0){
			
			results.setMessage("没有数据");
			results.setStatus("1");
			return results;
			
		}
		results.setData(list);
		results.setStatus("0");
		return results;
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertCourseNofree(@RequestBody @Valid CourseNofree courseNofree,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		if (valid.hasErrors()) {
			results.setMessage("信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}
		courseNofree.setAddtime(new Date());
		courseNofree.setId(KeyGen.uuid());
		int insert=courseNofreeService.insertCourseNofree(courseNofree);
		
		if(insert==0){
			
			results.setStatus("1");
			return results;
			
		}
		results.setStatus("0");
		return results;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateCourseNofree(@RequestBody CourseNofree courseNofree){
		
		Results<String> results=new Results<String>();
		
		int update=courseNofreeService.updateCourseNofree(courseNofree);
		
		if(update==0){
			
			results.setStatus("1");
			return results;
			
		}
		results.setStatus("0");
		return results;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteCourseNofree(@RequestParam(name="id",required=true)String id){
		
		Results<String> results=new Results<String>();
		
		int delete=courseNofreeService.deleteCourseNofree(id);
		
		if(delete==0){
			
			results.setStatus("1");
			return results;
			
		}
		results.setStatus("0");
		return results;
	}
	
	@RequestMapping("/polyv")
	public Results<Polyv> getPolyv(@RequestParam(name="vid",required=false)String vid){
		Results<Polyv> result=new Results<Polyv>();
		Polyv vo=new Polyv();
		vo.setSecretkey("ucxGUE1iuw");
		vo.setWritetoken("a1df864b-405e-4782-9494-733e9b51c5d5");
		if(vid!=null && !"".equals(vid)){
			long ts=new Date().getTime();
			vo.setTs(String.valueOf(ts));
			String si="ucxGUE1iuw"+vid+String.valueOf(ts);
			vo.setSign(MD5.digest(si));
			vo.setVideoId(vid);
		}
		
		
		result.setData(vo);
		result.setStatus("0");
		return result;
	}
	public static void main(String[] args) {
		System.out.println();
	}
}
