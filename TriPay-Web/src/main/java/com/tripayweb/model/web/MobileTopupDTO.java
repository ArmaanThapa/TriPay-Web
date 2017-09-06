package com.tripayweb.model.web;

public class MobileTopupDTO {

	private TopupType topupType;
	private String serviceProvider;
	private String mobileNo;
	private String area;
	private String amount;

	public TopupType getTopupType() {
		return topupType;
	}

	public void setTopupType(TopupType topupType) {
		this.topupType = topupType;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

}
