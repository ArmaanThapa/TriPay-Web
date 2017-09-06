package com.tripayweb.app.model.request;

public class InsuranceBillPaymentRequest {

	private String sessionId;

	private String serviceProvider;

	private String policyNumber;

	private String policyDate;

	private String amount;

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolicyDate() {
		return policyDate;
	}

	public void setPolicyDate(String policyDate) {
		this.policyDate = policyDate;
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
