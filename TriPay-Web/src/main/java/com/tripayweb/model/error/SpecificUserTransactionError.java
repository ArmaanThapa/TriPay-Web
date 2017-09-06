package com.tripayweb.model.error;

public class SpecificUserTransactionError {

	private boolean valid;

	private String page;

	private String size;

	public boolean getValid() {
		return valid;
	}

	public void setValid(boolean valid2) {
		this.valid = valid2;
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
		return "SpecificUserTransactionError [valid=" + getValid() + ", page=" + getPage() + ", size=" + getSize() + "]";
	}
	
	
}
