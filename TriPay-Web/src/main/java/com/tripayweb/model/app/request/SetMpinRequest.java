package com.tripayweb.model.app.request;

public class SetMpinRequest {

	private String newMpin;

	private String confirmMpin;

	public String getNewMpin() {
		return newMpin;
	}

	public void setNewMpin(String newMpin) {
		this.newMpin = newMpin;
	}

	public String getConfirmMpin() {
		return confirmMpin;
	}

	public void setConfirmMpin(String confirmMpin) {
		this.confirmMpin = confirmMpin;
	}

}
