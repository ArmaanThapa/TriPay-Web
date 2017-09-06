package com.tripayweb.app.api.impl;

import com.tripayweb.app.api.ITransactionApi;
import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Language;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.Version;
import com.tripayweb.app.model.request.OnePayRequest;
import com.tripayweb.app.model.request.TransactionRequest;
import com.tripayweb.app.model.response.OnePayResponse;
import com.tripayweb.app.model.response.TransactionDTO;
import com.tripayweb.app.utils.SecurityUtils;
import com.tripayweb.model.web.Status;
import com.tripayweb.util.JSONParserUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.thirdparty.model.ResponseDTO;
import org.json.JSONObject;

public class TransactionApi implements ITransactionApi{
	
    @Override
    public ResponseDTO validateTransaction(TransactionRequest dto) {
        ResponseDTO jsonResponse = new ResponseDTO();
        try{
        	System.err.println(dto.getTransactionRefNo());
        	System.out.println("inside the Api of prepaid TopUp");
            Client client = Client.create();
            WebResource webResource = client.resource(UrlMetadatas.transactionCheck(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
            ClientResponse response = webResource.accept("application/json").type("application/json").header("hash", SecurityUtils.getHash(dto.toJSON().toString())).post(ClientResponse.class,dto.toJSON());
            String strResponse  = response.getEntity(String.class);
            System.err.println("STRING RESPONSE IS ::"+strResponse);
            if(response.getStatus() == 200){
                JSONObject jsonObject = new JSONObject(strResponse);
                String code = JSONParserUtil.getString(jsonObject,"code");
                String message = JSONParserUtil.getString(jsonObject,"message");
                if(code != null){
                    System.out.println("code is not null");
                    if(code.equalsIgnoreCase("S00")) {
                        System.out.print("code is S00");
                        jsonResponse.setCode(code);
                        JSONObject error  = JSONParserUtil.getObject(jsonObject,"details");
                        if(error != null){
                            jsonResponse.setValid(JSONParserUtil.getBoolean(error,"valid"));
                            jsonResponse.setStatus(JSONParserUtil.getString(error,"message"));
                        }
                    }
                    else {
                        System.out.println("code is F00");
                        jsonResponse.setCode("F00");
                    }
                    jsonResponse.setMessage(message);
                    jsonResponse.setDetails(JSONParserUtil.getObject(jsonObject,"details").toString());
                }
            }else {
                jsonResponse.setCode("F03");
                jsonResponse.setMessage("Service Unavailable");
                jsonResponse.setDetails("RESPONSE ::"+strResponse);
            }
        }catch(Exception ex){
                jsonResponse.setCode("F03");
                jsonResponse.setMessage("Exception Occurred");
                ex.printStackTrace();
        }
        return jsonResponse;
    }

    @Override
    public ResponseDTO getAllServices() {
        ResponseDTO jsonResponse = new ResponseDTO();
        try{
            Client client = Client.create();
            WebResource webResource = client.resource(UrlMetadatas.transactionCheck(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
            ClientResponse response = webResource.accept("application/json").type("application/json").header("hash", "123456").get(ClientResponse.class);
            String strResponse  = response.getEntity(String.class);
            System.err.println("STRING RESPONSE IS ::"+strResponse);
            if(response.getStatus() == 200){
                JSONObject jsonObject = new JSONObject(strResponse);
                String code = JSONParserUtil.getString(jsonObject,"code");
                String message = JSONParserUtil.getString(jsonObject,"message");
                if(code != null){
                    System.out.println("code is not null");
                    if(code.equalsIgnoreCase("S00")) {
                        jsonResponse.setCode(code);
                        jsonResponse.setSuccess(true);
                        jsonResponse.setInfo(JSONParserUtil.getArray(jsonObject,"details"));
                    }
                    else {
                        System.out.println("code is F00");
                        jsonResponse.setCode("F00");
                    }
                    jsonResponse.setMessage(message);
                    jsonResponse.setDetails(JSONParserUtil.getObject(jsonObject,"details").toString());
                }

            }else {
                jsonResponse.setCode("F03");
                jsonResponse.setMessage("Service Unavailable");
                jsonResponse.setDetails("RESPONSE ::"+strResponse);
            }
        }catch(Exception ex){
            jsonResponse.setCode("F03");
            jsonResponse.setMessage("Exception Occurred");
            ex.printStackTrace();
        }
        return jsonResponse;
    }

    @Override
    public OnePayResponse getOnePayResponse(OnePayRequest dto) {
    OnePayResponse result = new OnePayResponse();
        try{
            org.codehaus.jettison.json.JSONObject payload = new org.codehaus.jettison.json.JSONObject();
            payload.put("sessionId",dto.getSessionId());
            payload.put("transactionRefNo",dto.getTransactionRefNo());
            Client client = Client.create();
            WebResource webResource = client.resource(UrlMetadatas.getOnePayTransactionURL(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
            ClientResponse response = webResource.accept("application/json").type("application/json").header("hash", "123456").post(ClientResponse.class,payload);
            String strResponse  = response.getEntity(String.class);
            System.err.println("STRING RESPONSE IS ::"+strResponse);
            if(response.getStatus() == 200){
                JSONObject jsonObject = new JSONObject(strResponse);
                String code = JSONParserUtil.getString(jsonObject,"code");
                String message = JSONParserUtil.getString(jsonObject,"message");
                if(code != null){
                    System.out.println("code is not null");
                    if(code.equalsIgnoreCase("S00")) {
                        result.setSuccess(true);
                        result.setJson(JSONParserUtil.getString(jsonObject,"json"));
                        result.setServiceCode(JSONParserUtil.getString(jsonObject,"serviceCode"));
                        result.settStatus(Status.valueOf(JSONParserUtil.getString(jsonObject,"tStatus")));
                        result.setServiceName(JSONParserUtil.getString(jsonObject,"serviceName"));
                    }
                    else {
                        System.out.println("code is F00");
                        result.setSuccess(false);
                    }

                }

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }


        return result;
    }

    @Override
    public int getChoiceByServiceCode(String code) {
        int choice = 0;
        switch(code){
            case "SMB":
                choice = 1;
                break;
            case "SMU":
            case "SMR":
                choice = 2;
                break;
            case "VACP":
            case "VATP":
            case "VBVP":
            case "VBGP":
            case "VIDP":
            case "VMSP":
            case "VMMP":
            case "VMTP":
            case "VRGP":
            case "VTVP":
            case "VTMP":
            case "VTCP":
            case "VTSP":
            case "VTGP":
            case "VUSP":
            case "VUGP":
            case "VVSP":
            case "VVGP":
            case "VVFP":
                choice = 3;
                break;
            case "VACC":
            case "VATC":
            case "VBGC":
            case "VIDC":
            case "VMTC":
            case "VRGC":
            case "VTDC":
            case "VVFC":
                choice = 4;
                break;
            case "VATV":
            case "VDTV":
            case "VRTV":
            case "VSTV":
            case "VTTV":
            case "VVTV":
                choice = 5;
                break;
            case "VATL":
            case "VBGL":
            case "VMDL":
            case "VRGL":
            case "VTCL":
                choice = 6;
                break;
            case "VTTE":
            case "VTPE":
            case "VNDE":
            case "VSTE":
            case "VSAE":
            case "VREE":
            case "VMPE":
            case "VDOE":
            case "VNUE":
            case "VMDE":
            case "VMME":
            case "VDRE":
            case "VJUE":
            case "VJRE":
            case "VIPE":
            case "VDNE":
            case "VDHE":
            case "VCCE":
            case "VCWE":
            case "VBYE":
            case "VBRE":
            case "VBME":
            case "VBBE":
            case "VAAE":
            case "VARE":
                choice = 7;
                break;
            case "VIPG":
            case "VMMG":
            case "VGJG":
            case "VADG":
                choice = 8;
                break;
            case "VIPI":
            case "VTAI":
            case "VILI":
            case "VBAI":
                choice = 9;
                break;
            default:
                break;
        }

        return choice;
    }

    @Override
    public ResponseDTO getAllBanks() {
        ResponseDTO result = new ResponseDTO();
        try{
            Client client = Client.create();
            WebResource webResource = client.resource(UrlMetadatas.getAllBanksURL(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
            ClientResponse response = webResource.accept("application/json").type("application/json").header("hash", "123456").get(ClientResponse.class);
            String strResponse  = response.getEntity(String.class);
            System.err.println("STRING RESPONSE IS ::"+strResponse);
            if(response.getStatus() == 200){
                JSONObject jsonObject = new JSONObject(strResponse);
                String code = JSONParserUtil.getString(jsonObject,"code");
                String message = JSONParserUtil.getString(jsonObject,"message");
                if(code != null){
                    System.out.println("code is not null");
                    if(code.equalsIgnoreCase("S00")) {
                        result.setSuccess(true);
                    } else {
                        System.out.println("code is F00");
                        result.setSuccess(false);
                    }
                    result.setCode(code);
                    result.setMessage(message);
                    result.setDetails(JSONParserUtil.getArray(jsonObject,"details").toString());
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }


        return result;
    }

    @Override
    public ResponseDTO getIFSCByBank(String bankCode) {
        ResponseDTO result = new ResponseDTO();
        try{
            Client client = Client.create();
            WebResource webResource = client.resource(UrlMetadatas.getIfscURL(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH)+bankCode);
            ClientResponse response = webResource.accept("application/json").type("application/json").header("hash", "123456").get(ClientResponse.class);
            String strResponse  = response.getEntity(String.class);
            System.err.println("STRING RESPONSE IS ::"+strResponse);
            if(response.getStatus() == 200){
                JSONObject jsonObject = new JSONObject(strResponse);
                String code = JSONParserUtil.getString(jsonObject,"code");
                String message = JSONParserUtil.getString(jsonObject,"message");
                if(code != null){
                    System.out.println("code is not null");
                    if(code.equalsIgnoreCase("S00")) {
                        result.setSuccess(true);
                    } else {
                        System.out.println("code is F00");
                        result.setSuccess(false);
                    }
                    result.setCode(code);
                    result.setMessage(message);
                    result.setDetails(JSONParserUtil.getArray(jsonObject,"details").toString());
                }

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }


        return result;
    }

	@Override
	public ResponseDTO validateMTransaction(TransactionRequest dto) {

	       ResponseDTO jsonResponse = new ResponseDTO();
	        try{
	            Client client = Client.create();
	            System.out.println("Payload : " +dto.toJSON());
	            WebResource webResource = client.resource(UrlMetadatas.merchantTransactionCheck(Version.VERSION_1, Role.MERCHANT, Device.WEBSITE, Language.ENGLISH));
	            ClientResponse response = webResource.accept("application/json").type("application/json").header("hash", SecurityUtils.getHash(dto.toJSON().toString())).post(ClientResponse.class,dto.toJSON());
	            String strResponse  = response.getEntity(String.class);
	            System.err.println("STRING RESPONSE IS ::"+strResponse);
	            if(response.getStatus() == 200){
	                JSONObject jsonObject = new JSONObject(strResponse);
	                String code = JSONParserUtil.getString(jsonObject,"code");
	                String message = JSONParserUtil.getString(jsonObject,"message");
	                if(code != null){
	                    System.out.println("code is not null");
	                    if(code.equalsIgnoreCase("S00")) {
	                        System.out.print("code is S00");
	                        jsonResponse.setCode(code);
	                        JSONObject error  = JSONParserUtil.getObject(jsonObject,"details");
	                        if(error != null){
	                            jsonResponse.setValid(JSONParserUtil.getBoolean(error,"valid"));
	                            jsonResponse.setStatus(JSONParserUtil.getString(error,"message"));
	                        }
	                    }
	                    else {
	                        System.out.println("code is F00");
	                        jsonResponse.setCode("F00");
	                    }
	                    jsonResponse.setMessage(message);
	                    jsonResponse.setDetails(JSONParserUtil.getObject(jsonObject,"details").toString());
	                }
	            }else {
	                jsonResponse.setCode("F03");
	                jsonResponse.setMessage("Service Unavailable");
	                jsonResponse.setDetails("RESPONSE ::"+strResponse);
	            }
	        }catch(Exception ex){
	                jsonResponse.setCode("F03");
	                jsonResponse.setMessage("Exception Occurred");
	                ex.printStackTrace();
	        }
	        return jsonResponse;
	    }

}
