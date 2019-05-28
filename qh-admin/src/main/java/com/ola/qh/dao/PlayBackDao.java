package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.PlayBack;

public interface PlayBackDao {

	public List<PlayBack> playBackList(@Param("liveId")String liveId,@Param("playbackName")String playbackName,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public int playBackCount(@Param("liveId")String liveId,@Param("playbackName")String playbackName);
	
	public int existPlayBack(@Param("liveId")String liveId);
	
	public int insertPlayBack(PlayBack playBack);
	
	public int updatePlayBack(PlayBack playBack);
	
	public int deletePlayBack(@Param("id")String id);
}
