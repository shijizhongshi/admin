package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.PlayBack;
import com.ola.qh.util.Results;

public interface IPlayBackService {

	public Results<List<PlayBack>> playBackList(String liveId,String playbackName,int pageNo,int pageSize);
	
	public int existPlayBack(String liveId);
	
	public Results<String> insertPlayBack(PlayBack playBack);
	
	public Results<String> updatePlayBack(PlayBack playBack);
	
	public Results<String> deletePlayBack(String id);
}
