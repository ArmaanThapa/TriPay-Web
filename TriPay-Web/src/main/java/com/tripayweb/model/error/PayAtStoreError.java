package com.tripayweb.model.error;

public class PayAtStoreError {

	private boolean valid;

	private String serviceProvider;

	private String orderID;

	private String amount;

	private String message;

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

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "PayAtStoreError [valid=" + valid + ", serviceProvider=" + getServiceProvider() + ", orderID=" + getOrderID()
				+ ", amount=" + getAmount() + ", message=" + getMessage() + "]";
	}
	
	

}
