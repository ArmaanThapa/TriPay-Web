package com.tripayweb.app.model.response;

import org.json.JSONArray;
public class VersionResponse {

    private String code;
    private String status;
    private String message;
    private Object details;
    private JSONArray arrayDetails;

    public String getCode() {
        return code;
    }

    public JSONArray getArrayDetails() {
        return arrayDetails;
    }

    public void setArrayDetails(JSONArray arrayDetails) {
        this.arrayDetails = arrayDetails;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }
}
