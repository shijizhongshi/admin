package com.ola.qh.service.imp;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ola.qh.dao.BusinessDao;
import com.ola.qh.entity.Business;
import com.ola.qh.entity.BusinessBook;
import com.ola.qh.entity.BusinessBookHistory;
import com.ola.qh.service.IBusinessService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class BusinessService implements IBusinessService{

	@Autowired
	private BusinessDao businessDao;
	
	@Transactional
	@Override
	public Results<String> save(Business b) {
		// TODO Auto-generated method stub
		///////保存加盟商的信息
		Results<String> result=new Results<String>();
		if(b.getId()!=null && !"".equals(b.getId())){
			///////修改用户的基本信息
			businessDao.update(b);
			if("1".equals(b.getStatus()) && "2".equals(b.getStatus())){
				////////学员不在属于这个加盟商
				businessDao.deleteBusinessUser(b.getId());
			}
			result.setStatus("0");
			return result;
		}else{
			
			int count = businessDao.exist(b.getName(),null);
			if(count>0){
				result.setStatus("1");
				result.setMessage("该名称的加盟商已经存在了");
				return result;
			}
			
			int num = businessDao.exist(null, b.getAddress());
			if(num>0){
				result.setStatus("1");
				result.setMessage("该地址的加盟商已经存在了");
				return result;
			}
			String businessId=KeyGen.uuid();
			Business bold = businessDao.single(null, b.getUsername(),null);
			if(bold!=null){
				result.setStatus("1");
				result.setMessage("该账号的加盟商已经存在了,请修改~");
				return result;
			}
			b.setId(businessId);
			Date date=new Date();
			b.setAddtime(date);
			Calendar c=Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.YEAR, 1);
			b.setExpireTime(c.getTime());
			businessDao.insert(b);//////保存加盟商的基本信息
			
			BusinessBook bb=new BusinessBook();
			bb.setAccount(b.getAccount());////课程总的金额
			bb.setAddtime(new Date());
			bb.setBusinessId(businessId);
			bb.setPayaccount(b.getPayaccount());
			bb.setSurplusaccount(b.getAccount());
			bb.setUsedaccount(BigDecimal.ZERO);
			bb.setId(KeyGen.uuid());
			businessDao.insertbook(bb);
			
			BusinessBookHistory bbh=new BusinessBookHistory();
			
			bbh.setBusinessId(businessId);
			bbh.setAddtime(new Date());
			bbh.setId(KeyGen.uuid());
			bbh.setAccount(b.getAccount());
			bbh.setPayaccount(b.getPayaccount());
			bbh.setTypes(1);/////进钱
			businessDao.insertBookHistory(bbh);
			result.setStatus("0");
			return result;
		}
		
		
	}
	
	@Transactional
	@Override
	public Results<String> charge(Business b) {
		// TODO Auto-generated method stub
		Results<String> result=new Results<String>();
		///////充值金额必须大于0;;;;;;
		if(b.getPayaccount().compareTo(BigDecimal.ZERO)==1 && b.getAccount().compareTo(BigDecimal.ZERO)==1){
			Business bnew = businessDao.single(b.getId(),null,null);
			if(bnew.getExpireTime()!=null){
				//////过期时间不为空
				if(bnew.getExpireTime().getTime()>=new Date().getTime()){
					/////钱不够但是没有到期的话
					Calendar c=Calendar.getInstance();
	                c.setTime(bnew.getExpireTime());
	                c.add(Calendar.YEAR, 1);
	                b.setExpireTime(c.getTime());
				}else if(bnew.getExpireTime().getTime()<new Date().getTime()){
					//////到期了
					Calendar c=Calendar.getInstance();
	                c.setTime(new Date());
	                c.add(Calendar.YEAR, 1);
	                b.setExpireTime(c.getTime());
				}
                ////////基本资料中有效期延长
				b.setStatus("0");
                businessDao.update(b);
                
               BusinessBook bb=businessDao.singlebook(b.getId(), null);
               if(bb!=null){
            	   ///////////账本表//////////////////////////////////
            	   BusinessBook bbnew=new BusinessBook();
            	   bbnew.setBusinessId(b.getId());
            	   ///////兑换的总的课程金额
            	   bbnew.setAccount(bb.getAccount().add(b.getAccount()));
            	   //////实际支付的总金额
            	   bbnew.setPayaccount(bb.getPayaccount().add(b.getPayaccount()));
            	   ////剩余兑换课程总额
            	   bbnew.setSurplusaccount(bb.getSurplusaccount().add(b.getAccount()));
            	   bbnew.setUpdatetime(new Date());
            	   businessDao.updatebook(bbnew);
            	   
            	   ///////////账本记录表//////////////////////////////////
            	   BusinessBookHistory bbh=new BusinessBookHistory();
            	   bbh.setId(KeyGen.uuid());
            	   bbh.setAddtime(new Date());
            	   bbh.setBusinessId(b.getId());
            	   bbh.setPayaccount(b.getPayaccount());
            	   bbh.setAccount(b.getAccount());
            	   bbh.setTypes(1);
            	   businessDao.insertBookHistory(bbh);
               }
			}
			result.setStatus("0");
			return result;
		}else{
			result.setStatus("1");
			result.setMessage("充值金额不能为空");
			return result;
		}
	}

	@Override
	public List<Business> list(String name, String address, String fromdate, String todate, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		List<Business> list = businessDao.selectList(name, address, fromdate, todate, pageNo, pageSize);
		for (Business business : list) {
			BusinessBook bb = businessDao.singlebook(business.getId(), null);
			business.setSurplusaccount(bb.getSurplusaccount());/////剩余多少兑换课程的金额
			business.setPayaccount(bb.getPayaccount());/////总共充值的钱数
			business.setAccount(bb.getAccount());////总的兑换金额
		}
		return list;
	}

	@Transactional
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		businessDao.deleteBusinessUser(id);
		return businessDao.delete(id);
	}

	@Override
	public int selectListCount(String name, String address, String fromdate, String todate) {
		// TODO Auto-generated method stub
		return businessDao.selectListCount(name, address, fromdate, todate);
	}


	

}
