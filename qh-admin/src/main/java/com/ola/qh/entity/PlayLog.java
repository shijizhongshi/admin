package com.ola.qh.entity;

public class PlayLog {

	private Integer upid;//每次播放产生的随机数字
	private String userid;//用户id
	private String videoid;//视频id
	private String custom_id;//自定义id
	private Integer play_duration;//播放时长
	private Integer play_position;//最后播放位置
	private Integer video_duration;//视频时长
	private String ip;//ip地址
	private String province;//省份名称
	private String city;//城市名称
	private String referer;//来源域名
	private String device;//设备类型
	private String operating_system;//操作系统
	private String browser;//浏览器类型
	private String terminal;//终端类型
	private String start_time;//开始播放时间
	private String end_time;//结束播放时间
	
	//
	private String userName;
	
	private String sectionName;
	
	private String courseTypeSubclassName;
	//
	private Integer isShow;
	

	
	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getCourseTypeSubclassName() {
		return courseTypeSubclassName;
	}

	public void setCourseTypeSubclassName(String courseTypeSubclassName) {
		this.courseTypeSubclassName = courseTypeSubclassName;
	}

	public String getCustom_id() {
		return custom_id;
	}

	public void setCustom_id(String custom_id) {
		this.custom_id = custom_id;
	}

	public String getVideoid() {
		return videoid;
	}

	public void setVideoid(String videoid) {
		this.videoid = videoid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getUpid() {
		return upid;
	}

	public void setUpid(Integer upid) {
		this.upid = upid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getVideo_duration() {
		return video_duration;
	}

	public void setVideo_duration(Integer video_duration) {
		this.video_duration = video_duration;
	}

	public Integer getPlay_position() {
		return play_position;
	}

	public void setPlay_position(Integer play_position) {
		this.play_position = play_position;
	}

	public Integer getPlay_duration() {
		return play_duration;
	}

	public void setPlay_duration(Integer play_duration) {
		this.play_duration = play_duration;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOperating_system() {
		return operating_system;
	}

	public void setOperating_system(String operating_system) {
		this.operating_system = operating_system;
	}

}
