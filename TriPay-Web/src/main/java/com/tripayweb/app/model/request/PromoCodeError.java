package com.tripayweb.app.model.request;

public class PromoCodeError{

    private boolean valid;

    private String promoCode;
    private String terms;
    private String startDate;
    private String endDate;
    private String value;
    private String serviceType;
    private String sessionId;
    private String status;
    private long promoCodeId;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getPromoCodeId() {
        return promoCodeId;
    }

    public void setPromoCodeId(long promoCodeId) {
        this.promoCodeId = promoCodeId;
    }

    @Override
    public String toString() {
        return "PromoCodeError{" +
                "valid=" + valid +
                ", promoCode='" + promoCode + '\'' +
                ", terms='" + terms + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", value='" + value + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", status='" + status + '\'' +
                ", promoCodeId=" + promoCodeId +
                '}';
    }
}
