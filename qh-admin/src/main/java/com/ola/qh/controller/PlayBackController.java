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

import com.ola.qh.entity.PlayBack;
import com.ola.qh.service.IPlayBackService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/playback")
public class PlayBackController {

	@Autowired
	private IPlayBackService playBackService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<PlayBack>> playBackList(@RequestParam(name="liveId",required=false)String liveId,
			@RequestParam(name="playbackName",required=false)String playbackName,@RequestParam(name="pageNo",required=true)int pageNo,
			@RequestParam(name="pageSize",required=true)int pageSize){
		
		return playBackService.playBackList(liveId, playbackName, pageNo, pageSize);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String>	insertPlayBack(@RequestBody @Valid PlayBack playBack,BindingResult valid){
		
		Results<String> results=new Results<String>();
		if(valid.hasErrors()){
			results.setMessage("信息不足");
			results.setStatus("1");
			return results;
		}
		return playBackService.insertPlayBack(playBack);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String>	updatePlayBack(@RequestBody PlayBack playBack){
		
		return playBackService.updatePlayBack(playBack);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String>	deletePlayBack(@RequestParam(name="id",required=true)String id){
		
		return playBackService.deletePlayBack(id);
	}
}
