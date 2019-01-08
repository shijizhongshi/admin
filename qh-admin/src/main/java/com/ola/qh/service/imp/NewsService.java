package com.ola.qh.service.imp;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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
	public List<News> selectNewList(int pageNo,int pageSize,String title) {
		// TODO Auto-generated method stub
		return newsDao.selectNewList(pageNo, pageSize, title, null);
	}

	public int selectNewListCount(String title){
		return newsDao.selectNewListCount(null, title);
	}
	@Override
	public News singlenews(String id) {
		// TODO Auto-generated method stub
		return newsDao.singlenews(id);
	}

	@Override
	public int saveNews(News news) {
		// TODO Auto-generated method stub
		return newsDao.saveNews(news);
	}


	@Override
	public int updateNews(News news) {
		// TODO Auto-generated method stub
		return newsDao.updateNews(news);
	}

	@Override
	public int deletenews(String id) {
		// TODO Auto-generated method stub
		return newsDao.deletenews(id);
	}

}
