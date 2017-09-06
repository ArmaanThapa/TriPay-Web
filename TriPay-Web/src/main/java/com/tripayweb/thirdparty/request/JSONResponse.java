package com.tripayweb.thirdparty.request;


import com.tripayweb.app.model.ResponseStatus;

public class JSONResponse {

    private ResponseStatus status;
    private String message;
    private Object details;


    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
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
