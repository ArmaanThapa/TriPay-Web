package com.thirdparty.validation;

import com.tripayweb.validation.CommonValidation;
import com.thirdparty.model.AuthenticateDTO;
import com.thirdparty.model.StatusCheckDTO;
import com.thirdparty.model.error.CommonError;

public class MerchantValidation {

    public CommonError checkError(AuthenticateDTO dto){
        CommonError error = new CommonError();
        boolean valid = true;
        if(dto.getId() <=0){
            valid = false;
        }
        if(CommonValidation.isNull(dto.getIpAddress())){
            valid = false;
        }
        if(CommonValidation.isNull(dto.getAmount())){
            valid = false;
        }
        error.setValid(valid);
        return error;
    }

    public CommonError checkError(StatusCheckDTO dto){
        CommonError error = new CommonError();
        boolean valid = true;
        try{
            if(dto.getMerchantId() <= 0){
                valid = false;
                error.setMessage("Enter Merchant ID");
            }else if(CommonValidation.isNull(dto.getMerchantRefNo())){
                valid = false;
                error.setMessage("Enter Merchant Ref No");
            }else if(CommonValidation.isNull(dto.getSecretKey())){
                valid = false;
                error.setMessage("Enter Secret Key");
            }
        }catch(Exception e){
            valid = false;
        }
        error.setValid(valid);
        return error;

    }
}
