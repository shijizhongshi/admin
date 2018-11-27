package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ShopDrug {
	
	private String id;
	
	private String userId;
	
	private String shopId;
	
	private String drugName;////药品名称
	
	private String healingPowers;//////治疗功能
	
	private String specification;////药品规格
	
	private BigDecimal originalPrice;////原价
	
	private BigDecimal discountPrice;///折扣价
	
	private BigDecimal price;////原价
	
	private String drugDetail;///
	
	private String specificationParams;////规格参数
	
	private String productName;
	
	private String manufacturer;/////生产厂家
	
	private String drugSign;////药品标识
	
	private String approvalNumber;///批准文号
	
	private String periodValidity;////有效期
	
	private String storages;///存储
	
	private String units;////单位
	
	private String properPeople;////适宜人群
	
	private String elements;////成分
	
	private String characters;////性状
	
	private String packagings;////包装
	
	private String indication;///适应症
	
	private String dosage;///用量
	
	private String badSymptom;///不良症状
	
	private String taboo;////禁忌
	
	private String attentionMatter;///注意事项
	
	private String drugInteractions;///药物相互作用

	private String drugAction;////药理作用
	
	private String shapCode;////条形码
	
	private String friendlyHint;////友情提示
	
	private String imgUrl;/////主图
	
	private Date addtime;
	
	private Date updatetime;
	
	private int status;////0:自动状态
	
	private int ishot;////1"首页的热卖
	

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

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getHealingPowers() {
		return healingPowers;
	}

	public void setHealingPowers(String healingPowers) {
		this.healingPowers = healingPowers;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDrugDetail() {
		return drugDetail;
	}

	public void setDrugDetail(String drugDetail) {
		this.drugDetail = drugDetail;
	}

	public String getSpecificationParams() {
		return specificationParams;
	}

	public void setSpecificationParams(String specificationParams) {
		this.specificationParams = specificationParams;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDrugSign() {
		return drugSign;
	}

	public void setDrugSign(String drugSign) {
		this.drugSign = drugSign;
	}

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	public String getPeriodValidity() {
		return periodValidity;
	}

	public void setPeriodValidity(String periodValidity) {
		this.periodValidity = periodValidity;
	}


	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getProperPeople() {
		return properPeople;
	}

	public void setProperPeople(String properPeople) {
		this.properPeople = properPeople;
	}

	public String getElements() {
		return elements;
	}

	public void setElements(String elements) {
		this.elements = elements;
	}
	
	public String getStorages() {
		return storages;
	}

	public void setStorages(String storages) {
		this.storages = storages;
	}

	public String getCharacters() {
		return characters;
	}

	public void setCharacters(String characters) {
		this.characters = characters;
	}

	public String getPackagings() {
		return packagings;
	}

	public void setPackagings(String packagings) {
		this.packagings = packagings;
	}

	public String getIndication() {
		return indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getBadSymptom() {
		return badSymptom;
	}

	public void setBadSymptom(String badSymptom) {
		this.badSymptom = badSymptom;
	}

	public String getTaboo() {
		return taboo;
	}

	public void setTaboo(String taboo) {
		this.taboo = taboo;
	}

	public String getAttentionMatter() {
		return attentionMatter;
	}

	public void setAttentionMatter(String attentionMatter) {
		this.attentionMatter = attentionMatter;
	}

	public String getDrugInteractions() {
		return drugInteractions;
	}

	public void setDrugInteractions(String drugInteractions) {
		this.drugInteractions = drugInteractions;
	}

	public String getDrugAction() {
		return drugAction;
	}

	public void setDrugAction(String drugAction) {
		this.drugAction = drugAction;
	}

	public String getShapCode() {
		return shapCode;
	}

	public void setShapCode(String shapCode) {
		this.shapCode = shapCode;
	}

	public String getFriendlyHint() {
		return friendlyHint;
	}

	public void setFriendlyHint(String friendlyHint) {
		this.friendlyHint = friendlyHint;
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

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIshot() {
		return ishot;
	}

	public void setIshot(int ishot) {
		this.ishot = ishot;
	}
	
	
	
}
