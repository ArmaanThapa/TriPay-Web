package com.tripayweb.controller.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tripayweb.util.ModelMapKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.api.ILogoutApi;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.LogoutRequest;
import com.tripayweb.app.model.response.LogoutResponse;
import com.tripayweb.util.Authorities;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class LogoutController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;

	private final ILogoutApi logoutApi;
	private final IAuthenticationApi authenticationApi;

	public LogoutController(ILogoutApi logoutApi, IAuthenticationApi authenticationApi) {
		this.logoutApi = logoutApi;
		this.authenticationApi = authenticationApi;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(method = RequestMethod.GET, value = "User/Logout")
	public String userLogout(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, RedirectAttributes modelMap) {
		String sessionId = (String) session.getAttribute("sessionId");
		System.err.print("Inside User Logout");
		LogoutRequest logout = new LogoutRequest();
		logout.setSessionId(sessionId);
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
					LogoutResponse resp = logoutApi.logout(logout, Role.USER);
					modelMap.addFlashAttribute(ModelMapKey.MESSAGE, "You've successfully logged out");
					session.invalidate();
					return "redirect:/Home";
				}
			}
		}
		return "redirect:/";

	}

	@RequestMapping(method = RequestMethod.POST, value = "Admin/Logout")
	public String adminLogout(@ModelAttribute("login") LogoutRequest dto, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
					LogoutResponse resp = logoutApi.logout(dto, Role.ADMIN);
					modelMap.addAttribute("message", resp.getMessage());
					session.invalidate();
					return "redirect:/Admin/Home";
				}
			}
		}
		return "redirect:/Admin/Home";

	}
}
