package com.tripayweb.app.model;

public enum Version {
	VERSION_1("v1"),VERSION_2("v2");

	private final String value;

	private Version(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public static Version getEnum(String value) {
		if (value == null)
			throw new IllegalArgumentException();
		for (Version v : values())
			if (value.equalsIgnoreCase(v.getValue()))
				return v;
		throw new IllegalArgumentException();
	}
}
