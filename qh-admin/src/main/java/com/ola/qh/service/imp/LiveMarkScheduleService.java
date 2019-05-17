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
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		System.out.println(sf.format(now));
		// 获取直播集合
		List<Date> dateList = courseDao.selectLiveShow(sf.format(now));
		// 获取当前时间的时间戳格式
		long currentTime = System.currentTimeMillis();
		for (Date date : dateList) {
			System.out.println(date);
			// 转换成13位时间戳
			long timesTamp = date.getTime();
			// 判断时间差是否小于1个小时
			if (timesTamp - currentTime <= (60 * 60 * 1000)) {
				//获取要推送的直播集合
				System.out.println("打印数据 = "+sf.format(date));
				List<LiveMark> list = courseDao.selectStartTime(sf.format(date));
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
	}

}
