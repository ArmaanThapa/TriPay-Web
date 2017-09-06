package com.tripayweb.model.error;

public class VerifyMobileError {

	private boolean valid;

	private String mobileNumber;

	private String key;

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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "VerifyMobileError [valid=" + valid + ", mobileNumber=" + getMobileNumber() + ", key=" + getKey() + "]";
	}

	

}
