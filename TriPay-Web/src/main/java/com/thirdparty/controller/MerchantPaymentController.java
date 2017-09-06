package com.thirdparty.controller;

import com.tripayweb.app.api.ILoadMoneyApi;
import com.tripayweb.app.api.ILogoutApi;
import com.tripayweb.app.api.ITransactionApi;
import com.tripayweb.app.api.IUserApi;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.LoadMoneyRequest;
import com.tripayweb.app.model.request.LogoutRequest;
import com.tripayweb.app.model.request.TransactionRequest;
import com.tripayweb.app.model.request.UserDetailsRequest;
import com.tripayweb.app.model.response.EBSRedirectResponse;
import com.tripayweb.app.model.response.LoadMoneyResponse;
import com.tripayweb.app.model.response.TransactionDTO;
import com.tripayweb.app.model.response.UserDetailsResponse;
import com.tripayweb.util.Authorities;
import com.tripayweb.util.JSONParserUtil;
import com.tripayweb.util.ModelMapKey;
import com.tripayweb.util.SecurityUtil;
//import com.sun.org.apache.xpath.internal.operations.Mod;
import com.thirdparty.api.IMerchantApi;
import com.thirdparty.model.*;
import com.thirdparty.model.error.*;
import com.thirdparty.model.error.VerifyError;
import com.thirdparty.validation.LoginValidation;
import com.thirdparty.validation.MerchantValidation;
import com.thirdparty.validation.RegisterValidation;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

@Controller
@RequestMapping("/ws/api")
public class MerchantPaymentController {

    private final IUserApi userApi;
    private final IMerchantApi merchantApi;
    private final MerchantValidation merchantValidation;
    private final LoginValidation loginValidation;
    private final RegisterValidation registerValidation;
    private final ILoadMoneyApi loadMoneyApi;
    private final ILogoutApi logoutApi;
    private final ITransactionApi transactionApi;
    public MerchantPaymentController(IUserApi userApi,IMerchantApi merchantApi, MerchantValidation merchantValidation,LoginValidation loginValidation,RegisterValidation registerValidation,ILoadMoneyApi loadMoneyApi,ILogoutApi logoutApi,ITransactionApi transactionApi){
        this.userApi = userApi;
        this.merchantApi = merchantApi;
        this.merchantValidation = merchantValidation;
        this.loginValidation = loginValidation;
        this.registerValidation = registerValidation;
        this.loadMoneyApi = loadMoneyApi;
        this.logoutApi = logoutApi;
        this.transactionApi = transactionApi;
    }

    @RequestMapping(value={"/","/getLoginPage"},method=RequestMethod.GET)
    public String getLoginPageGET(Model model){
        model.addAttribute("login",new LoginDTO());
        return "ThirdParty/Login";
    }
//
//    @RequestMapping(value={"/{page}"},method=RequestMethod.GET)
//    public String getPage(@PathVariable(value = "page") String page,Model model){
//        PaymentDTO paymentDTO = new PaymentDTO();
//        LoginDTO loginDTO = new LoginDTO();
//        RegisterDTO registerDTO = new RegisterDTO();
//        VerifyDTO verifyDTO = new VerifyDTO();
//        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
//        model.addAttribute("changePassword",changePasswordDTO);
//        model.addAttribute("verifyOTP",verifyDTO);
//        model.addAttribute("signUp",registerDTO);
//        model.addAttribute("pgRequest",paymentDTO);
//        model.addAttribute("login",loginDTO);
//        return "ThirdParty/"+page;
//    }

    @RequestMapping(value="/getErrorPage",method=RequestMethod.GET)
    public String getErrorPageGET(){
        return "ThirdParty/Error";
    }
    @RequestMapping(value="/startTest",method=RequestMethod.GET)
    public String getTestGET(){

        return "ThirdParty/Test";
    }

