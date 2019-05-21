package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
///////销售人员
public class Salesman {

	private String id;
	
	@NotEmpty
	private String name;///////销售人员姓名
	
	@NotEmpty
	private String imgUrl;///////销售人员照片
	
	@NotEmpty
	private String mobile;///////销售人员电话
	
	@NotEmpty
	private String address;///////销售人员地址
	
	private String qrCode;///////二维码
	
	private List<SalesmanSecond> second=new ArrayList<SalesmanSecond>();
	
	private Date addtime;
	
	private Date updatetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public List<SalesmanSecond> getSecond() {
		return second;
	}

	public void setSecond(List<SalesmanSecond> second) {
		this.second = second;
	}
	
	
}
