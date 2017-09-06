package com.tripayweb.controller.mobile.api;

import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.api.*;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.*;
import com.tripayweb.app.model.response.*;
import com.tripayweb.model.error.OneClickPayError;
import com.tripayweb.model.web.MobileTopupDTO;
import com.tripayweb.util.Authorities;
import com.tripayweb.util.JSONParserUtil;
import com.tripayweb.validation.OneClickPayqwikValidation;
import com.thirdparty.model.ResponseDTO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/Api/{version}/{role}/{device}/{language}")
public class TransactionAPIController {

    private IAuthenticationApi authenticationApi;
    private ITransactionApi transactionApi;
    private final ISendMoneyApi sendMoneyApi;
    private final ILoadMoneyApi loadMoneyApi;
    private final ITopupApi topupApi;
    private final IBillPaymentApi billPaymentApi;


    public TransactionAPIController(IAuthenticationApi authenticationApi, ITransactionApi transactionApi, ISendMoneyApi sendMoneyApi, ILoadMoneyApi loadMoneyApi, ITopupApi topupApi, IBillPaymentApi billPaymentApi) {
        this.authenticationApi = authenticationApi;
        this.transactionApi = transactionApi;
        this.sendMoneyApi = sendMoneyApi;
        this.loadMoneyApi = loadMoneyApi;
        this.topupApi = topupApi;
        this.billPaymentApi = billPaymentApi;
    }

