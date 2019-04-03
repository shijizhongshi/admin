package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.OperatingDao;
import com.ola.qh.entity.Operating;
import com.ola.qh.service.IOperatingService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class OperatingService implements IOperatingService{

	@Autowired
	private OperatingDao operatingDao;
	

	@Transactional
	@Override
	public Results<List<Operating>> operatingList(String userRoleUsername, String operatingScope, String operatingStatus,
			int pageNo, int pageSize) {
		
		Results<List<Operating>> results=new Results<List<Operating>>();
		
		try {
			
			List<Operating> list=operatingDao.operatingList(userRoleUsername, operatingScope, operatingStatus, pageNo, pageSize);
			int count=operatingDao.operatingCount(userRoleUsername, operatingScope, operatingStatus);
			
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

	@Transactional
	@Override
	public Results<String> insertOperating(Operating operating) {
		
		Results<String> results=new Results<String>();
		
		try {
			
			
			operating.setAddtime(new Date());
			operating.setId(KeyGen.uuid());
			int insert=operatingDao.insertOperating(operating);
		
				if(insert<=0){
					
					results.setStatus("1");
					return results;
				}
				results.setStatus("0");
				return results;
		
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				results.setStatus("1");
				return results;
			}
	}

	@Override
	public int deleteOperating(String id) {
		// TODO Auto-generated method stub
		return operatingDao.deleteOperating(id);
	}
}
