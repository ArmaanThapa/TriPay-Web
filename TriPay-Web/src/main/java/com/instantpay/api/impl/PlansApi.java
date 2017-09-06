package com.instantpay.api.impl;

import com.instantpay.api.IPlansApi;
import com.instantpay.model.request.Format;
import com.instantpay.model.request.PlansRequest;
import com.instantpay.util.InstantPayConstants;
import com.tripayweb.util.JSONParserUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONObject;

public class PlansApi implements IPlansApi{

    @Override
    public String getPlansApi(PlansRequest dto) {
        String stringResponse = "";
        System.err.println("token ::"+InstantPayConstants.TOKEN);
        System.err.println("spkey ::"+dto.getSpKey());
        System.err.println("circle ::"+dto.getCircle());
        System.err.println("format ::"+Format.JSON.getFormat());
        try {
            WebResource resource = Client.create().resource(InstantPayConstants.URL_PLANS)
                    .queryParam("token", InstantPayConstants.TOKEN)
                    .queryParam("spkey", dto.getSpKey())
                    .queryParam("circle",dto.getCircle())
                    .queryParam("format", Format.JSON.getFormat());
            ClientResponse clientResponse = resource.get(ClientResponse.class);
                stringResponse = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                System.err.println("response ::"+stringResponse);
            }else {
                System.err.println("response is ::"+clientResponse.getStatus());
                System.err.println("response ::::"+stringResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringResponse;
    }
}
