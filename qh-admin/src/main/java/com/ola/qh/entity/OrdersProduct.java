package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class OrdersProduct {

	private String id;
	
	private String ordersId;
	
	private String userId;
	
	private String muserId;/////商家的id
	private String productId;////商品的ID
	
	private String productName;////商品的名称
	
	private BigDecimal productPrice;////商品的价格
	@NotNull
	private int count;///商品的数量
	
	private BigDecimal coupon;////用券情况
	
	private BigDecimal payout;////实际支付的金额
	
	private String productImg;////商品的主图片
	
	private String statusCode;////订单的状态
	
	private String statusName;///解释状态名
	
	private String oldStatusCode;////之前的老状态
	
	private Date addtime;
	
	private Date updatetime;
	
	private String headImgUrl;
	
	private String nickname;
	
	private OrdersProductRefund opr=new OrdersProductRefund();
	
	private String cartId;////购物车的id
	
	private BigDecimal freight;////运费
	
	
	
	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMuserId() {
		return muserId;
	}

	public void setMuserId(String muserId) {
		this.muserId = muserId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BigDecimal getCoupon() {
		return coupon;
	}

	public void setCoupon(BigDecimal coupon) {
		this.coupon = coupon;
	}

	public BigDecimal getPayout() {
		return payout;
	}

	public void setPayout(BigDecimal payout) {
		this.payout = payout;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getOldStatusCode() {
		return oldStatusCode;
	}

	public void setOldStatusCode(String oldStatusCode) {
		this.oldStatusCode = oldStatusCode;
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

	public OrdersProductRefund getOpr() {
		return opr;
	}

	public void setOpr(OrdersProductRefund opr) {
		this.opr = opr;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
