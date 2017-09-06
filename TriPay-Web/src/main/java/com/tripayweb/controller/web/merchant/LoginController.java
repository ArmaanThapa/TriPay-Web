package com.tripayweb.controller.web.merchant;

import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.api.ILogoutApi;
import com.tripayweb.app.api.IMerchantApi;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.AllTransactionRequest;
import com.tripayweb.app.model.request.LoginRequest;
import com.tripayweb.app.model.request.LogoutRequest;
import com.tripayweb.app.model.response.AllTransactionResponse;
import com.tripayweb.app.model.response.LoginResponse;
import com.tripayweb.app.model.response.LogoutResponse;
import com.tripayweb.app.model.response.UserDetailsResponse;
import com.tripayweb.model.error.LoginError;
import com.tripayweb.util.Authorities;
import com.tripayweb.util.ModelMapKey;
import com.tripayweb.validation.LoginValidation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Merchant")
public class LoginController {
    private final IMerchantApi merchantApi;
    private final ILogoutApi logoutApi;
    private final LoginValidation loginValidation;
    private final IAuthenticationApi authenticationApi;
    public LoginController(IMerchantApi merchantApi, ILogoutApi logoutApi,LoginValidation loginValidation,IAuthenticationApi authenticationApi) {
        this.merchantApi = merchantApi;
        this.logoutApi = logoutApi;
        this.loginValidation = loginValidation;
        this.authenticationApi = authenticationApi;
    }

    @RequestMapping(value="/Home",method= RequestMethod.GET)
    public String getMerchantLoginPage(@RequestParam(value="msg",required=false) String message, HttpSession session, Model model){
        LoginRequest dto  = new LoginRequest();
        model.addAttribute("login",dto);
        String sessionId = (String)session.getAttribute("msession");

        if (message != null) {
            model.addAttribute(ModelMapKey.MESSAGE, message);
        }
        if(sessionId != null) {
            UserDetailsResponse response = authenticationApi.getUserDetailsFromSession(sessionId);
            if(response != null) {
                String authority = response.getAuthority();
                if(authority != null) {
                    if (authority.contains(Authorities.MERCHANT) && authority.contains(Authorities.AUTHENTICATED)) {
                        model.addAttribute("user",response);
                        return "Merchant/Home";
                    }
                }
            }
        }
        return "Merchant/Login";
    }

    @RequestMapping(value="/Home",method= RequestMethod.POST)
    public String getMerchantLoginPage(@ModelAttribute("login") LoginRequest dto, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model){
        dto.setIpAddress(request.getRemoteAddr());
        LoginError error = loginValidation.checkMerchantLogin(dto);
        if(error.isValid()){
            LoginResponse loginResponse = merchantApi.login(dto);
            if(loginResponse.isSuccess()){
                        model.addAttribute("user",loginResponse);
                        session.setAttribute("msession",loginResponse.getSessionId());
                        return "Merchant/Home";
            }
            model.addAttribute(ModelMapKey.MESSAGE,loginResponse.getMessage());
        }
        model.addAttribute("error",error);
        return "Merchant/Login";
    }

  //  public ResponseEntity<AllTransactionResponse> getTotalBalance()
    @RequestMapping(value="/Logout",method= RequestMethod.GET)
    public String logoutMerchant(HttpServletRequest request,HttpServletResponse response,HttpSession session,RedirectAttributes model){
        String sessionId = (String)session.getAttribute("msession");
        if(sessionId != null){
            String authority = authenticationApi.getAuthorityFromSession(sessionId,Role.USER);
            if(authority != null) {
                if (authority.contains(Authorities.MERCHANT) && authority.contains(Authorities.AUTHENTICATED)) {
                    LogoutRequest logoutRequest = new LogoutRequest();
                    logoutRequest.setSessionId(sessionId);
                    LogoutResponse logoutResponse = logoutApi.logout(logoutRequest, Role.MERCHANT);
                    if (logoutResponse.isSuccess()) {
                        model.addFlashAttribute(ModelMapKey.MESSAGE, "You've been successfully logged out");
                        session.invalidate();
                        return "redirect:/Merchant/Home";
                    }
                }
            }
        }
        return "Merchant/Login";
    }


}
