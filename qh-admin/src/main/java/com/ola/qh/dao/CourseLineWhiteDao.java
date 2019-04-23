package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.CourseLineWhite;
import com.ola.qh.entity.User;

public interface CourseLineWhiteDao {

	public List<CourseLineWhite> lineWhiteList(@Param("liveId")String liveId,@Param("mobile")String mobile,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public Integer lineWhiteListCount(@Param("liveId")String liveId,@Param("mobile")String mobile);
	
	public int insertLineWhite(CourseLineWhite courseLineWhite);
	
	public int updateLineWhite(CourseLineWhite courseLineWhite);
	
	public int deleteLineWhite(@Param("id")String id,@Param("liveId")String liveId);

	public User selectCountById(@Param("viewerId")String viewerId);
}
