package com.instantpay.model.request;

public class PlansRequest {
    private String token;
    private String spKey;
    private String circle;
    private Format format;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSpKey() {
        return spKey;
    }

    public void setSpKey(String spKey) {
        this.spKey = spKey;
    }

    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }
}
