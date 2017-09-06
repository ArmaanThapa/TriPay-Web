package com.instantpay.util;


import com.instantpay.model.request.AmountRequest;
import com.instantpay.model.request.Format;
import com.instantpay.model.request.Mode;
import com.instantpay.model.request.ValidationRequest;

public class IPayConvertUtil {
    public static ValidationRequest convertDTHRequest(AmountRequest dto){
        ValidationRequest ndto = new ValidationRequest();
        ndto.setSpKey(dto.getServiceProvider());
        ndto.setAccount(dto.getCustomerID());
        ndto.setAgentId(InstantPayConstants.AGENT_ID);
        ndto.setAmount(dto.getAmount());
        ndto.setFormat(Format.JSON);
        ndto.setMode(Mode.VALIDATE);
        ndto.setToken(InstantPayConstants.TOKEN);
        return ndto;
    }

    public static ValidationRequest convertAirtelLandlineRequest(AmountRequest dto){
        ValidationRequest ndto = new ValidationRequest();
        ndto.setSpKey(dto.getServiceProvider());
        ndto.setAccount(dto.getLandlineNumber());
        ndto.setOptional1(dto.getStdCode());
        ndto.setAgentId(InstantPayConstants.AGENT_ID);
        ndto.setAmount("20");
        ndto.setFormat(Format.JSON);
        ndto.setMode(Mode.VALIDATE);
        ndto.setToken(InstantPayConstants.TOKEN);
        return ndto;
    }



    public static ValidationRequest convertBSNLLandlineRequest(AmountRequest dto){
        ValidationRequest ndto = new ValidationRequest();
        ndto.setSpKey(dto.getServiceProvider());
        ndto.setAccount(dto.getLandlineNumber());
        ndto.setOptional1(dto.getStdCode());
        ndto.setOptional2(dto.getAccountNumber());
        ndto.setOptional3("LLI");
        ndto.setAgentId(InstantPayConstants.AGENT_ID);
        ndto.setAmount("50");
        ndto.setFormat(Format.JSON);
        ndto.setMode(Mode.VALIDATE);
        ndto.setToken(InstantPayConstants.TOKEN);
        return ndto;
    }
    public static ValidationRequest convertMTNLLandlineRequest(AmountRequest dto){
        ValidationRequest ndto = new ValidationRequest();
        ndto.setSpKey(dto.getServiceProvider());
        ndto.setAccount(dto.getLandlineNumber());
        ndto.setOptional1(dto.getCustomerID());
        ndto.setAgentId(InstantPayConstants.AGENT_ID);
        ndto.setAmount("50");
        ndto.setFormat(Format.JSON);
        ndto.setMode(Mode.VALIDATE);
        ndto.setToken(InstantPayConstants.TOKEN);
        return ndto;
    }

    public static ValidationRequest convertRelianceLandlineRequest(AmountRequest dto){
        ValidationRequest ndto = new ValidationRequest();
        ndto.setSpKey(dto.getServiceProvider());
        ndto.setAccount(dto.getLandlineNumber());
        ndto.setOptional1(dto.getStdCode());
        ndto.setAgentId(InstantPayConstants.AGENT_ID);
        ndto.setAmount("50");
        ndto.setFormat(Format.JSON);
        ndto.setMode(Mode.VALIDATE);
        ndto.setToken(InstantPayConstants.TOKEN);
        return ndto;
    }

    public static ValidationRequest convertTataDocomoLandlineRequest(AmountRequest dto){
        ValidationRequest ndto = new ValidationRequest();
        ndto.setSpKey(dto.getServiceProvider());
        ndto.setAccount(dto.getLandlineNumber());
        ndto.setOptional1(dto.getStdCode());
        ndto.setAgentId(InstantPayConstants.AGENT_ID);
        ndto.setAmount("40");
        ndto.setFormat(Format.JSON);
        ndto.setMode(Mode.VALIDATE);
        ndto.setToken(InstantPayConstants.TOKEN);
        return ndto;
    }

