package com.tripayweb.model.app.request;

import org.springframework.web.multipart.MultipartFile;

public class UploadProfilePictureRequest {

	private MultipartFile profilePicture;
	private String fileLocation;

	public MultipartFile getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(MultipartFile profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

}
