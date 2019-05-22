package com.ola.qh.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseDao;
import com.ola.qh.entity.LiveMark;
import com.ola.qh.service.ILiveMarkScheduleService;
import com.ola.qh.service.IPushService;

@Service
public class LiveMarkScheduleService implements ILiveMarkScheduleService {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private IPushService pushService;

	@Override
	public void timedPushOneHour() {
		Date now = new Date();
		// 格式化
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHH");
		// 获取直播集合
		List<Date> dateList = courseDao.selectLiveShow(sf.format(now));
		// 获取当前时间的时间戳格式
		for (int i = 0; i < dateList.size(); i++) {
			// 获取要推送的直播集合
			List<LiveMark> list = courseDao.selectStartTimeByHour(sf.format(now));
			for (LiveMark liveMark : list) {
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

	@Override
	public void timedPushFiveMin() {
		Date now = new Date();
		// 格式化
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHH");
		// 获取直播集合
		List<Date> dateList = courseDao.selectLiveShow(sf.format(now));
		for (Date date : dateList) {
			// 当前时间转时间戳
			long currentTime = now.getTime();
			// 直播时间转时间戳
			long liveTime = date.getTime();
			if (liveTime - currentTime <= (5 * 60 * 1000) && liveTime - currentTime >= 0) {
				SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMddHHmm");
				List<LiveMark> list = courseDao.selectStartTimeByMin(sFormat.format(date));
				// 查询符合时间的直播集合
				for (LiveMark liveMark : list) {
					String userId = liveMark.getUserId();
					// 标题
					String title = "直播即将开始";
					// 推送
					String content = "您预约的直播即将开始，赶紧进入直播间和大家打声招呼吧~";
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

}
