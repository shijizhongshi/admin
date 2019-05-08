package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.FaceTeache;

public interface FaceTeacheDao {

	public List<FaceTeache> faceTeacheList(@Param("courseTypeSubclassName")String courseTypeSubclassName,@Param("teacherName")String teacherName,
			@Param("courseName")String courseName,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public int faceTeacheCount(@Param("courseTypeSubclassName")String courseTypeSubclassName,
			@Param("teacherName")String teacherName,@Param("courseName")String courseName);
	
	public int insertFaceTeache(FaceTeache faceTeache);
	
	public int updateFaceTeache(FaceTeache faceTeache);
	
	public int deleteFaceTeache(@Param("id")String id);
}
