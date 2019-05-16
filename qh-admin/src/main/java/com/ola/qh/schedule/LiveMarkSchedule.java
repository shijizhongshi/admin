package com.ola.qh.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ola.qh.service.ILiveMarkSchedule;
import com.ola.qh.util.Results;

@Component
public class LiveMarkSchedule {

	@Autowired
	private ILiveMarkSchedule liveMarkSchedule;

	@Scheduled(cron = "1 0 0/1 * * ? ")
	public void liveMark() {
		// 定时发送直播开始的提醒消息
		liveMarkSchedule.timedPushOneHour();
	}

}
