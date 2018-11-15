package com.ola.qh.entity;

import org.hibernate.validator.constraints.NotEmpty;
/**
 * 
 * 
* @ClassName: Banner
* @Description:  Banner对象get,set
* @author guozihan
* @date   2018年11月15日
*
 */
public class Banner {

	/* 图片ID */
	private String id;
	/* 图片链接 */
	@NotEmpty(message = "图片链接不能为空")
	private String imageurl;
	/* 图片类型 */
	private int type;
	/* 外部链接 */
	@NotEmpty(message = "图片链接不能为空")
	private String outLinks;
	/* 是否展示 */
	private int isshow;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getOutLinks() {
		return outLinks;
	}

	public void setOutLinks(String outLinks) {
		this.outLinks = outLinks;
	}

	public int getIsshow() {
		return isshow;
	}

	public void setIsshow(int isshow) {
		this.isshow = isshow;
	}

}
