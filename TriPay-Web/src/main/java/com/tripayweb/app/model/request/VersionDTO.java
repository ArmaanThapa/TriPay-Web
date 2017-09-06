package com.tripayweb.app.model.request;

public class VersionDTO {

	private String created;
	private String lastmodeified;
	private String version;
	private String status;

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getLastmodeified() {
		return lastmodeified;
	}

	public void setLastmodeified(String lastmodeified) {
		this.lastmodeified = lastmodeified;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
