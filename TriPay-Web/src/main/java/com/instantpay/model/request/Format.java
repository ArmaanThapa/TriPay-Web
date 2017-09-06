package com.instantpay.model.request;

public enum Format {
    JSON("json"),XML("xml");
    private String value;
    Format(String value){
        this.value = value;
    }
    public String getFormat(){
        return value;
    }
}


