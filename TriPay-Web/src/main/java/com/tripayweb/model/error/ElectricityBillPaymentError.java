package com.tripayweb.model.error;

public class ElectricityBillPaymentError {
	
	private boolean valid;
	
	private String serviceProvider;

	private String accountNumber;

	private String cycleNumber;

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

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCycleNumber() {
		return cycleNumber;
	}

	public void setCycleNumber(String cycleNumber) {
		this.cycleNumber = cycleNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "ElectricityBillPaymentError [valid=" + valid + ", serviceProvider=" + getServiceProvider()
				+ ", accountNumber=" + getAccountNumber() + ", cycleNumber=" + getCycleNumber() + ", amount=" + getAmount() + "]";
	}



}
