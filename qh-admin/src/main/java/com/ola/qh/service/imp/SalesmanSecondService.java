package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.SalesmanDao;
import com.ola.qh.dao.SalesmanSecondDao;
import com.ola.qh.entity.SalesmanSecond;
import com.ola.qh.service.ISalesmanSecondService;
import com.ola.qh.util.Results;

@Service
public class SalesmanSecondService implements ISalesmanSecondService{

	@Autowired
	private SalesmanSecondDao salesmanSecondDao;
	@Autowired
	private SalesmanDao salesmanDao;
	
	@Transactional
	@Override
	public Results<List<SalesmanSecond>> SecondList(String salesmanId,String mobile,int pageNo,int pageSize) {

		Results<List<SalesmanSecond>> results=new Results<List<SalesmanSecond>>();
		try {
			
			List<SalesmanSecond> list=salesmanSecondDao.SecondList(salesmanId,mobile, pageNo, pageSize);
			int count=salesmanSecondDao.SecondCount(salesmanId,mobile);
			
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

	/*@Transactional
	@Override
	public Results<String> insertSecond(SalesmanSecond salesmanSecond) {
		
		Results<String> results=new Results<String>();
		try {
			
			SalesmanSecond exist=salesmanSecondDao.existSecond(salesmanSecond.getMobile());
			
			if(exist!=null){
				
				results.setStatus("1");
				results.setMessage("手机号已存在");
				return results;
			}
			
			salesmanSecond.setAddtime(new Date());
			salesmanSecond.setId(KeyGen.uuid());
			salesmanSecondDao.insertSecond(salesmanSecond);
			
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}*/

	@Transactional
	@Override
	public Results<String> updateSecond(String salesmanId,String mobile,String salesmanIdNew) {
		
		Results<String> results=new Results<String>();
		try {
			
			if(salesmanId.equals(salesmanIdNew)){
				results.setStatus("1");
				results.setMessage("不可对自己转入");
				return results;
			}
			SalesmanSecond salesmanSecond=new SalesmanSecond();
			salesmanSecond.setSalesmanId(salesmanId);
			salesmanSecond.setSalesmanIdNew(salesmanIdNew);
			salesmanSecond.setUpdatetime(new Date());
			salesmanSecondDao.updateSecond(salesmanSecond);
			salesmanDao.deleteSalesman(salesmanSecond.getSalesmanId());
			
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

	/*@Transactional
	@Override
	public Results<String> deleteSecond(String salesmanId, String id) {
		
		Results<String> results=new Results<String>();
		try {
			
			salesmanSecondDao.deleteSecond(salesmanId, id);
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}*/

	
}
