package com.ola.qh.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ola.qh.service.IUserWithdrawService;
import com.ola.qh.service.imp.UserWithdrawService;
/**
 *提现的定时任务
* @ClassName:WithdrawSchedure
* @Description:
* @author guoyuxue
* @date:2019年1月14日 
*
 */

@Component
public class WithdrawSchedule {

	@Autowired
	private IUserWithdrawService withdrawService;
	
	/*@Scheduled(cron="0 0 0 ? * *")//0点执行
*/	public void withdiawRun() throws Exception{
		withdrawService.agreeWithdraw();
	}
	
	
}
