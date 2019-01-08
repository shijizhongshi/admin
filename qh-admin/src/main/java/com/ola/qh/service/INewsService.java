package com.ola.qh.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.News;

public interface INewsService {

	public List<News> selectNewList(int pageNo,int pageSize,String title);
	
	public int selectNewListCount(String title);
	
	public News singlenews(String id);
	
	public int saveNews(News news);
	
	public int updateNews(News news);
	
	public int deletenews(String id);

}
