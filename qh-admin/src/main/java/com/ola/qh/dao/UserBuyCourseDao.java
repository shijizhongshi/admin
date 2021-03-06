package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.BuyCourseDomain;
import com.ola.qh.entity.UserBuyCourse;

public interface UserBuyCourseDao {

	public int insertUserCourse(UserBuyCourse ubc);

	public List<UserBuyCourse> selectUserBuyCourse(BuyCourseDomain bcd);

	public int selectUserBuyCount(BuyCourseDomain bcd);

	public int selectUserBuyCourseCount(@Param("userId") String userId, @Param("classId") String classId,
			@Param("courseId") String courseId);

	public int updateBuy(@Param("classId") String classId, @Param("courseId") String courseId);

	public List<UserBuyCourse> selectByClassId(@Param("classId") String classId, @Param("sex") String sex,
			@Param("userrole") String userrole, @Param("isdoctor") String isdoctor, @Param("birthday") String birthday);

	public List<UserBuyCourse> selectByCourseId(@Param("courseId") String id, @Param("sex") String sex,
			@Param("userrole") String userrole, @Param("isdoctor") String isdoctor, @Param("birthday") String birthday);
	
	public int existCourseId(List<String> productId);
}
