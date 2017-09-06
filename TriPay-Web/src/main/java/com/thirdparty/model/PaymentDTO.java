package com.thirdparty.model;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class PaymentDTO extends  AbstractDTO implements JSONWrapper{
    private String sessionId;
    private double walletAmount;
    private String serviceCode;
    private double netAmount;
    private double amountToLoad;
    private boolean useWallet;


    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public boolean isUseWallet() {
        return useWallet;
    }

    public void setUseWallet(boolean useWallet) {
        this.useWallet = useWallet;
    }

    public double getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(double walletAmount) {
        this.walletAmount = walletAmount;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public double getAmountToLoad() {
        return amountToLoad;
    }

    public void setAmountToLoad(double amountToLoad) {
        this.amountToLoad = amountToLoad;
    }


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try{
            json.put("transactionID",getTransactionID());
            json.put("id",getId());
            json.put("sessionId",getSessionId());
            json.put("walletAmount",getWalletAmount());
            json.put("amountToLoad",getAmountToLoad());
            json.put("netAmount",getNetAmount());
            return json;
        }catch(JSONException e){
            return null;
        }

    }
}
