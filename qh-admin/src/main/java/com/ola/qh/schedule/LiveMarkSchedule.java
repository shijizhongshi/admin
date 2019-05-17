package com.ola.qh.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ola.qh.service.ILiveMarkScheduleService;

@Component
public class LiveMarkSchedule {

	@Autowired
	private ILiveMarkScheduleService liveMarkScheduleService;

	@Scheduled(cron = "1 0 0/1 * * ? ")
	public void liveMark() {
		// 定时发送直播开始的提醒消息
		liveMarkScheduleService.timedPushOneHour();
	}

}
