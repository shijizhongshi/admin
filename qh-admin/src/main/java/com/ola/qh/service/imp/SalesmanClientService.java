package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.SalesmanClientDao;
import com.ola.qh.entity.SalesmanClient;
import com.ola.qh.service.ISalesmanClientService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class SalesmanClientService implements ISalesmanClientService{

	@Autowired
	private SalesmanClientDao salesmanClientDao;
	
	@Transactional
	@Override
	public Results<List<SalesmanClient>> ClientList(String salesmanId,String mobile,int pageNo,int pageSize) {

		Results<List<SalesmanClient>> results=new Results<List<SalesmanClient>>();
		try {
			
			List<SalesmanClient> list=salesmanClientDao.ClientList(salesmanId,mobile, pageNo, pageSize);
			int count=salesmanClientDao.ClientCount(salesmanId,mobile);
			
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
	public Results<String> insertClient(SalesmanClient salesmanClient) {
		
		Results<String> results=new Results<String>();
		try {
			
			SalesmanClient exist=salesmanClientDao.existClient(salesmanClient.getMobile());
			
			if(exist!=null){
				
				results.setStatus("1");
				results.setMessage("手机号已存在");
				return results;
			}
			
			salesmanClient.setAddtime(new Date());
			salesmanClient.setId(KeyGen.uuid());
			salesmanClientDao.insertClient(salesmanClient);
			
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
	public Results<String> updateClient(SalesmanClient salesmanClient) {
		
		Results<String> results=new Results<String>();
		try {
			
			
			salesmanClient.setUpdatetime(new Date());
			salesmanClientDao.updateClient(salesmanClient);
			
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
	public Results<String> deleteClient(String salesmanId, String id) {
		
		Results<String> results=new Results<String>();
		try {
			
			salesmanClientDao.deleteClient(salesmanId, id);
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

	
}
