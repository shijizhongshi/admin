package com.ola.qh.service.imp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.UserBookDao;
import com.ola.qh.dao.UserWithdrawHistoryDao;
import com.ola.qh.entity.UserBook;
import com.ola.qh.entity.UserWithdrawHistory;
import com.ola.qh.service.IUserWithdrawHistoryService;
import com.ola.qh.util.Results;

@Service
public class UserWithdrawHistoryService implements IUserWithdrawHistoryService{

	@Autowired
	private UserWithdrawHistoryDao userWithdrawHistoryDao;
	@Autowired
	private UserBookDao userBookDao;
	
	@Override
	public List<UserWithdrawHistory> selectUserWithdrawHistory(String id,int pageNo,int pageSize) {
		
		return userWithdrawHistoryDao.selectUserWithdrawHistory(id,pageNo, pageSize);
	}

	@Transactional
	@Override
	public Results<String> updateUserWithdrawHistory(UserWithdrawHistory userwithdrawhistory) {
		
		Results<String> results=new Results<String>();
		try {
			
		UserBook userbooks=userBookDao.selectUserBook(userwithdrawhistory.getUserId());
		BigDecimal onmoney=userbooks.getOnMoney();
		BigDecimal money = userwithdrawhistory.getMoney();
		BigDecimal finishMoney=userbooks.getFinishMoney();
		BigDecimal onMoney =onmoney.subtract(money);
		if(userwithdrawhistory.getPayStatus()==1){
			
			userwithdrawhistory.setUpdatetime(new Date());
			userWithdrawHistoryDao.updateUserWithdrawHistory(userwithdrawhistory);
			
			BigDecimal FinishMoney =finishMoney.add(money);
			
			UserBook userbook=new UserBook();
			userbook.setOnMoney(onMoney);
			userbook.setUserId(userwithdrawhistory.getUserId());
			userbook.setFinishMoney(FinishMoney);
			userBookDao.updateUserBook(userbook);
			
			results.setStatus("0");
			return results;
		}
		BigDecimal canWithdraw=userbooks.getCanWithdraw();
		BigDecimal CanWithdraw =canWithdraw.add(money);
		
		userwithdrawhistory.setUpdatetime(new Date());
		userWithdrawHistoryDao.updateUserWithdrawHistory(userwithdrawhistory);
		
		UserBook userbook=new UserBook();
		userbook.setUserId(userwithdrawhistory.getUserId());
		userbook.setOnMoney(onMoney);
		userbook.setCanWithdraw(CanWithdraw);
		userBookDao.updateUserBook(userbook);

		results.setStatus("0");
		return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("修改失败");
			return results;
		}
	}

	
}
