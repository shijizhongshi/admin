package com.ola.qh.entity;

import java.util.Date;

public class ShopServeImg {

	private String id;
	
	private String serveId;
	
	private String imgUrl;
	
	private Date addtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServeId() {
		return serveId;
	}

	public void setServeId(String serveId) {
		this.serveId = serveId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	
}
