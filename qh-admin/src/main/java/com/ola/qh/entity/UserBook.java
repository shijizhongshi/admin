package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserBook {

	private String id;

	private String userId;

	private BigDecimal accountMoney;//账户累计总金额

	private Date addtime;

	private Date updatetime;
	
	private BigDecimal finishMoney;//已提现金额
	
	private BigDecimal onMoney;//提现中金额
	
	private BigDecimal fortheMoney;//待结算金额
	
	private BigDecimal canWithdraw;//可提现金额

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

	public BigDecimal getAccountMoney() {
		return accountMoney;
	}

	public void setAccountMoney(BigDecimal accountMoney) {
		this.accountMoney = accountMoney;
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

	public BigDecimal getFinishMoney() {
		return finishMoney;
	}

	public void setFinishMoney(BigDecimal finishMoney) {
		this.finishMoney = finishMoney;
	}

	public BigDecimal getOnMoney() {
		return onMoney;
	}

	public void setOnMoney(BigDecimal onMoney) {
		this.onMoney = onMoney;
	}

	public BigDecimal getFortheMoney() {
		return fortheMoney;
	}

	public void setFortheMoney(BigDecimal fortheMoney) {
		this.fortheMoney = fortheMoney;
	}

	public BigDecimal getCanWithdraw() {
		return canWithdraw;
	}

	public void setCanWithdraw(BigDecimal canWithdraw) {
		this.canWithdraw = canWithdraw;
	}

	
}
