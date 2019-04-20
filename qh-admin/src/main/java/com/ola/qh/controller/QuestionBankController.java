package com.ola.qh.controller;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseLiveCheck;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.entity.PlayLog;
import com.ola.qh.entity.QuestionBank;
import com.ola.qh.entity.VideoPlaybackRecord;
import com.ola.qh.service.ICourseService;
import com.ola.qh.service.ICourseSubclassService;
import com.ola.qh.service.IQuestionBankService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.Json;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.util.Thqs;
import com.ola.qh.weixin.handler.Requests;

@RestController
@RequestMapping("/api/questionbank")
public class QuestionBankController {

	@Autowired
	private IQuestionBankService questionBankService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ICourseSubclassService courseSubclassService;

	/**
	 * 题库的上传
	 * <p>
	 * Title: improtExcel
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/improtExcel", method = RequestMethod.POST, consumes = "multipart/form-data")
	public Results<String> improtExcel(@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "subId") String subId) throws Exception {

		return questionBankService.importExcel(file, subId);
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<List<QuestionBank>> selectQuestionBank(@RequestParam(value = "subId", required = true) String subId,
			@RequestParam(value = "pageNo", required = true) int pageNo,
			@RequestParam(value = "pageSize", required = true) int pageSize) {

		return questionBankService.selectQuestionBank(subId, pageNo, pageSize);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Results<String> updateQuestionBank(@RequestBody QuestionBank questionBank) {

		return questionBankService.updateQuestionBank(questionBank);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Results<String> deleteQuestionBank(@RequestParam(value = "id", required = true) String id) {

		return questionBankService.deleteQuestionBank(id);
	}

	/**
	 * h5题库展示页面
	 * 
	 * @param page                   分页
	 * @param realname               姓名
	 * @param courseTypeSubclassName 专业名
	 * @param status                 用户属性
	 * @return
	 */
	@RequestMapping(value = "/questionbankList", method = RequestMethod.GET)
	public Results<List<QuestionBank>> questionbankList(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "realname", required = false) String realname,
			@RequestParam(value = "courseTypeSubclassName", required = false) String courseTypeSubclassName,
			@RequestParam(value = "status", required = false) String status) {
		Results<List<QuestionBank>> results = new Results<>();

		results = questionBankService.selectQuestionBankList(realname, courseTypeSubclassName, status, page);

		return results;
	}

	/**
	 * 直播验证数据
	 * 
	 * @param page                   分页
	 * @param mobile                 手机号
	 * @param roomId                 房间号
	 * @param courseTypeSubclassName 专业名
	 * @return
	 */
	@RequestMapping(value = "/liveVerifyList", method = RequestMethod.GET)
	public Results<List<CourseLiveCheck>> liveVerifyList(
			@RequestParam(name = "fromdate", required = false) String fromdate,
			@RequestParam(name = "todate", required = false) String todate,
			@RequestParam(value = "pageNo", required = true) int pageNo,
			@RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "roomId", required = false) String roomId,
			@RequestParam(value = "courseTypeSubclassName", required = false) String courseTypeSubclassName) {
		Results<List<CourseLiveCheck>> results = new Results<>();

		results = questionBankService.selectLiveVerifyList(fromdate, todate, pageNo, pageSize, mobile, roomId,
				courseTypeSubclassName);

		return results;
	}

	/**
	 * 获取视频播放记录
	 * 
	 * @param userId     账号id 必填
	 * @param videoId    视频id 必填
	 * @param date       日期 必填
	 * @param numPerPage 一页展示条数
	 * @param page       第几页
	 * @return
	 */
	@RequestMapping(value = "/video/v2", method = RequestMethod.GET)
	public Results<List<PlayLog>> test(@RequestParam(value = "videoId", required = true) String videoId,
			@RequestParam(value = "date", required = true) String date,
			@RequestParam(value = "numPerPage", required = false) String numPerPage,
			@RequestParam(value = "page", required = false) String page) {
		Results<List<PlayLog>> results = new Results<List<PlayLog>>();

		// 必须为 treemap传参 ,参数顺序按首字母升序排序
		TreeMap<String, String> treeMap = new TreeMap<>();
		treeMap.put("userid", "91DD94C27B488135");
		treeMap.put("videoid", videoId);
		treeMap.put("date", date);
		treeMap.put("num_per_page", numPerPage);
		treeMap.put("page", page);

		// 拼接地址
		// t2iFuY3hnjXsSZ1PKnewAtHOtRhM1WL8 是cc视频的API key
		String address = Thqs.getThqstreeMap("t2iFuY3hnjXsSZ1PKnewAtHOtRhM1WL8", treeMap);
		try {
			Results<byte[]> testByte = Requests.testGet(Patterns.videoV2, null, address);
			byte[] bytess = testByte.getData();
			String byteString = new String(bytess);

			//json字符串转换
			VideoPlaybackRecord videoPlaybackRecord = Json.from(byteString, VideoPlaybackRecord.class);
			//String data = new String(bytess);
			List<PlayLog> list = videoPlaybackRecord.getPlay_logs().getPlay_log();
			for (PlayLog playLog : list) {
				//查name
				String userName = userService.selectNameById(playLog.getUserid());
				//查视频名和所属专业
				CourseChapter courseChapter =  courseSubclassService.selectNameAndCTSN(playLog.getVideoid());
				playLog.setUserName(userName);
				playLog.setCourseTypeSubclassName(courseChapter.getCourseTypeName());
				playLog.setSectionName(courseChapter.getSectionName());
			}
			results.setStatus("0");
			results.setCount(videoPlaybackRecord.getPlay_logs().getTotal());
			results.setData(videoPlaybackRecord.getPlay_logs().getPlay_log());

			return results;
		} catch (IOException e) {
			e.printStackTrace();
		}
		results.setStatus("0");
		results.setMessage("错误！");

		return results;
	}

	/**
	 * 获取用户自定义参数播放记录
	 * 
	 * @param mobile     手机号
	 * @param date       日期
	 * @param numPerPage 一页展示几条
	 * @param page       第几页
	 * @return
	 */
	@RequestMapping(value = "/custom/user/v2", method = RequestMethod.GET)
	public Results<String> customUserV2(@RequestParam(value = "mobile", required = true) String mobile,
			@RequestParam(value = "date", required = true) String date,
			@RequestParam(value = "numPerPage", required = false) String numPerPage,
			@RequestParam(value = "page", required = false) String page) {
		Results<String> results = new Results<>();
		// 根据手机号查询id
		String id = userService.selectIdByMobile(mobile);

		TreeMap<String, String> treeMap = new TreeMap<>();
		treeMap.put("userid", "91DD94C27B488135");
		treeMap.put("customid", id);
		treeMap.put("date", date);
		treeMap.put("num_per_page", numPerPage);
		treeMap.put("page", page);

		String address = Thqs.getThqstreeMap("t2iFuY3hnjXsSZ1PKnewAtHOtRhM1WL8", treeMap);
		try {
			Results<byte[]> testByte = Requests.testGet(Patterns.customUserV2, null, address);
			byte[] bytess = testByte.getData();
			String data = new String(bytess);

			results.setStatus("0");
			results.setData(data);

			return results;
		} catch (IOException e) {
			e.printStackTrace();
		}
		results.setStatus("0");
		results.setMessage("错误！");

		return results;
	}

	/**
	 * 获取视频自定义参数播放记录
	 * 
	 * @param videoId    视频id
	 * @param mobile     手机号
	 * @param date       日期
	 * @param numPerPage 一页展示几条
	 * @param page       第几页
	 * @return
	 */
	@RequestMapping(value = "/custom/video/v2", method = RequestMethod.GET)
	public Results<String> customVideoV2(@RequestParam(value = "videoId", required = true) String videoId,
			@RequestParam(value = "mobile", required = true) String mobile,
			@RequestParam(value = "date", required = true) String date,
			@RequestParam(value = "numPerPage", required = false) String numPerPage,
			@RequestParam(value = "page", required = false) String page) {
		Results<String> results = new Results<>();

		// 根据手机号查询id
		String id = userService.selectIdByMobile(mobile);

		TreeMap<String, String> treeMap = new TreeMap<>();
		treeMap.put("userid", "91DD94C27B488135");
		treeMap.put("videoid", videoId);
		treeMap.put("customid", id);
		treeMap.put("date", date);
		treeMap.put("num_per_page", numPerPage);
		treeMap.put("page", page);

		String address = Thqs.getThqstreeMap("t2iFuY3hnjXsSZ1PKnewAtHOtRhM1WL8", treeMap);
		try {
			Results<byte[]> testByte = Requests.testGet(Patterns.customVideoV2, null, address);
			byte[] bytess = testByte.getData();
			String data = new String(bytess);

			results.setStatus("0");
			results.setData(data);

			return results;
		} catch (IOException e) {
			e.printStackTrace();
		}
		results.setStatus("0");
		results.setMessage("错误！");

		return results;
	}
}
