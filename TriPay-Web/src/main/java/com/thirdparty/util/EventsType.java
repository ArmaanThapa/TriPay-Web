package com.thirdparty.util;

public enum EventsType {

	Free(1),
	Paid(2),
	Information(3),
	Webinar(4);
	
	private int numValue;
	
	EventsType(int numValue) {
		this.numValue = numValue;
	}

	public int getNumValue() {
		return numValue;
	}

	public void setNumValue(int numValue) {
		this.numValue = numValue;
	}
	
}
