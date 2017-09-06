package com.tripayweb.model.web;

public enum Status {

	Inactive("Inactive"), Active("Active"), Deleted("Deleted"), Success(
			"Success"), Failed("Failed"), Blocked("Blocked"), Reversed("Reversed"), Processing("Processing"), Initiated("Initiated"),Refunded("Refunded");

	private final String value;

	private Status(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public static Status getEnum(String value) {
		if (value == null)
			throw new IllegalArgumentException();
		for (Status v : values())
			if (value.equalsIgnoreCase(v.getValue()))
				return v;
		throw new IllegalArgumentException();
	}
}
