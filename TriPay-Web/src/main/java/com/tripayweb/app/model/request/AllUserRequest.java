package com.tripayweb.app.model.request;

import com.tripayweb.app.model.UserStatus;

public class AllUserRequest extends AdminRequest {

	private String sessionId;
	private int page;
	private int size;
	private UserStatus status;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

}
