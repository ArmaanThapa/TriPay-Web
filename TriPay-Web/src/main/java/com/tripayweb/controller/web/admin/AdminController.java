
package com.tripayweb.controller.web.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.tripayweb.app.api.IAdminApi;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.AllTransactionRequest;
import com.tripayweb.app.model.request.AllUserRequest;
import com.tripayweb.app.model.request.BlockUnBlockUserRequest;
import com.tripayweb.app.model.request.EmailLogRequest;
import com.tripayweb.app.model.request.MessageLogRequest;
import com.tripayweb.app.model.request.UserTransactionRequest;
import com.tripayweb.app.model.response.AllTransactionResponse;
import com.tripayweb.app.model.response.AllUserResponse;
import com.tripayweb.app.model.response.BlockUnBlockUserResponse;
import com.tripayweb.app.model.response.EmailLogResponse;
import com.tripayweb.app.model.response.MessageLogResponse;
import com.tripayweb.app.model.response.UserTransactionResponse;
import com.tripayweb.util.Authorities;

@Controller
@RequestMapping("/Admin")
public class AdminController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;

	private final IAdminApi adminApi;
	private final IAuthenticationApi authenticationApi;

	public AdminController(IAdminApi adminApi, IAuthenticationApi authenticationApi) {
		this.adminApi = adminApi;
		this.authenticationApi = authenticationApi;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/Transaction", method = RequestMethod.POST)
	public String getAllTransaction(@ModelAttribute("transaction") AllTransactionRequest dto,
			HttpServletRequest request, ModelMap modelMap, HttpServletResponse response, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.ADMIN);
			if (authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					dto.setSessionId(sessionId);
					AllTransactionResponse resp = adminApi.getAllTransaction(dto);
					modelMap.addAttribute("message", resp.getMessage());
					return "Admin/Transaction";
				}
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/Users", method = RequestMethod.POST)
	public String getAllUser(@ModelAttribute("user") AllUserRequest dto, HttpServletRequest request, ModelMap modelMap,
			HttpServletResponse response, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.ADMIN);
			if (authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					dto.setSessionId(sessionId);
					AllUserResponse resp = adminApi.getAllUser(dto);
					modelMap.addAttribute("message", resp.getMessage());
					return "Admin/Users";
				}
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/Users/Transaction", method = RequestMethod.POST)
	public String getUserTransaction(@ModelAttribute("userTransaction") UserTransactionRequest dto,
			HttpServletRequest request, ModelMap modelMap, HttpServletResponse response, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.ADMIN);
			if (authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					dto.setSessionId(sessionId);
					UserTransactionResponse resp = adminApi.getUserTransaction(dto);
					modelMap.addAttribute("message", resp.getMessage());
					return "Admin/UserProfile";
				}
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/MessageLog", method = RequestMethod.POST)
	public String getMessageLog(@ModelAttribute("messageLog") MessageLogRequest dto, HttpServletRequest request,
			ModelMap modelMap, HttpServletResponse response, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.ADMIN);
			if (authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					dto.setSessionId(sessionId);
					MessageLogResponse resp = adminApi.getMessageLog(dto);
					modelMap.addAttribute("message", resp.getMessage());
					return "Admin/MessageLog";
				}
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/EmailLog", method = RequestMethod.POST)
	public String getEmailLog(@ModelAttribute("messageLog") EmailLogRequest dto, HttpServletRequest request,
			ModelMap modelMap, HttpServletResponse response, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.ADMIN);
			if (authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					dto.setSessionId(sessionId);
					EmailLogResponse resp = adminApi.getEmailLog(dto);
					modelMap.addAttribute("message", resp.getMessage());
					return "Admin/EmailLog";
				}
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/block", method = RequestMethod.POST)
	public String blockUser(@ModelAttribute("blockUnblock") BlockUnBlockUserRequest dto, HttpServletRequest request,
			ModelMap modelMap, HttpServletResponse response, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.ADMIN);
			if (authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					dto.setSessionId(sessionId);
					BlockUnBlockUserResponse resp = adminApi.blockUser(dto);
					modelMap.addAttribute("message", resp.getMessage());
					return "Admin/Home";
				}
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/unblock", method = RequestMethod.POST)
	public String unblockUser(@ModelAttribute("blockUnblock") BlockUnBlockUserRequest dto, HttpServletRequest request,
			ModelMap modelMap, HttpServletResponse response, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.ADMIN);
			if (authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					dto.setSessionId(sessionId);
					BlockUnBlockUserResponse resp = adminApi.unblockUser(dto);
					modelMap.addAttribute("message", resp.getMessage());
					return "Admin/Home";
				}
			}
		}
		return "redirect:/";
	}
}
