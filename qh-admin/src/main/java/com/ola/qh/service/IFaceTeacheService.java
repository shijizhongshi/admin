package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.FaceTeache;
import com.ola.qh.util.Results;

public interface IFaceTeacheService {

	public Results<List<FaceTeache>> faceTeacheList(String courseTypeSubclassName,String teacherName,String courseName,int pageNo,int pageSize);
	
	public Results<String> insertFaceTeache(FaceTeache faceTeache);
	
	public Results<String> updateFaceTeache(FaceTeache faceTeache);
	
	public Results<String> deleteFaceTeache(String id);
}
