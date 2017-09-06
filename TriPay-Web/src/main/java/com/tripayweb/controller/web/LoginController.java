package com.tripayweb.controller.web;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.recaptcha.api.IVerificationApi;
import com.tripayweb.app.api.ILoginApi;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.LoginRequest;
import com.tripayweb.app.model.response.LoginResponse;
import com.tripayweb.model.web.UserDTO;
import com.tripayweb.util.LogCat;
import com.tripayweb.util.ModelMapKey;
import com.tripayweb.validation.CommonValidation;

@Controller
@RequestMapping("/User")
public class LoginController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;

	private final ILoginApi appLoginApi;
	private final CommonValidation commonValidation;
	private final IVerificationApi verificationApi;

	public LoginController(ILoginApi loginApi, CommonValidation commonValidation, IVerificationApi verificationApi) {
		this.appLoginApi = loginApi;
		this.commonValidation = commonValidation;
		this.verificationApi = verificationApi;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/Travel/BusTravel")
	public String gettrevelbus(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
			ModelMap model, HttpSession session) {

		// ThirdPartyTravelBusApi obj=new ThirdPartyTravelBusApi();
		// SourcesRequest request1=new SourcesRequest();
		// request1.setConsumerSecret("04DAF1E2C80A758DCC6BCEBE436D25BC");
		// request1.setConsumerKey("59285FC07292811D72785AC287ACE3E8");
		// obj.sources(request1);
		String sessionId = (String) session.getAttribute("sessionId");

		if (sessionId != null && sessionId.length() != 0)
		{
			String id = "";
			String city = "";
			try {
				String val = LoginController.reafile();
				// API CALL FROM DATABASE

				// jsonString is a string variable that holds the JSON
				ArrayList<String> arrayidval = new ArrayList<>();
				ArrayList<String> arraynameval = new ArrayList<>();

				JSONArray jsonarray = new JSONArray(val);
				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject jsonobject = jsonarray.getJSONObject(i);
					String idname = jsonobject.getString("Id");
					String cityname = jsonobject.getString("Name");
					id += "#" + idname;
					city += "@" + cityname;
				}

				System.out.println(id + "$" + city);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("cityandid", id + "$" + city);
			return "User/Travel/Bus/BusTravel";

		} else {
			return "redirect:/Home";
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/Travel/Bus/Allbusshowdetail")
	public String getavailebelbustrevelbus(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
			ModelMap model, HttpSession session) {
		System.err.println("get Bus Details ::: ");
		String sessionId = (String) session.getAttribute("sessionId");

		if(sessionId != null && sessionId.length() != 0)
		{
			return "User/Travel/Bus/Allbusshowdetail";
		} else {
			return "redirect:/Home";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/Login")
	public String getHome(RedirectAttributes model, HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") LoginRequest loginDTO, UserDTO userDTO, HttpSession session) {
		System.out.println();
		if (true) {
			loginDTO.setIpAddress(request.getRemoteAddr());
			LoginResponse resp = appLoginApi.login(loginDTO, Role.USER);
			System.err.println("resp ::" + resp.getCode());
			System.err.println("armaan login");
			if (resp.getCode() != null) {
				System.err.println("resp code is not null");
				System.err.println(resp.getCode().equals("S00"));
				if (resp.getCode().equals("S00")) {
					session.setAttribute("sessionId", resp.getSessionId());
					session.setAttribute("balance", resp.getBalance());
					session.setAttribute("firstName", resp.getFirstName());
					session.setAttribute("userDetails", resp);
					session.setAttribute("username", resp.getUsername());
					session.setAttribute("emailId", resp.getEmail());
					session.setAttribute("accountId", resp.getAccountNumber());
					session.setAttribute("dailyTransaction", resp.getDailyTransaction());
					session.setAttribute("monthlyTransaction", resp.getMonthlyTransaction());
					return "redirect:/User/Home";
				} else if (resp.getCode().equals("F05")) {
					model.addFlashAttribute(ModelMapKey.ERROR, resp.getMessage());
					return "redirect:/Home";
				}
			}
		} else {
			model.addFlashAttribute(ModelMapKey.ERROR, "Invalid Captcha");
			return "redirect:/Home";
		}
		return "redirect:/Home";
	}

	public static String reafile() {
		String data = "";
		try {

			String val = System.getProperty("user.dir");

			// File f = new
			// File("/usr/local/apps/tomcat/webapps/ROOT/resources/BusSource.txt");
			File f = new File("/home/vibhanshu/Documents/PayQwikCentralSystem/tripayweb/WebContent/resources/BusSource.txt");

			// if(f.exists())
			// {
			// System.out.println(f.getAbsolutePath());
			FileInputStream fin = new FileInputStream(f);
			byte[] buffer = new byte[(int) f.length()];

			new DataInputStream(fin).readFully(buffer);
			fin.close();
			data = new String(buffer, "UTF-8");
			//
			// }

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return data;
	}

}
