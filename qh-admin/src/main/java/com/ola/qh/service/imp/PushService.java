package com.ola.qh.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserLoginDao;
import com.ola.qh.entity.UserLogin;
import com.ola.qh.message.MessageDemo;
import com.ola.qh.service.IPushService;
@Service
public class PushService implements IPushService{

	@Autowired
	private UserLoginDao userLoginDao;
	
	@Override
	public void send(String userId, String title, String content) throws Exception {
		// TODO Auto-generated method stub
		UserLogin ul = userLoginDao.selectUserLogin(userId);
		if(ul!=null && ul.getDeviceToken()!=null){
			if(ul.getDeviceType()==1){
				///////1是安卓推送  
				MessageDemo.sendAndroidUnicast(title, title, content, ul.getDeviceToken());
			}
			if(ul.getDeviceType()==2){
				///////2:ios推送
				MessageDemo.sendIOSUnicast(title, content, ul.getDeviceToken());
				
			}
		}
	}

}
