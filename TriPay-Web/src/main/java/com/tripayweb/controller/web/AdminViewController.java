package com.tripayweb.controller.web;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gcm.api.INotificationApi;
import com.gcm.model.NotificationDTO;
import com.gcm.utils.APIConstants;
import com.tripayweb.app.model.request.*;
import com.tripayweb.app.model.response.*;
import com.tripayweb.model.web.*;

import com.tripayweb.util.Authorities;
import com.tripayweb.util.JSONParserUtil;
import com.tripayweb.util.LogCat;
import com.tripayweb.util.ModelMapKey;
import com.tripayweb.validation.CommonValidation;
import com.tripayweb.validation.GCMValidation;
import com.tripayweb.validation.PromoCodeValidation;
import com.thirdparty.model.ResponseDTO;
import org.json.JSONArray;
import org.json.JSONException;
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
import org.springframework.web.bind.annotation.*;

import com.coupon.api.IPromoCodeApi;
import com.google.recaptcha.api.IVerificationApi;
import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.api.IAdminApi;
import com.tripayweb.app.api.ILogoutApi;
import com.tripayweb.app.api.IUserApi;
import com.tripayweb.app.api.IVersionApi;
import com.tripayweb.app.api.impl.AdminApi;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.UserStatus;
import com.tripayweb.model.error.RegisterError;
import com.tripayweb.validation.RegisterValidation;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/Admin")
public class AdminViewController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;
	private final IAuthenticationApi authenticationApi;
	private final IAdminApi appAdminApi;
	private final ILogoutApi logoutApi;
	private final IUserApi userApi;
	private final RegisterValidation registerValidation;
	private final IPromoCodeApi promoCodeApi;
	private final IVerificationApi verificationApi;
	private final IVersionApi versionApi;
	private final PromoCodeValidation promoCodeValidation;
	private final INotificationApi notificationApi;
    private final GCMValidation gcmValidation;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat dateFilter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public AdminViewController(AdminApi appAdminApi, IAuthenticationApi authenticationApi, ILogoutApi logoutApi,
			IUserApi userApi, RegisterValidation registerValidation, IPromoCodeApi promoCodeApi,
			IVerificationApi verificationApi, IVersionApi versionApi,PromoCodeValidation promoCodeValidation,INotificationApi notificationApi,GCMValidation gcmValidation) {
		this.appAdminApi = appAdminApi;
		this.authenticationApi = authenticationApi;
		this.logoutApi = logoutApi;
		this.userApi = userApi;
		this.registerValidation = registerValidation;
		this.promoCodeApi = promoCodeApi;
		this.verificationApi = verificationApi;
		this.versionApi = versionApi;
		this.promoCodeValidation = promoCodeValidation;
		this.notificationApi = notificationApi;
        this.gcmValidation = gcmValidation;
	}

	@ModelAttribute("GeneratePromoCode")
	public PromoCodeRequest code() {
		return new PromoCodeRequest();
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/Home")
	public String getUser(HttpServletRequest request, ModelMap model, HttpServletResponse response,
			HttpSession session) {

		String adminSessionId = (String) session.getAttribute("adminSessionId");
        System.err.print("In Admin Home :: " + adminSessionId);
		LogCat.print("In Admin Home :: " + adminSessionId);

		if (adminSessionId != null) {
			SessionDTO dto = new SessionDTO();
			dto.setSessionId(adminSessionId);
			String authority = authenticationApi.getAuthorityFromSession(adminSessionId, Role.USER);
			if (authority != null) {
				System.err.print(authority);
				if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					UserTransactionResponse userTransactionResponse = appAdminApi.getUserTransactionValues(dto);
					if (userTransactionResponse != null) {
						if (userTransactionResponse.isSuccess()) {
							JSONObject json = null;
							try {
								json = new JSONObject(userTransactionResponse.getResponse());
							} catch (JSONException e) {
							}
							if (json != null) {
								JSONObject details = JSONParserUtil.getObject(json, "details");
								model.addAttribute("lme", JSONParserUtil.getDouble(details, "totalLoadMoneyEBS"));
								model.addAttribute("lmv", JSONParserUtil.getDouble(details, "totalLoadMoneyVNet"));
								model.addAttribute("pool", JSONParserUtil.getDouble(details, "walletBalance"));
								model.addAttribute("tpay", JSONParserUtil.getDouble(details, "totalPayable"));
								model.addAttribute("mpayable", JSONParserUtil.getDouble(details, "merchantPayable"));
								model.addAttribute("tcommission", JSONParserUtil.getDouble(details, "totalCommission"));
							}
						}
					}
					model.addAttribute("totalTrans", (int) session.getAttribute("totalTrans"));
					model.addAttribute("totalUser", (int) session.getAttribute("totalUser"));
					model.addAttribute("onlineUser", (int) session.getAttribute("onlineUser"));
					model.addAttribute("totalMale", (int) session.getAttribute("totalMale"));
					model.addAttribute("totalFemale", (int) session.getAttribute("totalFemale"));
					model.addAttribute("todayTransaction", (int) session.getAttribute("todayTransaction"));
					model.addAttribute("aa", (int) session.getAttribute("aa"));
					model.addAttribute("bb", (int) session.getAttribute("bb"));
					model.addAttribute("cc", (int) session.getAttribute("cc"));
					model.addAttribute("dd", (int) session.getAttribute("dd"));
					model.addAttribute("ee", (int) session.getAttribute("ee"));
					model.addAttribute("year", (int) session.getAttribute("year"));
					model.addAttribute("month", (int) session.getAttribute("month"));
					model.addAttribute("date", (int) session.getAttribute("date"));
					GraphDTO gDto = new GraphDTO();
					gDto.setAa((int) session.getAttribute("aa"));
					gDto.setBb((int) session.getAttribute("bb"));
					gDto.setCc((int) session.getAttribute("cc"));
					gDto.setDd((int) session.getAttribute("dd"));
					gDto.setEe((int) session.getAttribute("ee"));
					gDto.setMonth((int) session.getAttribute("month"));
					gDto.setDate((int) session.getAttribute("date"));
					gDto.setYear((int) session.getAttribute("year"));
					model.addAttribute("gDto", gDto);
					return "Admin/Home";
				}
			}

		}
		return "Admin/Login";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/Home")
	public String getAdminHomePage(HttpServletRequest request, ModelMap modelMap, HttpServletResponse response,
			@ModelAttribute("login") LoginRequest loginDTO, ModelMap model, HttpSession session) {

			try {
				loginDTO.setIpAddress(request.getRemoteAddr());
				LoginResponse loginResponse = appAdminApi.login(loginDTO);
				String authority = loginResponse.getAuthority();
				session.setAttribute("adminSessionId", loginResponse.getSessionId());
				AllTransactionRequest transRequest = new AllTransactionRequest();
				transRequest.setPage(0);
				transRequest.setSize(1000000);
				transRequest.setSessionId((String) session.getAttribute("adminSessionId"));
				AllTransactionResponse transResponse = appAdminApi.getAllTransaction(transRequest);
				AllUserRequest userRequest = new AllUserRequest();
				userRequest.setPage(0);
				userRequest.setSize(1000000);
				userRequest.setSessionId((String) session.getAttribute("adminSessionId"));
				userRequest.setStatus(UserStatus.ONLINE);
				AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);

				userRequest.setPage(0);
				userRequest.setSize(1000000);
				userRequest.setSessionId((String) session.getAttribute("adminSessionId"));
				userRequest.setStatus(UserStatus.ALL);
				AllUserResponse onlineResponse = appAdminApi.getAllUser(userRequest);
				int m = 0;
				int f = 0;
				if(onlineResponse != null) {
					for (int i = 0; i < onlineResponse.getJsonArray().length(); i++) {
						String gender;
						try {
							gender = onlineResponse.getJsonArray().getJSONObject(i).getJSONObject("userDetail")
									.getString("gender");
							if (gender.equals("M")) {
								m++;
							} else if (gender.equals("F")) {
								f++;
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				}
				Calendar cal = Calendar.getInstance();
				transRequest.setStartDate(
						cal.get(Calendar.YEAR) + "-" + ((new Date().getMonth()) + 1) + "-" + cal.get(Calendar.DATE));
				transRequest.setEndDate(
						cal.get(Calendar.YEAR) + "-" + ((new Date().getMonth()) + 1) + "-" + cal.get(Calendar.DATE));

				transRequest.setSessionId((String) session.getAttribute("adminSessionId"));

				AllTransactionRequest a1 = new AllTransactionRequest();
				AllTransactionRequest b1 = new AllTransactionRequest();
				AllTransactionRequest c1 = new AllTransactionRequest();
				AllTransactionRequest d1 = new AllTransactionRequest();
				AllTransactionRequest e1 = new AllTransactionRequest();
				AllTransactionRequest f1 = new AllTransactionRequest();

				cal.add(Calendar.DATE, -1);
				SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
				String adate = s.format(new Date(cal.getTimeInMillis()));
				a1.setPage(0);
				a1.setSize(1000);
				a1.setStartDate(adate);
				a1.setEndDate(adate);
				a1.setSessionId((String) session.getAttribute("adminSessionId"));

				cal.add(Calendar.DATE, -2);
				String bdate = s.format(new Date(cal.getTimeInMillis()));
				b1.setPage(0);
				b1.setSize(1000);
				b1.setStartDate(bdate);
				b1.setEndDate(bdate);
				b1.setSessionId((String) session.getAttribute("adminSessionId"));

				cal.add(Calendar.DATE, -3);
				String cdate = s.format(new Date(cal.getTimeInMillis()));
				c1.setPage(0);
				c1.setSize(1000);
				c1.setStartDate(cdate);
				c1.setEndDate(cdate);
				c1.setSessionId((String) session.getAttribute("adminSessionId"));

				cal.add(Calendar.DATE, -4);
				String ddate = s.format(new Date(cal.getTimeInMillis()));
				d1.setPage(0);
				d1.setSize(1000);
				d1.setStartDate(ddate);
				d1.setEndDate(ddate);
				d1.setSessionId((String) session.getAttribute("adminSessionId"));

				cal.add(Calendar.DATE, -5);
				String edate = s.format(new Date(cal.getTimeInMillis()));
				e1.setPage(0);
				e1.setSize(1000);
				e1.setStartDate(edate);
				e1.setEndDate(edate);
				e1.setSessionId((String) session.getAttribute("adminSessionId"));

				AllTransactionResponse byDate = appAdminApi.getDaily(transRequest);

				AllTransactionResponse aRes = appAdminApi.getDaily(a1);
				AllTransactionResponse bRes = appAdminApi.getDaily(b1);
				AllTransactionResponse cRes = appAdminApi.getDaily(c1);
				AllTransactionResponse dRes = appAdminApi.getDaily(d1);
				AllTransactionResponse eRes = appAdminApi.getDaily(e1);

				JSONArray byDateData = byDate.getJsonArray();

				JSONArray aJson = aRes.getJsonArray();
				JSONArray bJson = bRes.getJsonArray();
				JSONArray cJson = cRes.getJsonArray();
				JSONArray dJson = dRes.getJsonArray();
				JSONArray eJson = eRes.getJsonArray();

				transRequest.setSessionId((String) session.getAttribute("adminSessionId"));
				SessionDTO dto = new SessionDTO();
				dto.setSessionId((String) session.getAttribute("adminSessionId"));
				UserTransactionResponse userTransactionResponse = appAdminApi.getUserTransactionValues(dto);
				if(userTransactionResponse != null){
					if(userTransactionResponse.isSuccess()){
						JSONObject json = new JSONObject(userTransactionResponse.getResponse());
						if(json != null){
							JSONObject details = JSONParserUtil.getObject(json,"details");
							model.addAttribute("lme",JSONParserUtil.getDouble(details,"totalLoadMoneyEBS"));
							model.addAttribute("lmv",JSONParserUtil.getDouble(details,"totalLoadMoneyVNet"));
							model.addAttribute("pool",JSONParserUtil.getDouble(details,"walletBalance"));
							model.addAttribute("tpay",JSONParserUtil.getDouble(details,"totalPayable"));
							model.addAttribute("mpayable",JSONParserUtil.getDouble(details,"merchantPayable"));
							model.addAttribute("tcommission",JSONParserUtil.getDouble(details,"totalCommission"));
						}
					}
				}
				if (loginResponse.getCode() != null) {
					if (loginResponse.getCode().toString().equals("S00")) {
						session.setAttribute("adminSessionId", loginResponse.getSessionId());
						session.setAttribute("totalTrans", transResponse.getJsonArray().length());
						session.setAttribute("totalUser", userResponse.getTotalUser());
						session.setAttribute("onlineUser", onlineResponse.getTotalUser());
						session.setAttribute("totalMale", m);
						session.setAttribute("totalFemale", f);
						session.setAttribute("todayTransaction", byDateData.length());
						model.addAttribute("resp", loginResponse);
						model.addAttribute("totalUser", userResponse.getTotalUser());
						model.addAttribute("onlineUser", onlineResponse.getTotalUser());
						model.addAttribute("totalTrans", transResponse.getJsonArray().length());
						model.addAttribute("todayTransaction", byDateData.length());
						model.addAttribute("totalMale", m);
						model.addAttribute("totalFemale", f);
						Calendar c = Calendar.getInstance();
						GraphDTO gDto = new GraphDTO();
						gDto.setYear(c.get(Calendar.YEAR));
						gDto.setMonth(new Date().getMonth() + 1);
						gDto.setDay(c.get(Calendar.DATE));
						gDto.setAa(aJson.length());
						gDto.setBb(bJson.length());
						gDto.setCc(cJson.length());
						gDto.setDd(dJson.length());
						gDto.setEe(eJson.length());
						session.setAttribute("aa", aJson.length());
						session.setAttribute("bb", bJson.length());
						session.setAttribute("cc", cJson.length());
						session.setAttribute("dd", dJson.length());
						session.setAttribute("ee", eJson.length());
						session.setAttribute("year", cal.get(Calendar.YEAR));
						session.setAttribute("month", (new Date().getMonth()) + 1);
						session.setAttribute("date", cal.get(Calendar.DATE));
						modelMap.addAttribute("gDto", gDto);
						return "Admin/Home";
					}
				}



			} catch (Exception e) {
				e.printStackTrace();
			}
			return "Admin/Login";
	}

	
	@RequestMapping(method = RequestMethod.POST, value = "/UserList")
	ResponseEntity<UserListDTO> getUserListInJSON(@ModelAttribute PagingDTO dto, HttpServletRequest request, HttpServletResponse response, HttpSession session,
												  ModelMap model) {
		UserListDTO result = new UserListDTO();
		String pincode= null;
		String circleName=null;
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
			if(authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					AllUserRequest userRequest = new AllUserRequest();
					userRequest.setPage(dto.getPage());
					userRequest.setSize(dto.getSize());
					userRequest.setSessionId(sessionCheck);
					userRequest.setStatus(UserStatus.ALL);
					AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
					JSONArray data = userResponse.getJsonArray();
					List<AdminUserDetails> userList = new ArrayList<>();
					try {
						if (data != null ) {
							for (int i = 0; i < data.length(); i++) {
								AdminUserDetails list = new AdminUserDetails();
								JSONObject jsonobject = data.getJSONObject(i);
								String userType = JSONParserUtil.getString(jsonobject, "userType");
								//String pin=jsonobject.getJSONObject("userDetail").getJSONObject("location").getString("pinCode");
								//String circle=jsonobject.getJSONObject("userDetail").getJSONObject("location").getString("circleName");
								JSONObject userDetails = JSONParserUtil.getObject(jsonobject, "userDetail");
								if(userType.equalsIgnoreCase("User")){
									JSONObject obj=(JSONObject) jsonobject.getJSONObject("userDetail");
									System.out.println(obj);
								if(obj.isNull("location")){
									
									list.setAuthority(jsonobject.getString("authority"));
									list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
									list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
									list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
									list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
									list.setGender(jsonobject.getJSONObject("userDetail").getString("gender"));
									list.setDob(jsonobject.getJSONObject("userDetail").getString("dateOfBirth"));
									list.setKycNonkyc(jsonobject.getJSONObject("accountDetail").getJSONObject("accountType").getString("name"));
									list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
									list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
									list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
									list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
									list.setMobileToken(jsonobject.getString("mobileToken"));
									list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
									DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
									long milliSeconds = Long.parseLong(jsonobject.getString("created"));
									Calendar calendar = Calendar.getInstance();
									calendar.setTimeInMillis(milliSeconds);
									list.setDateOfAccountCreation(formatter.format(calendar.getTime()));
									//LogCat.print("User name " + list.getFirstName());
									userList.add(list);
									model.addAttribute("userlist", userList);
								}
								
								else {
									list.setPinCode(jsonobject.getJSONObject("userDetail").getJSONObject("location").getString("pinCode"));
									list.setCircleName(jsonobject.getJSONObject("userDetail").getJSONObject("location").getString("circleName"));
									list.setAuthority(jsonobject.getString("authority"));
									list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
									list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
									list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
									list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
									list.setGender(jsonobject.getJSONObject("userDetail").getString("gender"));
									list.setDob(jsonobject.getJSONObject("userDetail").getString("dateOfBirth"));
									list.setKycNonkyc(jsonobject.getJSONObject("accountDetail").getJSONObject("accountType").getString("name"));
									list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
									list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
									list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
									list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
									list.setMobileToken(jsonobject.getString("mobileToken"));
									list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
									DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
									long milliSeconds = Long.parseLong(jsonobject.getString("created"));
									Calendar calendar = Calendar.getInstance();
									calendar.setTimeInMillis(milliSeconds);
									list.setDateOfAccountCreation(formatter.format(calendar.getTime()));
									//LogCat.print("User name " + list.getFirstName());
									userList.add(list);
									model.addAttribute("userlist", userList);
								}
								}
							}
							result.setSuccess(true);
							result.setFirstPage(userResponse.isFirstPage());
							result.setLastPage(userResponse.isLastPage());
							result.setJsonArray(userList);
							result.setNumberOfElements(userResponse.getNumberOfElements());
							result.setSize(userResponse.getSize());
							result.setTotalPages(userResponse.getTotalPages());
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					return new ResponseEntity<UserListDTO>(result,HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<UserListDTO>(result,HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/VerifiedUsers")
	ResponseEntity<UserListDTO> getVerifiedUserListInJSON(@ModelAttribute PagingDTO dto, HttpServletRequest request, HttpServletResponse response, HttpSession session,
												  ModelMap model) {
		UserListDTO result = new UserListDTO();
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
			if(authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					AllUserRequest userRequest = new AllUserRequest();
					userRequest.setPage(dto.getPage());
					userRequest.setSize(dto.getSize());
					userRequest.setSessionId(sessionCheck);
					userRequest.setStatus(UserStatus.ACTIVE);
					AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
					JSONArray data = userResponse.getJsonArray();
					List<AdminUserDetails> userList = new ArrayList<>();
					try {
						if (data != null ) {
							for (int i = 0; i < data.length(); i++) {
								AdminUserDetails list = new AdminUserDetails();
								JSONObject jsonobject = data.getJSONObject(i);
								String userType = JSONParserUtil.getString(jsonobject, "userType");
								if (userType.equalsIgnoreCase("User")) {
									list.setAuthority(jsonobject.getString("authority"));
									list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
									list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
									list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
									list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
									list.setGender(jsonobject.getJSONObject("userDetail").getString("gender"));
									list.setDob(jsonobject.getJSONObject("userDetail").getString("dateOfBirth"));
									list.setKycNonkyc(jsonobject.getJSONObject("accountDetail").getJSONObject("accountType").getString("name"));
									list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
									list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
									list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
									list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
									list.setMobileToken(jsonobject.getString("mobileToken"));
									list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
									list.setEmailStatus(jsonobject.getString("emailStatus"));
									list.setMobileStatus(jsonobject.getString("mobileStatus"));
									DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
									long milliSeconds = Long.parseLong(jsonobject.getString("created"));
									Calendar calendar = Calendar.getInstance();
									calendar.setTimeInMillis(milliSeconds);
									list.setDateOfAccountCreation(formatter.format(calendar.getTime()));
									//LogCat.print("User name " + list.getFirstName());
									userList.add(list);
								}
							}
							result.setSuccess(true);
							result.setFirstPage(userResponse.isFirstPage());
							result.setLastPage(userResponse.isLastPage());
							result.setJsonArray(userList);
							result.setNumberOfElements(userResponse.getNumberOfElements());
							result.setSize(userResponse.getSize());
							result.setTotalPages(userResponse.getTotalPages());
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					return new ResponseEntity<UserListDTO>(result,HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<UserListDTO>(result,HttpStatus.OK);
	}
@RequestMapping(method=RequestMethod.POST,value="/UserListOnSearch")
public ResponseEntity<UserListDTO> getUserListByMobile(@RequestParam String search,HttpServletRequest request,HttpServletResponse response,HttpSession session,ModelMap model){
	UserListDTO result = new UserListDTO();
	
	
	String sessionCheck = (String) session.getAttribute("adminSessionId");
	if (sessionCheck != null) {
		String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
		if(authority != null) {
			if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
				AllUserRequest userRequest = new AllUserRequest();
				userRequest.setPage(0);
				userRequest.setSize(100000);
				userRequest.setSessionId(sessionCheck);
				userRequest.setStatus(UserStatus.ALL);
				AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
				JSONArray data = userResponse.getJsonArray();
				List<AdminUserDetails> userList = new ArrayList<>();
				try {
					if (data != null ) {
						for (int i = 0; i < data.length(); i++) {
							AdminUserDetails list = new AdminUserDetails();
							JSONObject jsonobject = data.getJSONObject(i);
							String userType = JSONParserUtil.getString(jsonobject, "userType");
							if (userType.equalsIgnoreCase("User")&& (jsonobject.getJSONObject("userDetail").getString("contactNo")).equalsIgnoreCase(search)) {
								list.setAuthority(jsonobject.getString("authority"));
								list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
								list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
								list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
								list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
								list.setGender(jsonobject.getJSONObject("userDetail").getString("gender"));
								list.setDob(jsonobject.getJSONObject("userDetail").getString("dateOfBirth"));
								list.setKycNonkyc(jsonobject.getJSONObject("accountDetail").getJSONObject("accountType").getString("name"));
								list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
								list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
								list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
								list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
								list.setMobileToken(jsonobject.getString("mobileToken"));
								list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
								DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
								long milliSeconds = Long.parseLong(jsonobject.getString("created"));
								Calendar calendar = Calendar.getInstance();
								calendar.setTimeInMillis(milliSeconds);
								list.setDateOfAccountCreation(formatter.format(calendar.getTime()));
								LogCat.print("User name " + list.getFirstName());
								userList.add(list);
							}
						}
						result.setSuccess(true);
						result.setFirstPage(userResponse.isFirstPage());
						result.setLastPage(userResponse.isLastPage());
						result.setJsonArray(userList);
						result.setNumberOfElements(userResponse.getNumberOfElements());
						result.setSize(userResponse.getSize());
						result.setTotalPages(userResponse.getTotalPages());
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return new ResponseEntity<UserListDTO>(result,HttpStatus.OK);
			}
		}
	}
	return new ResponseEntity<UserListDTO>(result,HttpStatus.OK);
}
	

@RequestMapping(method=RequestMethod.GET,value="/UserListOnSearch")
public String searchedUsersList(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model)
{

		UserListDTO result=new UserListDTO();
			String sessionCheck = (String) session.getAttribute("adminSessionId");
				if (sessionCheck != null) {
						String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
							if(authority != null) {
									if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
			AllUserRequest userRequest = new AllUserRequest();
			userRequest.setPage(0);
			userRequest.setSize(100000);
			userRequest.setSessionId(sessionCheck);
			userRequest.setStatus(UserStatus.ALL);
			AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
			JSONArray data = userResponse.getJsonArray();
			List<AdminUserDetails> userList = new ArrayList<>();
			try {
				if (data != null ) {
					for (int i = 0; i < data.length(); i++) {
						AdminUserDetails list = new AdminUserDetails();
						JSONObject jsonobject = data.getJSONObject(i);
						String userType = JSONParserUtil.getString(jsonobject, "userType");
						if (userType.equalsIgnoreCase("User")&&(jsonobject.getJSONObject("userDetail").getString("contactNo")).equalsIgnoreCase(request.getParameter("search"))) {
							list.setAuthority(jsonobject.getString("authority"));
							list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
							list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
							list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
							list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
							list.setGender(jsonobject.getJSONObject("userDetail").getString("gender"));
							list.setDob(jsonobject.getJSONObject("userDetail").getString("dateOfBirth"));
							list.setKycNonkyc(jsonobject.getJSONObject("accountDetail").getJSONObject("accountType").getString("name"));
							list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
							list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
							list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
							list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
							list.setMobileToken(jsonobject.getString("mobileToken"));
							list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
							DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
							long milliSeconds = Long.parseLong(jsonobject.getString("created"));
							Calendar calendar = Calendar.getInstance();
							calendar.setTimeInMillis(milliSeconds);
							list.setDateOfAccountCreation(formatter.format(calendar.getTime()));
							userList.add(list);
							model.addAttribute("searchedlist",userList);
							System.out.println("boooom--"+userList);
							
							}
								
					}
						}
					
					result.setSuccess(true);
					result.setFirstPage(userResponse.isFirstPage());
					result.setLastPage(userResponse.isLastPage());
					result.setJsonArray(userList);
					result.setNumberOfElements(userResponse.getNumberOfElements());
					result.setSize(userResponse.getSize());
					result.setTotalPages(userResponse.getTotalPages());
				
									
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return "Admin/SearchUser";
		}
	}
}
return "Static/404";

}

	
	@RequestMapping(method = RequestMethod.GET, value = "/UserList")
	public String getUserList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {
		String mobile=request.getParameter("search");

		UserListDTO result=new UserListDTO();
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
			if(authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					AllUserRequest userRequest = new AllUserRequest();
					userRequest.setPage(0);
					userRequest.setSize(10);
					userRequest.setSessionId(sessionCheck);
					userRequest.setStatus(UserStatus.ALL);
					AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
					JSONArray data = userResponse.getJsonArray();
					List<AdminUserDetails> userList = new ArrayList<>();
//					try {
//						if (data != null ) {
//							for (int i = 0; i < data.length(); i++) {
//								AdminUserDetails list = new AdminUserDetails();
//								JSONObject jsonobject = data.getJSONObject(i);
//								String userType = JSONParserUtil.getString(jsonobject, "userType");
//								if (userType.equalsIgnoreCase("User")) {
//									list.setAuthority(jsonobject.getString("authority"));
//									list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
//									list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
//									list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
//									list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
//									list.setGender(jsonobject.getJSONObject("userDetail").getString("gender"));
//									list.setDob(jsonobject.getJSONObject("userDetail").getString("dateOfBirth"));
//									list.setKycNonkyc(jsonobject.getJSONObject("accountDetail").getJSONObject("accountType").getString("name"));
//									list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
//									list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
//									list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
//									list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
//									list.setMobileToken(jsonobject.getString("mobileToken"));
//									list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
//									DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
//									long milliSeconds = Long.parseLong(jsonobject.getString("created"));
//									Calendar calendar = Calendar.getInstance();
//									calendar.setTimeInMillis(milliSeconds);
//									list.setDateOfAccountCreation(formatter.format(calendar.getTime()));
//								//	LogCat.print("User name " + list.getFirstName());
//									if(list.getContactNo().equalsIgnoreCase(mobile)){
//									//userList.add(list);
//									//System.out.println("boooom--"+userList);
//									}
//
//
//								}
//							}
							result.setSuccess(true);
							result.setFirstPage(userResponse.isFirstPage());
							result.setLastPage(userResponse.isLastPage());
							result.setJsonArray(userList);
							result.setNumberOfElements(userResponse.getNumberOfElements());
							result.setSize(userResponse.getSize());
							result.setTotalPages(userResponse.getTotalPages());
						}
//
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
					return "Admin/UserList";
				}
			}

		return "redirect:/Admin/Home";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/BlockedUsers")
	ResponseEntity<UserListDTO> getBlockedUsersInJSON(@ModelAttribute PagingDTO dto,HttpServletRequest request, HttpServletResponse response, HttpSession session,
								  ModelMap model) {
		UserListDTO result = new UserListDTO();
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					AllUserRequest userRequest = new AllUserRequest();
					userRequest.setPage(dto.getPage());
					userRequest.setSize(dto.getSize());
					userRequest.setSessionId((String) session.getAttribute("adminSessionId"));
					userRequest.setStatus(UserStatus.BLOCKED);
					AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
					JSONArray data = userResponse.getJsonArray();
					List<AdminUserDetails> userList = new ArrayList<>();
					try {
						if (data != null) {
							for (int i = 0; i < data.length(); i++) {
								AdminUserDetails list = new AdminUserDetails();
								JSONObject jsonobject = data.getJSONObject(i);
								String userType = JSONParserUtil.getString(jsonobject, "userType");
								if (userType.equalsIgnoreCase("User")) {
									list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
									list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
									list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
									list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
									list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
									list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
									list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
									list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
									list.setMobileToken(jsonobject.getString("mobileToken"));
									userList.add(list);
								}
							}
							//model.put("userlist", userList);
							result.setSuccess(true);
							result.setFirstPage(userResponse.isFirstPage());
							result.setLastPage(userResponse.isLastPage());
							result.setJsonArray(userList);
							result.setNumberOfElements(userResponse.getNumberOfElements());
							result.setSize(userResponse.getSize());
							result.setTotalPages(userResponse.getTotalPages());

						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					return new ResponseEntity<UserListDTO>(result,HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<UserListDTO>(result,HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.GET, value = "/BlockedUsers")
	public String getUserVerified(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					AllUserRequest userRequest = new AllUserRequest();
					userRequest.setPage(0);
					userRequest.setSize(10);
					userRequest.setSessionId((String) session.getAttribute("adminSessionId"));
					userRequest.setStatus(UserStatus.BLOCKED);
					AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
					JSONArray data = userResponse.getJsonArray();
					List<AdminUserDetails> userList = new ArrayList<>();
//					try {
//						if (data != null) {
//							for (int i = 0; i < data.length(); i++) {
//								AdminUserDetails list = new AdminUserDetails();
//								JSONObject jsonobject = data.getJSONObject(i);
//								String userType = JSONParserUtil.getString(jsonobject, "userType");
//								if (userType.equalsIgnoreCase("User")) {
//									list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
//									LogCat.print(list.getFirstName());
//									list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
//									LogCat.print(list.getLastname());
//									list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
//									LogCat.print(list.getUsername());
//									list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
//									list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
//									list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
//									list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
//									list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
//									list.setMobileToken(jsonobject.getString("mobileToken"));
//									LogCat.print("User name " + list.getFirstName());
//									userList.add(list);
//								}
//							}
//							model.put("userlist", userList);
//						}
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
					return "Admin/BlockedUsers";
				}
			}
		}
		return "redirect:/Admin/Home";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/LockedUsers")
	ResponseEntity<UserListDTO> getUserLockedInJSON(@ModelAttribute PagingDTO dto,HttpServletRequest request, HttpServletResponse response, HttpSession session,
								ModelMap model) {
		UserListDTO result = new UserListDTO();
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			String adminAuthority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
			if(adminAuthority != null) {
				if(adminAuthority.contains(Authorities.ADMINISTRATOR) && adminAuthority.contains(Authorities.AUTHENTICATED)) {
					AllUserRequest userRequest = new AllUserRequest();
					userRequest.setPage(dto.getPage());
					userRequest.setSize(dto.getSize());
					userRequest.setSessionId(sessionCheck);
					userRequest.setStatus(UserStatus.ALL);
					AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
					JSONArray data = userResponse.getJsonArray();
					List<AdminUserDetails> userList = new ArrayList<>();
					try {
						if(data != null) {
							for (int i = 0; i < data.length(); i++) {
								AdminUserDetails list = new AdminUserDetails();
								JSONObject jsonobject = data.getJSONObject(i);
								String userType = JSONParserUtil.getString(jsonobject, "userType");
								String authority = JSONParserUtil.getString(jsonobject, "authority");
								if (userType.equalsIgnoreCase("User")) {
									if (authority.contains(Authorities.LOCKED) && authority.contains(Authorities.USER)) {
										list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
										list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
										list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
										list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
										list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
										list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
										list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
										list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
										list.setMobileToken(jsonobject.getString("mobileToken"));
										userList.add(list);
									}
								}
							}
							result.setJsonArray(userList);
							result.setTotalPages(userResponse.getTotalPages());
							result.setSize(userResponse.getSize());
							result.setSuccess(true);
							result.setNumberOfElements(userResponse.getNumberOfElements());
							result.setFirstPage(userResponse.isFirstPage());
							result.setLastPage(userResponse.isLastPage());
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					return new ResponseEntity<UserListDTO>(result,HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<UserListDTO>(result,HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.GET, value = "/LockedUsers")
	public String getUserLocked(HttpServletRequest request, HttpServletResponse response, HttpSession session,
								  ModelMap model) {

		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
					String adminAuthority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
					if(adminAuthority != null) {
						if(adminAuthority.contains(Authorities.ADMINISTRATOR) && adminAuthority.contains(Authorities.AUTHENTICATED)) {
							AllUserRequest userRequest = new AllUserRequest();
							userRequest.setPage(0);
							userRequest.setSize(10);
							userRequest.setSessionId(sessionCheck);
							userRequest.setStatus(UserStatus.LOCKED);
							AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
							JSONArray data = userResponse.getJsonArray();
							List<AdminUserDetails> userList = new ArrayList<>();
							try {
								if(data != null) {
									for (int i = 0; i < data.length(); i++) {
										AdminUserDetails list = new AdminUserDetails();
										JSONObject jsonobject = data.getJSONObject(i);
										String userType = JSONParserUtil.getString(jsonobject, "userType");
										String authority = JSONParserUtil.getString(jsonobject, "authority");
										if (userType.equalsIgnoreCase("User")) {
											if (authority.contains(Authorities.LOCKED) && authority.contains(Authorities.USER)) {
												list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
												LogCat.print(list.getFirstName());
												list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
												LogCat.print(list.getLastname());
												list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
												LogCat.print(list.getUsername());
												list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
												list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
												list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
												list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
												list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
												list.setMobileToken(jsonobject.getString("mobileToken"));
												LogCat.print("User name " + list.getFirstName());
												userList.add(list);
											}
										}
									}
									model.put("userlist", userList);
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}
							return "Admin/LockedUsers";
						}
					}
				}
		return "redirect:/Admin/Home";
	}


//	@RequestMapping(method=RequestMethod.GET, value="/Merchant/{merchantName}")
//	public String getTransactionsOfMerchant(@PathVariable String email,HttpServletRequest request,HttpServletResponse response,HttpSession session)
//	{
//		
//		
//		
//	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/ActiveUsers")
	public String getActiveUsers(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {

		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
					if(authority != null) {
						if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
							AllUserRequest userRequest = new AllUserRequest();
							userRequest.setPage(0);
							userRequest.setSize(10);
							userRequest.setSessionId(sessionCheck);
							userRequest.setStatus(UserStatus.ONLINE);
							AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
							JSONArray data = userResponse.getJsonArray();
							List<AdminUserDetails> userList = new ArrayList<>();
//							try {
//								if(data != null) {
//									for (int i = 0; i < data.length(); i++) {
//										AdminUserDetails list = new AdminUserDetails();
//										JSONObject jsonobject = data.getJSONObject(i);
//										String userType = JSONParserUtil.getString(jsonobject, "userType");
//										if (userType.equalsIgnoreCase("User")) {
//											list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
//											LogCat.print(list.getFirstName());
//											list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
//											LogCat.print(list.getLastname());
//											list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
//											LogCat.print(list.getUsername());
//											list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
//
//											list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
//											list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
//											list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
//											list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
//											list.setMobileToken(jsonobject.getString("mobileToken"));
//
//											LogCat.print("User name " + list.getFirstName());
//											userList.add(list);
//										}
//									}
//									model.put("userlist", userList);
//								}
//							} catch (JSONException e) {
//								e.printStackTrace();
//							}
							return "Admin/ActiveUsers";
						}
					}
				}


		return "redirect:/Admin/Home";
	}


	@RequestMapping(method = RequestMethod.POST, value = "/ActiveUsers")
	ResponseEntity<UserListDTO> getActiveUserListInJSON(@ModelAttribute PagingDTO dto, HttpServletRequest request, HttpServletResponse response, HttpSession session,
														  ModelMap model) {
		UserListDTO result = new UserListDTO();
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
			if(authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					AllUserRequest userRequest = new AllUserRequest();
					userRequest.setPage(dto.getPage());
					userRequest.setSize(dto.getSize());
					userRequest.setSessionId(sessionCheck);
					userRequest.setStatus(UserStatus.ACTIVE);
					AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
					JSONArray data = userResponse.getJsonArray();
					List<AdminUserDetails> userList = new ArrayList<>();
					try {
						if (data != null ) {
							for (int i = 0; i < data.length(); i++) {
								AdminUserDetails list = new AdminUserDetails();
								JSONObject jsonobject = data.getJSONObject(i);
								String userType = JSONParserUtil.getString(jsonobject, "userType");
								if (userType.equalsIgnoreCase("User")) {
									list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
									list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
									list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
									list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));

									list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
									list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
									list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
									list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
									list.setMobileToken(jsonobject.getString("mobileToken"));
									userList.add(list);
								}
							}
							result.setSuccess(true);
							result.setFirstPage(userResponse.isFirstPage());
							result.setLastPage(userResponse.isLastPage());
							result.setJsonArray(userList);
							result.setNumberOfElements(userResponse.getNumberOfElements());
							result.setSize(userResponse.getSize());
							result.setTotalPages(userResponse.getTotalPages());
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					return new ResponseEntity<UserListDTO>(result,HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<UserListDTO>(result,HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/NEFTList")
	public String getNEFTList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
								 ModelMap model) {

		String sessionCheck = (String) session.getAttribute("adminSessionId");

		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
			if (authority != null) {
				if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					SessionDTO dto = new SessionDTO();
					dto.setSessionId(sessionCheck);
					List<NEFTResponse> neftList = appAdminApi.getNEFTList(dto,false,null,null);
					System.out.println("this is neft"+neftList);
					model.addAttribute("neftList", neftList);
					return "Admin/NEFTList";
				}
			}
		}
		return "redirect:/Admin/Home";
	}
	@RequestMapping(method = RequestMethod.GET, value = "/MerchantNEFTList")
	public String getMerchantNEFTList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
								 ModelMap model) {

		String sessionCheck = (String) session.getAttribute("adminSessionId");

		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
			if (authority != null) {
				if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					SessionDTO dto = new SessionDTO();
					dto.setSessionId(sessionCheck);
					List<NEFTResponse> neftList = appAdminApi.getNEFTList(dto);
					System.out.println("this is neft"+neftList);
					model.addAttribute("neftList", neftList);
					return "Admin/MerchantNeft";
				}
			}
		}
		return "redirect:/Admin/Home";
	}
	@RequestMapping(method=RequestMethod.POST,value="/neftListFiltered")
	public String getNeftListFiltered(@ModelAttribute TransactionFilter filter,HttpServletRequest request,HttpServletResponse response,Model model,HttpSession session) throws ParseException
	{
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		String toDateTime = filter.getEndDate()+" 23:59";
		String fromDateTime = filter.getStartDate()+" 00:00";
		Date to = dateFilter.parse(toDateTime);
		Date from = dateFilter.parse(fromDateTime);
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
			if (authority != null) {
				if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					SessionDTO dto = new SessionDTO();
					dto.setSessionId(sessionCheck);
					List<NEFTResponse> neftList = appAdminApi.getNEFTList(dto,true,from,to);
					
					System.out.println("this is neft"+neftList);
					model.addAttribute("neftList", neftList);
					return "Admin/NEFTList";
				}
			}
		}
		return "redirect:/Admin/Home";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/GetServiceTypes")
	ResponseEntity<ServiceTypeResponse> getServiceType(HttpServletRequest request, HttpServletResponse response, HttpSession session,
							  ModelMap model) {

		String sessionCheck = (String) session.getAttribute("adminSessionId");
		ServiceTypeResponse serviceTypeResponse = null;
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
			if (authority != null) {
				if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					SessionDTO dto = new SessionDTO();
					dto.setSessionId(sessionCheck);
					serviceTypeResponse = appAdminApi.getServiceType();
					return new ResponseEntity<ServiceTypeResponse>(serviceTypeResponse,HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<ServiceTypeResponse>(serviceTypeResponse,HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/VerifiedUsers")
	public String getVerifiedUsers(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
			if(authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					AllUserRequest userRequest = new AllUserRequest();
					userRequest.setPage(0);
					userRequest.setSize(10);
					userRequest.setSessionId((String) session.getAttribute("adminSessionId"));
					userRequest.setStatus(UserStatus.ACTIVE);
					AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
					JSONArray data = userResponse.getJsonArray();
					List<AdminUserDetails> userList = new ArrayList<>();
//					try {
//						if(data != null) {
//							for (int i = 0; i < data.length(); i++) {
//								AdminUserDetails list = new AdminUserDetails();
//								JSONObject jsonobject = data.getJSONObject(i);
//								String userType = JSONParserUtil.getString(jsonobject, "userType");
//								if (userType.equalsIgnoreCase("User")) {
//									list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
//									list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
//									list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
//									list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
//									list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
//									list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
//									list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
//									list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
//									list.setEmailStatus(jsonobject.getString("emailStatus"));
//									list.setMobileStatus(jsonobject.getString("mobileStatus"));
//									list.setMobileToken(jsonobject.getString("mobileToken"));
//									userList.add(list);
//								}
//							}
//
//							model.put("userlist", userList);
//						}
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}

					return "Admin/VerifiedUsers";
				}
			}
		}
		return "redirect:/Admin/Home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/Users/VerifiedUsers",produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdminUserDetails> getVerifiedUsersInJSON(HttpServletRequest request, HttpServletResponse response, HttpSession session,
																   ModelMap model) {
		JSONArray array = new JSONArray();
		AdminUserDetails result = new AdminUserDetails();
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
			if(authority != null) {
				if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					AllUserRequest userRequest = new AllUserRequest();
					userRequest.setPage(0);
					userRequest.setSize(100);
					userRequest.setSessionId(sessionCheck);
					userRequest.setStatus(UserStatus.ACTIVE);
					AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
					JSONArray data = userResponse.getJsonArray();
					try {
						for (int i = 0; i < data.length(); i++) {
							AdminUserDetails list = new AdminUserDetails();
							JSONObject jsonobject = data.getJSONObject(i);
							String userType = JSONParserUtil.getString(jsonobject, "userType");
							if (userType.equalsIgnoreCase("User")) {
								list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
								list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
								list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
								list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
								list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
								array.put(list);
							}
						}
						result.setJsonArray(array);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return new ResponseEntity<AdminUserDetails>(result, HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.GET, value = "/UnverifiedUsers")
	public String getUnverifiedUsers(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {

		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
					String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
					if(authority != null) {
						if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
							AllUserRequest userRequest = new AllUserRequest();
							userRequest.setPage(0);
							userRequest.setSize(10);
							userRequest.setSessionId(sessionCheck);
							userRequest.setStatus(UserStatus.INACTIVE);
							AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
							JSONArray data = userResponse.getJsonArray();
							List<AdminUserDetails> userList = new ArrayList<>();
//							try {
//								if(data != null) {
//									for (int i = 0; i < data.length(); i++) {
//										AdminUserDetails list = new AdminUserDetails();
//										JSONObject jsonobject = data.getJSONObject(i);
//										String userType = JSONParserUtil.getString(jsonobject, "userType");
//										if (userType.equalsIgnoreCase("User")) {
//											list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
//											LogCat.print(list.getFirstName());
//											list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
//											LogCat.print(list.getLastname());
//											list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
//											LogCat.print(list.getUsername());
//											list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
//											list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
//											list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
//											list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
//											list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
//											list.setMobileToken(jsonobject.getString("mobileToken"));
//											list.setEmailStatus(jsonobject.getString("emailStatus"));
//											list.setMobileStatus(jsonobject.getString("mobileStatus"));
//											LogCat.print("User name " + list.getFirstName());
//											userList.add(list);
//										}
//									}
//									model.put("userlist", userList);
//								}
//							} catch (JSONException e) {
//								e.printStackTrace();
//							}
							return "Admin/UnverifiedUsers";
						}
					}
				}


		return "redirect:/Admin/Home";
	}

	@RequestMapping(method=RequestMethod.GET,value="/KYCusers")
	public String getKycUsers(HttpServletRequest request,HttpServletResponse response,Model model,HttpSession session)
	{
		System.out.println("inside getkyc users method");
		UserListDTO result = new UserListDTO();
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
			if(authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					AllUserRequest userRequest = new AllUserRequest();
					userRequest.setPage(0);
					userRequest.setSize(1000000);
					userRequest.setSessionId(sessionCheck);
					userRequest.setStatus(UserStatus.ALL);
					AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
					JSONArray data = userResponse.getJsonArray();
					System.out.println("this is response...."+userResponse);
					List<AdminUserDetails> userList = new ArrayList<>();
					try {
						if (data != null ) {
							for (int i = 0; i < data.length(); i++) {
								AdminUserDetails list = new AdminUserDetails();
								JSONObject jsonobject = data.getJSONObject(i);
								String userType = JSONParserUtil.getString(jsonobject, "userType");
								String kycnonkyc=jsonobject.getJSONObject("accountDetail").getJSONObject("accountType").getString("name");
								//System.out.println(kycnonkyc +"this is a demo message");
								
								if (userType.equalsIgnoreCase("User") && kycnonkyc.equalsIgnoreCase("KYC")) {
									list.setAuthority(jsonobject.getString("authority"));
									list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
									list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
									list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
									list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
									list.setGender(jsonobject.getJSONObject("userDetail").getString("gender"));
									list.setDob(jsonobject.getJSONObject("userDetail").getString("dateOfBirth"));
									list.setKycNonkyc(jsonobject.getJSONObject("accountDetail").getJSONObject("accountType").getString("name"));
									list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
									list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
									list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
									list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
									list.setMobileToken(jsonobject.getString("mobileToken"));
									list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
									DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
									long milliSeconds = Long.parseLong(jsonobject.getString("created"));
									Calendar calendar = Calendar.getInstance();
									calendar.setTimeInMillis(milliSeconds);
									list.setDateOfAccountCreation(formatter.format(calendar.getTime()));
									//LogCat.print("User name " + list.getFirstName());
									userList.add(list);
								}
							}
							model.addAttribute("KYCuserlist", userList);
							return "Admin/KYCusers";
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
				}
			}
		}
		return "redirect:/Admin/Home";
	}

	

	@RequestMapping(method = RequestMethod.POST, value = "/UnverifiedUsers")
	ResponseEntity<UserListDTO> getUnverifiedUserListInJSON(@ModelAttribute PagingDTO dto, HttpServletRequest request, HttpServletResponse response, HttpSession session,
														ModelMap model) {
		UserListDTO result = new UserListDTO();
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
			if(authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					AllUserRequest userRequest = new AllUserRequest();
					userRequest.setPage(dto.getPage());
					userRequest.setSize(dto.getSize());
					userRequest.setSessionId(sessionCheck);
					userRequest.setStatus(UserStatus.INACTIVE);
					AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
					JSONArray data = userResponse.getJsonArray();
					List<AdminUserDetails> userList = new ArrayList<>();
					try {
						if (data != null ) {
							for (int i = 0; i < data.length(); i++) {
								AdminUserDetails list = new AdminUserDetails();
								JSONObject jsonobject = data.getJSONObject(i);
								String userType = JSONParserUtil.getString(jsonobject, "userType");
								if (userType.equalsIgnoreCase("User")) {
									list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
									list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
									list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
									list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
									list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
									list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
									list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
									list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
									list.setMobileToken(jsonobject.getString("mobileToken"));
									list.setEmailStatus(jsonobject.getString("emailStatus"));
									list.setMobileStatus(jsonobject.getString("mobileStatus"));
									userList.add(list);
								}
							}
							result.setSuccess(true);
							result.setFirstPage(userResponse.isFirstPage());
							result.setLastPage(userResponse.isLastPage());
							result.setJsonArray(userList);
							result.setNumberOfElements(userResponse.getNumberOfElements());
							result.setSize(userResponse.getSize());
							result.setTotalPages(userResponse.getTotalPages());
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					return new ResponseEntity<UserListDTO>(result,HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<UserListDTO>(result,HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.GET, value = "/TransactionReport")
	public String getTransactionReports(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model)
			throws JSONException {
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
//			String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
//			if(authority != null) {
//				if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
//					AllTransactionRequest transRequest = new AllTransactionRequest();
//					transRequest.setPage(0);
//					transRequest.setSize(10000);
//					transRequest.setSessionId(sessionCheck);
//					AllTransactionResponse allTransaction = appAdminApi.getAllTransaction(transRequest);
//					SessionDTO sessionDTO = new SessionDTO();
//					sessionDTO.setSessionId(sessionCheck);
//					UserTransactionResponse userTransactionResponse = appAdminApi.getUserTransactionValues(sessionDTO);
//					if (userTransactionResponse != null) {
//						if (userTransactionResponse.isSuccess()) {
//							JSONObject json = null;
//							try {
//								json = new JSONObject(userTransactionResponse.getResponse());
//							} catch (JSONException e) {
//							}
//							if (json != null) {
//								JSONObject details = JSONParserUtil.getObject(json, "details");
//								model.addAttribute("nlme", JSONParserUtil.getDouble(details, "totalLoadMoneyEBSNow"));
//								model.addAttribute("nlmv", JSONParserUtil.getDouble(details, "totalLoadMoneyVNETNow"));
//								model.addAttribute("ntopup", JSONParserUtil.getDouble(details, "totalPayableNow"));
//							}
//						}
//					}
//					AllUserRequest user = new AllUserRequest();
//					user.setPage(0);
//					user.setSize(100000);
//					user.setSessionId((String) session.getAttribute("adminSessionId"));
//					user.setStatus(UserStatus.ALL);
//					AllUserResponse allUsers = appAdminApi.getAllUser(user);
//
//					List<AdminUserDetails> userList = new ArrayList<AdminUserDetails>();
//
//					JSONArray users = allUsers.getJsonArray();
//					JSONArray transactions = allTransaction.getJsonArray();
//					if(users != null && transactions != null) {
//						for (int i = 0; i < transactions.length(); i++) {
//							for (int j = 0; j < users.length(); j++) {
//								JSONObject singleT = transactions.getJSONObject(i);
//								String id = singleT.getJSONObject("account").getString("id");
//								AdminUserDetails list = new AdminUserDetails();
//								JSONObject singleU = users.getJSONObject(j);
//								String di = singleU.getJSONObject("accountDetail").getString("id");
//								// if(!singleT.getJSONObject("userDetail").get("name").equals("instantpay@payqwik.in")){
//								if (di.equals(id) && !singleU.get("username").equals("instantpay@payqwik.in")) {
//									if (!singleT.get("transactionType").equals("COMMISSION")
//											&& !singleT.get("transactionType").equals("SETTLEMENT")) {
//
//										long milliSeconds = Long.parseLong(singleT.getString("created"));
//										Calendar calendar = Calendar.getInstance();
//										calendar.setTimeInMillis(milliSeconds);
//										list.setDateOfTransaction(dateFilter.format(calendar.getTime()));
//										list.setAmount(singleT.getString("amount"));
//
//										list.setCurrentBalance(singleT.getString("currentBalance"));
//										list.setStatus(singleT.getString("status"));
//										list.setTransactionRefNo(removeLastChar(singleT.getString("transactionRefNo")));
//										list.setServiceType(singleT.getString("description"));
//										list.setUsername(singleU.getJSONObject("userDetail").getString("firstName") + " "
//												+ singleU.getJSONObject("userDetail").getString("lastName"));
//										list.setContactNo(singleU.getJSONObject("userDetail").getString("contactNo"));
//										list.setBalance(singleU.getJSONObject("accountDetail").getDouble("balance"));
//										list.setDateOfTransaction(dateFilter.format(calendar.getTime()));
//										if ((singleT.getBoolean("debit"))) {
//											list.setDebit(singleT.getString("amount"));
//										} else {
//											list.setCredit(singleT.getString("amount"));
//										}
//										userList.add(list);
//									}
//
//
//								}
//							}
//						}
//					}
//					try {
//						model.put("userList", userList);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					return "Admin/DailyReport";
//				}
//			
			return "Admin/DailyReport";
		}
		return "redirect:/Admin/Home";
	}
	@RequestMapping(method = RequestMethod.POST, value = "/TransactionReportInJSON")
	ResponseEntity<UserListDTO> getTransactionReportsInJSON(@ModelAttribute PagingDTO dto,HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model)
			throws JSONException {
		System.out.println("inside tx report.....");
		UserListDTO resultSet = new UserListDTO();
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		//String sessionCheck = dto.getSessionId();
		System.out.println(sessionCheck);
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
			if(authority != null) {
				if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					AllTransactionRequest transRequest = new AllTransactionRequest();
					transRequest.setPage(dto.getPage());
					transRequest.setSize(dto.getSize());
					transRequest.setSessionId(sessionCheck);
					AllUserResponse allTransaction = appAdminApi.getAllTransactions(transRequest);
					List<TransactionListResponse> results = new ArrayList<>();
					JSONArray listArray = allTransaction.getJsonArray();
					for(int i=0 ; i < allTransaction.getJsonArray().length(); i++){
						JSONObject json = listArray.getJSONObject(i);
						TransactionListResponse userListDTO = new TransactionListResponse();
						userListDTO.setId(JSONParserUtil.getLong(json,"id"));
						userListDTO.setUsername(JSONParserUtil.getString(json,"username"));
						//userListDTO.setAmount(JSONParserUtil.getDouble(json,"amount"));
						userListDTO.setAuthority(JSONParserUtil.getString(json,"authority"));
						userListDTO.setCommission(JSONParserUtil.getDouble(json,"commission"));
						userListDTO.setContactNo(JSONParserUtil.getString(json,"contactNo"));
						userListDTO.setCurrentBalance(JSONParserUtil.getDouble(json,"currentBalance"));
						long milliSeconds = JSONParserUtil.getLong(json,"dateOfTransaction");
						Calendar calendar = Calendar.getInstance();
						calendar.setTimeInMillis(milliSeconds);
						userListDTO.setDateOfTransaction(dateFilter.format(calendar.getTime()));
						if(JSONParserUtil.getBoolean(json, "debit")){
							userListDTO.setAmountPayable((Double.parseDouble(json.getString("amount"))));
						}else
						{
							userListDTO.setAmountReceivable((Double.parseDouble(json.getString("amount"))));
						}
						userListDTO.setDebit(JSONParserUtil.getBoolean(json,"debit"));
						userListDTO.setTransactionRefNo(JSONParserUtil.getString(json,"transactionRefNo"));
						userListDTO.setDescription(JSONParserUtil.getString(json,"description"));
						userListDTO.setEmail(JSONParserUtil.getString(json,"email"));
						userListDTO.setServiceType(JSONParserUtil.getString(json,"serviceType"));
						userListDTO.setStatus(Status.valueOf(JSONParserUtil.getString(json, "status")));
						results.add(userListDTO);
					}

					resultSet.setSuccess(allTransaction.isSuccess());
					resultSet.setJsonArray(results);
					resultSet.setFirstPage(allTransaction.isFirstPage());
					resultSet.setLastPage(allTransaction.isLastPage());
					resultSet.setNumberOfElements(allTransaction.getNumberOfElements());
					resultSet.setSize(allTransaction.getSize());
					resultSet.setTotalPages(allTransaction.getTotalPages());
					//return new ResponseEntity<UserListDTO>(resultSet,HttpStatus.OK);
					//System.out.println("LIST::"+results);
				}
			}
		}

		return new ResponseEntity<UserListDTO>(resultSet,HttpStatus.OK);
	}

	
	@RequestMapping(method = RequestMethod.POST, value = "/TransactionReport")
	public String filterTransactionReports(@ModelAttribute TransactionFilter dto,HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model)
			throws JSONException {
		try {
            System.out.println("inside txxxxxxxxxxxxxxxx....");
			String toDateTime = dto.getEndDate()+" 23:59";
			String fromDateTime = dto.getStartDate()+" 00:00";
			Date to = dateFilter.parse(toDateTime);
			Date from = dateFilter.parse(fromDateTime);
			System.out.println("tx to:"+to);
			System.out.println("tx from:"+from);

			String sessionCheck = (String) session.getAttribute("adminSessionId");
			if (sessionCheck != null) {
				String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
				if(authority != null) {
					if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
						SessionDTO sessionDTO = new SessionDTO();
						sessionDTO.setSessionId(sessionCheck);
						UserTransactionResponse userTransactionResponse = appAdminApi.getUserTransactionValues(sessionDTO);
						if (userTransactionResponse != null) {
							if (userTransactionResponse.isSuccess()) {
								JSONObject json = null;
								try {
									json = new JSONObject(userTransactionResponse.getResponse());
								} catch (JSONException e) {
								}
								if (json != null) {
									JSONObject details = JSONParserUtil.getObject(json, "details");
									model.addAttribute("nlme", JSONParserUtil.getDouble(details, "totalLoadMoneyEBSNow"));
									model.addAttribute("nlmv", JSONParserUtil.getDouble(details, "totalLoadMoneyVNETNow"));
									model.addAttribute("ntopup", JSONParserUtil.getDouble(details, "totalPayableNow"));
								}
							}
						}
						model.addAttribute(ModelMapKey.MESSAGE, "Transactions From " + dto.getStartDate() + " to " + dto.getEndDate());
						AllTransactionRequest transRequest = new AllTransactionRequest();
						transRequest.setPage(0);
						transRequest.setSize(100000);
						transRequest.setSessionId((String) session.getAttribute("adminSessionId"));
						AllUserResponse allTransaction = appAdminApi.getAllTransactions(transRequest);
						List<TransactionListResponse> results = new ArrayList<>();
						JSONArray listArray = allTransaction.getJsonArray();
						for(int i=0 ; i < allTransaction.getJsonArray().length(); i++){

							JSONObject json = listArray.getJSONObject(i);
							System.err.println("json is "+json.toString());
							//long milliSecond = Long.parseLong(json.getString("created"));
							//Calendar calendarr = Calendar.getInstance();
							//calendarr.setTimeInMillis(milliSecond);
							long milliSeconds = JSONParserUtil.getLong(json,"dateOfTransaction");
							System.out.println("date in milis: "+milliSeconds);
							Calendar calendar = Calendar.getInstance();
							calendar.setTimeInMillis(milliSeconds);
							Date txdate=calendar.getTime();
							System.out.println(txdate.after(from));
							System.out.println(txdate.before(to));
							System.out.println("the date is:"+txdate);
							//Date transactionDate = calendarr.getTime();
							//System.out.println("the date is:"+transactionDate);
							if (txdate.after(from) && txdate.before(to)){
								TransactionListResponse userListDTO = new TransactionListResponse();
								userListDTO.setId(JSONParserUtil.getLong(json,"id"));
								userListDTO.setUsername(JSONParserUtil.getString(json,"username"));
								//userListDTO.setAmount(JSONParserUtil.getDouble(json,"amount"));
								userListDTO.setAuthority(JSONParserUtil.getString(json,"authority"));
								userListDTO.setCommission(JSONParserUtil.getDouble(json,"commission"));
								userListDTO.setContactNo(JSONParserUtil.getString(json,"contactNo"));
								userListDTO.setStatus(Status.valueOf(JSONParserUtil.getString(json,"status")));
								userListDTO.setCurrentBalance(JSONParserUtil.getDouble(json,"currentBalance"));
								userListDTO.setDateOfTransaction(dateFilter.format(txdate));
								System.out.println("the dto tx date is:"+dateFilter.format(txdate));


								if(JSONParserUtil.getBoolean(json, "debit")){
									userListDTO.setAmountPayable((Double.parseDouble(json.getString("amount"))));
								}else
								{
									userListDTO.setAmountReceivable((Double.parseDouble(json.getString("amount"))));
								}
								userListDTO.setDebit(JSONParserUtil.getBoolean(json,"debit"));
								userListDTO.setTransactionRefNo(JSONParserUtil.getString(json,"transactionRefNo"));
								userListDTO.setDescription(JSONParserUtil.getString(json,"description"));
								userListDTO.setEmail(JSONParserUtil.getString(json,"email"));
								userListDTO.setServiceType(JSONParserUtil.getString(json,"serviceType"));
								results.add(userListDTO);
								//System.out.println(results);


							}

						}
						model.addAttribute("userList", results);
					}



					return "Admin/DailyReportFiltered";
				}
			}
		}


		catch(Exception ex){
			model.addAttribute(ModelMapKey.MESSAGE,"Please Enter Date in valid format(YYYY-MM-DD)");
		}
		return "redirect:/Admin/Home";
	}


    @RequestMapping(method=RequestMethod.POST,value="/filteredUserKYCResults")
    public String getFilteredKYCUserList(@ModelAttribute TransactionFilter dto,HttpServletRequest request,HttpServletResponse response,Model model,HttpSession session) throws ParseException {

        UserListDTO result=new UserListDTO();
        String toDateTime = dto.getEndDate() + " 23:59";
        String fromDateTime = dto.getStartDate() + " 00:00";
        Date to = dateFilter.parse(toDateTime);
        Date from = dateFilter.parse(fromDateTime);

        String sessionCheck = (String) session.getAttribute("adminSessionId");
        if (sessionCheck != null) {
            String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
            if(authority != null) {
                if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
                    AllUserRequest userRequest = new AllUserRequest();
                    userRequest.setPage(0);
                    userRequest.setSize(100000);
                    userRequest.setSessionId(sessionCheck);
                    userRequest.setStatus(UserStatus.ALL);
                    AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
                    JSONArray data = userResponse.getJsonArray();
                    List<AdminUserDetails> userList = new ArrayList<>();
                    try {
                        if (data != null ) {

                            for (int i = 0; i < data.length(); i++) {
                                AdminUserDetails list = new AdminUserDetails();
                                JSONObject jsonobject = data.getJSONObject(i);
                                String userType = JSONParserUtil.getString(jsonobject, "userType");
                                long milliSeconds = Long.parseLong(jsonobject.getString("created"));
                                String kycnonkyc=jsonobject.getJSONObject("accountDetail").getJSONObject("accountType").getString("name");
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTimeInMillis(milliSeconds);
                                Date txdate=calendar.getTime();
                                if (txdate.after(from) && txdate.before(to)){
                                    if (userType.equalsIgnoreCase("User") && kycnonkyc.equalsIgnoreCase("KYC")) {
                                        list.setAuthority(jsonobject.getString("authority"));
                                        list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
                                        list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
                                        list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
                                        list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
                                        list.setGender(jsonobject.getJSONObject("userDetail").getString("gender"));
                                        list.setDob(jsonobject.getJSONObject("userDetail").getString("dateOfBirth"));
                                        list.setKycNonkyc(jsonobject.getJSONObject("accountDetail").getJSONObject("accountType").getString("name"));
                                        list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
                                        list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
                                        list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
                                        list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
                                        list.setMobileToken(jsonobject.getString("mobileToken"));
                                        list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
                                        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                                        long milliSecond = Long.parseLong(jsonobject.getString("created"));
                                        Calendar calendarr = Calendar.getInstance();
                                        calendarr.setTimeInMillis(milliSecond);
                                        list.setDateOfAccountCreation(formatter.format(calendarr.getTime()));
                                        userList.add(list);

                                        System.out.println("boooom--"+userList);

                                    }
                                }
                            }
                            model.addAttribute("searchedlist",userList);
                            return "Admin/filteredUserResults";
                        }




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return "Admin/Home";

    }


    @RequestMapping(method=RequestMethod.GET,value="/filteredUserResults")
    public String getFilteredUserList(@ModelAttribute TransactionFilter dto,HttpServletRequest request,HttpServletResponse response,Model model,HttpSession session) throws ParseException {

        UserListDTO result=new UserListDTO();
        String toDateTime = dto.getEndDate() + " 23:59";
        String fromDateTime = dto.getStartDate() + " 00:00";
        Date to = dateFilter.parse(toDateTime);
        Date from = dateFilter.parse(fromDateTime);

        String sessionCheck = (String) session.getAttribute("adminSessionId");
        if (sessionCheck != null) {
            String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
            if(authority != null) {
                if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
                    AllUserRequest userRequest = new AllUserRequest();
                    userRequest.setPage(0);
                    userRequest.setSize(100000);
                    userRequest.setSessionId(sessionCheck);
                    userRequest.setStatus(UserStatus.ALL);
                    AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
                    JSONArray data = userResponse.getJsonArray();
                    List<AdminUserDetails> userList = new ArrayList<>();
                    try {
                        if (data != null ) {

                            for (int i = 0; i < data.length(); i++) {
                                AdminUserDetails list = new AdminUserDetails();
                                JSONObject jsonobject = data.getJSONObject(i);
                                JSONObject obj=jsonobject.getJSONObject("userDetail");
                                String userType = JSONParserUtil.getString(jsonobject, "userType");
                                long milliSeconds = Long.parseLong(jsonobject.getString("created"));
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTimeInMillis(milliSeconds);
                                Date txdate=calendar.getTime();
                                if (txdate.after(from) && txdate.before(to)){
                                    if (userType.equalsIgnoreCase("User") && obj.isNull("location")) {
                                        list.setAuthority(jsonobject.getString("authority"));
                                        list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
                                        list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
                                        list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
                                        list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
                                        list.setGender(jsonobject.getJSONObject("userDetail").getString("gender"));
                                        list.setDob(jsonobject.getJSONObject("userDetail").getString("dateOfBirth"));
                                        list.setKycNonkyc(jsonobject.getJSONObject("accountDetail").getJSONObject("accountType").getString("name"));
                                        list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
                                        list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
                                        list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
                                        list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
                                        list.setMobileToken(jsonobject.getString("mobileToken"));
                                        list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
                                        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                                        long milliSecond = Long.parseLong(jsonobject.getString("created"));
                                        Calendar calendarr = Calendar.getInstance();
                                        calendarr.setTimeInMillis(milliSecond);
                                        list.setDateOfAccountCreation(formatter.format(calendarr.getTime()));
                                        userList.add(list);

                                        System.out.println("boooom--"+userList);

                                    }
                                    else{
                                    	list.setPinCode(jsonobject.getJSONObject("userDetail").getJSONObject("location").getString("pinCode"));
                                    	list.setCircleName(jsonobject.getJSONObject("userDetail").getJSONObject("location").getString("circleName"));
                                    	 list.setAuthority(jsonobject.getString("authority"));
                                         list.setFirstName(jsonobject.getJSONObject("userDetail").getString("firstName"));
                                         list.setLastname(jsonobject.getJSONObject("userDetail").getString("lastName"));
                                         list.setUsername(jsonobject.getJSONObject("userDetail").getString("name"));
                                         list.setAccountNumber(jsonobject.getJSONObject("accountDetail").getString("accountNumber"));
                                         list.setGender(jsonobject.getJSONObject("userDetail").getString("gender"));
                                         list.setDob(jsonobject.getJSONObject("userDetail").getString("dateOfBirth"));
                                         list.setKycNonkyc(jsonobject.getJSONObject("accountDetail").getJSONObject("accountType").getString("name"));
                                         list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
                                         list.setBalance(jsonobject.getJSONObject("accountDetail").getDouble("balance"));
                                         list.setContactNo(jsonobject.getJSONObject("userDetail").getString("contactNo"));
                                         list.setEmail(jsonobject.getJSONObject("userDetail").getString("email"));
                                         list.setMobileToken(jsonobject.getString("mobileToken"));
                                         list.setPoints(jsonobject.getJSONObject("accountDetail").getLong("points"));
                                         DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                                         long milliSecond = Long.parseLong(jsonobject.getString("created"));
                                         Calendar calendarr = Calendar.getInstance();
                                         calendarr.setTimeInMillis(milliSecond);
                                         list.setDateOfAccountCreation(formatter.format(calendarr.getTime()));
                                         userList.add(list);

                                         System.out.println("boooom--"+userList);
                                    }
                                }
                            }
                            model.addAttribute("searchedlist",userList);
                            return "Admin/filteredUserResults";
                        }




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return "Admin/Home";

    }

    
    

    @RequestMapping(method = RequestMethod.GET, value = "/PromoTransactions")
	public String filterTransactionReports(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model)
			throws JSONException {
			String sessionCheck = (String) session.getAttribute("adminSessionId");
			if (sessionCheck != null) {
				String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
				if(authority != null) {
					if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
						SessionDTO sessionDTO = new SessionDTO();
						sessionDTO.setSessionId(sessionCheck);
						AllTransactionRequest transRequest = new AllTransactionRequest();
						transRequest.setPage(0);
						transRequest.setSize(100000);
						transRequest.setSessionId((String) session.getAttribute("adminSessionId"));
						AllTransactionResponse allTransaction = appAdminApi.getPromoTransaction(transRequest);
						List<AdminUserDetails> userList = new ArrayList<AdminUserDetails>();
						JSONArray transactions = allTransaction.getJsonArray();
						if(transactions != null) {
							for (int i = 0; i < transactions.length(); i++) {
									JSONObject t = transactions.getJSONObject(i);
									AdminUserDetails details = new AdminUserDetails();
									long milliseconds = JSONParserUtil.getLong(t,"created");
								    Calendar calendar = Calendar.getInstance();
									calendar.setTimeInMillis(milliseconds);
									details.setDateOfTransaction(dateFilter.format(calendar.getTime()));
								    details.setTransactionRefNo(JSONParserUtil.getString(t,"transactionRefNo"));
									details.setCurrentBalance(String.valueOf(JSONParserUtil.getDouble(t,"currentBalance")));
									details.setContactNo(JSONParserUtil.getString(t,"contactNo"));
									details.setAmount(String.valueOf(JSONParserUtil.getDouble(t,"amount")));
									details.setFirstName(JSONParserUtil.getString(t,"description"));
									details.setEmail(JSONParserUtil.getString(t,"email"));
									details.setStatus(JSONParserUtil.getString(t,"status"));
									userList.add(details);
							}
						}
						model.put("userList", userList);
						return "Admin/PromoTransactions";
					}
				}
			}
		return "redirect:/Admin/Home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/EmailLog")
	public String getEmail(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null)
			return "/Admin/EmailLog";
		return "/Admin/Login";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/MaleUsers")
	public String getMaleUser(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
					String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
					if(authority != null) {
						if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
							System.out.println(1);
							AllUserRequest onlineRequest = new AllUserRequest();
							onlineRequest.setPage(0);
							onlineRequest.setSize(100);
							onlineRequest.setSessionId((String) session.getAttribute("adminSessionId"));
							onlineRequest.setStatus(UserStatus.ALL);
							System.out.println(2);
							AllUserResponse onlineResponse = appAdminApi.getAllUser(onlineRequest);
							int m = 0;
							int f = 0;
							List<AdminUserDetails> maleUserList = new ArrayList<>();
							try {
								for (int i = 0; i < onlineResponse.getJsonArray().length(); i++) {
									AdminUserDetails single = new AdminUserDetails();
									String gender;
									gender = onlineResponse.getJsonArray().getJSONObject(i).getJSONObject("userDetail")
											.getString("gender");

									single.setUsername(onlineResponse.getJsonArray().getJSONObject(i).getJSONObject("userDetail")
											.getString("firstName"));
									single.setContactNo(onlineResponse.getJsonArray().getJSONObject(i).getJSONObject("userDetail")
											.getString("contactNo"));

									single.setEmail(onlineResponse.getJsonArray().getJSONObject(i).getJSONObject("userDetail")
											.getString("email"));

									single.setAmount(onlineResponse.getJsonArray().getJSONObject(i).getJSONObject("accountDetail")
											.getString("balance"));

									if (gender.equals("M")) {

										maleUserList.add(single);
										System.err.println("Value of objct " + maleUserList.size());
										System.err.println(single.getUsername());

									}

								}
							} catch (JSONException e) {
								e.printStackTrace();
							}
							model.addAttribute("userList", maleUserList);
							return "Admin/MaleUsers";
						}
					}
				}


		return "redirect:/Admin/Home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/FemaleUsers")
	public String getFemaleUser(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {

		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
				String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
			if(authority != null) {
				if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					AllUserRequest onlineRequest = new AllUserRequest();
					onlineRequest.setPage(0);
					onlineRequest.setSize(100);
					onlineRequest.setSessionId((String) session.getAttribute("adminSessionId"));
					onlineRequest.setStatus(UserStatus.ALL);
					AllUserResponse onlineResponse = appAdminApi.getAllUser(onlineRequest);
					int m = 0;
					int f = 0;
					List<AdminUserDetails> maleUserList = new ArrayList<>();
					try {
						if(onlineResponse != null) {
							for (int i = 0; i < onlineResponse.getJsonArray().length(); i++) {
								AdminUserDetails single = new AdminUserDetails();
								String gender;
								gender = onlineResponse.getJsonArray().getJSONObject(i).getJSONObject("userDetail")
										.getString("gender");

								single.setUsername(onlineResponse.getJsonArray().getJSONObject(i).getJSONObject("userDetail")
										.getString("firstName"));
								single.setContactNo(onlineResponse.getJsonArray().getJSONObject(i).getJSONObject("userDetail")
										.getString("contactNo"));

								single.setEmail(onlineResponse.getJsonArray().getJSONObject(i).getJSONObject("userDetail")
										.getString("email"));

								single.setAmount(onlineResponse.getJsonArray().getJSONObject(i).getJSONObject("accountDetail")
										.getString("balance"));

								if (gender.equals("F")) {

									maleUserList.add(single);

								}

							}
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					model.addAttribute("userList", maleUserList);
					return "Admin/FemaleUsers";
				}
			}
			}

		return "redirect:/Admin/Home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/SMSLog")
	public String getSMSLogGet(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {

		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null)
			return "/Admin/SMSLog";
		return "/Admin/Login";
	}


	@RequestMapping(method = RequestMethod.POST, value = "/SMSLog")
	public String getSMSLog(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model, @RequestParam("startDate") String start, @RequestParam("endDate") String end) {
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
			if(authority != null) {
				if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					MessageLogRequest transRequest = new MessageLogRequest();
					transRequest.setStartDate(start);
					transRequest.setEndDate(end);
					transRequest.setSessionId(sessionCheck);
					MessageLogResponse userResponse = appAdminApi.getMessageLog(transRequest);
					JSONArray data = userResponse.getJsonArray();
					List<AdminUserDetails> userList = new ArrayList<>();
					try {
						if (data != null) {
							for (int i = 0; i < data.length(); i++) {
								AdminUserDetails list = new AdminUserDetails();
								JSONObject jsonobject = data.getJSONObject(i);
								list.setContactNo(jsonobject.getString("destination"));
								list.setEmailTemplate(jsonobject.getString("template"));
								list.setCommision(jsonobject.getString("message"));
								DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
								long milliSeconds = Long.parseLong(jsonobject.getString("created"));
								Calendar calendar = Calendar.getInstance();
								calendar.setTimeInMillis(milliSeconds);
								list.setDateOfTransaction(formatter.format(calendar.getTime()));
								userList.add(list);
							}
						}
						model.put("userList", userList);
					} catch (JSONException e) {
						e.printStackTrace();
					}

					return "Admin/SMSLog";
				}
			}
		}
		return "redirect:/Admin/Home";
	}
	
	@RequestMapping(value = "/{username}/Merchant", method = RequestMethod.GET)
	public String merchantTransaction(@PathVariable String username,Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws JSONException{
		UserListDTO resultSet = new UserListDTO();
		double totaltx = 0;
		System.out.println("inside merchant....."+username);
		ReceiptsResponse result = new ReceiptsResponse();
		String sessionId = (String) session.getAttribute("adminSessionId");
	     System.out.println("this is session id="+sessionId);
	     if (sessionId != null && sessionId.length() != 0) {
	         String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
	         if (authority != null) {
	             if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
	                 ReceiptsRequest receipts = new ReceiptsRequest();

	                 receipts.setSessionId(sessionId);
	                 receipts.setPage(0);
	                 receipts.setSize(1000);
	                 receipts.setUsername(username);
	                 
	                 result = appAdminApi.getSingleMerchantTransactionList(receipts);
	                 
	                System.out.println("this is the response"+result.toString());
	                 List<MTransactionResponseDTO> transactionList = new ArrayList<>();
	                 if(result.isSuccess()) {
	                     try {
	                         JSONObject json = new JSONObject(result.getResponse());
	                         JSONObject details = JSONParserUtil.getObject(json,"details");
	                         JSONArray content = JSONParserUtil.getArray(details,"content");
	                         if(content != null) {
	                             for (int i=0 ; i < content.length(); i++) {
	                                 JSONObject temp = content.getJSONObject(i);
	                                 boolean debit = JSONParserUtil.getBoolean(temp,"debit");
	                                // System.out.println("inside for loop of merchant");
	                                 totaltx+=JSONParserUtil.getDouble(temp, "amount");
	                                     MTransactionResponseDTO transaction = new MTransactionResponseDTO();
	                                     long milliseconds = JSONParserUtil.getLong(temp, "dateOfTransaction");
	                                     Calendar calendar = Calendar.getInstance();
	                                     calendar.setTimeInMillis(milliseconds);
	                                     transaction.setDate(dateFilter.format(calendar.getTime()));
	                                     transaction.setTransactionRefNo(JSONParserUtil.getString(temp, "transactionRefNo"));
	                                     transaction.setCurrentBalance(JSONParserUtil.getDouble(temp, "currentBalance"));
	                                     transaction.setUsername(temp.getString("username"));
	                                     transaction.setServiceType(temp.getString("serviceType"));
	                                     transaction.setAmount(JSONParserUtil.getDouble(temp, "amount"));
	                                     transaction.setContactNo(JSONParserUtil.getString(temp, "contactNo"));
	                                     transaction.setEmail(JSONParserUtil.getString(temp, "email"));
	                                    //transaction.setTotalPages(totalPages);
	                                     transaction.setStatus(Status.valueOf(JSONParserUtil.getString(temp, "status")));
	                                     transaction.setDescription(JSONParserUtil.getString(temp, "description"));
	                                     transactionList.add(transaction);
	                                 
	                            }
	                             System.out.println("this is total tx..........................."+totaltx);
	     	                    request.setAttribute("totaltx", totaltx);
	                             model.addAttribute("userList", transactionList);
	                             return "Admin/merchant";
	                             //model.addAttribute("transactionList",transactionList);
//	                             resultSet.setTotalPages(details.getLong("totalPages"));
//	                             resultSet.setFirstPage(details.getBoolean("firstPage"));
//	                             resultSet.setLastPage(details.getBoolean("lastPage"));
//	                             resultSet.setNumberOfElements(details.getLong("numberOfElements"));
//	                             resultSet.setJsonArray(transactionList);
	                             //return new ResponseEntity<UserListDTO>(resultSet,HttpStatus.OK) ;
	                         }
	                     } catch (JSONException e) {
	                         e.printStackTrace();
	                         return "Admin/Home";
	                     }
	                     
	                     return "Admin/merchant";
	                 }
	                
	             }
	         }
	     }
	     return "redirect:/Admin/Home" ;
	 }

		
		

	@RequestMapping(value = "/User/{userName}", method = RequestMethod.GET)
	public String userDetails(ModelMap modelMap, @PathVariable String userName, HttpSession session, Model model)
			throws JSONException {

		String sessionCheck = (String) session.getAttribute("adminSessionId");
		String userAccountNumber = null;
		List<AdminUserDetails> userList = new ArrayList<AdminUserDetails>();
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
			if(authority != null) {
				if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					
					UserTransactionRequest req = new UserTransactionRequest();
					req.setUsername(userName);
					req.setPage(0);
					req.setSize(100000);
					req.setSessionId((String) session.getAttribute("adminSessionId"));
					AllTransactionResponse allTransaction = appAdminApi.getSingleUser(req);
					allTransaction.getResponse();
					JSONArray data = allTransaction.getJsonArray();
					System.err.println("Data  :: " + data);
					for (int i = 0; i < data.length(); i++) {
						AdminUserDetails list = new AdminUserDetails();
						JSONObject jsonobject = data.getJSONObject(i);
						list.setAmount(jsonobject.getString("amount"));
						list.setCurrentBalance(jsonobject.getString("currentBalance"));
						list.setStatus(jsonobject.getString("status"));
						list.setContactNo(userName);
						list.setTransactionRefNo(removeLastChar(jsonobject.getString("transactionRefNo")));
						list.setServiceType(jsonobject.getString("description"));
						if ((jsonobject.getString("transactionRefNo").contains("D"))) {
							list.setDebit(jsonobject.getString("amount"));
						} else {
							list.setCredit(jsonobject.getString("amount"));
						}
						if (i == 0) {
							userAccountNumber = jsonobject.getJSONObject("account").getLong("accountNumber") + "";
						}
						DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
						long milliSeconds = Long.parseLong(jsonobject.getString("created"));
						Calendar calendar = Calendar.getInstance();
						calendar.setTimeInMillis(milliSeconds);
						list.setDateOfTransaction(formatter.format(calendar.getTime()));
						userList.add(list);
					}
					AllUserRequest userRequest = new AllUserRequest();
					userRequest.setPage(0);
					userRequest.setSize(100000);
					userRequest.setSessionId((String) session.getAttribute("adminSessionId"));
					userRequest.setStatus(UserStatus.ALL);
					AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
					JSONArray userData = userResponse.getJsonArray();
					AdminUserDetails l = new AdminUserDetails();
					if(userData != null) {
						for (int j = 0; j < userData.length(); j++) {
							JSONObject obj = new JSONObject();
							obj = userData.getJSONObject(j);
							String oneAccontNumber = obj.getJSONObject("accountDetail").getString("accountNumber");
							if (userAccountNumber != null && oneAccontNumber != null) {
								if (userAccountNumber.equals(oneAccontNumber)) {
									l.setAuthority(obj.getString("authority"));
									l.setMobileStatus(obj.getString("mobileStatus"));
									l.setEmailStatus(obj.getString("emailStatus"));
									l.setEmail(obj.getJSONObject("userDetail").getString("email"));
									l.setMobileToken(obj.getString("mobileToken"));
									l.setFirstName(obj.getJSONObject("userDetail").getString("name"));
									l.setContactNo(obj.getJSONObject("userDetail").getString("contactNo"));
									if (obj.getJSONObject("userDetail").getString("image") == null) {
										l.setImage("/resources/images/sample.jpg");
									} else {
										l.setImage(obj.getJSONObject("userDetail").getString("image"));
									}
								}
							
						
							}
						}
					}
						
					Calendar cal = Calendar.getInstance();
					model.addAttribute("year", cal.get(Calendar.YEAR));
					model.addAttribute("month", (new Date().getMonth()) + 1);
					model.addAttribute("date", cal.get(Calendar.DATE));
					model.addAttribute("userList", userList);
					System.out.println("dis is userlist"+userList);
					model.addAttribute("user", l);
					return "Admin/User";
				}
			}
		}
		return "redirect:/Admin/Home";
	}

	@RequestMapping(value = "/User/Block/{userName}", method = RequestMethod.GET)
	public String userBlock(ModelMap modelMap, @PathVariable String userName, HttpSession session, Model model) {
		LogCat.print("Block Users :: " + userName);
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if(sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
			if(authority != null){
				if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					BlockUserRequest req = new BlockUserRequest();
					req.setAdminSessionId(sessionCheck);
					req.setUsername(userName);
					BlockUserResponse resp = appAdminApi.userBlock(req);
					model.addAttribute("message", resp.getMessage());
					return "redirect:/Admin/User/" + userName;
				}
			}
		}
		return "redirect:/Admin/Home";
	}

	@RequestMapping(value = "/User/Unblock/{userName}", method = RequestMethod.GET)
	public String userUnBlock(ModelMap modelMap, @PathVariable String userName, HttpSession session, Model model) {
		LogCat.print("UnBlock Users :: " + userName);
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if(sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					BlockUnBlockUserRequest req = new BlockUnBlockUserRequest();
					req.setSessionId((String) session.getAttribute("adminSessionId"));
					req.setUsername(userName);
					BlockUnBlockUserResponse resp = appAdminApi.unblockUser(req);
					LogCat.print("Response  ::" + resp.getMessage());
					model.addAttribute("message", resp.getMessage());
					return "redirect:/Admin/User/" + userName;
				}
			}
		}
		return "/Admin/Home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/PoolAccount")
	public String getPoolAccount(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws JSONException {

		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
			if(authority != null) {
				if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					AllTransactionRequest transRequest = new AllTransactionRequest();
					transRequest.setPage(0);
					transRequest.setSize(100);
					transRequest.setSessionId(sessionCheck);
					AllTransactionResponse allTransaction = appAdminApi.getAllTransaction(transRequest);
					AllUserRequest user = new AllUserRequest();
					user.setPage(0);
					user.setSize(100);
					user.setSessionId((String) session.getAttribute("adminSessionId"));
					user.setStatus(UserStatus.ALL);
					AllUserResponse allUsers = appAdminApi.getAllUser(user);
					List<AdminUserDetails> userList = new ArrayList<AdminUserDetails>();
					JSONArray u = allUsers.getJsonArray();
					JSONArray t = allTransaction.getJsonArray();
					if(u != null && t != null) {
						for (int s = 0; s < t.length(); s++) {
							for (int i = 0; i < u.length(); i++) {
								JSONObject singleU = u.getJSONObject(i);
								String di = singleU.getJSONObject("accountDetail").getString("id");
								AdminUserDetails list = new AdminUserDetails();
								JSONObject singleT = t.getJSONObject(s);
								String id = singleT.getJSONObject("account").getString("id");
								if (id.equals(di) && singleT.get("status").equals("Success")
										&& !singleU.get("username").equals("instantpay@payqwik.in")
										&& !singleT.get("service").equals(null)
										&& !singleT.getJSONObject("service").get("code").equals("SMR")
										&& !singleT.getJSONObject("service").get("code").equals("SMU")) {
									if (!singleT.get("transactionType").equals("COMMISSION")
											&& !singleT.get("transactionType").equals("SETTLEMENT")) {
										list.setAmount(singleT.getString("amount"));
										list.setCurrentBalance(singleT.getString("currentBalance"));
										list.setStatus(singleT.getString("status"));
										list.setTransactionRefNo(removeLastChar(singleT.getString("transactionRefNo")));
										list.setServiceType(singleT.getString("description"));
										long milliSeconds = Long.parseLong(singleT.getString("created"));
										Calendar calendar = Calendar.getInstance();
										calendar.setTimeInMillis(milliSeconds);
										list.setUsername(singleU.getJSONObject("userDetail").getString("firstName") + " "
												+ singleU.getJSONObject("userDetail").getString("lastName"));
										list.setContactNo(singleU.getJSONObject("userDetail").getString("contactNo"));
										list.setBalance(singleU.getJSONObject("accountDetail").getDouble("balance"));
										list.setDateOfTransaction(dateFilter.format(calendar.getTime()));
										if ((singleT.getBoolean("debit"))) {
											list.setDebit(singleT.getString("amount"));
										} else {
											list.setCredit(singleT.getString("amount"));
										}
										userList.add(list);
									}
								}
							}
						}
					}
					try {
						model.put("userList", userList);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return "Admin/PoolAccount";
				}
			}
		}
		return "redirect:/Admin/Home";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/PoolAccount")
	public String filterPoolAccountTransactions(@ModelAttribute TransactionFilter dto, HttpServletRequest request, HttpServletResponse response, HttpSession session,
								 ModelMap model) throws JSONException {
		try {
			String toDateTime = dto.getEndDate() + " 23:59";
			String fromDateTime = dto.getStartDate() + " 00:00";
			Date to = dateFilter.parse(toDateTime);
			Date from = dateFilter.parse(fromDateTime);
			String sessionCheck = (String) session.getAttribute("adminSessionId");
			if (sessionCheck != null) {
				String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
				if(authority != null) {
					if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
						model.addAttribute(ModelMapKey.MESSAGE, "Pool Transactions From " + dto.getStartDate() + " to " + dto.getEndDate());
						AllTransactionRequest transRequest = new AllTransactionRequest();
						transRequest.setPage(0);
						transRequest.setSize(100);
						transRequest.setSessionId((String) session.getAttribute("adminSessionId"));
						AllTransactionResponse allTransaction = appAdminApi.getAllTransaction(transRequest);
						AllUserRequest user = new AllUserRequest();
						user.setPage(0);
						user.setSize(100);
						user.setSessionId((String) session.getAttribute("adminSessionId"));
						user.setStatus(UserStatus.ALL);

						AllUserResponse allUsers = appAdminApi.getAllUser(user);
						List<AdminUserDetails> userList = new ArrayList<AdminUserDetails>();

						JSONArray u = allUsers.getJsonArray();
						JSONArray t = allTransaction.getJsonArray();
						if(t != null && u != null) {
							for (int s = 0; s < t.length(); s++) {
								for (int i = 0; i < u.length(); i++) {
									JSONObject singleU = u.getJSONObject(i);
									String di = singleU.getJSONObject("accountDetail").getString("id");
									AdminUserDetails list = new AdminUserDetails();
									JSONObject singleT = t.getJSONObject(s);
									String id = singleT.getJSONObject("account").getString("id");

									if (id.equals(di) && singleT.get("status").equals("Success")
											&& !singleU.get("username").equals("instantpay@payqwik.in")
											&& !singleT.get("service").equals(null)
											&& !singleT.getJSONObject("service").get("code").equals("SMR")
											&& !singleT.getJSONObject("service").get("code").equals("SMU")) {
										if (!singleT.get("transactionType").equals("COMMISSION")
												&& !singleT.get("transactionType").equals("SETTLEMENT")) {
											// list.setCommision(z.getString("commission"));
											long milliSeconds = Long.parseLong(singleT.getString("created"));
											Calendar calendar = Calendar.getInstance();
											calendar.setTimeInMillis(milliSeconds);
											Date transactionDate = calendar.getTime();
											if (transactionDate.after(from) && transactionDate.before(to)) {
												list.setAmount(singleT.getString("amount"));
												list.setCurrentBalance(singleT.getString("currentBalance"));
												list.setStatus(singleT.getString("status"));
												list.setTransactionRefNo(removeLastChar(singleT.getString("transactionRefNo")));
												list.setServiceType(singleT.getString("description"));
												list.setUsername(singleU.getJSONObject("userDetail").getString("firstName") + " "
														+ singleU.getJSONObject("userDetail").getString("lastName"));
												list.setContactNo(singleU.getJSONObject("userDetail").getString("contactNo"));
												list.setBalance(singleU.getJSONObject("accountDetail").getDouble("balance"));
												list.setDateOfTransaction(dateFilter.format(calendar.getTime()));

												if ((singleT.getBoolean("debit"))) {
													list.setDebit(singleT.getString("amount"));
												} else {
													list.setCredit(singleT.getString("amount"));
												}

												userList.add(list);
											}
										}
									}
								}
							}
						}
						try {

							model.put("userList", userList);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return "Admin/PoolAccount";
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:/Admin/Home";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/CommissionReportInJSON")
	public ResponseEntity<UserListDTO> getCommissionReportInJson(@ModelAttribute PagingDTO dto,HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws JSONException {
		UserListDTO resultSet = new UserListDTO();
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		//String sessionCheck = dto.getSessionId();
		System.out.println(sessionCheck);
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
			if(authority != null) {
				if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					AllTransactionRequest transRequest = new AllTransactionRequest();
					transRequest.setPage(dto.getPage());
					transRequest.setSize(dto.getSize());
					transRequest.setSessionId(sessionCheck);
					AllUserResponse allTransaction = appAdminApi.getAllTransactions(transRequest);
					List<TransactionListResponse> results = new ArrayList<>();
					JSONArray listArray = allTransaction.getJsonArray();
					for(int i=0 ; i < allTransaction.getJsonArray().length(); i++){
						JSONObject json = listArray.getJSONObject(i);
						TransactionListResponse userListDTO = new TransactionListResponse();
						userListDTO.setId(JSONParserUtil.getLong(json,"id"));
						userListDTO.setUsername(JSONParserUtil.getString(json,"username"));
						userListDTO.setAmount(JSONParserUtil.getDouble(json,"amount"));
						userListDTO.setAuthority(JSONParserUtil.getString(json,"authority"));
						userListDTO.setCommission(JSONParserUtil.getDouble(json,"commission"));
						userListDTO.setContactNo(JSONParserUtil.getString(json,"contactNo"));
						userListDTO.setCurrentBalance(JSONParserUtil.getDouble(json,"currentBalance"));
						long milliSeconds = JSONParserUtil.getLong(json,"dateOfTransaction");
						Calendar calendar = Calendar.getInstance();
						calendar.setTimeInMillis(milliSeconds);
						userListDTO.setDateOfTransaction(dateFilter.format(calendar.getTime()));
						userListDTO.setDebit(JSONParserUtil.getBoolean(json,"debit"));
						userListDTO.setTransactionRefNo(JSONParserUtil.getString(json,"transactionRefNo"));
						userListDTO.setDescription(JSONParserUtil.getString(json,"description"));
						userListDTO.setEmail(JSONParserUtil.getString(json,"email"));
						userListDTO.setServiceType(JSONParserUtil.getString(json,"serviceType"));
						userListDTO.setStatus(Status.valueOf(JSONParserUtil.getString(json, "status")));
						results.add(userListDTO);
					}
					resultSet.setSuccess(allTransaction.isSuccess());
					resultSet.setJsonArray(results);
					resultSet.setFirstPage(allTransaction.isFirstPage());
					resultSet.setLastPage(allTransaction.isLastPage());
					resultSet.setNumberOfElements(allTransaction.getNumberOfElements());
					resultSet.setSize(allTransaction.getSize());
					resultSet.setTotalPages(allTransaction.getTotalPages());

				}
			}
		}

		return new ResponseEntity<UserListDTO>(resultSet,HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.GET, value = "/CommissionAccount")
	public String getCommissionAccount(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws JSONException {
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			String authority  = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
			if(authority != null) {
				if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					AllTransactionRequest transRequest = new AllTransactionRequest();
					transRequest.setPage(0);
					transRequest.setSize(100);
					transRequest.setSessionId((String) session.getAttribute("adminSessionId"));
					AllTransactionResponse allTransaction = appAdminApi.getAllTransaction(transRequest);
					AllUserRequest user = new AllUserRequest();
					user.setPage(0);
					user.setSize(100);
					user.setSessionId((String) session.getAttribute("adminSessionId"));
					user.setStatus(UserStatus.ALL);
					AllUserResponse allUsers = appAdminApi.getAllUser(user);
					List<AdminUserDetails> userList = new ArrayList<AdminUserDetails>();
					JSONArray u = allUsers.getJsonArray();
					JSONArray t = allTransaction.getJsonArray();
					if(u != null && t != null) {
						for (int i = 0; i < u.length(); i++) {
							JSONObject k = u.getJSONObject(i);
							String di = k.getJSONObject("accountDetail").getString("id");
							for (int s = 0; s < t.length(); s++) {
								AdminUserDetails list = new AdminUserDetails();
								JSONObject z = t.getJSONObject(s);
								String id = z.getJSONObject("account").getString("id");
								if (id.equals(di)) {
									if (!z.get("service").equals(null) && !z.getJSONObject("service").get("code").equals("LMC")
											&& z.get("status").equals("Success") && z.get("transactionType").equals("COMMISSION")) {
										list.setAmount(z.getString("amount"));
										list.setCurrentBalance(z.getString("currentBalance"));
										list.setStatus(z.getString("status"));
										list.setTransactionRefNo(z.getString("transactionRefNo"));
										list.setServiceType(z.getString("description"));
										// list.setCommision(z.getString("commission"));
										long milliSeconds = Long.parseLong(z.getString("created"));
										Calendar calendar = Calendar.getInstance();
										calendar.setTimeInMillis(milliSeconds);
										list.setUsername(k.getJSONObject("userDetail").getString("firstName") + " "
												+ k.getJSONObject("userDetail").getString("lastName"));
										list.setContactNo(k.getJSONObject("userDetail").getString("contactNo"));
										list.setBalance(k.getJSONObject("accountDetail").getDouble("balance"));
										list.setDateOfTransaction(dateFilter.format(calendar.getTime()));
										// list.setCommision(z.getString("commission"));
										list.setCommision(z.getString("amount"));

										userList.add(list);
									}
								}
							}
						}
					}
					try {

						model.put("userList", userList);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return "Admin/CommissionAccount";
				}
			}
		}
		return "redirect:/Admin/Home";
	}



	@RequestMapping(method = RequestMethod.POST, value = "/CommissionAccount")
	public String getCommissionAccount(@ModelAttribute TransactionFilter dto,  HttpServletRequest request, HttpServletResponse response, HttpSession session,
									   ModelMap model) throws JSONException {
		try {
			String toDateTime = dto.getEndDate() + " 23:59";
			String fromDateTime = dto.getStartDate() + " 00:00";
			Date to = dateFilter.parse(toDateTime);
			Date from = dateFilter.parse(fromDateTime);
			String sessionCheck = (String) session.getAttribute("adminSessionId");
			if (sessionCheck != null) {
				String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
				if(authority != null) {
					if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
						model.addAttribute(ModelMapKey.MESSAGE, "Commission Transactions From " + dto.getStartDate() + " to " + dto.getEndDate());
						AllTransactionRequest transRequest = new AllTransactionRequest();
						transRequest.setPage(0);
						transRequest.setSize(100);
						transRequest.setSessionId((String) session.getAttribute("adminSessionId"));
						AllTransactionResponse allTransaction = appAdminApi.getAllTransaction(transRequest);
						AllUserRequest user = new AllUserRequest();
						user.setPage(0);
						user.setSize(100);
						user.setSessionId((String) session.getAttribute("adminSessionId"));
						user.setStatus(UserStatus.ALL);
						AllUserResponse allUsers = appAdminApi.getAllUser(user);
						List<AdminUserDetails> userList = new ArrayList<>();
						JSONArray u = allUsers.getJsonArray();
						JSONArray t = allTransaction.getJsonArray();
						if(u != null && t != null) {
							for (int i = 0; i < u.length(); i++) {
								JSONObject k = u.getJSONObject(i);
								String di = k.getJSONObject("accountDetail").getString("id");
								for (int s = 0; s < t.length(); s++) {
									AdminUserDetails list = new AdminUserDetails();
									JSONObject z = t.getJSONObject(s);
									String id = z.getJSONObject("account").getString("id");
									if (id.equals(di)) {
										if (!z.get("service").equals(null) && !z.getJSONObject("service").get("code").equals("LMC")
												&& z.get("status").equals("Success") && z.get("transactionType").equals("COMMISSION")) {
											long milliSeconds = Long.parseLong(z.getString("created"));
											Calendar calendar = Calendar.getInstance();
											calendar.setTimeInMillis(milliSeconds);
											Date transactionDate = calendar.getTime();
											if (transactionDate.after(from) && transactionDate.before(to)) {
												list.setAmount(z.getString("amount"));
												list.setCurrentBalance(z.getString("currentBalance"));
												list.setStatus(z.getString("status"));
												list.setTransactionRefNo(z.getString("transactionRefNo"));
												list.setServiceType(z.getString("description"));
												list.setUsername(k.getJSONObject("userDetail").getString("firstName") + " "
														+ k.getJSONObject("userDetail").getString("lastName"));
												list.setContactNo(k.getJSONObject("userDetail").getString("contactNo"));
												list.setBalance(k.getJSONObject("accountDetail").getDouble("balance"));
												list.setDateOfTransaction(dateFilter.format(calendar.getTime()));
												list.setCommision(z.getString("amount"));
												userList.add(list);
											}
										}
									}
								}
							}
						}
						try {
							model.put("userList", userList);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return "Admin/CommissionAccount";
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "redirect:/Admin/Home";
	}



	@RequestMapping(method = RequestMethod.GET, value = "/SettlementAccount")
	public String getSettlementAccount(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws JSONException {
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			String authority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
			if(authority != null) {
				if(authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					AllTransactionRequest transRequest = new AllTransactionRequest();
					transRequest.setPage(0);
					transRequest.setSize(100);
					transRequest.setSessionId((String) session.getAttribute("adminSessionId"));
					AllTransactionResponse allTransaction = appAdminApi.getAllTransaction(transRequest);
					AllUserRequest user = new AllUserRequest();
					user.setPage(0);
					user.setSize(100);
					user.setSessionId((String) session.getAttribute("adminSessionId"));
					user.setStatus(UserStatus.ALL);
					AllUserResponse allUsers = appAdminApi.getAllUser(user);
					List<AdminUserDetails> userList = new ArrayList<AdminUserDetails>();
					JSONArray t = allTransaction.getJsonArray();
					JSONArray u = allUsers.getJsonArray();
					if(t != null && u != null) {
						for (int s = 0; s < t.length(); s++) {
							for (int ii = 0; ii < u.length(); ii++) {
								JSONObject singleU = u.getJSONObject(ii);
								String di = singleU.getJSONObject("accountDetail").getString("id");
								AdminUserDetails list = new AdminUserDetails();
								JSONObject singleT = t.getJSONObject(s);
								String id = singleT.getJSONObject("account").getString("id");
								if (id.equals(di)) {
									if (singleT.get("status").equals("Success")) {
										if (singleT.getString("transactionType").equals("DEFAULT")) {
											String trans = findTransactionId(singleT.getString("transactionRefNo"));
											if (trans != null) {
												list.setServiceType(singleT.getString("description"));
												// LOGIC
												for (int i = 0; i < t.length(); i++) {
													JSONObject z1 = t.getJSONObject(i);
													if (z1.getString("transactionType").equals("COMMISSION")) {
														if (z1.get("transactionRefNo").equals(trans + "CC")) {
															list.setCommision(z1.getString("amount"));
														}
													}

													if (z1.getString("transactionType").equals("SETTLEMENT")) {
														if (z1.get("transactionRefNo").equals(trans + "CS")) {
															list.setPoolAccount(z1.getString("amount"));
														}
													}

													if (z1.getString("transactionType").equals("DEFAULT")) {
														if (z1.get("transactionRefNo").equals(trans + "C")) {
															list.setInstantPayAccount(z1.getString("amount"));
														}
													}
												}

												list.setUsername(singleU.getJSONObject("userDetail").getString("firstName") + " "
														+ singleU.getJSONObject("userDetail").getString("lastName"));
												list.setContactNo(singleU.getJSONObject("userDetail").getString("contactNo"));
												list.setTravelAccount(0.0 + "");
												list.setTransactionRefNo(trans);
												long milliSeconds = Long.parseLong(singleT.getString("created"));
												Calendar calendar = Calendar.getInstance();
												calendar.setTimeInMillis(milliSeconds);
												list.setDateOfTransaction(dateFilter.format(calendar.getTime()));
												userList.add(list);
											}
										}
									}
								}
							}
						}
					}

					try {

						model.put("userList", userList);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return "Admin/SettlementAccount";
				}
			}
		}
		return "redirect:/Admin/Home";
	}



	@RequestMapping(method = RequestMethod.POST, value = "/SettlementAccount")
	public String getSettlementAccount(@ModelAttribute TransactionFilter dto, HttpServletRequest request, HttpServletResponse response, HttpSession session,
									   ModelMap model) throws JSONException {
		try {

			String sessionCheck = (String) session.getAttribute("adminSessionId");
			if (sessionCheck != null) {
				String authority = authenticationApi.getAuthorityFromSession(sessionCheck, Role.USER);
				if (authority != null) {
					if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
						model.addAttribute(ModelMapKey.MESSAGE, "Settlement Transactions From " + dto.getStartDate() + " to " + dto.getEndDate());
						AllTransactionRequest transRequest = new AllTransactionRequest();
						transRequest.setStartDate(dto.getStartDate());
						transRequest.setEndDate(dto.getEndDate());
						transRequest.setReportType(dto.getServiceType());
						transRequest.setSessionId((String) session.getAttribute("adminSessionId"));
						AllTransactionResponse allTransaction = appAdminApi.getSettlementTransactions(transRequest);
						if (allTransaction.isSuccess()) {
							List<TransactionReport> reports = new ArrayList<>();
							JSONArray reportArray = allTransaction.getJsonArray();
							if (reportArray != null) {
								for (int i = 0; i < reportArray.length(); i++) {
									JSONObject json = reportArray.getJSONObject(i);
									TransactionReport report = new TransactionReport();
									report.setAmount(JSONParserUtil.getDouble(json, "amount"));
									report.setDate(JSONParserUtil.getString(json, "date"));
									report.setService(JSONParserUtil.getString(json, "service"));
									report.setDebit(JSONParserUtil.getBoolean(json, "debit"));
									report.setTransactionRefNo(JSONParserUtil.getString(json, "transactionRefNo"));
									report.setStatus(com.tripayweb.model.web.Status.valueOf(JSONParserUtil.getString(json, "status")));
									report.setDescription(JSONParserUtil.getString(json, "description"));
									reports.add(report);
								}
								model.addAttribute("transactions", reports);
								model.addAttribute(ModelMapKey.MESSAGE, dto.getServiceType() + " reports from " + dto.getStartDate() + "to " + dto.getEndDate());
							}
							return "Admin/SettlementTransactions";
						}
						return "Admin/SettlementAccount";
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:/Admin/Home";
		}

		return "redirect:/Admin/Home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/ListMerchant")
	public String getListMerchant(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {

		String sessionCheck = (String) session.getAttribute("adminSessionId");
		
		if (sessionCheck != null) {
			String adminAuthority = authenticationApi.getAuthorityFromSession(sessionCheck,Role.USER);
			if(adminAuthority != null) {
				if(adminAuthority.contains(Authorities.ADMINISTRATOR) && adminAuthority.contains(Authorities.AUTHENTICATED)) {
					AllUserRequest userRequest = new AllUserRequest();
					
					userRequest.setPage(0);
					userRequest.setSize(100);
					userRequest.setSessionId((String) session.getAttribute("adminSessionId"));
					userRequest.setStatus(UserStatus.ALL);
					AllUserResponse userResponse = appAdminApi.getAllMerchants(userRequest);
					JSONArray data = userResponse.getJsonArray();
					List<AdminUserDetails> userList = new ArrayList<AdminUserDetails>();
					try {
						for (int i = 0; i < data.length(); i++) {
							AdminUserDetails list = new AdminUserDetails();
							JSONObject json = data.getJSONObject(i);
							list.setContactNo(JSONParserUtil.getString(json,"contactNo"));
							list.setDateOfAccountCreation(JSONParserUtil.getString(json,"dateOfRegistration"));
							list.setEmail(JSONParserUtil.getString(json,"email"));
							list.setFirstName(JSONParserUtil.getString(json,"name"));
							list.setImage(APIConstants.HOST_NAME+JSONParserUtil.getString(json,"image"));
							userList.add(list);
						}

						model.put("userlist", userList);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					return "Admin/ListMerchant";
				}
			}
		}
		return "redirect:/Admin/Home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/AddMerchant")
	public String getAddMerchant(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {

		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null)
			return "/Admin/AddMerchant";
		return "/Admin/Login";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/SendNotification")
	public String processNotification(@ModelAttribute GCMRequest dto, HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                      ModelMap model) {
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		System.err.println(dto.toString());
        GCMError error = gcmValidation.checkError(dto);
        if(error.isValid()){
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        String  status = saveLogo(rootDirectory,dto.getGcmImage());
        String []parts = status.split("\\|");
        final int partition = 100;
        if(parts[0].equals("true")) {
            List<String> regIds = new ArrayList<>();
            AllUserRequest userRequest = new AllUserRequest();
            userRequest.setPage(0);
            userRequest.setSize(100);
            userRequest.setSessionId((String) session.getAttribute("adminSessionId"));
            userRequest.setStatus(UserStatus.ACTIVE);
            AllUserResponse userResponse = appAdminApi.getAllUser(userRequest);
            JSONArray data = userResponse.getJsonArray();
            try {
                for (int i = 0; i < data.length(); i++) {
                    JSONObject jsonobject = data.getJSONObject(i);
                    String gcmId = jsonobject.getString("gcmId");
                    regIds.add(gcmId);
                }
                for (int i = 0; i < regIds.size(); i += partition) {
                    NotificationDTO notificationDTO = new NotificationDTO();
                    notificationDTO.setTitle(dto.getTitle());
                    notificationDTO.setMessage(dto.getMessage());
                    notificationDTO.setImage(com.gcm.utils.APIConstants.HOST_NAME+parts[1]);
                    notificationDTO.setRegsitrationIds(regIds.subList(i, Math.min(partition + i - 1,regIds.size())));
                    notificationApi.sendNotification(notificationDTO);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
                return "/Admin/SendNotification";
        }else{
            model.addAttribute("error",error);
            return "/Admin/SendNotification";
        }

	}

    private String saveLogo(String rootDirectory, MultipartFile image){
        boolean saved = false;
        String contentType = image.getContentType();
        String []fileExtension = contentType.split("/");
        String filePath = null;
        if(fileExtension[1].equals("png") || fileExtension[1].equals("jpg") || fileExtension[1].equals("jpeg")){
            String fileName = String.valueOf(System.currentTimeMillis());
            File dirs = new File(rootDirectory + "/resources/gcm/" + fileName + "." + fileExtension[1]);
            dirs.mkdirs();
            try {
                image.transferTo(dirs);
                filePath = "/resources/gcm/" + fileName + "." + fileExtension[1];
                saved = true;
                return saved+"|"+filePath;
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return saved+"|"+filePath;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/SendNotification")
    public String sendNotification(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                   ModelMap model) {
        String sessionCheck = (String) session.getAttribute("adminSessionId");
        if (sessionCheck != null)
            return "/Admin/SendNotification";
        return "/Admin/Login";
    }

	@RequestMapping(method = RequestMethod.GET, value = "/GeneratePromoCode")
	public String generatePromoCode(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		System.err.println("inside get promocode");
		if (sessionCheck != null) {
			    ServiceTypeResponse servicesList = appAdminApi.getService();
				if(servicesList != null) {
					if(servicesList.isSuccess()) {
						System.err.println(servicesList.getServicesDTOs());
						model.addAttribute("services", servicesList.getServicesDTOs());
					}
				}
			model.addAttribute("promoCodeRequest", new PromoCodeRequest());
			return "Admin/GeneratePromoCode";
		}
		return "redirect:/Admin/Home";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/GeneratePromoCode")
	public String generatePromoCodeFrom(@ModelAttribute("promoCodeRequest") PromoCodeRequest promoCode,HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model ) {
		System.err.println("inside post get promo code");
		String sessionId = (String) session.getAttribute("adminSessionId");
		String startDateTime = promoCode.getStartDate();
		String endDateTime = promoCode.getEndDate();
		promoCode.setStartDate(startDateTime);
		promoCode.setEndDate(endDateTime);

		if (sessionId != null) {
			System.err.println("inside session id not null");
			PromoCodeError error = promoCodeValidation.checkRequest(promoCode);
			System.err.println("error is valid ::"+error.isValid() +" ");
			System.err.println("error is valid ::"+error +" ");
			if(error.isValid()) {
                System.err.println("error is valid");
				promoCode.setSessionId(sessionId);
				PromoCodeResponse resp = promoCodeApi.generateRequest(promoCode);
				model.addAttribute("resp",resp);
				return "Admin/GeneratePromoCode";
			}
			model.addAttribute("error",error);
			return "Admin/GeneratePromoCode";
		}
		return "/Admin/Login";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/PromoCodeList")
	public String promoCodeList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model, SessionDTO dto) throws JSONException {

		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {

			dto.setSessionId(sessionCheck);
			PromoCodeResponse resp = promoCodeApi.listPromo(dto);
			System.err.println("Resp :: " + resp.getResponse());
			System.err.println("Resp :: " + resp.getMessage());
			System.err.println("Resp :: " + resp.getCode());
			JSONArray arr = (JSONArray) resp.getDetails();
			List<PromoCodeRequest> list = new ArrayList<PromoCodeRequest>();
			for (int i = 0; i < arr.length(); i++) {
				PromoCodeRequest re = new PromoCodeRequest();
				JSONObject jsonobject = arr.getJSONObject(i);
				re.setPromoCode(jsonobject.getString("promoCode"));
				re.setEndDate(jsonobject.getString("endDate"));
				re.setStartDate(jsonobject.getString("startDate"));
				re.setTerms(jsonobject.getString("terms"));
				re.setDescription(jsonobject.getString("description"));
				re.setFixed(jsonobject.getBoolean("fixed"));
				re.setValue(jsonobject.getDouble("value"));
				list.add(re);
			}
			model.addAttribute("list", list);
			return "/Admin/PromoCodeList";
		}
		return "/Admin/Login";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/SendPromotionalSMS")
	public String SendPromotionalSMS(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {

		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null)
			return "/Admin/SendPromotionalSMS";
		return "/Admin/Login";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/Logout")
	public String userLogout(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap modelMap) {
        System.err.println("inside logout of admin");
		String adminSessionId = (String) session.getAttribute("adminSessionId");
		LogoutRequest logout = new LogoutRequest();
		logout.setSessionId(adminSessionId);
		if (adminSessionId != null && adminSessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(adminSessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					LogoutResponse resp = logoutApi.logout(logout, Role.ADMIN);
					LogCat.print("Response :: " + resp.getCode());
					LogCat.print("Response :: " + resp.getMessage());
					LogCat.print("Response :: " + resp.getDetails());
					session.invalidate();
					return "redirect:/Admin/Home";
				}
			}
		}
		return "Admin/Login";

	}

	@RequestMapping(value = { "/Merchant" }, method = RequestMethod.GET)
	public String getMerchantFormPage(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {
		String sessionId = (String) session.getAttribute("adminSessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					System.out.print("admin is authenticated");
					MRegistrationRequest merchantRequest = new MRegistrationRequest();
					model.addAttribute("addMerchant", merchantRequest);
					return "Admin/AddMerchant";
				}
			}

		}
		return "Admin/Login";
	}

	@RequestMapping(value = "/Version")
	public String versiion(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws JSONException {
		String sessionCheck = (String) session.getAttribute("adminSessionId");
		if (sessionCheck != null) {
			SessionDTO dto = new SessionDTO();
			dto.setSessionId(sessionCheck);
			VersionResponse resp = versionApi.listVersion(dto);
			System.err.print("details ::"+resp.getDetails().toString());
			JSONArray arr = resp.getArrayDetails();
			ArrayList<VersionDTO> list = new ArrayList<>();

			for (int i = 0; i < arr.length(); i++) {
				VersionDTO obj = new VersionDTO();
				JSONObject singleV = arr.getJSONObject(i);
				DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				long milliSeconds = Long.parseLong(singleV.getString("created"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(milliSeconds);
				obj.setCreated(formatter.format(calendar.getTime()));
				obj.setVersion("V." + singleV.get("versionCode") + "." + singleV.getString("subversionCode"));
				obj.setStatus(singleV.getString("status"));
				list.add(obj);
			}
			model.addAttribute("userList", list);
			return "Admin/Version";
		}
		return "Admin/Login";
	}

	@RequestMapping(value = { "/Merchant" }, method = RequestMethod.POST)
	public String processMerchantRegistration(@ModelAttribute("addMerchant") MRegistrationRequest merchant,
			@RequestParam(value = "mlogo",required = false) MultipartFile image,HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		String sessionId = (String) session.getAttribute("adminSessionId");

		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.ADMINISTRATOR) && authority.contains(Authorities.AUTHENTICATED)) {
					System.out.print("inside processing the merchant Registration");
					merchant.setSessionId(sessionId);
					RegisterError error = registerValidation.checkMerchantRegistrationError(merchant);
					if (error.isValid()) {
                        System.err.println(image.getSize());
                        if(image.getSize() > 0) {
                            boolean validImage = isValidImage(image);
                            if (validImage) {
                                String rootDirectory = request.getSession().getServletContext().getRealPath("/");
                                System.err.print(rootDirectory);
                                String status = saveLogo(rootDirectory, image);
                                System.err.print(status);
                                if (status.contains("|")) {
                                    String saved[] = status.split("\\|");
                                    for (String s : saved) {
                                        System.err.print("fsdf ::" + s);
                                    }
                                    System.err.print("saved[0]" + saved[0]);
                                    if (saved[0].contains("true")) {
                                        System.err.println("path of file is" + saved[1]);
                                        merchant.setImage(saved[1]);
                                    }
                                }
                            }
                        }else {
                            error.setMiddleName("Choose Image");
                        }
						AddMerchantResponse resp = appAdminApi.addMerchant(merchant);
						if (resp.isSuccess()) {
							model.addAttribute(ModelMapKey.MESSAGE, resp.getDetails());
							return "Admin/AddMerchant";
						}
						else {
							model.addAttribute(ModelMapKey.MESSAGE,
									"Error Processing Merchant Details...Please try again later");
							return "Admin/AddMerchant";
						}
						
					} else {
                        System.err.println("error is there in input");
						model.addAttribute(ModelMapKey.MESSAGE, error);
					}
					return "Admin/AddMerchant";
				}
			}
		}
		return "Admin/Login";
	}


	private boolean isValidImage(MultipartFile file) {
		long length = 2 * 1024 * 1024;
		File imageFile = null;
		try {
			imageFile = convert(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean isValid = false;
		if (file.getContentType().contains("image")) {
			isValid = true;
		}
		if (file.getSize() <= length) {
			try {
				Image image = ImageIO.read(imageFile);

				if (image == null) {
					System.out.println("The file" + file.getName() + "could not be opened , it is not an image");
				} else {
					isValid = true;
				}
			} catch (IOException ex) {
				System.out
						.println("The file" + file.getOriginalFilename() + "could not be opened , an error occurred.");
			}
		}
		return isValid;
	}

	private File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	private String saveImage(String rootDirectory, MultipartFile image) {
		boolean isSaved = false;
		String filePath = "";
		String seperator = "|";
		String[] format = image.getContentType().split("/");
		String fileFormat = "";
		String[] formats = { "jpeg", "png", "jpg" };
		for (String fmt : formats) {
			if (format[1].equals(fmt)) {
				fileFormat = fmt;
			}
		}
		if (!CommonValidation.isNull(fileFormat)) {
			try {
				String fileName = String.valueOf(System.currentTimeMillis());
				File dirs = new File(rootDirectory + "/resources/profileImages/" + fileName + "." + fileFormat);
				dirs.mkdirs();
				image.transferTo(dirs);
				filePath = "/resources/profileImages/" + fileName + "." + fileFormat;
				isSaved = true;
				return isSaved + seperator + filePath;
			} catch (Exception ex) {
				return "Exception Occurred";
			}
		}
		return "Exception Occurred";
	}


	String debitTransactions(String transactionId) {
		char lastChar = transactionId.charAt(transactionId.length() - 1);
		return lastChar + "";
	}

	String debitLastTwoChar(String transactionId) {
		char lastChar = transactionId.charAt(transactionId.length() - 2);
		return lastChar + "";
	}

	String removeLastChar(String transactionId) {
		String lastChar = transactionId.substring(0, transactionId.length() - 1);
		return lastChar;
	}

	String removeDSCSCC(String transactionId) {
		char lastChar = transactionId.charAt(transactionId.length() - 1);
		char secLastChar = transactionId.charAt(transactionId.length() - 2);
		return secLastChar + lastChar + "";
	}

	String findTransactionId(String trasaction) {
		char last = trasaction.charAt(trasaction.length() - 1);
		char secLast;
		if (last == 68) {
			String s = trasaction.substring(0, trasaction.length() - 1);
			return s;
		}
		return null;
	}

}
