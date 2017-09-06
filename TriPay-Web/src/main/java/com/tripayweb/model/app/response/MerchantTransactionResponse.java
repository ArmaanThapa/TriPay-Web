package com.tripayweb.model.app.response;

import com.thirdparty.model.JSONWrapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class MerchantTransactionResponse implements JSONWrapper{
    private String username;
    private String mobileNumber;
    private String email;
    private String transactionRefNo;
    private String vpayqwikRefNo;
    private String amount;
    private String dateTime;
    private String status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTransactionRefNo() {
        return transactionRefNo;
    }

    public void setTransactionRefNo(String transactionRefNo) {
        this.transactionRefNo = transactionRefNo;
    }

    public String getVpayqwikRefNo() {
        return vpayqwikRefNo;
    }

    public void setVpayqwikRefNo(String vpayqwikRefNo) {
        this.vpayqwikRefNo = vpayqwikRefNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "username : '" + username + '\'' +
                ", mobileNumber : '" + mobileNumber + '\'' +
                ", email : '" + email + '\'' +
                ", transactionRefNo : '" + transactionRefNo + '\'' +
                ", vpayqwikRefNo='" + vpayqwikRefNo + '\'' +
                ", amount='" + amount + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("username",getUsername());
            json.put("mobileNumber",getMobileNumber());
            json.put("email",getEmail());
            json.put("transactionRefNo",getTransactionRefNo());
            json.put("vpayqwikRefNo",getVpayqwikRefNo());
            json.put("amount",getAmount());
            json.put("dateTime",getDateTime());
            json.put("status",getStatus());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    return json;
    }
}
