package com.tripayweb.app.model.response;


import com.tripayweb.model.web.Status;

public class OnePayResponse {
    private boolean success;
    private String serviceCode;
    private String serviceName;
    private Status tStatus;
    private String json;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Status gettStatus() {
        return tStatus;
    }

    public void settStatus(Status tStatus) {
        this.tStatus = tStatus;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
