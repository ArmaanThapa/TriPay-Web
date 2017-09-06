package com.tripayweb.app.model.request;

public class GetOperatorAndCircleForMobRequest {

	private String sessionId;
	
	private String mobileNumber;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
