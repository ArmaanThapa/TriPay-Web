package com.thirdparty.model;


public class PaymentResponse {

    private String orderID;
    private String success;
    private String description;
    private String additionalInfo;

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "orderID='" + orderID + '\'' +
                ", success='" + success + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
