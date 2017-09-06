package com.tripayweb.app.api.impl;

import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Language;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.Version;
import com.tripayweb.app.model.request.AllTransactionRequest;
import com.tripayweb.app.model.request.LoginRequest;
import com.tripayweb.app.model.response.AllTransactionResponse;
import com.tripayweb.app.model.response.LoginResponse;
import com.tripayweb.app.utils.SecurityUtils;
import com.tripayweb.util.APIUtils;
import com.tripayweb.util.JSONParserUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.tripayweb.app.api.IMerchantApi;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

public class MerchantApi implements IMerchantApi{

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public LoginResponse login(LoginRequest request) {
        LoginResponse resp = new LoginResponse();
        try {
            JSONObject payload = new JSONObject();
            payload.put("username", request.getUsername());
            payload.put("password", request.getPassword());
            payload.put("ipAddress", request.getIpAddress());
            logger.info("Payload " + payload);

            Client client = Client.create();
            WebResource webResource = client.resource(
                    UrlMetadatas.getLoginUrl(Version.VERSION_1, Role.MERCHANT, Device.WEBSITE, Language.ENGLISH));

            ClientResponse response = webResource.accept("application/json").type("application/json")
                    .header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

            logger.info(
                    "URL " + UrlMetadatas.getLoginUrl(Version.VERSION_1, Role.MERCHANT, Device.WEBSITE, Language.ENGLISH));
            String strResponse = response.getEntity(String.class);
            if (response.getStatus() != 200) {
                logger.info("Response 1" + strResponse);
                resp.setSuccess(false);
                resp.setCode("F00");
                resp.setMessage("Service unavailable");
                resp.setStatus("FAILED");
                resp.setResponse(APIUtils.getFailedJSON().toString());

            } else {

                logger.info("Response 2" + response.getStatus());
                if (strResponse != null) {
                    logger.info("Response 3" + strResponse);
                    org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
                    if (jobj != null) {
                        logger.info("Response 4");
                        final String status = (String) jobj.get("status");
                        final String code = (String) jobj.get("code");
                        final String message = (String) jobj.get("message");

                        if (code.equalsIgnoreCase("S00")) {
                            resp.setSuccess(true);
                            final org.json.JSONObject details = JSONParserUtil.getObject(jobj,"details");
                            final org.json.JSONObject accountDetail = JSONParserUtil.getObject(details,"accountDetail");
                            final org.json.JSONObject accountType = JSONParserUtil.getObject(accountDetail,"accountType");
                            final org.json.JSONObject userDetail = JSONParserUtil.getObject(details,"userDetail");
                            final String sessionId = JSONParserUtil.getString(details,"sessionId");
                            final double balance = JSONParserUtil.getDouble(accountDetail,"balance");
                            final long accountNumber = JSONParserUtil.getLong(accountDetail,"accountNumber");
                            final String typeName = JSONParserUtil.getString(accountType,"name");
                            final String typeDescription = JSONParserUtil.getString(accountType,"description");
                            final double monthlyLimit = JSONParserUtil.getDouble(accountType,"monthlyLimit");
                            final double dailyLimit = JSONParserUtil.getDouble(accountType,"dailyLimit");
                            final String username = JSONParserUtil.getString(userDetail,"username");
                            final String firstName = JSONParserUtil.getString(userDetail,"firstName");
                            final String lastName = JSONParserUtil.getString(userDetail,"lastName");
                            final String contactNo = JSONParserUtil.getString(userDetail,"contactNo");
                            final String email = JSONParserUtil.getString(userDetail,"email");
                            final String image = JSONParserUtil.getString(userDetail,"image");
                            resp.setBalance(balance);
                            resp.setSessionId(sessionId);
                            resp.setAccountNumber(accountNumber);
                            resp.setAccountType(typeName);
                            resp.setDailyTransaction(dailyLimit);
                            resp.setMonthlyTransaction(monthlyLimit);
                            resp.setFirstName(firstName);
                            resp.setLastName(lastName);
                            resp.setEmail(email);
                            resp.setUsername(username);
                            resp.setContactNo(contactNo);
                            resp.setImage(image);

                        } else {
                            resp.setSuccess(false);
                        }


                        resp.setCode(code);
                        resp.setStatus(status);
                        resp.setMessage(message);
                        resp.setResponse(strResponse);

                    } else {
                        resp.setSuccess(false);
                        resp.setCode("F00");
                        resp.setMessage("Service unavailable");
                        resp.setStatus("FAILED");
                        resp.setResponse(APIUtils.getFailedJSON().toString());
                    }
                } else {
                    resp.setSuccess(false);
                    resp.setCode("F00");
                    resp.setMessage("Service unavailable");
                    resp.setStatus("FAILED");
                    resp.setResponse(APIUtils.getFailedJSON().toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setSuccess(false);
            resp.setCode("F00");
            resp.setMessage("Service unavailable");
            resp.setStatus("FAILED");
            resp.setResponse(APIUtils.getFailedJSON().toString());
        }
        return resp;
    }

    @Override
    public AllTransactionResponse getAllTransaction(AllTransactionRequest request) {
        AllTransactionResponse resp = new AllTransactionResponse();
        try {

            JSONObject payload = new JSONObject();
            logger.info("All Transactions");

            logger.info("Session in Api " + request.getSessionId());
            payload.put("sessionId", request.getSessionId());

            logger.info("Payload " + payload.toString());

            Client client = Client.create();
            WebResource webResource = client.resource(
                    UrlMetadatas.getMerchantTransactions(Version.VERSION_1, Role.MERCHANT, Device.WEBSITE, Language.ENGLISH));
            ClientResponse response = webResource.accept("application/json").type("application/json")
                    .header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

            logger.info("URL " + UrlMetadatas.getMerchantTransactions(Version.VERSION_1, Role.ADMIN, Device.WEBSITE,
                    Language.ENGLISH));
            String strResponse = response.getEntity(String.class);
            if (response.getStatus() != 200) {
                logger.info("Response 1 :: " + strResponse);
                resp.setSuccess(false);
                resp.setCode("F00");
                resp.setMessage("Service unavailable");
                resp.setStatus("FAILED");
                resp.setResponse(APIUtils.getFailedJSON().toString());
            } else {

                if (strResponse != null) {
                    org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
                    if (jobj != null) {
                        System.err.println("Transaction1 :: " + jobj);
                        final String status = (String) jobj.get("status");
                        final String code = (String) jobj.get("code");
                        final String message = (String) jobj.get("message");
                        if(code.equalsIgnoreCase("S00")) {
                            final org.json.JSONArray totalTtransactions = (org.json.JSONArray) jobj
                                    .getJSONArray("details");
//                            resp.setJsonArray(totalTtransactions);
                            resp.setSuccess(true);
                        } else {
                            resp.setSuccess(false);
                        }
                        resp.setCode(code);
                        resp.setStatus(status);
                        resp.setMessage(message);
                        resp.setResponse(strResponse);

                    } else {
                        resp.setSuccess(false);
                        resp.setCode("F00");
                        resp.setMessage("Service unavailable");
                        resp.setStatus("FAILED");
                        resp.setResponse(APIUtils.getFailedJSON().toString());
                    }
                } else {
                    resp.setSuccess(false);
                    resp.setCode("F00");
                    resp.setMessage("Service unavailable");
                    resp.setStatus("FAILED");
                    resp.setResponse(APIUtils.getFailedJSON().toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setSuccess(false);
            resp.setCode("F00");
            resp.setMessage("Service unavailable");
            resp.setStatus("FAILED");
            resp.setResponse(APIUtils.getFailedJSON().toString());
        }
        return resp;
    }

}
