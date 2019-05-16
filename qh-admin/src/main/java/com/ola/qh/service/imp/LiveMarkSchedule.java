package com.ola.qh.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ola.qh.dao.CourseDao;
import com.ola.qh.entity.LiveMark;
import com.ola.qh.service.ILiveMarkSchedule;
import com.ola.qh.service.IPushService;

public class LiveMarkSchedule implements ILiveMarkSchedule {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private IPushService pushService;

	@Override
	public void timedPushOneHour() {
		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		System.out.println("当前时间" + sf.format(now));
		// 获取直播集合
		List<Date> dateList = courseDao.selectLiveShow(sf.format(now));
		for (Date date : dateList) {
			System.out.println(date);
		}
		// 获取需要推送的直播集合
		List<LiveMark> list = courseDao.selectStartTime(sf.format(now));
		long currentTime = System.currentTimeMillis();
		for (LiveMark liveMark : list) {
			// 直播开始时间转换
			// long timesTamp = liveMark.getStarttime().getTime();

			// 测试时间
			long timesTamp = System.currentTimeMillis();
			// 时间差小于一个小时 发送
			if (timesTamp - currentTime <= 60 * 60 * 1000) {
				String userId = liveMark.getUserId();
				// 标题
				String title = "直播即将开始";
				SimpleDateFormat sFormat = new SimpleDateFormat("HH:mm");
				// 推送
				String content = "您预约的直播课将于" + sFormat.format(liveMark.getStarttime()) + "开始，记得准时观看哦~";
				try {
					pushService.send(userId, title, content);
					// 根据userId更新状态值
					courseDao.updateStatus(userId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
