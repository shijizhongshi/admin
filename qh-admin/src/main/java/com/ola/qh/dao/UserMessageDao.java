package com.ola.qh.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserMessage;

public interface UserMessageDao {

	public int insert(UserMessage um);

	public Integer insertMessage(@Param("id") String id, @Param("addtime") Date addtime, @Param("title") String title,
			@Param("describe") String content, @Param("userId") String userId, @Param("types") int types);
}
