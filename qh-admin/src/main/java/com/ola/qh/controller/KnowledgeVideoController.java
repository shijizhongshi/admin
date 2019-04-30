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
import com.ola.qh.entity.KnowledgeVideo;
import com.ola.qh.service.IKnowledgeVideoService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/KnowledgeVideo")
public class KnowledgeVideoController {

	@Autowired
	private IKnowledgeVideoService knowledgeVideoService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<KnowledgeVideo>> KnowledgeVideoList(@RequestParam(name="pageNo",required=true)int pageNo,
			@RequestParam(name="pageSize",required=true)int pageSize,@RequestParam(name="title",required=false)String title
			,@RequestParam(name="courseTypeSubclassName",required=false)String courseTypeSubclassName){
		
		return knowledgeVideoService.KnowledgeVideoList(pageNo, pageSize, title, courseTypeSubclassName);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertKnowledgeVideo(@RequestBody @Valid KnowledgeVideo knowledgeVideo,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		if(valid.hasErrors()){
			
			results.setStatus("1");
			results.setMessage("信息不足");
			return results;
		}
		return knowledgeVideoService.insertKnowledgeVideo(knowledgeVideo);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateKnowledgeVideo(@RequestBody KnowledgeVideo knowledgeVideo){
		
		return knowledgeVideoService.updateKnowledgeVideo(knowledgeVideo);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteKnowledgeVideo(@RequestParam(name="id",required=true)String id){
		
		Results<String> results=new Results<String>();
		
		int delete=knowledgeVideoService.deleteKnowledgeVideo(id);
		if(delete<=0){
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}
	@RequestMapping("/VideoOrders")
	public Results<String> KnowledgeVideoOrders(@RequestParam(name="id",required=true)String id,
			@RequestParam(name="orders",required=true)int orders,
			@RequestParam(name="operateType",required=true)String operateType){
		return knowledgeVideoService.sectionOrders(id, orders, operateType);
	}
	@RequestMapping(value = "/updateAll",method = RequestMethod.GET)
	public Results<String> updateAll () {
		Results<String> results = new Results<String>();
		results = knowledgeVideoService.updateAll();
		
		return results;
	}
	
}
