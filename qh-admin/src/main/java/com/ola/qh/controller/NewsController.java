package com.ola.qh.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.News;
import com.ola.qh.service.INewsService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

/**
 * 
 * @ClassName: NewsController
 * @Description: 不同类别的新闻展示
 * @author guoyuxue
 * @date 2018年11月15日
 *
 */
@RestController
@RequestMapping(value="/api/news")
public class NewsController {

	@Autowired
	private INewsService newsService;
	/**
	 * 如果page=1的话说明首页展示的资讯
	 * 如果page是变量的话就是资讯的列表页
	 * @param page
	 * @author guoyuxue
	 * @return
	 */
	@RequestMapping(value="/newLists")
	public Results<List<News>> newsList(
			@RequestParam(name="page", required=false) int page,
			@RequestParam(name="title",required=false)String title){
		
		Results<List<News>> result = new Results<List<News>>();
		int pageNo = (page - 1) * Patterns.pageSize;
		
		List<News> lists = newsService.selectNewList(pageNo,Patterns.pageSize,title);
		result.setCount(newsService.selectNewListCount(title));
		result.setData(lists);
		result.setStatus("0");
		return result;
		
	}
	/**
	 * <p>Title: newSingle</p>  
	 * <p>Description: 资讯的详情页</p>  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/single",method=RequestMethod.GET)
	public Results<News> newSingle(@RequestParam(name="id",required=true)String id){
		
		Results<News> result=new Results<News>();
		News news = newsService.singlenews(id);
		if(news!=null){
			result.setData(news);
			result.setStatus("0");
			return result;
		}
		result.setStatus("1");
		result.setMessage("对象不存在");
		return result;
	}
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Results<String> saveNews(@RequestBody @Valid News news,BindingResult valid){
		
		Results<String> result = new Results<String>();
		if (valid.hasErrors()) {
			result.setMessage("新闻信息不完整");
			result.setStatus("1");
			return result;
		}
		news.setId(KeyGen.uuid());
		news.setAddtime(new Date());
		int num = newsService.saveNews(news);
		if(num>0){
			result.setStatus("0");
			return result;
		}
		result.setMessage("保存失败");
		result.setStatus("1");
		return result;
	}
	
	
	
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateNews(@RequestBody News news){
		Results<String> result = new Results<String>();
		int num = newsService.updateNews(news);
		if(num>0){
			result.setStatus("0");
			return result;
		}
		result.setMessage("修改失败");
		result.setStatus("1");
		return result;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteNews(@RequestParam(name="id",required=true)String id){
		Results<String> result = new Results<String>();
		int num = newsService.deletenews(id);
		if(num>0){
			result.setStatus("0");
			return result;
		}
		result.setMessage("删除失败");
		result.setStatus("1");
		return result;
	}
	
	
}
