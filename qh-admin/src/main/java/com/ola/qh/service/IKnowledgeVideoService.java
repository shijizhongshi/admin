package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.KnowledgeVideo;
import com.ola.qh.util.Results;

public interface IKnowledgeVideoService {
	
	public Results<List<KnowledgeVideo>> KnowledgeVideoList(int pageNo,int pageSize,String title,String courseTypeSubclassName);
	
	public Results<String> insertKnowledgeVideo(KnowledgeVideo knowledgeVideo);
	
	public Results<String> updateKnowledgeVideo(KnowledgeVideo knowledgeVideo);
	
	public int deleteKnowledgeVideo(String id);
	
	public Results<String> sectionOrders(String id,int orders,String operateType);

	public Results<String> updateAll();
}
