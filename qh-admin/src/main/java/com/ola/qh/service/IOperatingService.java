package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Operating;
import com.ola.qh.util.Results;

public interface IOperatingService {

	public Results<List<Operating>> operatingList(String userRoleCategory,String userRoleUsername,String operatingScope,
			String operatingStatus,int pageNo,int pageSize);
	
	public int insertOperating(Operating operating);
	
	public int deleteOperating(String id);
}
