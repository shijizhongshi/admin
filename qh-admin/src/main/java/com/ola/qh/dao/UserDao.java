package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.User;

public interface UserDao {

	public int updateUser(User user);
	
	public User singleUser(@Param("id")String id);
	
	public int insertUser(User user);
	
	public List<User> selectUser(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("mobile")String mobile,@Param("nickname")String nickname,@Param("userrole")String userrole);
	
	public int selectUserCount(@Param("mobile")String mobile,@Param("nickname")String nickname,@Param("userrole")String userrole);
	
	public int delete(@Param("id")String id);
	
	public int updateFavorite(@Param("productId")String productId);
	
	public String adminLogin(@Param("username")String username,@Param("password")String password);
	
	
	public List<User> selectStudent(@Param("fromdate")String fromdate,@Param("todate")String todate,
			@Param("realnameORmobile")String realnameORmobile,@Param("status")String status,
			@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public int selectStudentCount(@Param("fromdate")String fromdate,@Param("todate")String todate,
			@Param("realnameORmobile")String realnameORmobile,@Param("status")String status);
	
	public Integer selectCountByUserId(@Param("userId")String id);

	public List<User> send(@Param("sex")String sex,@Param("userrole") String userrole,@Param("isdoctor") String isdoctor,@Param("birthday") String birthday);

}
