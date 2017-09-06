package com.tripayweb.app.model.response;

import com.tripayweb.app.model.MerchantDTO;

import java.util.List;

public class MerchantResponse {
    private boolean success;
    private String code;
    private String message;
    private List<MerchantDTO> merchantList;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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

    public List<MerchantDTO> getMerchantList() {
        return merchantList;
    }

    public void setMerchantList(List<MerchantDTO> merchantList) {
        this.merchantList = merchantList;
    }
}
