package com.tripayweb.app.model.response;


import com.thirdparty.model.JSONWrapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class VRedirectResponse implements JSONWrapper{
    private String PAID;
    private String BID;
    private String MD;
    private String CRN;
    private String AMT;
    private String  PRN;
    private String ITC;
    private String PID;

    @Override
    public String toString() {
        return "VRedirectResponse{" +
                "PAID='" + PAID + '\'' +
                ", BID='" + BID + '\'' +
                ", MD='" + MD + '\'' +
                ", CRN='" + CRN + '\'' +
                ", AMT='" + AMT + '\'' +
                ", PRN='" + PRN + '\'' +
                ", ITC='" + ITC + '\'' +
                ", PID='" + PID + '\'' +
                '}';
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getPAID() {
        return PAID;
    }

    public void setPAID(String PAID) {
        this.PAID = PAID;
    }

    public String getBID() {
        return BID;
    }

    public void setBID(String BID) {
        this.BID = BID;
    }

    public String getMD() {
        return MD;
    }

    public void setMD(String MD) {
        this.MD = MD;
    }

    public String getCRN() {
        return CRN;
    }

    public void setCRN(String CRN) {
        this.CRN = CRN;
    }

    public String getAMT() {
        return AMT;
    }

    public void setAMT(String AMT) {
        this.AMT = AMT;
    }

    public String getPRN() {
        return PRN;
    }

    public void setPRN(String PRN) {
        this.PRN = PRN;
    }

    public String getITC() {
        return ITC;
    }

    public void setITC(String ITC) {
        this.ITC = ITC;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {

            json.put("paid",getPAID());
            json.put("bid",getBID());
            json.put("md",getMD());
            json.put("crn",getCRN());
            json.put("pid",getPID());
            json.put("prn",getPRN());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
