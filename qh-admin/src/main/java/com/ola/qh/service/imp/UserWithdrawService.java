package com.ola.qh.service.imp;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.ola.qh.dao.UserWithdrawDao;
import com.ola.qh.entity.UserWithdraw;
import com.ola.qh.service.IUserWithdrawService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.weixin.WeixinDraw;

@Service
public class UserWithdrawService implements IUserWithdrawService{

	@Autowired
	private UserWithdrawDao userWithdrawDao;
	
	@Override
	public List<UserWithdraw> selectUserWithdraw(String id,int pageNo,int pageSize) {
		
		return userWithdrawDao.selectUserWithdraw(id,pageNo, pageSize);
	}
	@Override
	public void agreeWithdraw() throws Exception{
		// TODO Auto-generated method stub
		/////查出所有没有过的记录;打钱
		List<UserWithdraw> alilist=userWithdrawDao.selectListNoCheck(1);
		for (UserWithdraw userWithdraw : alilist) {
			String userId = userWithdrawDao.checkedAccountBook(userWithdraw.getUserId());
			if(userId==null){
				////说明账本没有问题
				if(userWithdraw.getTypes()==1){
					////提现到支付宝
					Map<String,String> map = WeixinDraw.AlipayFundTransToaccountTransfer(userWithdraw.getAliaccount(), userWithdraw.getRealname(), userWithdraw.getMoney().toString());
					if("0".equals(map.get("status"))){
						/////支付成功
						userWithdrawDao.updateChecked(userWithdraw.getId(), "1",null, new Date());
					}else{
						/////支付失败
						userWithdrawDao.updateChecked(userWithdraw.getId(), "2",map.get("error"), new Date());
					}
				}
				
			}else{
				/////说明账本有问题
				userWithdrawDao.updateChecked(userWithdraw.getId(), "2", "该用户金额存在问题", new Date());
			}
			
		}
		List<UserWithdraw> weixinlist=userWithdrawDao.selectListNoCheck(2);
		for (UserWithdraw userWithdraw : weixinlist) {
			String userId = userWithdrawDao.checkedAccountBook(userWithdraw.getUserId());
			if(userId==null){
				////说明账本没有问题
				if(userWithdraw.getTypes()==2){
					////提现到微信
					Map<String,String> map = WeixinDraw.wexintransfer(userWithdraw.getOpenId(), userWithdraw.getMoney().intValue(), "用户提现", KeyGen.uuid().toString());
					if("SUCCESS".equals(map.get("status"))){
						//////支付成功~
						userWithdrawDao.updateChecked(userWithdraw.getId(), "1",null, new Date());
					}else{
						////支付失败~
						userWithdrawDao.updateChecked(userWithdraw.getId(), "2", map.get("err_code_des"), new Date());
					}
				}
				
			}else{
				/////说明账本有问题
				userWithdrawDao.updateChecked(userWithdraw.getId(), "2", "该用户金额存在问题", new Date());
			}
			
		}
		
		
	}

	
}