    @RequestMapping(value={"/auth","/validate"},method=RequestMethod.POST)
    public String authenticateMerchant(@ModelAttribute AuthenticateDTO dto,HttpServletRequest request,HttpServletResponse response,HttpSession session){
        dto.setIpAddress(request.getRemoteAddr());
        System.err.println(dto.getIpAddress());
        System.err.println("IP  : " +dto.getIpAddress());
        CommonError error = merchantValidation.checkError(dto);
        session.setAttribute("transactionID",dto.getTransactionID());
        if(error.isValid()){
               AuthenticationResponse result = merchantApi.authenticateMerchant(dto);
                if(result.getCode().equals("S00")){
                    session.setAttribute("image",result.getImage());
                    session.setAttribute("successURL",result.getSuccessURL());
                    session.setAttribute("failureURL",result.getFailureURL());
                    session.setAttribute("id",result.getMerchantId());
                    session.setAttribute("amount",dto.getAmount());
                    session.setAttribute("extra",dto.getAdditionalInfo());
                    return "redirect:/ws/api/Login";
                }else {
                    return "redirect:/ws/api/FailedAuthentication";
                }
        }else {
            return "redirect:/ws/api/FailedAuthentication";
        }

    }



    @RequestMapping(value={"/mauth","/mvalidate"},method=RequestMethod.POST)
    ResponseEntity<AuthenticationResponse> authenticateMerchantAPI(@ModelAttribute AuthenticateDTO dto,HttpServletRequest request,HttpServletResponse response,HttpSession session){
        ResponseDTO result = new ResponseDTO();
        CommonError error = merchantValidation.checkError(dto);
        AuthenticationResponse apiResult = new AuthenticationResponse();
        if(error.isValid()) {
            apiResult = merchantApi.authenticateMerchant(dto);
        }
        return new ResponseEntity<AuthenticationResponse>(apiResult,HttpStatus.OK);
    }


    @RequestMapping(value="/FailedAuthentication",method=RequestMethod.GET)
    public String failedAuthentication(Model model){
        return "ThirdParty/Error";
    }
    @RequestMapping(value = {"/Login"},method=RequestMethod.GET)
    public String getLoginPage(@RequestParam(value=ModelMapKey.MESSAGE,required = false) String msg,ModelMap model){
        LoginDTO dto = new LoginDTO();
        if(msg != null){
            model.addAttribute(ModelMapKey.MESSAGE,msg);
        }
        model.addAttribute("login",dto);
        return "ThirdParty/Login";
    }

    @RequestMapping(value={"/Login"},method= RequestMethod.POST)
    public String validateLoginRequest(@ModelAttribute("login") LoginDTO dto, HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model){
        LoginError error  =  loginValidation.checkError(dto);
        double netAmount  =  Double.parseDouble((String)session.getAttribute("amount"));
        ResponseDTO result = null;
        if(error.isValid()){
            String token = (String)session.getAttribute("token");
            dto.setToken(token);
            result = merchantApi.authenticateUser(dto);
            if(result.getCode().equalsIgnoreCase("S00")){
                PaymentDTO paymentDTO = new PaymentDTO();
                session.setAttribute("sessionId",result.getSessionId());
                paymentDTO.setNetAmount(netAmount);
                paymentDTO.setWalletAmount(result.getAmount());
                paymentDTO.setUseWallet(true);
                if(netAmount <= result.getAmount()){
                    paymentDTO.setAmountToLoad(0);
                }else{
                    paymentDTO.setAmountToLoad(Math.ceil(netAmount - result.getAmount()));
                }
                model.addAttribute("balance",result.getAmount());
                model.addAttribute("username",result.getDetails());
                model.addAttribute("fullName",result.getInfo());
                model.addAttribute("pgRequest",paymentDTO);
                return "ThirdParty/Dashboard";
            }else{
                model.addAttribute(ModelMapKey.MESSAGE,result.getMessage());
                return "ThirdParty/Login";
            }
        }else {
            model.addAttribute(ModelMapKey.ERROR,error);
            return "ThirdParty/Login";
        }
    }

