package com.ola.qh.dao;

import java.util.List;

import com.ola.qh.entity.News;

public interface NewsDao {
	
	public List<News> selectNewList();

}
