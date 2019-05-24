package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.LivePay;
import com.ola.qh.util.Results;

public interface ILivePayService {

	public Results<List<LivePay>> livePayList(String status,String livename,String startTime,int pageNo,int pageSize);
}
