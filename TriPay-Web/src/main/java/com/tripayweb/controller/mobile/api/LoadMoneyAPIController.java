
package com.tripayweb.controller.mobile.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tripayweb.app.model.request.Response;
import com.tripayweb.app.model.request.VNetRequest;
import com.tripayweb.app.model.response.VNetResponse;
import com.tripayweb.app.model.response.VRedirectResponse;
import com.tripayweb.model.error.LoadMoneyError;
import com.tripayweb.model.mobile.MResponseDTO;
import com.tripayweb.util.ModelMapKey;
import com.tripayweb.validation.LoadMoneyValidation;
import com.thirdparty.model.ResponseDTO;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.api.ILoadMoneyApi;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.LoadMoneyRequest;
import com.tripayweb.app.model.response.EBSRedirectResponse;
import com.tripayweb.app.model.response.LoadMoneyResponse;
import com.tripayweb.util.Authorities;

@Controller
@RequestMapping("/Api/{version}/{role}/{device}/{language}/LoadMoney")
public class LoadMoneyAPIController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;

	private final ILoadMoneyApi loadMoneyApi;
	private final IAuthenticationApi authenticationApi;
	private final LoadMoneyValidation loadMoneyValidation;

	public LoadMoneyAPIController(ILoadMoneyApi loadMoneyApi, IAuthenticationApi authenticationApi,LoadMoneyValidation loadMoneyValidation) {
		this.loadMoneyApi = loadMoneyApi;
		this.authenticationApi = authenticationApi;
		this.loadMoneyValidation = loadMoneyValidation;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/Process", method = RequestMethod.POST)
	public String processLoadMoney(@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
								   @PathVariable(value = "device") String device, @PathVariable(value = "language") String language, @ModelAttribute LoadMoneyRequest dto, HttpServletRequest request,
								   HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		String sessionId = dto.getSessionId();
		System.err.println(dto.getAmount());
		System.err.println("load money controller");
		System.out.println("session ID :: "+sessionId);
		System.err.println(dto.getAmount());
		System.err.println(dto.getName());
		System.err.println("Pay :: "+dto.getChannel());
		LoadMoneyError error = loadMoneyValidation.checkError(dto);
		LoadMoneyResponse loadMoneyResponse = new LoadMoneyResponse();
		if (error.isValid()) {
			System.err.println("kkkkkkkkkkkkkkkkk load");
			if (sessionId != null && sessionId.length() != 0) {
				String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
				System.err.println("Authority :: " + authority);
				if (authority != null) {
					if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
						dto.setSessionId(sessionId);
						if (dto.isUseVnet()) {
							VNetRequest vNetRequest = new VNetRequest();
							vNetRequest.setSessionId(sessionId);
							vNetRequest.setReturnURL(dto.getReturnUrl());
							vNetRequest.setAmount(dto.getAmount());
							VNetResponse vNetResponse = loadMoneyApi.initiateVnetBanking(vNetRequest);
							if(vNetResponse.isSuccess()){
								modelMap.addAttribute("vnet",vNetResponse);
								return "User/LoadMoney/VNetPay";
							}
						} else {
							loadMoneyResponse = loadMoneyApi.loadMoneyRequest(dto);
							if (loadMoneyResponse.isSuccess()) {
								modelMap.addAttribute("loadmoney", loadMoneyResponse);
								return "User/LoadMoney/Pay";
							} else {
								System.err.format("Load money response is %s\n", loadMoneyResponse.getDescription());
								modelMap.addAttribute(ModelMapKey.MESSAGE, loadMoneyResponse.getDescription());
								return "User/LoadMoney";
							}
						}
					}
				}
			}
		}else {
            modelMap.addAttribute(ModelMapKey.ERROR,error.getAmount());
        }
		return "redirect:/";
	}

	@RequestMapping(value = "/InitiateLoadMoney", method = RequestMethod.POST)
	public ResponseEntity<LoadMoneyResponse> initiateLoadMoney(@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
								   @PathVariable(value = "device") String device, @PathVariable(value = "language") String language, @ModelAttribute LoadMoneyRequest dto, HttpServletRequest request,
								   HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		String sessionId = dto.getSessionId();
        dto.setReturnUrl("-");
		System.err.println("");
		System.out.println("session ID :: "+sessionId);
		System.err.println("Pay :: "+dto.getChannel());

		LoadMoneyError error = loadMoneyValidation.checkError(dto);
		LoadMoneyResponse loadMoneyResponse = new LoadMoneyResponse();
		if (error.isValid()) {
			if (sessionId != null && sessionId.length() != 0) {
				String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
				if (authority != null) {
					if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {

						dto.setSessionId(sessionId);
						loadMoneyResponse = loadMoneyApi.loadMoneyRequest(dto);
					}
				}
			}
		}
		return new ResponseEntity<LoadMoneyResponse>(loadMoneyResponse,HttpStatus.OK);
	}


	@RequestMapping(value = "/RedirectSDK", method = RequestMethod.POST,produces = {
			MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<EBSRedirectResponse> redirectLoadMoneySDK(@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
														  @PathVariable(value = "device") String device, @PathVariable(value = "language") String language, @ModelAttribute EBSRedirectResponse redirectResponse, HttpServletRequest request, HttpServletResponse response,HttpSession session, Model model){

//		EBSRedirectResponse redirectResponse = loadMoneyApi.processRedirectResponse(request);
		EBSRedirectResponse newResponse = null;
		ResponseDTO result = null;
		String success = redirectResponse.getResponseCode();
        result = loadMoneyApi.verifyEBSTransaction(redirectResponse);
		if(result != null){
			String code = result.getCode();
			if(code.equalsIgnoreCase("S00")){
				redirectResponse.setSuccess(true);
				redirectResponse.setResponseCode("0");
			}else {
				redirectResponse.setSuccess(false);
				redirectResponse.setResponseCode("1");
			}
			newResponse = loadMoneyApi.processRedirectSDK(redirectResponse);
		}
		return new ResponseEntity<EBSRedirectResponse>(newResponse,HttpStatus.OK);
	}

	@RequestMapping(value = "/Redirect", method = RequestMethod.POST,produces = {
			MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<EBSRedirectResponse> redirectLoadMoney(@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
														  @PathVariable(value = "device") String device, @PathVariable(value = "language") String language,HttpServletRequest request, HttpServletResponse response,HttpSession session, Model model){

		EBSRedirectResponse redirectResponse = loadMoneyApi.processRedirectResponse(request);
		JSONObject json = new JSONObject(redirectResponse);
		System.out.println(" :: ::"+json);
		return new ResponseEntity<EBSRedirectResponse>(redirectResponse,HttpStatus.OK);
	}

	@RequestMapping(value = "/VRedirect",produces = {MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<ResponseDTO> redirectVNetLoadMoney(VRedirectResponse dto, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		System.err.println(dto.toString());
		ResponseDTO responseDTO = loadMoneyApi.handleRedirectRequest(dto);
		return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
	}
}
