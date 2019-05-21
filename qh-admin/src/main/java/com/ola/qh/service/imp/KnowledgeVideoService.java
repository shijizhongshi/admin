package com.ola.qh.service.imp;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.ola.qh.dao.KnowledgeVideoDao;
import com.ola.qh.dao.UserVideoDao;
import com.ola.qh.entity.KnowledgeVideo;
import com.ola.qh.entity.UserVideo;
import com.ola.qh.service.IKnowledgeVideoService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.util.Thqs;
import com.ola.qh.weixin.handler.Requests;

@Service
public class KnowledgeVideoService implements IKnowledgeVideoService {

	@Autowired
	private KnowledgeVideoDao knowledgeVideoDao;

	@Autowired
	private UserVideoDao userVideoDao;

	@Transactional
	@Override
	public Results<List<KnowledgeVideo>> KnowledgeVideoList(int pageNo, int pageSize, String title,
			String courseTypeSubclassName) {

		Results<List<KnowledgeVideo>> results = new Results<List<KnowledgeVideo>>();
		// try {

		List<KnowledgeVideo> list = knowledgeVideoDao.KnowledgeVideoList(pageNo, pageSize, title,
				courseTypeSubclassName);

		int count = knowledgeVideoDao.KnowledgeVideoCount(title, courseTypeSubclassName);

		/*
		 * for (KnowledgeVideo knowledgeVideo : list) {
		 * 
		 * List<String> CourseTypeNames = new ArrayList<String>();
		 * 
		 * List<String> listCourseTypeSubclass = new ArrayList<String>();
		 * 
		 * if (knowledgeVideo.getCourseTypeSubclassName().indexOf(",") >= 0) {
		 * listCourseTypeSubclass =
		 * Arrays.asList(knowledgeVideo.getCourseTypeSubclassName().split(","));
		 * 
		 * knowledgeVideo.setCourseTypeSubclassNames(listCourseTypeSubclass);
		 * 
		 * for (String string : listCourseTypeSubclass) {
		 * 
		 * CourseTypeSubclass courseTypeSubclass =
		 * courseDao.singleCourseTypeSubclass(string);
		 * 
		 * String CourseType =
		 * courseDao.singleCourseType(courseTypeSubclass.getCourseTypeId())
		 * .getCourseTypeName(); CourseTypeNames.add(CourseType);
		 * 
		 * } } else { knowledgeVideo.getCourseTypeSubclassNames().add(knowledgeVideo.
		 * getCourseTypeSubclassName()); CourseTypeSubclass courseTypeSubclass =
		 * courseDao
		 * .singleCourseTypeSubclass(knowledgeVideo.getCourseTypeSubclassName());
		 * 
		 * String CourseType =
		 * courseDao.singleCourseType(courseTypeSubclass.getCourseTypeId())
		 * .getCourseTypeName(); CourseTypeNames.add(CourseType);
		 * 
		 * } HashSet<String> b = new HashSet<String>(CourseTypeNames);
		 * CourseTypeNames.clear(); CourseTypeNames.addAll(b);
		 * knowledgeVideo.setCourseTypeNames(CourseTypeNames);
		 * 
		 * }
		 */
		results.setCount(count);
		results.setData(list);
		results.setStatus("0");
		return results;

		/*
		 * } catch (Exception e) {
		 * TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		 * results.setStatus("1"); return results; }
		 */
	}

