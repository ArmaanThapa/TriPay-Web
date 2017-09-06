package com.tripayweb.model.error;

import com.thirdparty.model.JSONWrapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class SendMoneyBankError implements JSONWrapper{

    boolean valid;
    private String bankCode;
    private String ifscCode;
    private String accountNumber;
    private String amount;
    private String accountName;


    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try{
            json.put("valid",isValid());
            json.put("bankCode",getBankCode());
            json.put("ifscCode",getIfscCode());
            json.put("accountNumber",getAccountNumber());
            json.put("amount",getAmount());
            json.put("accountName",getAccountName());
        }catch(JSONException ex){
            ex.printStackTrace();
        }
        return json;
    }
}
