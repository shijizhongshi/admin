package com.ola.qh.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ola.qh.service.ILiveMarkScheduleService;

@Component
public class LiveMarkSchedule {

	@Autowired
	private ILiveMarkScheduleService liveMarkScheduleService;

	@Scheduled(cron = "3 1 0/1 * * ? ")//定时任务 在每个小时的1分03秒开始执行
	public void liveMark() {
		// 定时发送直播开始的提醒消息
		liveMarkScheduleService.timedPushOneHour();
	}
	
	@Scheduled(cron = "3 0/5 * * * ? ")//定时任务 间隔五分钟执行一次
	public void liveMarkFiveMin() {
		// 定时发送直播开始的提醒消息
		liveMarkScheduleService.timedPushFiveMin();
	}

}
