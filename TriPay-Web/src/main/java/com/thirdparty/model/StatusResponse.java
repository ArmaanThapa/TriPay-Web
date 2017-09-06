package com.thirdparty.model;


import com.tripayweb.model.web.Status;

public class StatusResponse {
    private String code;
    private String message;
    private String transactionDate;
    private String paymentId;
    private String amount;
    private String merchantRefNo;
    private Status status;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMerchantRefNo() {
        return merchantRefNo;
    }

    public void setMerchantRefNo(String merchantRefNo) {
        this.merchantRefNo = merchantRefNo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
