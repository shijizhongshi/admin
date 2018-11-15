package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.NewsDao;
import com.ola.qh.entity.News;
import com.ola.qh.service.INewsService;

/**
 * 
 * @ClassName: NewsService
 * @Description: 不同类别的新闻資訊的管理
 * @author guoyuxue
 * @date 2018年11月15日
 *
 */
@Service
public class NewsService implements INewsService {

	@Autowired
	private NewsDao newsDao;
	
	
	@Override
	public List<News> selectNewList() {
		// TODO Auto-generated method stub
		return newsDao.selectNewList();
	}

}
