package com.ola.qh.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class Shop {

	private String id;
	
	private String userId;
	@NotNull(message="必须选择店铺的类型")
	private int shopType;////1:服务店铺    2:商城店铺
	
	@NotEmpty(message="店铺名称不能为空")
	private String shopName;////店铺名称
	
	@NotEmpty(message="负责人姓名不能为空")
	private String leaderName;////负责人姓名
	
	@NotEmpty(message="负责人手机号不能为空")
	private String leaderMobile;////负责人手机号
	
	private String address;////
	@NotEmpty(message="营业执照图片不能为空")
	private String businessLicenseUrl;/////营业执照图片
	@NotEmpty(message="执业资格证图片不能为空")
	private String licenseStatusUrl;/////执业资格证图片
	@NotEmpty(message="门口图片不能为空")
	private String doorHeadUrl;//////门口图片
	
	private String businessEnvironmentUrl;////营业环境照片
	
	private String shopLogo;//////商城参数的logo
	
	private String licenceUrl;/////商城的许可证
	
	private int islimits;/////查看是否已经审核通过了
	
	private Date addtime;
	
	private Date updatetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getShopType() {
		return shopType;
	}

	public void setShopType(int shopType) {
		this.shopType = shopType;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getLeaderMobile() {
		return leaderMobile;
	}

	public void setLeaderMobile(String leaderMobile) {
		this.leaderMobile = leaderMobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusinessLicenseUrl() {
		return businessLicenseUrl;
	}

	public void setBusinessLicenseUrl(String businessLicenseUrl) {
		this.businessLicenseUrl = businessLicenseUrl;
	}

	public String getLicenseStatusUrl() {
		return licenseStatusUrl;
	}

	public void setLicenseStatusUrl(String licenseStatusUrl) {
		this.licenseStatusUrl = licenseStatusUrl;
	}

	public String getDoorHeadUrl() {
		return doorHeadUrl;
	}

	public void setDoorHeadUrl(String doorHeadUrl) {
		this.doorHeadUrl = doorHeadUrl;
	}

	public String getBusinessEnvironmentUrl() {
		return businessEnvironmentUrl;
	}

	public void setBusinessEnvironmentUrl(String businessEnvironmentUrl) {
		this.businessEnvironmentUrl = businessEnvironmentUrl;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getLicenceUrl() {
		return licenceUrl;
	}

	public void setLicenceUrl(String licenceUrl) {
		this.licenceUrl = licenceUrl;
	}

	public int getIslimits() {
		return islimits;
	}

	public void setIslimits(int islimits) {
		this.islimits = islimits;
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
	
	
	
}
