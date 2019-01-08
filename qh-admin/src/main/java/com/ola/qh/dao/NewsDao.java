package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.News;

public interface NewsDao {
	
	public List<News> selectNewList(@Param("pageNo") int pageNo,
			@Param("pageSize") int pageSize,
			@Param("title")String title,
			@Param("contentTypes")String contentTypes);
	
	public int selectNewListCount(@Param("contentTypes")String contentTypes,@Param("title")String title);
	
	public News singlenews(String id);
	
	public int saveNews(News news);
	
	public int updateNews(News news);
	
	public int deletenews(@Param("id")String id);

}
