package com.tripayweb.app.model;

public enum Role {
	ADMIN("Admin"), MERCHANT("Merchant"), USER("User");

	private final String value;

	private Role(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public static Role getEnum(String value) {
		if (value == null)
			throw new IllegalArgumentException();
		for (Role v : values())
			if (value.equalsIgnoreCase(v.getValue()))
				return v;
		throw new IllegalArgumentException();
	}
}
