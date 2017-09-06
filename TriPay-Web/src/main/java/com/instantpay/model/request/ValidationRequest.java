package com.instantpay.model.request;


public class ValidationRequest {

    private String token;
    private Mode mode;
    private String spKey;
    private String agentId;
    private String account;
    private String amount;
    private String optional1;
    private String optional2;
    private String optional3;
    private String optional4;
    private String optional5;
    private Format format;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public String getSpKey() {
        return spKey;
    }

    public void setSpKey(String spKey) {
        this.spKey = spKey;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOptional1() {
        return optional1;
    }

    public void setOptional1(String optional1) {
        this.optional1 = optional1;
    }

    public String getOptional2() {
        return optional2;
    }

    public void setOptional2(String optional2) {
        this.optional2 = optional2;
    }

    public String getOptional3() {
        return optional3;
    }

    public void setOptional3(String optional3) {
        this.optional3 = optional3;
    }

    public String getOptional4() {
        return optional4;
    }

    public void setOptional4(String optional4) {
        this.optional4 = optional4;
    }

    public String getOptional5() {
        return optional5;
    }

    public void setOptional5(String optional5) {
        this.optional5 = optional5;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }


    @Override
    public String toString() {
        return "ValidationRequest{" +
                "token='" + token + '\'' +
                ", mode=" + mode +
                ", spKey='" + spKey + '\'' +
                ", agentId='" + agentId + '\'' +
                ", account='" + account + '\'' +
                ", amount='" + amount + '\'' +
                ", optional1='" + optional1 + '\'' +
                ", optional2='" + optional2 + '\'' +
                ", optional3='" + optional3 + '\'' +
                ", optional4='" + optional4 + '\'' +
                ", optional5='" + optional5 + '\'' +
                ", format=" + format +
                '}';
    }
}