    @RequestMapping(value = {"/getAllBanks", "/listBanks"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDTO> listAllBanks(@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
                                             @PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
                                             HttpServletRequest request, HttpServletResponse response) {
        ResponseDTO jsonResponse = transactionApi.getAllBanks();
        System.out.println(jsonResponse.getCode() + " " + jsonResponse.getDetails() + " " + jsonResponse.getMessage());
        return new ResponseEntity<ResponseDTO>(jsonResponse, HttpStatus.OK);
    }

    @RequestMapping(value = {"/getIFSC/{bankCode}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDTO> listIFSC(@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
                                         @PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
                                         @PathVariable(value = "bankCode") String bankCode, HttpServletRequest request, HttpServletResponse response) {
        ResponseDTO jsonResponse = null;
        if (bankCode != null) {
            jsonResponse = transactionApi.getIFSCByBank(bankCode);
            System.out.println(jsonResponse.getCode() + " " + jsonResponse.getDetails() + " " + jsonResponse.getMessage());
            return new ResponseEntity<ResponseDTO>(jsonResponse, HttpStatus.OK);
        } else {
            jsonResponse = new ResponseDTO();
            jsonResponse.setSuccess(false);
            jsonResponse.setCode("F04");
            jsonResponse.setMessage("Please enter bankCode");
            return new ResponseEntity<ResponseDTO>(jsonResponse, HttpStatus.OK);
        }
    }


    @RequestMapping(value = {"/validateTransactionRequest", "/checkTransactionLimit", "/isValidTransaction"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDTO> validateTransactionRequest(@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
                                                           @PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
                                                           @ModelAttribute TransactionRequest dto, HttpServletRequest request, HttpServletResponse response) {
        ResponseDTO jsonResponse = new ResponseDTO();
        dto.setTransactionRefNo("C");
        jsonResponse = transactionApi.validateTransaction(dto);
        System.out.println(jsonResponse.getCode() + " " + jsonResponse.getDetails() + " " + jsonResponse.getMessage());
        return new ResponseEntity<ResponseDTO>(jsonResponse, HttpStatus.OK);
    }

    @RequestMapping(value = {"/OneClickPay"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> validateOneClickPay(@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
                                          @PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
                                          @RequestBody OnePayRequest dto, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        ResponseDTO jsonResponse = new ResponseDTO();
        String sessionId = dto.getSessionId();
        // String amount=dto.getAmount();
        System.out.println(dto.getAmount());
            String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
            int choice = 0;
            if (authority != null) {
                if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
                    OnePayResponse resp = transactionApi.getOnePayResponse(dto);
                    String serviceCode = resp.getServiceCode();
                    String amount = dto.getAmount();
                    if (amount != null) {
                        double parsedAmount = Double.parseDouble(amount);
                        if (parsedAmount >= 10 && parsedAmount <= 10000) {
                            JSONObject json = new JSONObject(resp.getJson());
                            if (serviceCode != null) {
                                choice = transactionApi.getChoiceByServiceCode(serviceCode);
                                if (choice == 1) {
                                    ResponseDTO result = new ResponseDTO();

                                    result.setSuccess(false);
                                    result.setCode("F04");
                                    result.setMessage("Service Not Yet Active");
                                    return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
                                } else if (choice == 2) {
                                    SendMoneyMobileRequest sendMoneyMobileRequest = new SendMoneyMobileRequest();
                                    sendMoneyMobileRequest.setSessionId(sessionId);
                                    sendMoneyMobileRequest.setAmount(dto.getAmount());
                                    sendMoneyMobileRequest.setMobileNumber(JSONParserUtil.getString(json, "mobileNumber"));
                                    sendMoneyMobileRequest.setMessage(JSONParserUtil.getString(json, "message"));
                                    SendMoneyMobileResponse sendMoneyMobileResponse = sendMoneyApi.sendMoneyMobileRequest(sendMoneyMobileRequest);
                                    return new ResponseEntity<SendMoneyMobileResponse>(sendMoneyMobileResponse, HttpStatus.OK);
                                } else if (choice == 3) {
                                    PrepaidTopupRequest topupRequest = new PrepaidTopupRequest();
                                    topupRequest.setSessionId(sessionId);
                                    topupRequest.setAmount(dto.getAmount());
                                    topupRequest.setMobileNo(JSONParserUtil.getString(json, "mobileNo"));
                                    topupRequest.setTopupType("Prepaid");
                                    topupRequest.setServiceProvider(JSONParserUtil.getString(json, "serviceProvider"));
                                    topupRequest.setArea(JSONParserUtil.getString(json, "area"));
                                    PrepaidTopupResponse prepaidTopupResponse = topupApi.prePaid(topupRequest);
                                    return new ResponseEntity<PrepaidTopupResponse>(prepaidTopupResponse, HttpStatus.OK);
                                } else if (choice == 4) {
                                    PostpaidTopupRequest postpaidRequest = new PostpaidTopupRequest();
                                    postpaidRequest.setSessionId(sessionId);
                                    postpaidRequest.setAmount(dto.getAmount());
                                    postpaidRequest.setMobileNo(JSONParserUtil.getString(json, "mobileNo"));
                                    postpaidRequest.setTopupType("Postpaid");
                                    postpaidRequest.setServiceProvider(JSONParserUtil.getString(json, "serviceProvider"));
                                    postpaidRequest.setArea(JSONParserUtil.getString(json, "area"));
                                    PostpaidTopupResponse postpaidTopupResponse = topupApi.postPaid(postpaidRequest);
                                    return new ResponseEntity<PostpaidTopupResponse>(postpaidTopupResponse, HttpStatus.OK);
                                } else if (choice == 5) {
                                    DTHBillPaymentRequest dthBillPaymentRequest = new DTHBillPaymentRequest();
                                    dthBillPaymentRequest.setServiceProvider(JSONParserUtil.getString(json, "serviceProvider"));
                                    dthBillPaymentRequest.setAmount(dto.getAmount());
                                    dthBillPaymentRequest.setSessionId(sessionId);
                                    dthBillPaymentRequest.setDthNo(JSONParserUtil.getString(json, "dthNo"));
                                    DTHBillPaymentResponse dthBillPaymentResponse = billPaymentApi.dthBill(dthBillPaymentRequest);
                                    return new ResponseEntity<DTHBillPaymentResponse>(dthBillPaymentResponse, HttpStatus.OK);

                                } else if (choice == 6) {
                                    LandlineBillPaymentRequest landlineBillPaymentRequest = new LandlineBillPaymentRequest();
                                    landlineBillPaymentRequest.setSessionId(sessionId);
                                    landlineBillPaymentRequest.setServiceProvider(JSONParserUtil.getString(json, "serviceProvider"));
                                    landlineBillPaymentRequest.setStdCode(JSONParserUtil.getString(json, "stdCode"));
                                    landlineBillPaymentRequest.setLandlineNumber(JSONParserUtil.getString(json, "landlineNumber"));
                                    landlineBillPaymentRequest.setAccountNumber(JSONParserUtil.getString(json, "accountNumber"));
                                    landlineBillPaymentRequest.setAmount(dto.getAmount());
                                    LandlineBillPaymentResponse landlineBillPaymentResponse = billPaymentApi.landline(landlineBillPaymentRequest);
                                    return new ResponseEntity<LandlineBillPaymentResponse>(landlineBillPaymentResponse, HttpStatus.OK);
                                } else if (choice == 7) {
                                    ElectricityBillPaymentRequest electricityBillPaymentRequest = new ElectricityBillPaymentRequest();
                                    electricityBillPaymentRequest.setSessionId(sessionId);
                                    electricityBillPaymentRequest.setAmount(dto.getAmount());
                                    electricityBillPaymentRequest.setServiceProvider(JSONParserUtil.getString(json, "serviceProvider"));
                                    electricityBillPaymentRequest.setAccountNumber(JSONParserUtil.getString(json, "accountNumber"));
                                    electricityBillPaymentRequest.setCycleNumber(JSONParserUtil.getString(json, "cycleNumber"));
                                    ElectricityBillPaymentResponse electricityBillPaymentResponse = billPaymentApi.electricBill(electricityBillPaymentRequest);
                                    return new ResponseEntity<ElectricityBillPaymentResponse>(electricityBillPaymentResponse, HttpStatus.OK);
                                } else if (choice == 8) {
                                    GasBillPaymentRequest gasBillPaymentRequest = new GasBillPaymentRequest();
                                    gasBillPaymentRequest.setSessionId(sessionId);
                                    gasBillPaymentRequest.setAccountNumber(JSONParserUtil.getString(json, "accountNumber"));
                                    gasBillPaymentRequest.setServiceProvider(JSONParserUtil.getString(json, "serviceProvider"));
                                    gasBillPaymentRequest.setAmount(dto.getAmount());
                                    GasBillPaymentResponse gasBillPaymentResponse = billPaymentApi.gasBill(gasBillPaymentRequest);
                                    return new ResponseEntity<GasBillPaymentRequest>(gasBillPaymentRequest, HttpStatus.OK);

                                } else if (choice == 9) {
                                    InsuranceBillPaymentRequest insuranceBillPaymentRequest = new InsuranceBillPaymentRequest();
                                    insuranceBillPaymentRequest.setSessionId(sessionId);
                                    insuranceBillPaymentRequest.setAmount(dto.getAmount());
                                    insuranceBillPaymentRequest.setServiceProvider(JSONParserUtil.getString(json, "serviceProvider"));
                                    insuranceBillPaymentRequest.setPolicyDate(JSONParserUtil.getString(json, "policyDate"));
                                    insuranceBillPaymentRequest.setPolicyNumber(JSONParserUtil.getString(json, "policyNumber"));
                                    InsuranceBillPaymentResponse insuranceBillPaymentResponse = billPaymentApi.insuranceBill(insuranceBillPaymentRequest);
                                    return new ResponseEntity<InsuranceBillPaymentResponse>(insuranceBillPaymentResponse, HttpStatus.OK);
                                }

                            }
                        }

                    }
                }
                jsonResponse.setCode("F00");
                jsonResponse.setDetails("entered amount is not valid");
                try {
                    jsonResponse.setAmount(Double.parseDouble(dto.getAmount()));
                } catch (Exception e) {
                    jsonResponse.setMessage("please enter a valid number");
                    jsonResponse.setSessionId(sessionId);
                    jsonResponse.setMessage("invalid input please check and try again");
                    jsonResponse.setSuccess(false);
                    jsonResponse.setValid(false);
                    jsonResponse.setStatus("failed");
                    return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
                }
                jsonResponse.setSessionId(sessionId);
                jsonResponse.setMessage("invalid input please check and try again");
                jsonResponse.setSuccess(false);
                jsonResponse.setValid(false);
                jsonResponse.setStatus("failed");


                return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
            }

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }
}
