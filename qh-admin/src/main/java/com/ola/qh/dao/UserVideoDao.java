package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserVideo;

public interface UserVideoDao {

	public List<UserVideo> videoList(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("videoName")String videoName);
	
	public int videoCount(@Param("videoName")String videoName);
	
	public int insert(UserVideo uv);
	
	public int deleteVideo(@Param("id")String id);
}
