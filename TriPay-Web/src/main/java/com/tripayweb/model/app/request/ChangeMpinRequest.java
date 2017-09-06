package com.tripayweb.model.app.request;

public class ChangeMpinRequest {

	private String oldMpin;
	private String newMpin;
	private String confirmMpin;

	public String getOldMpin() {
		return oldMpin;
	}

	public void setOldMpin(String oldMpin) {
		this.oldMpin = oldMpin;
	}

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
