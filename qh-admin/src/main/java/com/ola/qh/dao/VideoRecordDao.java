package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.VideoRecord;

public interface VideoRecordDao {

	public List<VideoRecord> VideoRecordList(@Param("userId")String userId,@Param("courseName")String courseName,
			@Param("chapterName")String chapterName,@Param("sectionName")String sectionName,@Param("pageNo")int pageNo
			,@Param("pageSize")int pageSize);
	
	public int VideoRecordCount(@Param("userId")String userId,@Param("courseName")String courseName,
			@Param("chapterName")String chapterName,@Param("sectionName")String sectionName);
}
