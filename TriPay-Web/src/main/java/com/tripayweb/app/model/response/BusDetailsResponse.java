package com.tripayweb.app.model.response;

import com.tripayweb.model.error.BusDetailsError;

public class BusDetailsResponse {

	private boolean success;
	private String code;
	private String message;
	private String status;
	private BusDetailsError details;
	private String response;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BusDetailsError getDetails() {
		return details;
	}
	public void setDetails(BusDetailsError details) {
		this.details = details;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
}
