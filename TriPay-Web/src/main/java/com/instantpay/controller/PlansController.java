package com.instantpay.controller;

import com.instantpay.api.IPlansApi;
import com.instantpay.model.request.PlansRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/InstantPay")
public class PlansController {

    private IPlansApi plansApi;

    public PlansController(IPlansApi plansApi){
        this.plansApi = plansApi;
    }

    @RequestMapping(value="/GetPlans",method= RequestMethod.POST)
    ResponseEntity<String> getPlans(@ModelAttribute PlansRequest dto, HttpServletRequest request, HttpServletResponse response){
        String strResponse = "";
        strResponse = plansApi.getPlansApi(dto);
        return new ResponseEntity<String>(strResponse.toString(), HttpStatus.OK);
    }
}
