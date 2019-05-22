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

import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;
import com.ola.qh.entity.CourseTypeSubclassNames;
import com.ola.qh.service.IBuyCourseService;
import com.ola.qh.service.ICourseService;
import com.ola.qh.service.ILiveMarkScheduleService;
import com.ola.qh.util.Results;

/**
 * 
 * @ClassName: CourseController
 * @Description:课程的类别和课程的处理
 * @author guoyuxue
 * @date 2018年11月20日
 *
 */
@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	private ICourseService courseService;

	@Autowired
	private IBuyCourseService buyCourseService;
	
	@Autowired
	private ILiveMarkScheduleService liveMarkScheduleService;

	/**
	 * 大类别的集合
	 * <p>
	 * Title: listCourseType
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/courseTypeList")
	public Results<List<CourseType>> listCourseType() {
		Results<List<CourseType>> result = new Results<List<CourseType>>();
		List<CourseType> list = courseService.courseTypeList();
		for (CourseType courseType : list) {
			courseType.setList(courseService.courseTypeSubclassList(courseType.getId()));
		}
		result.setData(list);
		result.setStatus("0");
		return result;
	}

	/**
	 * 保存大类别或者是小类别简单保存
	 * <p>
	 * Title: saveCourseType
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param id
	 * @param courseTypeName
	 * @param courseTypeId
	 * @return
	 */
	@RequestMapping("/courseTypeSave")
	public Results<String> saveCourseType(@RequestParam(name = "courseTypeName", required = true) String courseTypeName,
			@RequestParam(name = "courseTypeId", required = false) String courseTypeId) {
		Results<String> result = new Results<String>();
		int num = courseService.insertCourseType(courseTypeName, courseTypeId);
		if (num > 0) {
			result.setStatus("0");
			return result;
		}
		result.setStatus("1");
		result.setMessage("保存失败");
		return result;
	}

	/**
	 * 修改大类别和小类别
	 * <p>
	 * Title: updateCourseType
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param id
	 * @param courseTypeName
	 * @param courseTypeId
	 * @return
	 */
	@RequestMapping("/courseTypeUpdate")
	public Results<String> updateCourseType(@RequestParam(name = "id", required = true) String id,
			@RequestParam(name = "courseTypeName", required = true) String courseTypeName,
			@RequestParam(name = "courseTypeId", required = false) String courseTypeId) {
		Results<String> result = new Results<String>();
		int num = courseService.updateCourseType(courseTypeName, id, courseTypeId);
		if (num > 0) {
			result.setStatus("0");
			return result;
		}
		result.setStatus("1");
		result.setMessage("修改失败");
		return result;
	}

	/**
	 * 子类别的集合
	 * <p>
	 * Title: listCourseTypeSubclass
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param courseTypeId
	 * @return
	 */
	@RequestMapping("/courseTypeSubclassList")
	public Results<List<CourseTypeSubclass>> listCourseTypeSubclass(
			@RequestParam(name = "courseTypeId", required = true) String courseTypeId) {

		Results<List<CourseTypeSubclass>> result = new Results<List<CourseTypeSubclass>>();
		List<CourseTypeSubclass> list = courseService.courseTypeSubclassList(courseTypeId);
		result.setData(list);
		result.setStatus("0");
		return result;

	}

	/**
	 * 
	 * <p>
	 * Title: listCourse
	 * </p>
	 * <p>
	 * 多条件的查询,是否精品课程,按照大类别.小类别查询课程
	 * </p>
	 * 首页传精品课程page=1 其他的情况则是page是变量
	 * 
	 * @param page
	 * @param courseTypeName
	 * @param courseTypeSubclassName
	 * @param courseExcellent
	 * @return
	 */
	@RequestMapping("/courseList")
	public Results<List<Course>> listCourse(@RequestParam(name = "pageNo", required = true) int pageNo,
			@RequestParam(name = "pageSize", required = true) int pageSize,
			@RequestParam(name = "courseTypeName", required = false) String courseTypeName,
			@RequestParam(name = "courseTypeSubclassName", required = false) String courseTypeSubclassName,
			@RequestParam(name = "courseName", required = false) String courseName,
			@RequestParam(name = "userId", required = false) String userId) {

		Results<List<Course>> result = new Results<List<Course>>();

		Course course = new Course();
		course.setCourseTypeName(courseTypeName);
		course.setCourseTypeSubclassName(courseTypeSubclassName);
		course.setPageNo(pageNo);
		course.setPageSize(pageSize);
		course.setCourseName(courseName);
		List<Course> list = courseService.courseList(course);
		if (userId != null && !"".equals(userId)) {
			for (Course course2 : list) {
				int count = buyCourseService.existOpenCourse(course2.getId(), userId, null);
				if (count == 0) {
					course2.setIsbuy("0");
				} else {
					course2.setIsbuy("1");
				}
			}
		}

		result.setStatus("0");
		result.setData(list);
		result.setCount(courseService.courseCount(courseTypeName, courseTypeSubclassName, courseName));
		return result;
	}

	/**
	 * 课程的保存和修改
	 * <p>
	 * Title: courseSaveUpdate
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param course
	 * @param valid
	 * @return
	 */
	@RequestMapping(value = "/courseSaveUpdate", method = RequestMethod.POST)
	public Results<String> courseSaveUpdate(@RequestBody @Valid Course course, BindingResult valid) {

		Results<String> result = new Results<String>();
		if (course.getId() == null || "".equals(course.getId())) {
			//// 修改课程的基本信息
			if (valid.hasErrors()) {
				result.setStatus("1");
				result.setMessage("添加课程信息不完整");
				return result;
			}
		}
		int num = courseService.insertUpdateCourse(course);
		if (num > 0) {
			result.setStatus("0");
			return result;
		}
		result.setStatus("1");
		result.setMessage("操作失败");
		return result;

	}

	@RequestMapping("/deleteCourse")
	public Results<String> deleteCourse(@RequestParam(name = "courseId", required = true) String courseId) {
		Results<String> result = new Results<String>();
		int num = courseService.deleteCourse(courseId);
		if (num > 0) {
			result.setStatus("0");
			return result;
		}
		result.setStatus("1");
		result.setMessage("操作失败");
		return result;

	}

	/**
	 * 推送页面 专业下拉列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectCourseTypeSubclassNameAll", method = RequestMethod.GET)
	public Results<List<CourseTypeSubclass>> selectCourseTypeSubclassNameAll() {
		Results<List<CourseTypeSubclass>> results = new Results<List<CourseTypeSubclass>>();
		results = courseService.selectCourseTypeSubclassNameAll();

		return results;
	}

	/**
	 * 根据大类别ID查询子类别ID
	 * 
	 * @param courseTypeId
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Results<List<CourseTypeSubclass>> selectCourseTypeSubclass(
			@RequestParam(name = "courseTypeId", required = true) String courseTypeId) {
		Results<List<CourseTypeSubclass>> results = new Results<List<CourseTypeSubclass>>();
		results = courseService.selectCourseTypeSubclass(courseTypeId);

		return results;
	}

	/**
	 * 添加一级分类(大分类)
	 * 
	 * @param courseTypeName
	 * @return
	 */
	@RequestMapping(value = "/insertCourseTypeName", method = RequestMethod.GET)
	public Results<String> insertCourseTypeName(@RequestParam(name = "courseTypeName") String courseTypeName,
			@RequestParam(name = "imgUrl") String imgUrl) {
		Results<String> results = new Results<String>();
		results = courseService.insertCourseTypeName(courseTypeName, imgUrl);

		return results;
	}

	/**
	 * 删除一级分类(大分类)
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteCourseType", method = RequestMethod.GET)
	public Results<String> deleteCourseType(@RequestParam(name = "id") String id) {
		Results<String> results = new Results<String>();
		results = courseService.deleteCourseType(id);

		return results;
	}

	@RequestMapping(value = "/updateOne", method = RequestMethod.GET)
	public Results<String> updateOne(@RequestParam(name = "id") String id,
			@RequestParam(name = "courseTypeName") String courseTypeName,
			@RequestParam(name = "imgUrl") String imgUrl) {
		Results<String> results = new Results<String>();
		results = courseService.updateOne(id, courseTypeName, imgUrl);

		return results;
	}

	/**
	 * 添加二级类别(小分类)
	 * 
	 * @param courseTypeSubclassName
	 * @param courseTypeId
	 * @return
	 */
	@RequestMapping(value = "/insertCourseTypeSubclassName", method = RequestMethod.GET)
	public Results<String> insertCourseTypeSubclassName(
			@RequestParam(name = "courseTypeSubclassName") String courseTypeSubclassName,
			@RequestParam(name = "courseTypeId") String courseTypeId, @RequestParam(name = "imgUrl") String imgUrl) {
		Results<String> results = new Results<String>();
		results = courseService.insertCourseTypeSubclassName(courseTypeId, courseTypeSubclassName, imgUrl);

		return results;
	}

	/**
	 * 修改二级类别(小分类)
	 * 
	 * @param courseTypeSubclassName
	 * @param courseTypeSubclassId
	 * @return
	 */
	@RequestMapping(value = "/updateCourseTypeSubclassName", method = RequestMethod.GET)
	public Results<String> updateCourseTypeSubclassName(
			@RequestParam(name = "courseTypeSubclassName") String courseTypeSubclassName,
			@RequestParam(name = "courseTypeSubclassId") String courseTypeSubclassId,
			@RequestParam(name = "imgUrl") String imgUrl) {
		Results<String> results = new Results<String>();
		results = courseService.updateCourseTypeSubclassName(courseTypeSubclassId, courseTypeSubclassName, imgUrl);

		return results;
	}

	/**
	 * 删除二级类别(小分类)
	 * 
	 * @param courseTypeSubclassId
	 * @return
	 */
	@RequestMapping(value = "/deleteTwo", method = RequestMethod.GET)
	public Results<String> deleteTwo(@RequestParam(name = "courseTypeSubclassId") String courseTypeSubclassId) {
		Results<String> results = new Results<String>();
		results = courseService.deleteCourseTypeSubclass(courseTypeSubclassId);

		return results;
	}

	/**
	 * 根据二级类别ID查询三级类别集合
	 * 
	 * @param courseTypeSubclassId
	 * @return
	 */
	@RequestMapping(value = "/selectThree", method = RequestMethod.GET)
	public Results<List<CourseTypeSubclassNames>> insertThree(
			@RequestParam(name = "courseTypeSubclassId") String courseTypeSubclassId) {
		Results<List<CourseTypeSubclassNames>> results = new Results<List<CourseTypeSubclassNames>>();
		results = courseService.selectThree(courseTypeSubclassId);

		return results;
	}

	/**
	 * 添加三级类别
	 * 
	 * @param courseTypeSubclassId
	 * @param miniSubclassName
	 * @return
	 */
	@RequestMapping(value = "/insertThree", method = RequestMethod.GET)
	public Results<String> insertThree(@RequestParam(name = "courseTypeSubclassId") String courseTypeSubclassId,
			@RequestParam(name = "miniSubclassName") String miniSubclassName) {
		Results<String> results = new Results<String>();
		results = courseService.insertThree(courseTypeSubclassId, miniSubclassName);

		return results;
	}

	/**
	 * 修改三级类别
	 * 
	 * @param miniId
	 * @param miniSubclassName
	 * @return
	 */
	@RequestMapping(value = "/updateThree", method = RequestMethod.GET)
	public Results<String> updateThree(@RequestParam(name = "miniId") String miniId,
			@RequestParam(name = "miniSubclassName") String miniSubclassName) {
		Results<String> results = new Results<String>();
		results = courseService.updateThree(miniId, miniSubclassName);

		return results;
	}

	/**
	 * 删除三级类别
	 * 
	 * @param miniId
	 * @return
	 */
	@RequestMapping(value = "/deleteThree", method = RequestMethod.GET)
	public Results<String> deleteThree(@RequestParam(name = "miniId") String miniId) {
		Results<String> results = new Results<String>();
		results = courseService.deleteThree(miniId);

		return results;
	}

	/**
	 * 测试定时任务
	 */
	@RequestMapping("/aaa")
	public void test() {

		liveMarkScheduleService.timedPushOneHour();
	}
	
	@RequestMapping("/bbb")
	public void testb () {
		
		liveMarkScheduleService.timedPushFiveMin();
	}

}
