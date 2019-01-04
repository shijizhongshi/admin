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

import com.ola.qh.entity.CourseLineShow;
import com.ola.qh.service.ICourseNofreeService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/courselive")
public class CourseLiveShowController {

	@Autowired
	private ICourseNofreeService courseNofreeService;

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Results<String> saveLive(@RequestBody @Valid CourseLineShow cl, BindingResult valid) {
		Results<String> result = new Results<String>();
		int num = courseNofreeService.insertLive(cl);
		if (num < 0) {
			result.setStatus("1");
			result.setMessage("保存失败");
			return result;
		}
		result.setStatus("0");
		return result;
	}

	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateLive(@RequestBody CourseLineShow cl) {
		Results<String> result = new Results<String>();
		if (cl.getId() != null && !"".equals(cl.getId())) {
			int num = courseNofreeService.updateLive(cl);
			if (num < 0) {
				result.setStatus("1");
				result.setMessage("更新失败");
				return result;
			}
			result.setStatus("0");
			return result;
		}
		result.setStatus("1");
		result.setMessage("记录标识不能为空");
		return result;

	}
	
	@RequestMapping("/list")
	public Results<List<CourseLineShow>> list(@RequestParam(name="page",required=true)int page){
		Results<List<CourseLineShow>> result=new Results<List<CourseLineShow>>();
		int pageSize=Patterns.pageSize;
		int pageNo=(page-1)*pageSize;
		List<CourseLineShow> list = courseNofreeService.selectLiveList(pageNo, pageSize);
		result.setData(list);
		result.setStatus("0");
		return result;
	}
	
	
	@RequestMapping("/single")
	public Results<CourseLineShow> list(@RequestParam(name="id",required=true)String id){
		Results<CourseLineShow> result=new Results<CourseLineShow>();
		CourseLineShow cl = courseNofreeService.singleLive(id);
		result.setData(cl);
		result.setStatus("0");
		return result;
	}
	
	@RequestMapping(value="/delete")
	public Results<String> deleteLive(@RequestParam(name="id",required=true)String id) {
		Results<String> result = new Results<String>();
		int num = courseNofreeService.deleteLive(id);
		if (num < 0) {
			result.setStatus("1");
			result.setMessage("删除失败");
			return result;
		}
		result.setStatus("0");
		return result;
	}
	
}