package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Operating;
import com.ola.qh.util.Results;

public interface IOperatingService {

	public Results<List<Operating>> operatingList(String userRoleUsername,String operatingScope,
			String operatingStatus,int pageNo,int pageSize,String todate,String fromdate);
	
	public Results<String> insertOperating(Operating operating);
	
	public int deleteOperating(String id);
}
