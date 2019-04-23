package com.ola.qh.entity;

import java.util.List;

public class LiveAccess {

	private String result;
	
	private String reason;

	private List<UserEnterLeaveActions> userEnterLeaveActions;

	private Integer count;

	private Integer pageIndex;
	
	

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<UserEnterLeaveActions> getUserEnterLeaveActions() {
		return userEnterLeaveActions;
	}

	public void setUserEnterLeaveActions(List<UserEnterLeaveActions> userEnterLeaveActions) {
		this.userEnterLeaveActions = userEnterLeaveActions;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

}
