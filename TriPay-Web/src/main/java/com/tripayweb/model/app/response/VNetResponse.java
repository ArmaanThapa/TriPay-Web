package com.tripayweb.model.app.response;

public class VNetResponse {

    private boolean success;
    private boolean code;
    private String returnURL;
    private String mid;
    private String pid;
    private String crnNo;
    private String prnNo;
    private String merchantName;
    private String itc;
    private String amount;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public String getReturnURL() {
        return returnURL;
    }

    public void setReturnURL(String returnURL) {
        this.returnURL = returnURL;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCrnNo() {
        return crnNo;
    }

    public void setCrnNo(String crnNo) {
        this.crnNo = crnNo;
    }

    public String getPrnNo() {
        return prnNo;
    }

    public void setPrnNo(String prnNo) {
        this.prnNo = prnNo;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getItc() {
        return itc;
    }

    public void setItc(String itc) {
        this.itc = itc;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
