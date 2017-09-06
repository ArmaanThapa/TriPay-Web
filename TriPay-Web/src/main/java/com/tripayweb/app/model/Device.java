package com.tripayweb.app.model;

public enum Device {
	WEBSITE("Website"), ANDROID("Android"), IOS("Ios"), WINDOWS("Windows");

	private final String value;

	private Device(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public static Device getEnum(String value) {
		if (value == null)
			throw new IllegalArgumentException();
		for (Device v : values())
			if (value.equalsIgnoreCase(v.getValue()))
				return v;
		throw new IllegalArgumentException();
	}
}