    @RequestMapping(value = {"/Process"},method=RequestMethod.POST)
    public String processPaymentPage(@ModelAttribute("pgRequest") PaymentDTO dto, HttpSession session,Model model){
        double amountToLoad = dto.getAmountToLoad();
        String message = null;
        boolean valid = false;
        String sessionId = (String)session.getAttribute("sessionId");
        if(sessionId != null) {
            System.err.println("session id is not null");
            UserDetailsRequest userDetailsRequest = new UserDetailsRequest();
            userDetailsRequest.setSessionId(sessionId);
            UserDetailsResponse userDetailsResponse = userApi.getUserDetails(userDetailsRequest,Role.USER);
            if(userDetailsResponse != null) {
                System.err.println("user details is not null");
                TransactionRequest transactionDTO = new TransactionRequest();
                transactionDTO.setAmount(""+dto.getNetAmount());
                transactionDTO.setSessionId(sessionId);
                transactionDTO.setSenderUsername(userDetailsResponse.getUsername());
                transactionDTO.setTransactionRefNo("C");
                ResponseDTO result = transactionApi.validateTransaction(transactionDTO);
                if(result != null){
                    System.err.println("result is not null");
                        if(result.getCode().equalsIgnoreCase("S00")){
                            System.err.println("response is S00");
                            try{
                                JSONObject json = new JSONObject(result.getDetails());
                                valid = JSONParserUtil.getBoolean(json,"valid");
                                if(!valid){
                                    message = JSONParserUtil.getString(json,"message");
                                }
                            }catch(JSONException ex){
                                ex.printStackTrace();
                            }
                        }
                }
                String authority = userDetailsResponse.getAuthority();
                if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
                    if (valid) {
                        if (amountToLoad > 0) {
                            LoadMoneyRequest loadMoneyRequest = new LoadMoneyRequest();
                            loadMoneyRequest.setAmount("" + amountToLoad);
                            loadMoneyRequest.setSessionId(sessionId);
                            loadMoneyRequest.setReturnUrl("https://www.vpayqwik.com/ws/api/LoadMoney/Redirect");
                            loadMoneyRequest.setAddress(userDetailsResponse.getAddress());
                            loadMoneyRequest.setEmail(userDetailsResponse.getEmail());
                            loadMoneyRequest.setPhone(userDetailsResponse.getContactNo());
                            loadMoneyRequest.setName(userDetailsResponse.getFirstName() + " " + userDetailsResponse.getLastName());
                            LoadMoneyResponse loadMoneyResponse = loadMoneyApi.loadMoneyRequest(loadMoneyRequest);
                            model.addAttribute("loadmoney", loadMoneyResponse);
                            return "User/LoadMoney/Pay";
                        } else {
                            return "redirect:/ws/api/Process/Payment";
                        }
                    }
                    model.addAttribute(ModelMapKey.MESSAGE,message);
                }
            }
            //set again payment dto
            PaymentDTO paymentDTO = dto;
            model.addAttribute("balance",dto.getWalletAmount());
            model.addAttribute("username",userDetailsResponse.getContactNo());
            model.addAttribute("fullName",userDetailsResponse.getFirstName() + " "+userDetailsResponse.getLastName());
            model.addAttribute("pgRequest",paymentDTO);
            System.err.println("new dto initialized");
            return "ThirdParty/Dashboard";
        }
        return "redirect:/ws/api/getLoginPage";
    }

    @RequestMapping(value = "/LoadMoney/Redirect", method = RequestMethod.POST)
    public String redirectLoadMoney(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                    Model model) {
        EBSRedirectResponse redirectResponse = loadMoneyApi.processRedirectResponse(request);
        if (redirectResponse.isSuccess() || redirectResponse.getResponseCode().equals("0")) {
            return "ThirdParty/Loading";
        } else {
            model.addAttribute("msg", "Transaction failed, Please try again later.");
        }
        model.addAttribute("loadmoneyResponse", redirectResponse);
        return "User/Loading";
    }

    @RequestMapping(value = "/success", method = RequestMethod.POST)
    public String successPayment(PaymentResponse dto,HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                    Model model) {

        System.err.println("dto is ::"+dto);
        int success = 1;
        if(success == 1){
            model.addAttribute(ModelMapKey.MESSAGE,dto.getDescription());
        }else {
            model.addAttribute(ModelMapKey.MESSAGE,"Your payment is failed,Try again later");
        }
        return "ThirdParty/Success";
    }


    @RequestMapping(value = {"/Process/Payment"},method=RequestMethod.GET)
    public String processPayment(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model){
        String sessionId = (String)session.getAttribute("sessionId");
        String token = (String)session.getAttribute("token");
        double netAmount  =  Double.parseDouble((String)session.getAttribute("amount"));
        UserDetailsRequest userDetailsRequest = new UserDetailsRequest();
        userDetailsRequest.setSessionId(sessionId);
        UserDetailsResponse userDetailsResponse = userApi.getUserDetails(userDetailsRequest,Role.USER);
        String authority = userDetailsResponse.getAuthority();
        if(authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
                PaymentDTO paymentDTO = new PaymentDTO();
                paymentDTO.setSessionId(sessionId);
                paymentDTO.setToken(token);
                paymentDTO.setNetAmount(netAmount);
                paymentDTO.setId((Long)session.getAttribute("id"));
                paymentDTO.setTransactionID((String)session.getAttribute("transactionID"));
                ResponseDTO result = merchantApi.processPayment(paymentDTO);
                if(result.getCode().equalsIgnoreCase("S00")) {
                    model.addAttribute("url", (String) session.getAttribute("successURL"));
                    model.addAttribute("orderID", paymentDTO.getTransactionID());
                    model.addAttribute("success","1");
                    model.addAttribute("description","Payment Successfully Processed ...");
                    model.addAttribute("additionalInfo",session.getAttribute("extra"));
            }else{
                model.addAttribute("url", (String) session.getAttribute("failureURL"));
            }
                model.addAttribute(ModelMapKey.MESSAGE,result.getDetails());
                LogoutRequest logoutRequest = new LogoutRequest();
                logoutRequest.setSessionId(sessionId);
                logoutApi.logout(logoutRequest, Role.USER);
                return "ThirdParty/Receipt";
            } else {
                return "redirect:/ws/api/Process/Payment";
            }
        }


    @RequestMapping(value = {"/processMpay"},method=RequestMethod.POST)
    ResponseEntity<ResponseDTO> processPayment(@ModelAttribute PaymentDTO dto,HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model){
        ResponseDTO result = new ResponseDTO();
        UserDetailsRequest userDetailsRequest = new UserDetailsRequest();
        userDetailsRequest.setSessionId(dto.getSessionId());
        UserDetailsResponse userDetailsResponse = userApi.getUserDetails(userDetailsRequest,Role.USER);
        String authority = userDetailsResponse.getAuthority();
        if(authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
            result = merchantApi.processPayment(dto);
        }
        return new ResponseEntity<ResponseDTO>(result,HttpStatus.OK);
    }


    @RequestMapping(value = {"/Register","/SignUp"},method=RequestMethod.GET)
    public String getRegistrationPage(Model model){
        RegisterDTO dto = new RegisterDTO();
        model.addAttribute("signUp",dto);
        return "ThirdParty/SignUp";
    }

    @RequestMapping(value={"/Register","/SignUp"},method= RequestMethod.POST)
    public String validateRegistrationRequest(@ModelAttribute("signUp") RegisterDTO dto, HttpServletRequest request, HttpServletResponse response,ModelMap model){
        System.err.print(dto.toString());
        RegisterError error = registerValidation.checkError(dto);
        if(error.isValid()){
            ResponseDTO result = merchantApi.registerUser(dto);
            String code = result.getCode();
            if(code.equalsIgnoreCase("S00")){
                model.addAttribute(ModelMapKey.MESSAGE,result.getMessage());
                model.addAttribute("contactNo",dto.getContactNo());
                VerifyDTO verify = new VerifyDTO();
                verify.setContactNo(dto.getContactNo());
                model.addAttribute("verifyOTP",verify);
                return "ThirdParty/VerifyMobile";
            }else if(code.equalsIgnoreCase("F04")){
                error.setContactNo(result.getMessage());
                error.setEmail(result.getDetails());
            }
        }
        model.addAttribute("error",error);
        return "ThirdParty/SignUp";
    }


    @RequestMapping(value="/VerifyMobile",method=RequestMethod.GET)
    public String getOTPPage(ModelMap model){
        VerifyDTO dto = new VerifyDTO();
        dto.setContactNo((String)model.get("contactNo"));
        model.addAttribute("verifyOTP",dto);
        return "ThirdParty/VerifyMobile";
    }

    @RequestMapping(value="/VerifyMobile",method=RequestMethod.POST)
    public String processOTPVerification(@ModelAttribute("verifyOTP") VerifyDTO dto,ModelMap model){
        System.err.println(dto.toString());
        VerifyError error = registerValidation.checkError(dto);
        if(error.isValid()){
            ResponseDTO result = merchantApi.verifyOTP(dto);
            String code = result.getCode();
            String message = result.getMessage();
            if(code.equalsIgnoreCase("S00")) {
                model.addAttribute(ModelMapKey.MESSAGE,message);
                return "redirect:/ws/api/Login";
            }else if(code.equalsIgnoreCase("F04")){
                model.addAttribute(ModelMapKey.MESSAGE,message);
            }
        }else {
            model.addAttribute(ModelMapKey.ERROR,error);
        }
        return "ThirdParty/VerifyMobile";
    }

    @RequestMapping(value="/ForgotPassword",method=RequestMethod.GET)
    public String getForgotPasswordPage(ModelMap model){
        ForgotPasswordDTO dto = new ForgotPasswordDTO();
        model.addAttribute("forgotPassword",dto);
        return "ThirdParty/ForgotPassword";
    }

    @RequestMapping(value="/ForgotPassword",method=RequestMethod.POST)
    public String processForgotPassword(@ModelAttribute("forgotPassword") ForgotPasswordDTO dto, ModelMap model){
        ForgotPasswordError error = registerValidation.checkError(dto);
        if(error.isValid()){
            ResponseDTO result = merchantApi.forgotPassword(dto);
            if(result.getCode().equalsIgnoreCase("S00")){
                model.addAttribute(ModelMapKey.MESSAGE,result.getMessage());
                VerifyDTO verifyDTO = new VerifyDTO();
                verifyDTO.setContactNo(dto.getUsername());
                model.addAttribute("forgotPasswordOTP",verifyDTO);
                return "ThirdParty/VerifyOTP";
            }else  {
                error.setUsername("Contact No. not exists");
            }
        }
        model.addAttribute("error",error);
        return "ThirdParty/ForgotPassword";
    }

    @RequestMapping(value="/VerifyOTP",method=RequestMethod.POST)
    public String processForgotPasswordOTP(@ModelAttribute("forgotPasswordOTP") VerifyDTO dto, ModelMap model){
        VerifyError error = registerValidation.checkError(dto);
        if(error.isValid()){
            ResponseDTO result = merchantApi.validateOTP(dto);
            if(result.getCode().equalsIgnoreCase("S00")){
               model.addAttribute(ModelMapKey.MESSAGE,result.getMessage());
                ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
                changePasswordDTO.setUsername(dto.getContactNo());
                changePasswordDTO.setOtp(dto.getOtp());
                model.addAttribute("changePassword",changePasswordDTO);
                return "ThirdParty/ChangePassword";
            }else {
                error.setOtp(result.getMessage());
            }
        }
        model.addAttribute("error",error);
        return "ThirdParty/VerifyOTP";
    }

    @RequestMapping(value="/ChangePassword",method=RequestMethod.POST)
    public String processChangePassword(@ModelAttribute("changePassword") ChangePasswordDTO dto,ModelMap model){
        ChangePasswordError error = registerValidation.checkError(dto);
        if(error.isValid()){
                ResponseDTO result = merchantApi.changePassword(dto);
                if(result.getCode().equalsIgnoreCase("S00")){
                    model.addAttribute(ModelMapKey.MESSAGE,result.getMessage());
                    return "redirect:/ws/api/Login";
                }
        }
        model.addAttribute("error",error);
        return "ThirdParty/ChangePassword";
    }


    @RequestMapping(value="/Status",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<StatusResponse> checkTransactionStatus(@RequestBody StatusCheckDTO dto, ModelMap model){
        StatusResponse statusResponse = new StatusResponse();
        CommonError error = merchantValidation.checkError(dto);
        if(error.isValid()){
            statusResponse = merchantApi.checkStatus(dto);
        }else {
            statusResponse.setCode("F04");
            statusResponse.setMessage(error.getMessage());
        }
        return new ResponseEntity<StatusResponse>(statusResponse, HttpStatus.OK);
    }

}
