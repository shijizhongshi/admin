package com.ola.qh.entity;

public class UserEnterLeaveActions {

	private String viewerId;// 用户id

	private String viewerName;// 用户昵称

	private String viewerIp;// 用户ip

	private String city;// 用户地域

	private String enterTime;// 进入时间

	private String leaveTime;// 离开时间

	private Integer watchTime;// 直播观看时长

	private Integer terminal;// 终端类型，0表示pc端，1表示移动端

	private String customInfo;// json格式字符串，自定义用户信息

	public String getCustomInfo() {
		return customInfo;
	}

	public void setCustomInfo(String customInfo) {
		this.customInfo = customInfo;
	}

	public String getViewerId() {
		return viewerId;
	}

	public void setViewerId(String viewerId) {
		this.viewerId = viewerId;
	}

	public String getViewerName() {
		return viewerName;
	}

	public void setViewerName(String viewerName) {
		this.viewerName = viewerName;
	}

	public String getViewerIp() {
		return viewerIp;
	}

	public void setViewerIp(String viewerIp) {
		this.viewerIp = viewerIp;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public Integer getWatchTime() {
		return watchTime;
	}

	public void setWatchTime(Integer watchTime) {
		this.watchTime = watchTime;
	}

	public Integer getTerminal() {
		return terminal;
	}

	public void setTerminal(Integer terminal) {
		this.terminal = terminal;
	}

}
