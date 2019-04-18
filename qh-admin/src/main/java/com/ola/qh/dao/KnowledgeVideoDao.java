package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.KnowledgeVideo;

public interface KnowledgeVideoDao {

	public List<KnowledgeVideo> KnowledgeVideoList(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,
			@Param("title")String title,@Param("courseTypeSubclassName")String courseTypeSubclassName);
	
	public int KnowledgeVideoCount(@Param("title")String title,@Param("courseTypeSubclassName")String courseTypeSubclassName);
	
	public int insertKnowledgeVideo(KnowledgeVideo knowledgeVideo);
	
	public int updateKnowledgeVideo(KnowledgeVideo knowledgeVideo);
	
	public int deleteKnowledgeVideo(@Param("id")String id);
	
	public int selectOrder(@Param("type")String type,@Param("orders")int orders);
	
	public int updateOrders(@Param("id")String id,@Param("originalOrder")int originalOrder,
			@Param("orders")int orders);
	
	///////得到记录中最大的orders
	public Integer selectMaxOrder();
}
