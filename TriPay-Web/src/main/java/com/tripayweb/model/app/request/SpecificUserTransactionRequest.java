package com.tripayweb.model.app.request;

public class SpecificUserTransactionRequest {
	private String page;

	private String size;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "SpecificUserTransactionRequest [page=" + getPage() + ", size=" + getSize() + "]";
	}
	

}
