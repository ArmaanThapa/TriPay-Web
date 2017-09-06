package com.tripayweb.model.error;

public class SendMoneyMobileError {
	private boolean valid;
	
	private String mobileNumber;

	private String amount;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "SendMoneyMobileError [valid=" + valid + ", mobileNumber=" + getMobileNumber() + ", amount=" + getAmount() + "]";
	}

	
}
