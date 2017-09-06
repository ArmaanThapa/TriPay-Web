package com.tripayweb.model.app.response;

public class RegisterResponse {

	private String code;
	private String status;
	private String message;
	private Object details;
	private String data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getDetails() {
		return details;
	}

	public void setDetails(Object details) {
		this.details = details;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
