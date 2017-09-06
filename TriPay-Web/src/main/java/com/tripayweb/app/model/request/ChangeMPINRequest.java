package com.tripayweb.app.model.request;

public class ChangeMPINRequest {

	private String sessionId;
	private String username;
	private String oldMpin;
	private String newMpin;
	private String confirmMpin;

    public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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
