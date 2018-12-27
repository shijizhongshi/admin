package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class News {

	private String id;
	
	private String types;
	@NotEmpty(message="新闻标题不能为空")
	private String title;///标题
	@NotEmpty(message="图片不能为空")
	private String imgUrl;///标题图片
	@NotEmpty(message="内容不能为空")
	private String content;///内容
	
	private Date addtime;
	
	private int isstick;////是否置顶
	
	private int isrecommend;////是否推荐
	
	private int contentTypes;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public int getIsstick() {
		return isstick;
	}

	public void setIsstick(int isstick) {
		this.isstick = isstick;
	}

	public int getContentTypes() {
		return contentTypes;
	}

	public void setContentTypes(int contentTypes) {
		this.contentTypes = contentTypes;
	}

	public int getIsrecommend() {
		return isrecommend;
	}

	public void setIsrecommend(int isrecommend) {
		this.isrecommend = isrecommend;
	}


	
	
}
