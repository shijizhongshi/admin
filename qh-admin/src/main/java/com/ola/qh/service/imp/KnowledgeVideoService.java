package com.ola.qh.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.CourseDao;
import com.ola.qh.dao.KnowledgeVideoDao;
import com.ola.qh.dao.UserVideoDao;
import com.ola.qh.entity.CourseTypeSubclass;
import com.ola.qh.entity.KnowledgeVideo;
import com.ola.qh.entity.UserVideo;
import com.ola.qh.service.IKnowledgeVideoService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class KnowledgeVideoService implements IKnowledgeVideoService{

	@Autowired
	private KnowledgeVideoDao knowledgeVideoDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private UserVideoDao userVideoDao;

	@Transactional
	@Override
	public Results<List<KnowledgeVideo>> KnowledgeVideoList(int pageNo,int pageSize,String title,String courseTypeSubclassName) {
		
		Results<List<KnowledgeVideo>> results=new Results<List<KnowledgeVideo>>();
		try {
			
		List<KnowledgeVideo> list=knowledgeVideoDao.KnowledgeVideoList(pageNo, pageSize, title, courseTypeSubclassName);
		
		int count=knowledgeVideoDao.KnowledgeVideoCount(title, courseTypeSubclassName);
		
		for (KnowledgeVideo knowledgeVideo : list) {
			
			List<String> CourseTypeNames=new ArrayList<String>();
			
			List<String> listCourseTypeSubclass=new ArrayList<String>();
				
			if(knowledgeVideo.getCourseTypeSubclassName().indexOf(",")>=0){
				listCourseTypeSubclass=Arrays.asList(knowledgeVideo.getCourseTypeSubclassName().split(","));
				
				knowledgeVideo.setCourseTypeSubclassNames(listCourseTypeSubclass);
				
				for (String string : listCourseTypeSubclass) {
					
					CourseTypeSubclass courseTypeSubclass=courseDao.singleCourseTypeSubclass(string);
					
					String CourseType=courseDao.singleCourseType(courseTypeSubclass.getCourseTypeId()).getCourseTypeName();
					CourseTypeNames.add(CourseType);
					
				}
		}else{
				knowledgeVideo.getCourseTypeSubclassNames().add(knowledgeVideo.getCourseTypeSubclassName());
				CourseTypeSubclass courseTypeSubclass=courseDao.singleCourseTypeSubclass(knowledgeVideo.getCourseTypeSubclassName());
				
				String CourseType=courseDao.singleCourseType(courseTypeSubclass.getCourseTypeId()).getCourseTypeName();
				CourseTypeNames.add(CourseType);
				
			}
			 HashSet<String> b = new HashSet<String>(CourseTypeNames);   
			 CourseTypeNames.clear();   
			 CourseTypeNames.addAll(b);
			 knowledgeVideo.setCourseTypeNames(CourseTypeNames);
			
		}
		results.setCount(count);
		results.setData(list);
		results.setStatus("0");
		return results;
		
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

	@Override
	public Results<String> insertKnowledgeVideo(KnowledgeVideo knowledgeVideo) {
		
		Results<String> results=new Results<String>();
		
		if(knowledgeVideo.getStatus()==1){
			UserVideo userVideo=new UserVideo();
			userVideo.setId(KeyGen.uuid());
			userVideo.setNickname("admin");
			userVideo.setAddtime(new Date());
			userVideo.setFirstImage(knowledgeVideo.getFirstImage());
			userVideo.setVideoId(knowledgeVideo.getVideoId());
			userVideo.setTitle(knowledgeVideo.getTitle());
			userVideo.setUserId("1");
			userVideoDao.insert(userVideo);
		}
		
		String subTypeName="";
		for (int j=0;j<knowledgeVideo.getCourseTypeSubclassNames().size();j++) {
			String courseTypeSubclassName= knowledgeVideo.getCourseTypeSubclassNames().get(j);
			if(j==0){
						subTypeName=subTypeName+courseTypeSubclassName;
					}else{
						subTypeName=subTypeName+","+courseTypeSubclassName;
					}
		}
		
		Integer VideoMax = knowledgeVideoDao.selectMaxOrder();
		if(VideoMax!=null){
			knowledgeVideo.setOrders(VideoMax+1);
			knowledgeVideo.setCourseTypeSubclassName(subTypeName);
			knowledgeVideo.setId(KeyGen.uuid());
			knowledgeVideo.setAddtime(new Date());
			int insert=knowledgeVideoDao.insertKnowledgeVideo(knowledgeVideo);
			if(insert>0){
				results.setStatus("0");
				return results;
			}
			
		}
		results.setStatus("1");
		return results;
	}

	@Transactional
	@Override
	public Results<String> updateKnowledgeVideo(KnowledgeVideo knowledgeVideo) {
		
		Results<String> results=new Results<String>();
		try {
			
		if(knowledgeVideo.getStatus()==1){
			UserVideo userVideo=new UserVideo();
			userVideo.setId(KeyGen.uuid());
			userVideo.setAddtime(new Date());
			userVideo.setFirstImage(knowledgeVideo.getFirstImage());
			userVideo.setVideoId(knowledgeVideo.getVideoId());
			userVideo.setTitle(knowledgeVideo.getTitle());
			userVideo.setUserId("1");
			userVideoDao.insert(userVideo);
		}
		if(knowledgeVideo.getCourseTypeSubclassNames()!=null){
			
			String subTypeName="";
			for (int j=0;j<knowledgeVideo.getCourseTypeSubclassNames().size();j++) {
				String courseTypeSubclassName= knowledgeVideo.getCourseTypeSubclassNames().get(j);
				if(j==0){
							subTypeName=subTypeName+courseTypeSubclassName;
						}else{
							subTypeName=subTypeName+","+courseTypeSubclassName;
						}
			}
			
		knowledgeVideo.setCourseTypeSubclassName(subTypeName);
		}
		knowledgeVideo.setUpdatetime(new Date());
		int update=knowledgeVideoDao.updateKnowledgeVideo(knowledgeVideo);
		
		if(update<=0){
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
		Results<String> result=new Results<String>();
		if ("down".equals(operateType)) { //下移
            //获取下一条记录iorder
            int nextOrder = knowledgeVideoDao.selectOrder("down", orders);
            //修改下一条的为当前值
            knowledgeVideoDao.updateOrders(null, nextOrder, orders);
            //修改自己的排序为下一条
            knowledgeVideoDao.updateOrders(id, 0, nextOrder);
            result.setData(String.valueOf(nextOrder));
        }
        if ("up".equals(operateType)) { //上移
            //获取上一条记录iorder
        	int previousOrder = knowledgeVideoDao.selectOrder("up", orders);
            //修改上一条的为当前值
        	knowledgeVideoDao.updateOrders(null, previousOrder, orders);
            //修改自己的排序为上一条
        	knowledgeVideoDao.updateOrders(id, 0, previousOrder);
        	 result.setData(String.valueOf(previousOrder));
        }
        result.setStatus("0");
		return result;
	}
	
}
