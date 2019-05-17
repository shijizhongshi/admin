package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.SalesmanClientDao;
import com.ola.qh.dao.SalesmanDao;
import com.ola.qh.entity.Salesman;
import com.ola.qh.entity.SalesmanClient;
import com.ola.qh.service.ISalesmanService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@Service
public class SalesmanService implements ISalesmanService{

	@Autowired
	private SalesmanDao salesmanDao;
	@Autowired
	private SalesmanClientDao salesmanClientDao;
	
	@Transactional
	@Override
	public Results<List<Salesman>> SalesmanList(String name, String mobile, String address, int pageNo, int pageSize) {
		
		Results<List<Salesman>> results=new Results<List<Salesman>>();
		
		try {
			List<Salesman> list=salesmanDao.SalesmanList(name, mobile, address, pageNo, pageSize);
			for (Salesman salesman : list) {
				List<SalesmanClient> client=salesmanClientDao.ClientList(salesman.getId(), null, 0, 0);
				salesman.setClient(client);
			}
			int count=salesmanDao.SalesmanCount(name, mobile, address);
			
			results.setCount(count);
			results.setData(list);
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
	public Results<String> insertSalesman(Salesman salesman) {
		
		Results<String> results=new Results<String>();
		
		try {
			Pattern p = Pattern.compile(Patterns.INTERNAL_MOBILE_PATTERN);
			Matcher m = p.matcher(salesman.getMobile());
			if (!m.matches()) {
				results.setStatus("1");
				results.setMessage("手机号格式不对");
				return results;
			}
			Salesman exist=salesmanDao.exist(salesman.getMobile());
			if(exist!=null){
				results.setStatus("1");
				results.setMessage("手机号已存在");
				return results;
			}
			salesman.setAddtime(new Date());
			salesman.setId(KeyGen.uuid());
			salesmanDao.insertSalesman(salesman);
			
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
	public Results<String> updateSalesman(Salesman salesman) {
		
		Results<String> results=new Results<String>();
		
		try {
			if(salesman.getMobile()!=null){
				Pattern p = Pattern.compile(Patterns.INTERNAL_MOBILE_PATTERN);
				Matcher m = p.matcher(salesman.getMobile());
				if (!m.matches()) {
					results.setStatus("1");
					results.setMessage("手机号格式不对");
					return results;
				}
			}
			
			salesman.setUpdatetime(new Date());
			salesmanDao.updateSalesman(salesman);
			
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
	public Results<String> deleteSalesman(String id) {
		
		Results<String> results=new Results<String>();
		
		try {
			
			salesmanDao.deleteSalesman(id);
			
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

}
