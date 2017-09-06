package com.instantpay.api.impl;

import com.instantpay.api.IValidationApi;
import com.instantpay.model.request.ValidationRequest;
import com.instantpay.util.InstantPayConstants;
import com.tripayweb.util.JSONParserUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.thirdparty.model.ResponseDTO;
import org.json.JSONObject;

public class ValidationApi implements IValidationApi {

    @Override
    public String getAmount(ValidationRequest dto) {
        System.err.println(dto);
            String stringResponse = "";
        try {
            WebResource resource = Client.create().resource(InstantPayConstants.URL_VALIDATION)
                    .queryParam("token", dto.getToken())
                    .queryParam("mode", String.valueOf(dto.getMode()))
                    .queryParam("spkey", dto.getSpKey())
                    .queryParam("agentid", dto.getAgentId())
                    .queryParam("account", dto.getAccount())
                    .queryParam("amount", dto.getAmount())
                    .queryParam("optional1", (dto.getOptional1() == null)?"":dto.getOptional1())
                    .queryParam("optional2", (dto.getOptional2() == null)?"":dto.getOptional2())
                    .queryParam("optional3", (dto.getOptional3() == null)?"":dto.getOptional3())
                    .queryParam("format", dto.getFormat().getFormat());
            ClientResponse clientResponse = resource.get(ClientResponse.class);
            if (clientResponse.getStatus() == 200) {
                stringResponse = clientResponse.getEntity(String.class);
                System.err.println("response ::"+stringResponse);
                JSONObject o = new JSONObject(stringResponse);
                String ipay_errorcode = JSONParserUtil.getString(o, "ipay_errorcode");
                if(ipay_errorcode != null) {
                    String ipay_errordesc = JSONParserUtil.getString(o, "​ipay_errordesc");
                    if(ipay_errorcode.equalsIgnoreCase("IRA")) {
                        JSONObject particulars = JSONParserUtil.getObject(o,"particulars");
                        if(particulars != null){
                            String dueAmount = JSONParserUtil.getString(particulars,"dueamount");
                            stringResponse = dueAmount;
                            System.err.println("due amount ::"+dueAmount);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringResponse;
    }

    @Override
    public ResponseDTO validateTransaction(ValidationRequest dto) {
        System.err.print(dto);
        ResponseDTO result = new ResponseDTO();
        String stringResponse = "";
        try {
            WebResource resource = Client.create().resource(InstantPayConstants.URL_VALIDATION)
                    .queryParam("token", dto.getToken())
                    .queryParam("mode", String.valueOf(dto.getMode()))
                    .queryParam("spkey", dto.getSpKey())
                    .queryParam("agentid", dto.getAgentId())
                    .queryParam("account", dto.getAccount())
                    .queryParam("amount", dto.getAmount())
                    .queryParam("optional1", (dto.getOptional1() == null)?"":dto.getOptional1())
                    .queryParam("optional2", (dto.getOptional2() == null)?"":dto.getOptional2())
                    .queryParam("optional3", (dto.getOptional3() == null)?"":dto.getOptional3())
                    .queryParam("optional4", (dto.getOptional4() == null)?"":dto.getOptional4())
                    .queryParam("optional5", (dto.getOptional5() == null)?"":dto.getOptional5())
                    .queryParam("format", dto.getFormat().getFormat());
            System.err.println("inside the Validation Api");
            ClientResponse clientResponse = resource.get(ClientResponse.class);
            if (clientResponse.getStatus() == 200) {
                stringResponse = clientResponse.getEntity(String.class);
                System.err.println("response ::"+stringResponse);
                JSONObject o = new JSONObject(stringResponse);
                String ipay_errorcode = JSONParserUtil.getString(o, "ipay_errorcode");
                if(ipay_errorcode != null) {
                    String ipay_errordesc = JSONParserUtil.getString(o, "ipay_errordesc");
                    System.err.print(ipay_errorcode);
                    System.err.print(ipay_errordesc);
                    if(ipay_errorcode.equalsIgnoreCase("TXN")) {
                          result.setSuccess(true);
                    }else{
                        System.err.print(ipay_errordesc);
                        result.setSuccess(false);
                        result.setMessage(ipay_errordesc);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
