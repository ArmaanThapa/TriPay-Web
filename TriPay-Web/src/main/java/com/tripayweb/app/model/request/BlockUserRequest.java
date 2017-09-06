package com.tripayweb.app.model.request;

public class BlockUserRequest {

	private String username;
	private String adminSessionId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAdminSessionId() {
		return adminSessionId;
	}

	public void setAdminSessionId(String adminSessionId) {
		this.adminSessionId = adminSessionId;
	}

}
