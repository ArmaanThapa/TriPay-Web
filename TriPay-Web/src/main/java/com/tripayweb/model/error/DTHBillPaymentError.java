package com.tripayweb.model.error;

public class DTHBillPaymentError {
	private boolean valid;
	
	private String serviceProvider;

	private String dthNo;

	private String amount;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

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

	@Override
	public String toString() {
		return "DTHBillPaymentError [valid=" + valid + ", serviceProvider=" + getServiceProvider() + ", dthNo=" + getDthNo()
				+ ", amount=" + getAmount() + "]";
	}
	
	


}
