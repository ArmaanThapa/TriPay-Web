package com.tripayweb.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tripayweb.app.api.ICallBackApi;
import com.tripayweb.app.model.response.CallBackResponse;
import com.tripayweb.app.model.response.IPayCallBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.model.Role;
import com.tripayweb.util.Authorities;
import com.tripayweb.util.LogCat;
import com.tripayweb.util.ModelMapKey;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Controller
public class MainController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;

	private final IAuthenticationApi authenticationApi;
	private final ICallBackApi callBackApi;
	public MainController(IAuthenticationApi authenticationApi,ICallBackApi callBackApi) {
		this.authenticationApi = authenticationApi;
		this.callBackApi = callBackApi;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String getHome(@RequestParam(value = ModelMapKey.MESSAGE, required = false) String msg,
			@RequestParam(value = ModelMapKey.ERROR, required = false) String error, RedirectAttributes modelMap,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.err.print("principal ::"+principal);
        logger.info("/ Received Logger in");
		String sessionId = (String) session.getAttribute("sessionId");
		if (msg != null) {
			System.err.print("messaage is ::"+msg);
			modelMap.addAttribute(ModelMapKey.MESSAGE, msg);
		}
		if (error != null) {
			modelMap.addAttribute(ModelMapKey.ERROR, error);
			System.err.println("error in Get ::"+error);
		}
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			LogCat.print("Authority :: " + authority);
			if (authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					LogCat.print("Redirect Admin/Home");
					return "redirect:/Admin/Home";
				} else if (authority.contains(Authorities.MERCHANT) && authority.contains(Authorities.AUTHENTICATED)) {
					LogCat.print("Redirect Merchant/Home");
					return "redirect:/Merchant/Home";
				} else if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
					LogCat.print("Redirect User/Home");
					return "redirect:/User/Home";
				} else {
					logger.info(" /Home");
					return "Home";
				}
			}
		}
		logger.info("Redirect /Home");
		return "Home";
	}


	@RequestMapping(value="/IPayCall",method=RequestMethod.GET)
	ResponseEntity<String> processCallBack(IPayCallBack dto, HttpServletRequest request, HttpServletResponse response){
		if(request.getRemoteAddr().equalsIgnoreCase("180.179.213.127")){
			System.err.println("Instant Pay CallBack"+dto.toString());
//			callBackApi.attempt(dto);
		}
		return new ResponseEntity<String> (HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/Home")
	public String getHome(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = ModelMapKey.MESSAGE, required = false) String msg,
			@RequestParam(value = ModelMapKey.ERROR, required = false) String error, HttpSession session) {
		logger.info("/Home Received Logger");
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			LogCat.print("Authority :: " + authority);
			if (authority != null) {
				logger.info("Redirect /");
				return "redirect:/";
			}
		}

		if (msg != null) {
			modelMap.put(ModelMapKey.MESSAGE, msg);
		}
		if (error != null) {
			modelMap.put(ModelMapKey.ERROR, error);
		}

		logger.info("return home page");
		return "Home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/VerifyMobile")
	public String getVerifyMobile(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = ModelMapKey.MESSAGE, required = false) String msg,
			@RequestParam(value = "mobileNumber", required = false) String mobileNumber) {
		logger.debug("GET /VerifyMobile");
		if (msg != null) {
			modelMap.put(ModelMapKey.MESSAGE, msg);
		}
		if (mobileNumber != null) {
			modelMap.put("mobileNumber", mobileNumber);
		}
		return "VerifyMobile";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/Static/{staticPage}")
	public String getStatic(ModelMap map, @PathVariable("staticPage") String staticPage) {
		logger.debug("GET /Static/{}", staticPage);
		return "Static/" + staticPage;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/Static/{staticPage}")
	public String getStaticPost(ModelMap map, @PathVariable("staticPage") String staticPage) {
		logger.debug("POST /Static/{}", staticPage);
		return "Static/" + staticPage;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{page}")
	public String getPage(ModelMap map, @PathVariable("page") String page) {
		logger.debug("GET /{}", page);
		return page;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{page}")
	public String getPagePost(ModelMap map, @PathVariable("page") String page) {
		logger.debug("POST /{}", page);
		return page;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/Admin/{adminPage}")
	public String getAdmin(ModelMap map, @PathVariable("adminPage") String adminPage, HttpSession session) {
		LogCat.print("In Admin ::" + adminPage);
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					return "Admin/" + adminPage;
				}
			}
		}
		// return "Admin/" + adminPage;
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/Admin/{adminPage}")
	public String getAdminPost(ModelMap map, @PathVariable("adminPage") String adminPage, HttpSession session) {
		logger.debug("POST /Admin/{}", adminPage);
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					return "Admin/" + adminPage;
				}
			}
		}
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/User/{mainPage}")
	public String getUser(ModelMap map, @PathVariable("mainPage") String mainPage,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "menu", required = false) String menu, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");


        if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
					map.put("name", (String) session.getAttribute("firstName"));
					map.put("balance", (double) session.getAttribute("balance"));
					System.err.println("inside user main page");
					return "User/" + mainPage;
				}
			}
		}
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/User/{mainPage}")
	public String postUser(ModelMap map, @PathVariable("mainPage") String mainPage,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "menu", required = false) String menu, HttpSession session) {
		LogCat.print("POST User Page Request :: " + mainPage);
		logger.debug("POST /User/{}", mainPage);
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
					LogCat.print("MSG :: " + (map.containsKey("msg") ? map.get("msg") : "NULL"));
					LogCat.print("URL MSG :: " + msg);
					LogCat.print("URL MENU :: " + menu);
					map.put("msg", msg);
					map.put("menu", menu);
					return "User/" + mainPage;
				}
			}
		}
		return "redirect:/";
	}

}
