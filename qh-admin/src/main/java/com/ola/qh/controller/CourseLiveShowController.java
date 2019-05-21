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
import com.ola.qh.entity.CourseTeacher;
import com.ola.qh.service.ICourseNofreeService;
import com.ola.qh.service.ICourseTeacherService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/courselive")
public class CourseLiveShowController {

	@Autowired
	private ICourseNofreeService courseNofreeService;
	@Autowired
	private ICourseTeacherService courseTeacherService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Results<String> saveLive(@RequestBody @Valid CourseLineShow cl, BindingResult valid) {
		Results<String> result = new Results<String>();
		//根据teacherId查询老师姓名
		List<CourseTeacher> list = courseTeacherService.selectName(cl.getTeacherId());
		cl.setLecturer(list.get(0).getName());
		int num = courseNofreeService.insertLive(cl);
		if (num < 0) {
			result.setStatus("1");
			result.setMessage("保存失败");
			return result;
		}
		result.setStatus("0");
		return result;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Results<String> updateLive(@RequestBody CourseLineShow cl) {
		Results<String> result = new Results<String>();
		if (cl.getId() != null && !"".equals(cl.getId())) {
			//根据teacherId查询老师姓名
			List<CourseTeacher> list = courseTeacherService.selectName(cl.getTeacherId());
			cl.setLecturer(list.get(0).getName());
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
	public Results<List<CourseLineShow>> list(@RequestParam(name = "pageNo", required = true) int pageNo,
			@RequestParam(name = "pageSize", required = true) int pageSize,
			@RequestParam(name = "courseTypeName", required = false) String courseTypeName,
			@RequestParam(name = "courseTypeSubclassName", required = false) String courseTypeSubclassName,
			@RequestParam(name = "liveName", required = false) String liveName) {
		Results<List<CourseLineShow>> result = new Results<List<CourseLineShow>>();
		List<CourseLineShow> list = courseNofreeService.selectLiveList(pageNo, pageSize, courseTypeName,
				courseTypeSubclassName, liveName);
		int count = courseNofreeService.selectLiveListCount(courseTypeName, courseTypeSubclassName, liveName);
		result.setCount(count);
		result.setData(list);
		result.setStatus("0");
		return result;
	}

	@RequestMapping("/single")
	public Results<CourseLineShow> list(@RequestParam(name = "id", required = true) String id) {
		Results<CourseLineShow> result = new Results<CourseLineShow>();
		CourseLineShow cl = courseNofreeService.singleLive(id);
		result.setData(cl);
		result.setStatus("0");
		return result;
	}

	@RequestMapping(value = "/delete")
	public Results<String> deleteLive(@RequestParam(name = "id", required = true) String id) {
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

	/**
	 * 获取老师的集合
	 */
	@RequestMapping(value = "/teacherList", method = RequestMethod.GET)
	public Results<List<CourseTeacher>> teacherList(String courseTypeSubclassName) {
		Results<List<CourseTeacher>> results = new Results<List<CourseTeacher>>();
		results = courseTeacherService.selectNameList(courseTypeSubclassName);
		
		return results;
	}
}
