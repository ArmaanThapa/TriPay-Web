package com.tripayweb.controller.web.user;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octo.captcha.service.CaptchaServiceException;
import com.tripayweb.app.model.request.VNetRequest;
import com.tripayweb.app.model.response.VNetResponse;
import com.tripayweb.app.model.response.VRedirectResponse;
import com.tripayweb.model.web.WEBSRedirectResponse;
import com.tripayweb.util.ConvertUtil;
import com.thirdparty.model.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.api.ILoadMoneyApi;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.LoadMoneyRequest;
import com.tripayweb.app.model.response.EBSRedirectResponse;
import com.tripayweb.app.model.response.LoadMoneyResponse;
import com.tripayweb.model.error.LoadMoneyError;
import com.tripayweb.util.Authorities;
import com.tripayweb.util.ModelMapKey;
import com.tripayweb.validation.LoadMoneyValidation;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Controller
@RequestMapping("/User/LoadMoney")
public class LoadMoneyController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;
	
	private final ILoadMoneyApi loadMoneyApi;
	private final IAuthenticationApi authenticationApi;
	private final LoadMoneyValidation loadMoneyValidation;

	public LoadMoneyController(ILoadMoneyApi loadMoneyApi, IAuthenticationApi authenticationApi,
			LoadMoneyValidation loadMoneyValidation) {
		this.loadMoneyApi = loadMoneyApi;
		this.authenticationApi = authenticationApi;
		this.loadMoneyValidation = loadMoneyValidation;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/Process", method = RequestMethod.POST)
	public String processLoadMoney(@ModelAttribute LoadMoneyRequest dto, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		System.err.println("Inside load money controller");
		request.setAttribute("sessionId", sessionId);
		dto.setSessionId(sessionId);
		LoadMoneyResponse loadMoneyResponse = new LoadMoneyResponse();
		LoadMoneyError error = loadMoneyValidation.checkError(dto);
		if (error.isValid()) {
			if (sessionId != null && sessionId.length() != 0)
			{
				String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
				if (authority != null)
				{
				
					if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
						request.getSession().setAttribute("sessionId", sessionId);
						dto.setSessionId(sessionId);
						if (dto.isUseVnet()) {
							VNetRequest vNetRequest = new VNetRequest();
							vNetRequest.setSessionId(sessionId);
							vNetRequest.setReturnURL("https://www.vpayqwik.com/User/LoadMoney/VRedirect");
							vNetRequest.setAmount(dto.getAmount());
							VNetResponse vNetResponse = loadMoneyApi.initiateVnetBanking(vNetRequest);
							if(vNetResponse.isSuccess()){
								modelMap.addAttribute("vnet",vNetResponse);
                                return "User/LoadMoney/VNetPay";
							}
						} else {System.err.println("vpayqwik");
							dto.setReturnUrl("https://www.vpayqwik.com/User/LoadMoney/Redirect");
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
		} else {
			modelMap.put(ModelMapKey.ERROR, error);
			return "User/LoadMoney";
		}
		return "redirect:/User/LoadMoney";
	}


    @RequestMapping(value = "/Process", method = RequestMethod.GET)
	public String processLoadMoney(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			HttpSession session) {
        System.err.println("IP is ::"+request.getRemoteAddr());
		String sessionId = (String) session.getAttribute("sessionId");
		request.setAttribute("sessionId", sessionId);
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
					return "User/LoadMoney";
				}
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/Redirect", method = RequestMethod.POST)
	public String redirectLoadMoney(WEBSRedirectResponse ebsResponse, HttpServletRequest request, HttpServletResponse response, HttpSession session,
									Model model) {
		String ipAddress = request.getRemoteAddr();
		EBSRedirectResponse ebsRedirectResponse = ConvertUtil.convertFromWEBS(ebsResponse);
		ResponseDTO result = loadMoneyApi.verifyEBSTransaction(ebsRedirectResponse);
		if(result.isSuccess()){
			ebsRedirectResponse.setSuccess(true);
			ebsRedirectResponse.setResponseCode("0");
		}else {
			ebsRedirectResponse.setSuccess(false);
			ebsRedirectResponse.setResponseCode("1");
		}
		EBSRedirectResponse redirectResponse = loadMoneyApi.processRedirectSDK(ebsRedirectResponse);
			if (redirectResponse.isSuccess() || redirectResponse.getResponseCode().equals("0")) {
				model.addAttribute("msg", "Transasction Successful,You've successfully loaded amount "
						+ redirectResponse.getAmount() + " in your wallet");
			} else {
				model.addAttribute("msg", "Transaction failed, Please try again later.");
			}
			model.addAttribute("loadmoneyResponse", redirectResponse);
			return "User/Loading";

	}


    @RequestMapping(value = "/VRedirect")
	public String redirectVNetLoadMoney(VRedirectResponse dto, HttpServletRequest request, HttpServletResponse response, HttpSession session,
										Model model) {
		String ipAddress = request.getRemoteAddr();
			System.err.println(dto.toString());
			ResponseDTO responseDTO = loadMoneyApi.handleRedirectRequest(dto);
			model.addAttribute("response", responseDTO);
			return "User/Loading";

	}
}
