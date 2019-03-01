package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserVideo;
import com.ola.qh.util.Results;

public interface IUserVideoService {

	public Results<List<UserVideo>> videoList(int pageNo,int pageSize,String videoName);
	
	public Results<String> deleteVideo(String id);
}
