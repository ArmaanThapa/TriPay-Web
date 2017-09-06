package com.thirdparty.api.impl;


import com.tripayweb.app.utils.SecurityUtils;
import com.tripayweb.model.web.Status;
import com.tripayweb.util.JSONParserUtil;
import com.tripayweb.util.SecurityUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.thirdparty.api.IMerchantApi;
import com.thirdparty.model.*;
import com.thirdparty.util.MerchantConstants;
import org.codehaus.jettison.json.JSONObject;
import org.json.JSONException;

public class MerchantApi implements IMerchantApi{
    @Override
    public AuthenticationResponse authenticateMerchant(AuthenticateDTO dto) {
        AuthenticationResponse resp = new AuthenticationResponse();
        Client client = Client.create();
        System.err.println(MerchantConstants.AUTHENTICATE_URL);
        System.err.println(dto.toJSON());
        WebResource webResource = client.resource(MerchantConstants.AUTHENTICATE_URL);
        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class,dto.toJSON());
        String strResponse = response.getEntity(String.class);
        System.err.println("JSON Response::"+strResponse);
        if(response.getStatus() == 200){
            try {
                org.json.JSONObject json = new org.json.JSONObject(strResponse);
                final String code = JSONParserUtil.getString(json,"code");
                resp.setStatus(JSONParserUtil.getString(json,"status"));
                resp.setCode(code);
                if(code.equalsIgnoreCase("S00")){
                    resp.setSuccess(true);
                    String message = JSONParserUtil.getString(json,"message");
                    resp.setMessage(message);
                    org.json.JSONObject details = JSONParserUtil.getObject(json,"details");
                    resp.setSuccessURL(JSONParserUtil.getString(details,"successURL"));
                    resp.setFailureURL(JSONParserUtil.getString(details,"failureURL"));
                    resp.setImage(JSONParserUtil.getString(details,"image"));
                    resp.setMerchantId(JSONParserUtil.getLong(details,"merchantId"));
                }else{
                    resp.setSuccess(false);
                    resp.setMessage(JSONParserUtil.getString(json,"message"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {

        }
        return resp;
    }

    @Override
    public ResponseDTO authenticateUser(LoginDTO dto) {
        ResponseDTO resp = new ResponseDTO();
        Client client = Client.create();
        WebResource webResource = client.resource(MerchantConstants.AUTHENTICATE_USER);
        ClientResponse response = webResource.accept("application/json").type("application/json").header("hash","123456789").post(ClientResponse.class,dto.toJSON());
        String strResponse = response.getEntity(String.class);
        System.err.println("JSON Response::"+strResponse);
        if(response.getStatus() == 200){
            try {
                org.json.JSONObject json = new org.json.JSONObject(strResponse);
                final String code = JSONParserUtil.getString(json,"code");
                resp.setStatus(JSONParserUtil.getString(json,"status"));
                resp.setCode(code);
                if(code.equalsIgnoreCase("S00")){
                    String message = JSONParserUtil.getString(json,"message");
                    resp.setMessage(message);
                    org.json.JSONObject details = JSONParserUtil.getObject(json,"details");
                    if(details != null) {
                        final String sessionId = JSONParserUtil.getString(details,"sessionId");
                        resp.setSessionId(sessionId);
                        org.json.JSONObject accountDetail = JSONParserUtil.getObject(details, "accountDetail");
                        if(accountDetail != null){
                            resp.setAmount(JSONParserUtil.getDouble(accountDetail,"balance"));
                        }
                        org.json.JSONObject userDetail = JSONParserUtil.getObject(details,"userDetail");
                        if(userDetail != null){
                            resp.setDetails(JSONParserUtil.getString(userDetail,"username"));
                            resp.setInfo(JSONParserUtil.getString(userDetail,"firstName")+" "+JSONParserUtil.getString(userDetail,"lastName"));
                        }
                    }
                }else{
                    resp.setMessage(JSONParserUtil.getString(json,"message"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {

        }
        return resp;
    }

    @Override
    public ResponseDTO registerUser(RegisterDTO dto) {
        ResponseDTO result  = new ResponseDTO();
        Client client = Client.create();
        WebResource webResource = client.resource(MerchantConstants.REGISTER_USER);
        ClientResponse response = webResource.accept("application/json").type("application/json").header("hash", SecurityUtils.getHash(""+dto.toJSON())).post(ClientResponse.class,dto.toJSON());
        String strResponse = response.getEntity(String.class);
        System.err.println("JSON Response::"+strResponse);
        if(response.getStatus() == 200){
            try {
                org.json.JSONObject json = new org.json.JSONObject(strResponse);
                final String code = JSONParserUtil.getString(json,"code");
                result.setStatus(JSONParserUtil.getString(json,"status"));
                result.setCode(code);
                if(code.equalsIgnoreCase("S00")){
                        result.setMessage(JSONParserUtil.getString(json,"message"));
                }else{
                    result.setMessage(JSONParserUtil.getString(json,"message"));
                    if(code.equalsIgnoreCase("F00")){
                        org.json.JSONObject details = JSONParserUtil.getObject(json,"details");
                        if(details != null) {
                            boolean valid = JSONParserUtil.getBoolean(details, "valid");
                            if(!valid){
                                result.setCode("F04");
                                result.setMessage(JSONParserUtil.getString(details,"contactNo"));
                                result.setDetails(JSONParserUtil.getString(details,"email"));
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public ResponseDTO verifyOTP(VerifyDTO dto) {
        ResponseDTO result = new ResponseDTO();
        Client client = Client.create();
        WebResource webResource = client.resource(MerchantConstants.VERIFY_OTP);
        ClientResponse response = webResource.accept("application/json").type("application/json").header("hash", SecurityUtils.getHash(""+dto.toJSON())).post(ClientResponse.class,dto.toJSON());
        String strResponse = response.getEntity(String.class);
        System.err.println("JSON Response::"+strResponse);
        if(response.getStatus() == 200){
            try {
                org.json.JSONObject json = new org.json.JSONObject(strResponse);
                result.setStatus(JSONParserUtil.getString(json,"status"));
                final String code = JSONParserUtil.getString(json,"code");
                result.setCode(code);
                result.setMessage(JSONParserUtil.getString(json,"details"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public ResponseDTO forgotPassword(ForgotPasswordDTO dto) {
        ResponseDTO result = new ResponseDTO();
        Client client = Client.create();
        WebResource webResource = client.resource(MerchantConstants.FORGOT_PASSWORD);
        ClientResponse response = webResource.accept("application/json").type("application/json").header("hash", SecurityUtils.getHash(""+dto.toJSON())).post(ClientResponse.class,dto.toJSON());
        String strResponse = response.getEntity(String.class);
        System.err.println("JSON Response::"+strResponse);
        if(response.getStatus() == 200){
            try {
                org.json.JSONObject json = new org.json.JSONObject(strResponse);
                result.setStatus(JSONParserUtil.getString(json,"status"));
                final String code = JSONParserUtil.getString(json,"code");
                result.setCode(code);
                result.setMessage(JSONParserUtil.getString(json,"details"));
            } catch (JSONException e) {
                e.printStackTrace();
                result.setStatus("Service Unavailable");
            }
        }
        return result;
    }

    @Override
    public ResponseDTO validateOTP(VerifyDTO dto) {
        ResponseDTO result = new ResponseDTO();
        Client client = Client.create();
        WebResource webResource = client.resource(MerchantConstants.VALIDATE_FORGOT_PASSWORD_OTP+"/"+dto.getOtp());
        ClientResponse response = webResource.accept("application/json").type("application/json").header("hash", SecurityUtils.getHash(""+dto.toJSON())).post(ClientResponse.class,dto.toJSON());
        String strResponse = response.getEntity(String.class);
        System.err.println("JSON Response::"+strResponse);
        if(response.getStatus() == 200){
            try {
                org.json.JSONObject json = new org.json.JSONObject(strResponse);
                result.setStatus(JSONParserUtil.getString(json,"status"));
                final String code = JSONParserUtil.getString(json,"code");
                result.setCode(code);
                result.setMessage(JSONParserUtil.getString(json,"details"));
            } catch (JSONException e) {
                e.printStackTrace();
                result.setStatus("Service Unavailable");
            }
        }
        return result;
    }

    @Override
    public ResponseDTO changePassword(ChangePasswordDTO dto) {
        ResponseDTO result = new ResponseDTO();
        Client client = Client.create();
        WebResource webResource = client.resource(MerchantConstants.CHANGE_PASSWORD);
        ClientResponse response = webResource.accept("application/json").type("application/json").header("hash", SecurityUtils.getHash(""+dto.toJSON())).post(ClientResponse.class,dto.toJSON());
        String strResponse = response.getEntity(String.class);
        System.err.println("JSON Response::"+strResponse);
        if(response.getStatus() == 200){
            try {
                org.json.JSONObject json = new org.json.JSONObject(strResponse);
                result.setStatus(JSONParserUtil.getString(json,"status"));
                final String code = JSONParserUtil.getString(json,"code");
                result.setCode(code);
                result.setMessage(JSONParserUtil.getString(json,"details"));
            } catch (JSONException e) {
                e.printStackTrace();
                result.setStatus("Service Unavailable");
            }
        }
        return result;
    }

    @Override
    public ResponseDTO processPayment(PaymentDTO dto) {
        ResponseDTO result = new ResponseDTO();
        Client client = Client.create();
        WebResource webResource = client.resource(MerchantConstants.PROCESS_PAYMENT);
        ClientResponse response = webResource.accept("application/json").type("application/json").header("hash", SecurityUtils.getHash(""+dto.toJSON())).post(ClientResponse.class,dto.toJSON());
        String strResponse = response.getEntity(String.class);
        System.err.println("JSON Response::"+strResponse);
        if(response.getStatus() == 200){
            try {
                org.json.JSONObject json = new org.json.JSONObject(strResponse);
                result.setStatus(JSONParserUtil.getString(json,"status"));
                final String code = JSONParserUtil.getString(json,"code");
                result.setCode(code);
                result.setMessage(JSONParserUtil.getString(json,"details"));
            } catch (JSONException e) {
                e.printStackTrace();
                result.setStatus("Service Unavailable");
            }
        }
        return result;
    }

    @Override
    public StatusResponse checkStatus(StatusCheckDTO dto) {
        StatusResponse result = new StatusResponse();
        Client client = Client.create();
        WebResource webResource = client.resource(MerchantConstants.STATUS_CHECK);
        ClientResponse response = webResource.accept("application/json").type("application/json").header("hash", SecurityUtils.getHash(""+dto.toJSON())).post(ClientResponse.class,dto.toJSON());
        String strResponse = response.getEntity(String.class);
        System.err.println("JSON Response::"+strResponse);
        if(response.getStatus() == 200){
            try {
                org.json.JSONObject json = new org.json.JSONObject(strResponse);
                final String code = JSONParserUtil.getString(json,"code");
                result.setCode(code);
                result.setMessage(JSONParserUtil.getString(json,"message"));
                if(code.equalsIgnoreCase("S00")){
                    org.json.JSONObject details = JSONParserUtil.getObject(json,"details");
                    result.setTransactionDate(JSONParserUtil.getString(details,"transactionDate"));
                    result.setPaymentId(JSONParserUtil.getString(details,"paymentId"));
                    result.setMerchantRefNo(JSONParserUtil.getString(details,"merchantRefNo"));
                    result.setStatus(Status.valueOf(JSONParserUtil.getString(details,"status")));
                    result.setAmount(JSONParserUtil.getString(details,"amount"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                result.setMessage("Service Unavailable");
            }
        }
        return result;
    }
}
