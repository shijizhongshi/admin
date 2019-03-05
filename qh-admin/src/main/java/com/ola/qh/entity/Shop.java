package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@NotEmpty(message="详细地址不能为空")
	private String address;////
	
	private String businessLicenseUrl;/////营业执照图片
	
	@NotEmpty(message="门口图片不能为空")
	private String doorHeadUrl;//////门口图片
		
	private String shopLogo;//////商城参数的logo
	
	private String licenceUrl;/////商城的许可证
	
	private int islimits;/////查看是否已经审核通过了
	
	private String remarked;////备注
	
	private String businessHours;////营业时间
	
	private List<ShopImg> imgList=new ArrayList<ShopImg>();//执业资格证书
	
	private List<ShopImg> environmentImgList=new ArrayList<ShopImg>();////门头照片
	
	private Date addtime;
	
	private Date updatetime;
	

	private String isrecommend;////0:后台没有设置推荐   1:后台设置推荐的店铺
	
	private String idcard;////负责人的身份证号
	
	private double commentGrade;////评价的评分
	
	private List<String> comments;///评论内容

	private String serveDomain;////服务领域(中医推拿   小儿推拿等)
	
	private List<String> serveDaomainList=new ArrayList<String>();
	
	private String servetypeName;


	private String showtime;
	
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

	public String getDoorHeadUrl() {
		return doorHeadUrl;
	}

	public void setDoorHeadUrl(String doorHeadUrl) {
		this.doorHeadUrl = doorHeadUrl;
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

	public String getRemarked() {
		return remarked;
	}

	public void setRemarked(String remarked) {
		this.remarked = remarked;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public List<ShopImg> getImgList() {
		return imgList;
	}

	public void setImgList(List<ShopImg> imgList) {
		this.imgList = imgList;
	}

	public List<ShopImg> getEnvironmentImgList() {
		return environmentImgList;
	}

	public void setEnvironmentImgList(List<ShopImg> environmentImgList) {
		this.environmentImgList = environmentImgList;
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

	public String getIsrecommend() {
		return isrecommend;
	}

	public void setIsrecommend(String isrecommend) {
		this.isrecommend = isrecommend;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public double getCommentGrade() {
		return commentGrade;
	}

	public void setCommentGrade(double commentGrade) {
		this.commentGrade = commentGrade;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public String getServeDomain() {
		return serveDomain;
	}

	public void setServeDomain(String serveDomain) {
		this.serveDomain = serveDomain;
	}

	public List<String> getServeDaomainList() {
		return serveDaomainList;
	}

	public void setServeDaomainList(List<String> serveDaomainList) {
		this.serveDaomainList = serveDaomainList;
	}

	public String getServetypeName() {
		return servetypeName;
	}

	public void setServetypeName(String servetypeName) {
		this.servetypeName = servetypeName;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	
	
	
}
