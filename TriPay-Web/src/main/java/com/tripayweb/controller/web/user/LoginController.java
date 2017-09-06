package com.tripayweb.controller.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.recaptcha.api.IVerificationApi;
import com.google.recaptcha.model.JCaptchaRequest;
import com.google.recaptcha.model.JCaptchaResponse;
import com.tripayweb.app.api.ILoginApi;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.ChangePasswordWithOtpRequest;
import com.tripayweb.app.model.request.ForgetPasswordUserRequest;
import com.tripayweb.app.model.request.LoginRequest;
import com.tripayweb.app.model.request.ResendForgotPasswordOtpRequest;
import com.tripayweb.app.model.response.ChangePasswordWithOtpResponse;
import com.tripayweb.app.model.response.ForgetPasswordUserResponse;
import com.tripayweb.app.model.response.LoginResponse;
import com.tripayweb.app.model.response.ResendForgotPasswordOtpResponse;
import com.tripayweb.model.error.LoginError;
import com.tripayweb.util.LogCat;
import com.tripayweb.util.ModelMapKey;
import com.tripayweb.validation.LoginValidation;

@Controller
@RequestMapping("/")
public class LoginController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;

	private final ILoginApi loginApi;
	private final LoginValidation loginValidation;
	private final IVerificationApi verificationApi;

	public LoginController(ILoginApi loginApi, LoginValidation loginValidation, IVerificationApi verificationApi) {
		this.loginApi = loginApi;
		this.loginValidation = loginValidation;
		this.verificationApi = verificationApi;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/*@RequestMapping(method = RequestMethod.POST, value =User/Login")
	public String processUserLogin(@ModelAttribute("login") LoginRequest loginDTO, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, RedirectAttributes modelMap) {
		LoginError error = loginValidation.checkError(loginDTO);
		if (error.isValid()) {
			loginDTO.setIpAddress(request.getRemoteAddr());
			LoginResponse resp = loginApi.login(loginDTO, Role.USER);
			
			
			logger.debug("Message ::" + resp.getMessage());
			logger.debug("error code ::" + resp.getCode());
			System.err.println("Message ::" + resp.getMessage());
			System.err.println("error code ::" + resp.getCode());
			if (resp.getCode().equals("F00")) {
				modelMap.addFlashAttribute(ModelMapKey.ERROR, "Invalid Username/Password");
			}
		}
		return "forward:/";

	}*/

	@RequestMapping(method = RequestMethod.POST, value = "/Admin/Login")
	public String processAdminLogin(@ModelAttribute("login") LoginRequest loginDTO, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		LoginResponse resp = loginApi.login(loginDTO, Role.ADMIN);
		modelMap.addAttribute("message", resp.getMessage());
		return "redirect:/";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/ForgotPassword/Request")
	public String forgetPasswordUserRequest(@ModelAttribute("forgotPassword") ForgetPasswordUserRequest psw,
			HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		ForgetPasswordUserResponse resp = loginApi.forgetPasswordUserRequest(psw);
		modelMap.addAttribute("message", resp.getMessage());
		return "User/Home";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ForgotPassword/ChangePassword")
	public String changePasswordWithOtpRequest(
			@ModelAttribute("forgotPassword") ChangePasswordWithOtpRequest changePassword, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		ChangePasswordWithOtpResponse resp = loginApi.changePasswordWithOtpRequest(changePassword);
		modelMap.addAttribute("message", resp.getMessage());
		return "User/Home";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ForgotPassword/Resend")
	public String resendForgotPasswordOtpRequest(
			@ModelAttribute("forgotPassword") ResendForgotPasswordOtpRequest resendPassword, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		ResendForgotPasswordOtpResponse resp = loginApi.resendForgotPasswordOtpRequest(resendPassword);
		modelMap.addAttribute("message", resp.getMessage());
		return "User/Home";
	}

	@RequestMapping(value = "ForgotPassword", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<ForgetPasswordUserResponse> forgotPassword(@RequestBody ForgetPasswordUserRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		System.err.println("inside Forgot Password");
		ForgetPasswordUserResponse result = new ForgetPasswordUserResponse();
//		JCaptchaRequest cap = new JCaptchaRequest();
//		cap.setSessionId(request.getSession().getId());
//		cap.setCaptchaResponse(dto.getCaptchaResponse());
//		JCaptchaResponse jCaptchaResponse = verificationApi.isValidJCaptcha(cap);
//		if (jCaptchaResponse.isValid()) {
//			LogCat.print("username ::" + dto.getUsername());
//		} else {
//			result.setCode("F00");
//			result.setMessage("Not a valid captcha");
//		}
			result = loginApi.forgetPasswordUserRequest(dto);
		return new ResponseEntity<ForgetPasswordUserResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "ChangePasswordWithOTP", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<ChangePasswordWithOtpResponse> changePasswordWithOTP(
			@RequestHeader(value = "hash", required = false) String hash, @RequestBody ChangePasswordWithOtpRequest dto,
			HttpServletRequest request, HttpServletResponse response) {
		ChangePasswordWithOtpResponse result = new ChangePasswordWithOtpResponse();
		System.out.print("username" + dto.getUsername());
		System.out.print("password" + dto.getNewPassword());
		System.out.print("confirm password ::" + dto.getConfirmPassword());
		System.out.print("key::" + dto.getKey());
		result = loginApi.changePasswordWithOtpRequest(dto);
		return new ResponseEntity<ChangePasswordWithOtpResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "ResendForgotPasswordOTP", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<ResendForgotPasswordOtpResponse> resendForgotPasswordOTP(
			@RequestBody ResendForgotPasswordOtpRequest dto, HttpServletRequest request, HttpServletResponse response) {
		ResendForgotPasswordOtpResponse result = new ResendForgotPasswordOtpResponse();

		JCaptchaRequest cap = new JCaptchaRequest();
		cap.setSessionId(request.getSession().getId());
		cap.setCaptchaResponse(dto.getCaptchaResponse());
		JCaptchaResponse jCaptchaResponse = verificationApi.isValidJCaptcha(cap);

		if (jCaptchaResponse.isValid()) {
			result = loginApi.resendForgotPasswordOtpRequest(dto);
		} else {
			result.setCode("F00");
			result.setMessage("Not a valid captcha");
		}
		return new ResponseEntity<ResendForgotPasswordOtpResponse>(result, HttpStatus.OK);
	}

}
