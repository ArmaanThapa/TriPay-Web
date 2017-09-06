package com.tripayweb.app.model.response;

import com.tripayweb.model.error.BlockBusError;

public class BookBusSeatResponse {

	private boolean success;
	private String code;
	private String message;
	private String status;
	private BlockBusError details;
	private String response;
	private String codeCondtion;
	private String blockingRefNo;
	private String bookingRefNo;
	
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
	public BlockBusError getDetails() {
		return details;
	}
	public void setDetails(BlockBusError details) {
		this.details = details;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getCodeCondtion() {
		return codeCondtion;
	}
	public void setCodeCondtion(String codeCondtion) {
		this.codeCondtion = codeCondtion;
	}
	public String getBlockingRefNo() {
		return blockingRefNo;
	}
	public void setBlockingRefNo(String blockingRefNo) {
		this.blockingRefNo = blockingRefNo;
	}
	public String getBookingRefNo() {
		return bookingRefNo;
	}
	public void setBookingRefNo(String bookingRefNo) {
		this.bookingRefNo = bookingRefNo;
	}
	
}
