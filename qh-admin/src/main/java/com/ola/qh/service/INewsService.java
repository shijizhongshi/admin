package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.News;

public interface INewsService {

	public List<News> selectNewList(int pageNo,int pageSize);
	
	public News singlenews(String id);
	
	public int saveNews(News news);
	
	public int updateNews(News news);

}
