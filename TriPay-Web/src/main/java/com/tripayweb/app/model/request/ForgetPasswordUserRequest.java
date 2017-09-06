package com.tripayweb.app.model.request;

public class ForgetPasswordUserRequest {

	private String username;
	private String captchaResponse;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCaptchaResponse() {
		return captchaResponse;
	}

	public void setCaptchaResponse(String captchaResponse) {
		this.captchaResponse = captchaResponse;
	}

}
