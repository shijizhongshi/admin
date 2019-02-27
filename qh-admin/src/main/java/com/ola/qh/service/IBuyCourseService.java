package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.BuyCourseDomain;
import com.ola.qh.entity.OpenCourse;
import com.ola.qh.entity.UserBuyCourse;
import com.ola.qh.util.Results;

public interface IBuyCourseService {

	public Results<String> openCourse(OpenCourse oc);
	
	public Results<List<UserBuyCourse>> buyRecord(BuyCourseDomain bcd);
	
	public int existOpenCourse(String courseId, String userId, String classId);
	
	
	
}
