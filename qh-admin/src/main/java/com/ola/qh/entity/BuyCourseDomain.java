package com.ola.qh.entity;

public class BuyCourseDomain {

	private String nicknameORmobile;
	
	private String fromdate;
	
	private String todate;
	
	private String status;
	
	private String businessId;
	
	private int pageNo;
	
	private int pageSize;

	public String getNicknameORmobile() {
		return nicknameORmobile;
	}

	public void setNicknameORmobile(String nicknameORmobile) {
		this.nicknameORmobile = nicknameORmobile;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
