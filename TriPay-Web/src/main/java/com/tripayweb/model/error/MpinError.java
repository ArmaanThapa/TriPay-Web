package com.tripayweb.model.error;

public class MpinError {

	private String newMpin;
	private String oldMpin;
	private String confirmMpin;
	private boolean valid;

	public String getNewMpin() {
		return newMpin;
	}

	public void setNewMpin(String newMpin) {
		this.newMpin = newMpin;
	}

	public String getOldMpin() {
		return oldMpin;
	}

	public void setOldMpin(String oldMpin) {
		this.oldMpin = oldMpin;
	}

	public String getConfirmMpin() {
		return confirmMpin;
	}

	public void setConfirmMpin(String confirmMpin) {
		this.confirmMpin = confirmMpin;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
