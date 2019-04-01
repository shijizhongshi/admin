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

import com.ola.qh.entity.ExamCalendar;
import com.ola.qh.service.IExamCalendarService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/calendar")
public class ExamCalendarController {

	@Autowired
	private IExamCalendarService examCalendarService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<ExamCalendar>> calendarList(@RequestParam(name="courseTypeSubclassName",required=true)String courseTypeSubclassName){
		
		Results<List<ExamCalendar>> results=new Results<List<ExamCalendar>>();
		
		List<ExamCalendar> list=examCalendarService.calendarList(courseTypeSubclassName);
		
		results.setData(list);
		results.setStatus("0");
		return results;
		
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertCalendar(@RequestBody @Valid ExamCalendar examCalendar,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		if (valid.hasErrors()) {
			
			results.setMessage("填写信息不全");
			results.setStatus("1");
			return results;
		}
		examCalendar.setAddtime(new Date());
		examCalendar.setId(KeyGen.uuid());
		int insert=examCalendarService.insertCalendar(examCalendar);
		
		if (insert<=0) {
			results.setStatus("1");
			return results;
		}
		
		results.setStatus("0");
		return results;
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> insertCalendar(@RequestBody ExamCalendar examCalendar){
		
		Results<String> results=new Results<String>();
		
		if ("".equals(examCalendar.getId())) {
			
			results.setMessage("缺少id");
			results.setStatus("1");
			return results;
		}
		examCalendar.setUpdatetime(new Date());
		int update=examCalendarService.updateCalendar(examCalendar);
		
		if (update<=0) {
			results.setStatus("1");
			return results;
		}
		
		results.setStatus("0");
		return results;
		
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteCalendar(@RequestParam(name="id",required=true)String id){
		
		Results<String> results=new Results<String>();
		
		int delete=examCalendarService.deleteCalendar(id);
		
		if (delete<=0) {
			results.setStatus("1");
			return results;
		}		
				
		results.setStatus("0");
		return results;
		
	}
}