	/**
	 * 短视频添加
	 */
	@Override
	public Results<String> insertKnowledgeVideo(KnowledgeVideo knowledgeVideo) {

		Results<String> results = new Results<String>();
		// 通过cc接口获取视频第一帧作为封面
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("videoid", knowledgeVideo.getVideoId());
		treeMap.put("userid", Patterns.accountId186);
		treeMap.put("format", "json");

		String address = Thqs.getThqstreeMap(Patterns.token186, treeMap);
		/*
		 * String subTypeName = ""; for (int j = 0; j <
		 * knowledgeVideo.getCourseTypeSubclassNames().size(); j++) { String
		 * courseTypeSubclassName = knowledgeVideo.getCourseTypeSubclassNames().get(j);
		 * if (j == 0) { subTypeName = subTypeName + courseTypeSubclassName; } else {
		 * subTypeName = subTypeName + "," + courseTypeSubclassName; } }
		 */
		Integer VideoMax = knowledgeVideoDao.selectMaxOrder();
		if (VideoMax != null) {
			knowledgeVideo.setOrders(VideoMax + 1);
		} else {
			knowledgeVideo.setOrders(1);
		}
		try {
			Results<byte[]> testByte = Requests.testGet(Patterns.videoV5, null, address);
			byte[] bytess = testByte.getData();
			String byteString = new String(bytess);
			// 截取image
			JSONObject jsonObject = JSONObject.parseObject(byteString);
			String videoString = jsonObject.getString("video");
			JSONObject jsonObjects = JSONObject.parseObject(videoString);
			//封面
			String image = jsonObjects.getString("image");
			//高清图
			String newImage = image.replace("-0.jpg","-1.jpg");
			//添加
			//knowledgeVideo.setCourseTypeSubclassName(knowledgeVideo.getCourseTypeSubclassName());
			knowledgeVideo.setId(KeyGen.uuid());
			knowledgeVideo.setAddtime(new Date());
			knowledgeVideo.setFirstImage(newImage);
			int insert = knowledgeVideoDao.insertKnowledgeVideo(knowledgeVideo);
			if (knowledgeVideo.getStatus() == 1) {
				UserVideo userVideo = new UserVideo();
				userVideo.setId(KeyGen.uuid());
				userVideo.setNickname("admin");
				userVideo.setAddtime(new Date());
				// 封面添加
				userVideo.setFirstImage(newImage);
				userVideo.setVideoId(knowledgeVideo.getVideoId());
				userVideo.setTitle(knowledgeVideo.getTitle());
				userVideo.setUserId("1");
				userVideoDao.insert(userVideo);
			}
			if (insert > 0) {
				results.setStatus("0");
				return results;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

		results.setStatus("1");
		return results;

	}

	/**
	 * 短视频修改
	 */
	@Transactional
	@Override
	public Results<String> updateKnowledgeVideo(KnowledgeVideo knowledgeVideo) {

		Results<String> results = new Results<String>();
		// 通过cc接口获取视频第一帧作为封面
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("videoid", knowledgeVideo.getVideoId());
		treeMap.put("userid", Patterns.accountId186);
		treeMap.put("format", "json");

		String address = Thqs.getThqstreeMap(Patterns.token186, treeMap);
		try {
			Results<byte[]> testByte = Requests.testGet(Patterns.videoV5, null, address);
			byte[] bytess = testByte.getData();
			String byteString = new String(bytess);
			// 截取image
			JSONObject jsonObject = JSONObject.parseObject(byteString);
			String videoString = jsonObject.getString("video");
			JSONObject jsonObjects = JSONObject.parseObject(videoString);
			String image = jsonObjects.getString("image");
			//高清图
			String newImage = image.replace("-0.jpg","-1.jpg");

			if (knowledgeVideo.getStatus() == 1) {
				UserVideo userVideo = new UserVideo();
				userVideo.setId(KeyGen.uuid());
				userVideo.setAddtime(new Date());
				userVideo.setFirstImage(newImage);
				userVideo.setVideoId(knowledgeVideo.getVideoId());
				userVideo.setTitle(knowledgeVideo.getTitle());
				userVideo.setUserId("1");
				userVideoDao.insert(userVideo);
			}
			/*if (knowledgeVideo.getCourseTypeSubclassNames() != null) {

				String subTypeName = "";
				for (int j = 0; j < knowledgeVideo.getCourseTypeSubclassNames().size(); j++) {
					String courseTypeSubclassName = knowledgeVideo.getCourseTypeSubclassNames().get(j);
					if (j == 0) {
						subTypeName = subTypeName + courseTypeSubclassName;
					} else {
						subTypeName = subTypeName + "," + courseTypeSubclassName;
					}
				}

				knowledgeVideo.setCourseTypeSubclassName(subTypeName);
			}*/
			knowledgeVideo.setUpdatetime(new Date());
			// 封面
			knowledgeVideo.setFirstImage(newImage);
			int update = knowledgeVideoDao.updateKnowledgeVideo(knowledgeVideo);

			if (update <= 0) {
				results.setStatus("1");
				return results;
			}
			results.setStatus("0");
			return results;

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

	@Override
	public int deleteKnowledgeVideo(String id) {
		// TODO Auto-generated method stub
		return knowledgeVideoDao.deleteKnowledgeVideo(id);
	}

	@Transactional
	@Override
	public Results<String> sectionOrders(String id, int orders, String operateType) {
		// TODO Auto-generated method stub
		Results<String> result = new Results<String>();
		if ("down".equals(operateType)) { // 下移
			// 获取下一条记录iorder
			int nextOrder = knowledgeVideoDao.selectOrder("down", orders);
			// 修改下一条的为当前值
			knowledgeVideoDao.updateOrders(null, nextOrder, orders);
			// 修改自己的排序为下一条
			knowledgeVideoDao.updateOrders(id, 0, nextOrder);
			result.setData(String.valueOf(nextOrder));
		}
		if ("up".equals(operateType)) { // 上移
			// 获取上一条记录iorder
			int previousOrder = knowledgeVideoDao.selectOrder("up", orders);
			// 修改上一条的为当前值
			knowledgeVideoDao.updateOrders(null, previousOrder, orders);
			// 修改自己的排序为上一条
			knowledgeVideoDao.updateOrders(id, 0, previousOrder);
			result.setData(String.valueOf(previousOrder));
		}
		if ("title".equals(operateType)) { // 置顶
			// 获取最大值
			Integer VideoMax = knowledgeVideoDao.selectMaxOrder();
			if (orders == VideoMax) {
				result.setStatus("1");
				result.setMessage("已置顶");
				return result;
			}
			// 修改自己的排序最大值+1
			knowledgeVideoDao.updateOrders(id, 0, VideoMax + 1);
			result.setData(String.valueOf(VideoMax + 1));
		}
		result.setStatus("0");
		return result;
	}
	/**
	 * 更新全部短视频封面
	 */
	@Override
	public Results<String> updateAll() {
		Results<String> results = new Results<String>();
		//1.查询全部短视频的videoId
		List<KnowledgeVideo> list = knowledgeVideoDao.selectVideoIdAll();
		for (KnowledgeVideo knowledgeVideo : list) {
			// 通过cc接口获取视频第一帧作为封面
			TreeMap<String, String> treeMap = new TreeMap<String, String>();
			treeMap.put("videoid", knowledgeVideo.getVideoId());
			treeMap.put("userid", Patterns.accountId186);
			treeMap.put("format", "json");

			String address = Thqs.getThqstreeMap(Patterns.token186, treeMap);
			Results<byte[]> testByte;
			try {
				testByte = Requests.testGet(Patterns.videoV5, null, address);
				byte[] bytess = testByte.getData();
				String byteString = new String(bytess);
				// 截取image
				JSONObject jsonObject = JSONObject.parseObject(byteString);
				String videoString = jsonObject.getString("video");
				//update  根据id
				if (videoString != null) {
					JSONObject jsonObjects = JSONObject.parseObject(videoString);
					String image = jsonObjects.getString("image");
					//高清图
					String newImage = image.replace("-0.jpg","-1.jpg");
					knowledgeVideoDao.updateImage(newImage,knowledgeVideo.getId());
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		
		 
		
		results.setStatus("0");
		return results;
	}

}
