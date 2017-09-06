package com.tripayweb.model.error;

public class LoadMoneyRsaKeyError {

	private boolean valid;

	private String access_code;

	private String order_id;

	public boolean isVslid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getAccess_code() {
		return access_code;
	}

	public void setAccess_code(String access_code) {
		this.access_code = access_code;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
}
