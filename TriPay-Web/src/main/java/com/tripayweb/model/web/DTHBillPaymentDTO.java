package com.tripayweb.model.web;

public class DTHBillPaymentDTO {

	private String serviceProvider;

	private String dthNo;

	private String amount;

	private String sessionId;

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String getDthNo() {
		return dthNo;
	}

	public void setDthNo(String dthNo) {
		this.dthNo = dthNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	
}
