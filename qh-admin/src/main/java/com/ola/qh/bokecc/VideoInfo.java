package com.ola.qh.bokecc;

import java.io.File;

public class VideoInfo {
	
	/**
	 * 要上传的本地文件文件名
	 */
	private String name;
	
	/**
	 * 要上传的本地文件路径
	 */
	private String path;
	
	/**
	 * 要上传的本地文件大小
	 */
	private long size;
	
	/**
	 * 要上传的本地文件md5
	 */
	private String md5;
	
	/**
	 * 本次上传分配的视频id
	 */
	private String ccvid;
	
	/**
	 * 用户服务类型
	 */
	private String servicetype;
	
	/**
	 * 上传分类
	 */
	private int categoryid;
	
	/**
	 * 本次上传分配的metaurl
	 * metaurl用来查询文件状态及断点位置
	 */
	private String metaurl;
	
	/**
	 * 本次上传分配的chunkurl
	 * chunkurl用来上传文件内容块
	 */
	private String chunkurl;
	
	public VideoInfo() {
		
	}
	
	public VideoInfo(File localFile) {
		this.name = localFile.getName();
		this.path = localFile.getPath();
		this.size = localFile.length();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getCcvid() {
		return ccvid;
	}

	public void setCcvid(String ccvid) {
		this.ccvid = ccvid;
	}

	public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getMetaurl() {
		return metaurl;
	}

	public void setMetaurl(String metaurl) {
		this.metaurl = metaurl;
	}

	public String getChunkurl() {
		return chunkurl;
	}

	public void setChunkurl(String chunkurl) {
		this.chunkurl = chunkurl;
	}
	
}
