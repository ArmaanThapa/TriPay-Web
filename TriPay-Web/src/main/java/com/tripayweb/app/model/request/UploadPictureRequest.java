package com.tripayweb.app.model.request;

import org.springframework.web.multipart.MultipartFile;

public class UploadPictureRequest {

	private String sessionId;

	private MultipartFile profilePicture;

	private String path;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public MultipartFile getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(MultipartFile profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
