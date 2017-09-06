package com.tripayweb.model.error;

public class TotalTransactionError {
	private boolean valid;

	private String page;

	private String size;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

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
		return "TotalTransactionError [valid=" + valid + ", page=" + getPage() + ", size=" + getSize() + "]";
	}
	
	

}
