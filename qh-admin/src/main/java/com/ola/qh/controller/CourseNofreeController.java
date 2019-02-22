package com.ola.qh.controller;

import java.io.IOException;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
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
		int count=courseNofreeService.selectCourseNofreeCount(courseTypeName, courseTypeSubclassName, teachers, courseName);
		for (CourseNofree courseNofree : list) {
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			courseNofree.setShowtime(sf.format(courseNofree.getAddtime()));
		}
		results.setData(list);
		results.setStatus("0");
		results.setCount(count);
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
		vo.setSecretkey(Patterns.plovysecretkey);
		vo.setWritetoken(Patterns.plovywritetoken);
		if(vid!=null && !"".equals(vid)){
			long ts=new Date().getTime();
			vo.setTs(String.valueOf(ts));
			String si=Patterns.plovysecretkey+vid+String.valueOf(ts);
			vo.setSign(MD5.digest(si));
			vo.setVideoId(vid);
		}
		
		
		result.setData(vo);
		result.setStatus("0");
		return result;
	}
	
	 
	
}
