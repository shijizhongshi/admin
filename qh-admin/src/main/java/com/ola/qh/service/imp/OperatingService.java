package com.ola.qh.service.imp;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.OperatingDao;
import com.ola.qh.entity.Operating;
import com.ola.qh.service.IOperatingService;
import com.ola.qh.util.Results;

@Service
public class OperatingService implements IOperatingService{

	@Autowired
	private OperatingDao operatingDao;

	@Transactional
	@Override
	public Results<List<Operating>> operatingList(String userRoleCategory,String userRoleNickname, String operatingScope, String operatingStatus,
			int pageNo, int pageSize) {
		
		Results<List<Operating>> results=new Results<List<Operating>>();
		
		try {
			
			List<Operating> list=operatingDao.operatingList(userRoleCategory,userRoleNickname, operatingScope, operatingStatus, pageNo, pageSize);
			int count=operatingDao.operatingCount(userRoleCategory,userRoleNickname, operatingScope, operatingStatus);
			for (Operating operating : list) {
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				operating.setShowtime(sf.format(operating.getAddtime()));
			}
			
			results.setData(list);
			results.setCount(count);
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

	@Override
	public int insertOperating(Operating operating) {
		// TODO Auto-generated method stub
		return operatingDao.insertOperating(operating);
	}

	@Override
	public int deleteOperating(String id) {
		// TODO Auto-generated method stub
		return operatingDao.deleteOperating(id);
	}
}
