package com.ola.qh.service.imp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.ola.qh.dao.UserMessageDao;
import com.ola.qh.dao.UserWithdrawDao;
import com.ola.qh.entity.UserMessage;
import com.ola.qh.entity.UserWithdraw;
import com.ola.qh.service.IPushService;
import com.ola.qh.service.IUserWithdrawService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.weixin.WeixinDraw;

@Service
public class UserWithdrawService implements IUserWithdrawService{

	@Autowired
	private UserWithdrawDao userWithdrawDao;
	@Autowired
	private IPushService pushService;
	@Autowired
	private UserMessageDao messageDao;
	
	
	
	
	@Override
	public List<UserWithdraw> selectUserWithdraw(String mobile,String payStatus,String fromdate,String todate,int pageNo,int pageSize) {
		List<UserWithdraw> list = userWithdrawDao.selectUserWithdraw(mobile, payStatus, fromdate, todate, pageNo, pageSize);
		for (UserWithdraw userWithdraw : list) {
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			userWithdraw.setShowtime(sf.format(userWithdraw.getAddtime()));
		}
		return list;
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
						pushService.send(userId, "提现到账通知","您的提现申请已经审核通过且已到账,支付宝到账金额:"+userWithdraw.getMoney()+"元");
						/////////给商家保存一个消息
						UserMessage um=new UserMessage();
						um.setId(KeyGen.uuid());
						um.setDescribe("您的提现申请已经审核通过且已到账,支付宝到账金额:"+userWithdraw.getMoney()+"元");
						um.setHeadStatus(0);
						um.setTitle("提现到账通知");
						um.setTypes(4);
						um.setUserId(userId);
						messageDao.insert(um);
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
						//////到账成功的通知
						pushService.send(userId, "提现到账通知","您的提现申请已经审核通过且已到账,微信到账金额:"+userWithdraw.getMoney()+"元");
						/////////给商家保存一个消息
						UserMessage um=new UserMessage();
						um.setId(KeyGen.uuid());
						um.setDescribe("您的提现申请已经审核通过且已到账,微信到账金额:"+userWithdraw.getMoney()+"元");
						um.setHeadStatus(0);
						um.setTitle("提现到账通知");
						um.setTypes(4);
						um.setUserId(userId);
						messageDao.insert(um);
					}else{
						////支付失败~
						userWithdrawDao.updateChecked(userWithdraw.getId(), "2", map.get("err_code_des"), new Date());
					    //////到账失败的通知
						pushService.send(userId, "提现到账通知","提现审核出问题了,错误信息:"+map.get("err_code_des"));
						/////////给商家保存一个消息
						UserMessage um=new UserMessage();
						um.setId(KeyGen.uuid());
						um.setDescribe("提现审核出问题了,错误信息:"+map.get("err_code_des"));
						um.setHeadStatus(0);
						um.setTitle("提现到账通知");
						um.setTypes(4);
						um.setUserId(userId);
						messageDao.insert(um);
						
						
						
					}
				}
				
			}else{
				/////说明账本有问题
				userWithdrawDao.updateChecked(userWithdraw.getId(), "2", "该用户金额存在问题", new Date());
			}
			
		}
		
		
	}
	@Override
	public int selectUserWithdrawCount(String mobile, String payStatus, String fromdate, String todate) {
		// TODO Auto-generated method stub
		return userWithdrawDao.selectUserWithdrawCount(mobile, payStatus, fromdate, todate);
	}

	
}
