package com.tripayweb.validation;


import com.tripayweb.app.model.ServiceType;
import com.tripayweb.app.model.request.PromoCodeError;
import com.tripayweb.app.model.request.PromoCodeRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PromoCodeValidation {

    private SimpleDateFormat promoCodeFormat = new SimpleDateFormat("yyyy-MM-dd");
    public PromoCodeError checkRequest(PromoCodeRequest dto)  {
        PromoCodeError error = new PromoCodeError();
        boolean valid = true;
        if(CommonValidation.isNull(dto.getPromoCode())){
            valid = false;
            error.setPromoCode("Please Enter Promo Code");
        }else if(!CommonValidation.checkLength6(dto.getPromoCode())){
            valid = false;
            error.setPromoCode("Promo Code must be at least 6 characters long");
        }
        if(CommonValidation.isNull(dto.getStartDate())){
            valid = false;
            error.setStartDate("Please Enter start date");
        }else {
            try {
                promoCodeFormat.parse(dto.getStartDate());
            } catch (ParseException e) {
                e.printStackTrace();
                valid = false;
                error.setStartDate("Please enter proper start date");
            }
        }
        if(CommonValidation.isNull(dto.getEndDate())){
            valid = false;
            error.setEndDate("Please Enter End Date");
        }else {
            try {
                promoCodeFormat.parse(dto.getEndDate());
            } catch (ParseException e) {
                e.printStackTrace();
                valid = false;
                error.setEndDate("Please enter proper end date");
            }
        }

        if(!(dto.getValue() > 0)){
            valid = false;
            error.setValue("Please enter value of coupon");
        }

        if(CommonValidation.isNull(dto.getDescription())){
            valid = false;
            error.setDescription("Please enter valid description of promo code");
        }
        if(CommonValidation.isNull(dto.getTerms())){
            valid = false;
            error.setTerms("Please enter min amount to redeem that coupon");
        }else if(!CommonValidation.isNumeric(dto.getTerms())){
            valid = false;
            error.setTerms("Amount must be in numeric form");
        }

        error.setValid(valid);
        return error;
    }
}