    public static ValidationRequest convertGenericElectricityRequest(AmountRequest dto){
        ValidationRequest ndto = new ValidationRequest();
        ndto.setSpKey(dto.getServiceProvider());
        ndto.setAccount(dto.getCustomerID());
        ndto.setAgentId(InstantPayConstants.AGENT_ID);
        ndto.setAmount("5");
        ndto.setFormat(Format.JSON);
        ndto.setMode(Mode.VALIDATE);
        ndto.setToken(InstantPayConstants.TOKEN);
        return ndto;
    }

    public static ValidationRequest convertMDEElectricityRequest(AmountRequest dto){
        ValidationRequest ndto = new ValidationRequest();
        ndto.setSpKey(dto.getServiceProvider());
        ndto.setAccount(dto.getCustomerID());
        ndto.setOptional1(dto.getBillingUnit());
        ndto.setOptional2(dto.getProcessingCycle());
        ndto.setAgentId(InstantPayConstants.AGENT_ID);
        ndto.setAmount("5");
        ndto.setFormat(Format.JSON);
        ndto.setMode(Mode.VALIDATE);
        ndto.setToken(InstantPayConstants.TOKEN);
        return ndto;
    }


    public static ValidationRequest convertREEElectricityRequest(AmountRequest dto){
        ValidationRequest ndto = new ValidationRequest();
        ndto.setSpKey(dto.getServiceProvider());
        ndto.setAccount(dto.getCustomerID());
        ndto.setOptional1(dto.getCycleNumber());
        ndto.setAgentId(InstantPayConstants.AGENT_ID);
        ndto.setAmount("5");
        ndto.setFormat(Format.JSON);
        ndto.setMode(Mode.VALIDATE);
        ndto.setToken(InstantPayConstants.TOKEN);
        return ndto;
    }


    public static ValidationRequest convertTPEElectricityRequest(AmountRequest dto){
        ValidationRequest ndto = new ValidationRequest();
        ndto.setSpKey(dto.getServiceProvider());
        ndto.setAccount(dto.getCustomerID());
        ndto.setOptional1(dto.getCityName());
        ndto.setAgentId(InstantPayConstants.AGENT_ID);
        ndto.setAmount("5");
        ndto.setFormat(Format.JSON);
        ndto.setMode(Mode.VALIDATE);
        ndto.setToken(InstantPayConstants.TOKEN);
        return ndto;
    }

    public static ValidationRequest convertGenericGasRequest(AmountRequest dto){
        ValidationRequest ndto = new ValidationRequest();
        ndto.setSpKey(dto.getServiceProvider());
        ndto.setAccount(dto.getCustomerID());
        ndto.setAgentId(InstantPayConstants.AGENT_ID);
        ndto.setAmount("5");
        ndto.setFormat(Format.JSON);
        ndto.setMode(Mode.VALIDATE);
        ndto.setToken(InstantPayConstants.TOKEN);
        return ndto;
    }


    public static ValidationRequest convertMMGGasRequest(AmountRequest dto){
        ValidationRequest ndto = new ValidationRequest();
        ndto.setSpKey(dto.getServiceProvider());
        ndto.setAccount(dto.getCustomerID());
        ndto.setOptional1(dto.getBillGroupNumber());
        ndto.setAgentId(InstantPayConstants.AGENT_ID);
        ndto.setAmount("5");
        ndto.setFormat(Format.JSON);
        ndto.setMode(Mode.VALIDATE);
        ndto.setToken(InstantPayConstants.TOKEN);
        return ndto;
    }


    public static ValidationRequest convertInsuranceRequest(AmountRequest dto){
        ValidationRequest ndto = new ValidationRequest();
        ndto.setSpKey(dto.getServiceProvider());
        ndto.setAccount(dto.getCustomerID());
        ndto.setOptional1(dto.getDateOfBirth());
        ndto.setAgentId(InstantPayConstants.AGENT_ID);
        ndto.setAmount(dto.getAmount());
        ndto.setFormat(Format.JSON);
        ndto.setMode(Mode.VALIDATE);
        ndto.setToken(InstantPayConstants.TOKEN);
        return ndto;
    }


}
