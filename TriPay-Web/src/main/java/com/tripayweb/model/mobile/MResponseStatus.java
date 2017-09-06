package com.tripayweb.model.mobile;

public enum MResponseStatus {

	SUCCESS("Success","S00"),
	
	FAILURE("Failure","F00"),

	INTERNAL_SERVER_ERROR("Internal Server Error","F02"),

	INVALID_SESSION("Session Invalid","F03"),

	BAD_REQUEST("Bad Request","F04"),
	
	UNAUTHORIZED_USER("Un-Authorized User","F05");

	private MResponseStatus() {

	}
	
	private String key;

	private String value;

	private MResponseStatus(String key,String value) {
		this.key=key;
		this.value = value;
	}
	
	public String getKey(){
		return key;
	}
	public String getValue() {
		return value;
	}

	public static MResponseStatus getEnumByValue(String value) {
		if (value == null)
			throw new IllegalArgumentException();
		for (MResponseStatus v : values())
			if (value.equalsIgnoreCase(v.getValue()))
				return v;
		throw new IllegalArgumentException();
	}
	
	public static MResponseStatus getEnumByKey(String key) {
		if (key == null)
			throw new IllegalArgumentException();
		for (MResponseStatus v : values())
			if (key.equalsIgnoreCase(v.getKey()))
				return v;
		throw new IllegalArgumentException();
	}

}
