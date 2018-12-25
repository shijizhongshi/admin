package com.ola.qh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.CommentText;
import com.ola.qh.service.ICommentTextService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

/**
 * 
 * 
* @ClassName: CommentTextController
* @Description:  评论添加固定信息
* @author guozihan
* @date   2018/12/12
*
 */

@RestController
@RequestMapping(value="/api/commenttext")
public class CommentTextController {

	@Autowired
	private ICommentTextService commentTextService;
	
	
	@RequestMapping(value="/select")
	public Results<List<CommentText>> selectCommentText(@RequestParam(name="textStatus",required=true)int textStatus){
		
		Results<List<CommentText>> results=new Results<List<CommentText>>();
		
		List<CommentText> list=commentTextService.selectCommentText(textStatus);
		
		if(list==null || "".equals(list)){
			
			results.setStatus("1");
			return results;
			
		}
		
		results.setData(list);
		results.setStatus("0");
		return results;
	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Results<String> insertCommentText(@RequestBody @Valid CommentText commentText,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		if (valid.hasErrors()) {
			results.setMessage("信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}
		
		commentText.setId(KeyGen.uuid());
		int insert=commentTextService.insertCommentText(commentText);
		
		if(insert<=0){
			results.setMessage("保存失败");
			results.setStatus("1");
			return results;
			
		}
		
		results.setStatus("0");
		return results;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateCommentText(@RequestBody CommentText commentText){
		
		Results<String> results=new Results<String>();
		
		int update=commentTextService.updateCommentText(commentText);
		
		if(update<=0){
			results.setMessage("修改失败");
			results.setStatus("1");
			return results;
			
		}
		
		results.setStatus("0");
		return results;
	}
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteCommentText(@RequestParam(name="id",required=true)String id){
		
		Results<String> results=new Results<String>();
		
		int delete=commentTextService.deleteCommentText(id);
		
		if(delete<=0){
			results.setMessage("修改失败");
			results.setStatus("1");
			return results;
			
		}
		
		results.setStatus("0");
		return results;
	}
}
