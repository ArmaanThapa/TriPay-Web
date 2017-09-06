package com.tripayweb.app.model.request;

import org.codehaus.jettison.json.*;

public class TransactionRequest {
    private String amount;
    private String sessionId;
    private String transactionRefNo;
    private String senderUsername;
    private String receiverUsername;
    private String description;


    public String getTransactionRefNo() {
        return transactionRefNo;
    }

    public void setTransactionRefNo(String transactionRefNo) {
        this.transactionRefNo = transactionRefNo;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public JSONObject toJSON(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("amount",getAmount());
            jsonObject.put("sessionId",getSessionId());
            jsonObject.put("transactionRefNo",getTransactionRefNo());
            jsonObject.put("senderUsername",getSenderUsername());
            jsonObject.put("receiverUsername",getReceiverUsername());
            jsonObject.put("description",getDescription());
            return jsonObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
