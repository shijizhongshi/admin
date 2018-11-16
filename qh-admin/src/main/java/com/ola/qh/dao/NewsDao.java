package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.News;

public interface NewsDao {
	
	public List<News> selectNewList(@Param("pageNo") int pageNo,@Param("pageSize") int pageSize);
	
	public News singlenews(String id);
	
	public int saveNews(News news);
	
	public int updateNews(News news);

}
