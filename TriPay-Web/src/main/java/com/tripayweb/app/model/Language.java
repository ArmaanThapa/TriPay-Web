package com.tripayweb.app.model;

public enum Language {
	ENGLISH("en");

	private final String value;

	private Language(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public static Language getEnum(String value) {
		if (value == null)
			throw new IllegalArgumentException();
		for (Language v : values())
			if (value.equalsIgnoreCase(v.getValue()))
				return v;
		throw new IllegalArgumentException();
	}
	
}
