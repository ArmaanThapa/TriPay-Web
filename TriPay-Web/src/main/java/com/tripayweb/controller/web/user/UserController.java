package com.tripayweb.controller.web.user;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.recaptcha.model.JCaptchaRequest;
import com.google.recaptcha.model.JCaptchaResponse;
import com.tripayweb.validation.CommonValidation;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.recaptcha.api.IVerificationApi;
import com.google.recaptcha.model.JCaptchaRequest;
import com.google.recaptcha.model.JCaptchaResponse;
import com.google.recaptcha.model.ReCaptchaRequest;
import com.google.recaptcha.model.ReCaptchaResponse;
import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.api.IUserApi;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.SchoolPayment;
import com.tripayweb.app.model.request.ChangeMPINRequest;
import com.tripayweb.app.model.request.ChangePasswordRequest;
import com.tripayweb.app.model.request.DeleteMPINRequest;
import com.tripayweb.app.model.request.EditProfileRequest;
import com.tripayweb.app.model.request.InviteFriendEmailRequest;
import com.tripayweb.app.model.request.KycVerificationDTO;
import com.tripayweb.app.model.request.ReceiptsRequest;
import com.tripayweb.app.model.request.RedeemCode;
import com.tripayweb.app.model.request.RedeemCodeRequest;
import com.tripayweb.app.model.request.SetMPINRequest;
import com.tripayweb.app.model.request.UploadPictureRequest;
import com.tripayweb.app.model.request.UserDetailsRequest;
import com.tripayweb.app.model.response.ChangePasswordResponse;
import com.tripayweb.app.model.response.EditProfileResponse;
import com.tripayweb.app.model.response.InviteFriendEmailResponse;
import com.tripayweb.app.model.response.KycResponse;
import com.tripayweb.app.model.response.MPINResponse;
import com.tripayweb.app.model.response.ReceiptsResponse;
import com.tripayweb.app.model.response.TransactionDTO;
import com.tripayweb.app.model.response.UploadPictureResponse;
import com.tripayweb.app.model.response.UserDetailsResponse;
import com.tripayweb.model.app.response.RedeemCodeResponse;
import com.tripayweb.model.error.EditProfileError;
import com.tripayweb.model.error.KycError;
import com.tripayweb.util.APIUtils;
import com.tripayweb.util.Authorities;
import com.tripayweb.util.JSONParserUtil;
import com.tripayweb.util.LogCat;
import com.tripayweb.util.ModelMapKey;
import com.tripayweb.validation.ChangePasswordValidation;
import com.tripayweb.validation.EditProfileValidation;
import com.tripayweb.validation.KycValidation;
import com.tripayweb.validation.MpinValidation;
import com.sun.research.ws.wadl.Resource;

@Controller
@RequestMapping("/User")
public class UserController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;
	private final EditProfileValidation editProfileValidation;
	private final ChangePasswordValidation changePasswordValidation;
	private final IUserApi userApi;
	private final IAuthenticationApi authenticationApi;
	private final IVerificationApi verificationApi;
	private final KycValidation kycValidation;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public UserController(EditProfileValidation editProfileValidation,
			ChangePasswordValidation changePasswordValidation, IUserApi userApi, IAuthenticationApi authenticationApi,
			IVerificationApi verificationApi, KycValidation kycValidation) {
		this.editProfileValidation = editProfileValidation;
		this.changePasswordValidation = changePasswordValidation;
		this.userApi = userApi;
		this.authenticationApi = authenticationApi;
		this.verificationApi = verificationApi;
		this.kycValidation = kycValidation;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/EditProfile/Process", method = RequestMethod.POST)
	public String processEditedDetails(@ModelAttribute("editUser") EditProfileRequest dto, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		EditProfileError error = editProfileValidation.checkError(dto);
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
					if (error.isValid()) {
						dto.setSessionId(sessionId);
						EditProfileResponse resp = userApi.editProfile(dto);
						modelMap.addAttribute("message", resp.getMessage());
						return "User/Settings";
					} else {
						modelMap.addAttribute(ModelMapKey.ERROR, error);
						return "User/Settings";
					}
				}
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/UploadPicture", method = RequestMethod.POST)
	public String processProfilePicture(@ModelAttribute UploadPictureRequest dto, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes modelMap, HttpSession session) {

		UploadPictureResponse result = new UploadPictureResponse();
		MultipartFile file = dto.getProfilePicture();
		logger.info("file names" + file.getOriginalFilename());
		String contentType = file.getContentType();
		logger.info("content type ::" + contentType);
		boolean validImage = isValidImage(file);
		logger.info(validImage + "");
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
					if (validImage) {
						dto.setSessionId(sessionId);
						String rootDirectory = request.getSession().getServletContext().getRealPath("/");
						System.err.print(rootDirectory);
						String status = saveImage(rootDirectory, file);
						System.err.print(status);

						if (status.contains("|")) {

							String saved[] = status.split("\\|");
							for (String s : saved) {
								System.err.print("fsdf ::" + s);
							}
							System.err.print("saved[0]" + saved[0]);
							if (saved[0].contains("true")) {
								System.err.println("path of file is" + saved[1]);
								dto.setPath(saved[1]);
								result = userApi.uploadPicture(dto);
							}
						}
						modelMap.addAttribute("msg", result.getMessage());
					} else {

						modelMap.addAttribute(ModelMapKey.ERROR, "Please enter valid file format and valid size");
					}
				}
			}
		}

		return "redirect:/";

	}

	@RequestMapping(value = "/UpdatePassword/Process", method = RequestMethod.POST)
	public String processNewPassword(@ModelAttribute("updatePassword") ChangePasswordRequest dto,
			HttpServletRequest request, HttpServletResponse response, Model modelMap, HttpSession session) {
		logger.info("User " + dto.getConfirmPassword());
		logger.info("User " + dto.getNewPassword());
		logger.info("User " + dto.getOldPassword());
		logger.info("User Name" + (String) (session.getAttribute("username")));

		dto.setUsername((String) (session.getAttribute("username")));
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
					dto.setSessionId(sessionId);
					ChangePasswordResponse resp = userApi.changePassword(dto);
					modelMap.addAttribute("message", resp.getMessage());
					return "User/Settings";
				}
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/MPIN/Set", method = RequestMethod.POST)
	public String setMPIN(@ModelAttribute("setMPIN") SetMPINRequest dto, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
					dto.setSessionId(sessionId);
					MPINResponse resp = userApi.setMPIN(dto);
					modelMap.addAttribute("message", resp.getMessage());
					return "User/MPIN";
				}
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/MPIN/Change", method = RequestMethod.POST)
	public String setMPIN(@ModelAttribute("changeMPIN") ChangeMPINRequest dto, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
					dto.setSessionId(sessionId);
					MPINResponse resp = userApi.changeMPIN(dto);
					modelMap.addAttribute("message", resp.getMessage());
					return "User/MPIN";
				}
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/MPIN/Delete", method = RequestMethod.POST)
	public String setMPIN(@ModelAttribute("deleteMPIN") DeleteMPINRequest dto, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
					dto.setSessionId(sessionId);
					MPINResponse resp = userApi.deleteMPIN(dto);
					modelMap.addAttribute("message", resp.getMessage());
					return "User/MPIN";
				}
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/Receipts", method = RequestMethod.GET)
	public String getReceipts(@ModelAttribute("editUser") ReceiptsRequest dto, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) throws JSONException {
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
					ArrayList<TransactionDTO> transactionList = new ArrayList<>();
					logger.info("1 in controller");
					dto.setSessionId(sessionId);
					dto.setSize(1000);
					dto.setPage(0);
					ReceiptsResponse resp = userApi.getReceipts(dto);
					logger.info("Response ::" + resp.getAdditionalInfo());
					JSONArray receiptsArray = (JSONArray) resp.getAdditionalInfo();
					for (int i = 0; i < receiptsArray.length(); i++) {
						JSONObject obj = receiptsArray.getJSONObject(i);
						TransactionDTO transaction = new TransactionDTO();
						transaction.setAmount(JSONParserUtil.getDouble(obj, "amount"));
						long timeinMillis = (long) JSONParserUtil.getLong(obj, "created");
						Date dateTime = new Date(timeinMillis);
						System.err.println("time is ::" + sdf.format(dateTime));
						transaction.setDate("" + sdf.format(dateTime));
						transaction.setTransactionRefNo(JSONParserUtil.getString(obj, "transactionRefNo"));
						transaction.setDescription(JSONParserUtil.getString(obj, "description"));
						transaction.setStatus(JSONParserUtil.getString(obj, "status"));
						transaction.setDebit(JSONParserUtil.getBoolean(obj, "debit"));
						transactionList.add(transaction);
					}
					modelMap.put("transactions", transactionList);
					return "User/Receipts";
				}
			}
		}
		return "redirect:/";
	}


	@RequestMapping(value = "/GetMReceiptsInJSON", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<ReceiptsResponse> getReceiptsInJSON(@RequestBody ReceiptsRequest dto, HttpServletRequest request,
												 HttpServletResponse response,HttpSession session) {
		ReceiptsResponse result = new ReceiptsResponse();
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.MERCHANT) && authority.contains(Authorities.AUTHENTICATED)) {
					dto.setSessionId(sessionId);
					result = userApi.getMerchantReceipts(dto);
				}
			}
		}
		return new ResponseEntity<ReceiptsResponse>(result, HttpStatus.OK);
	}





	@RequestMapping(value = "/Invite/Email", method = RequestMethod.POST)
	public ResponseEntity<InviteFriendEmailResponse> inviteEmailFriend(
			@ModelAttribute("invitewebfriend") InviteFriendEmailRequest ifw, HttpServletRequest request,
			HttpServletResponse response, ModelMap mp, HttpSession session) {
		InviteFriendEmailResponse result = new InviteFriendEmailResponse();
		String sessionId = (String) session.getAttribute("sessionId");

		JCaptchaRequest cap = new JCaptchaRequest();
		cap.setSessionId(request.getSession().getId());
		cap.setCaptchaResponse(ifw.getCaptchaResponse());
		JCaptchaResponse jCaptchaResponse = verificationApi.isValidJCaptcha(cap);

		if (jCaptchaResponse.isValid()) {
			if (sessionId != null && sessionId.length() != 0) {
				String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
				if (authority != null) {
					if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
						ifw.setSessionId((String) session.getAttribute("sessionId"));
						InviteFriendEmailResponse resp = userApi.inviteEmailFriend(ifw);
						mp.addAttribute("message", resp.getMessage());
						return new ResponseEntity<InviteFriendEmailResponse>(resp, HttpStatus.OK);
					}
				}
			}
		} else {
			result.setCode("F00");
			result.setMessage("Invalid Captcha");
		}
		return new ResponseEntity<InviteFriendEmailResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/GetUserDetails", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<UserDetailsResponse> getUserDetails(@ModelAttribute("userDetails") UserDetailsRequest dto,
			HttpServletRequest request, HttpServletResponse response, HttpSession session,Model model) {
		UserDetailsResponse result = new UserDetailsResponse();
		List<UserDetailsResponse> list=new ArrayList<UserDetailsResponse>();
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
					dto.setSessionId(sessionId);
					result = userApi.getUserDetails(dto, Role.USER);
					JSONObject obj=new JSONObject(result);
					try {
						//boolean flag; 
					String usertype=obj.getString("userType");
					//System.out.println(usertype);
					
					if(usertype.equalsIgnoreCase("KYC")){
						result.setFlag(true);
						
					}
					else {
						result.setFlag(false);
						
					}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				//	System.out.println("this is response:"+result.toString());
					dto.setSessionId(sessionId);
					return new ResponseEntity<UserDetailsResponse>(result, HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<UserDetailsResponse>(result, HttpStatus.OK);
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

	@RequestMapping(value = "/ReSendEmailOTP", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<UserDetailsResponse> getReSendEmailOTP(@ModelAttribute("userDetails") UserDetailsRequest dto,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		UserDetailsResponse result = new UserDetailsResponse();
		String sessionId = (session.getAttribute("sessionId") == null ? dto.getSessionId()
				: (String) session.getAttribute("sessionId"));
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
					dto.setSessionId(sessionId);
					result = userApi.reSendEmailTop(dto);
					result.setCode("S00");
					result.setMessage("Email Sent Successfully");
					result.setSuccess(true);
					dto.setSessionId(sessionId);
					return new ResponseEntity<UserDetailsResponse>(result, HttpStatus.OK);
				}
			}
		} else {
			result.setCode("F03");
			result.setSuccess(false);
			result.setMessage("Invalid Session ID");
		}
		return new ResponseEntity<UserDetailsResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/RedeemCoupon", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<RedeemCodeResponse> getRedeemCode(@ModelAttribute("userDetails") RedeemCodeRequest dto,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		RedeemCodeResponse resp = new RedeemCodeResponse();
		if (sessionId != null && sessionId.length() != 0) {
			String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
			if (authority != null) {
				if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
					System.err.println("Redeem ::" + dto.getPromoCode());
					dto.setSessionId(sessionId);
					resp = userApi.redeemPromoCode(dto);
					dto.setSessionId(sessionId);
					return new ResponseEntity<RedeemCodeResponse>(resp, HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<RedeemCodeResponse>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/KycRequest/process", method = RequestMethod.POST)
	public String processUpgradeWallet(@ModelAttribute("upgradeWallet") KycVerificationDTO dto,
			HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, HttpSession session) {

		KycVerificationDTO verificationDTO = new KycVerificationDTO();
		KycResponse result = new KycResponse();
		KycError error = new KycError();
		String sessionId = (String) session.getAttribute("sessionId");
		verificationDTO.setSessionId(sessionId);
		verificationDTO.setAccountNumber(dto.getAccountNumber());
		verificationDTO.setMobileNumber(dto.getMobileNumber());
		if (sessionId != null && sessionId.length() != 0) {
			error = kycValidation.checkKycError(verificationDTO);
			if (error.isValid()) {
				result = userApi.kycVerification(verificationDTO);

			} else {
				result.setSuccess(false);
				result.setCode("F04");
				result.setDetails(error);
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unknown device");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		modelMap.addAttribute("resp", result.getCode());
		return "redirect:/Home";
	}

	@RequestMapping(value = "/OTP/kyc/Verify", method = RequestMethod.GET)
	public @ResponseBody String otpVerify(@RequestParam("key") String otp, HttpServletRequest request,
			HttpServletResponse response, ModelMap model, HttpSession session, KycVerificationDTO dto) {

		KycResponse result = new KycResponse();
		String sessionId = (String) session.getAttribute("sessionId");
		String mobileNumber = (String) session.getAttribute("mobileNumber");

		KycError error = new KycError();
		if (sessionId != null && sessionId.length() != 0) {
			error = kycValidation.checkKycOTPError(dto);
			if (error.isValid()) {
				result = userApi.kycOTPVerification(dto);
			} else {
				result.setSuccess(false);
				result.setCode("F04");
				result.setDetails(error);
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unknown device");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}

		return "/User/Home";
	}

	@RequestMapping(value = "/Kyc/resendOtp", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<KycResponse> resendOTPOffLineMerchant(@RequestBody KycVerificationDTO dto,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		System.err.println(".............................................................");

		String sessionId = (String) session.getAttribute("sessionId");
		String mobileNumber = (String) session.getAttribute("mobileNumber");

		KycVerificationDTO req = new KycVerificationDTO();
		KycResponse result = new KycResponse();
		System.err.println("session::::::");
		req.setSessionId(sessionId);
		req.setMobileNumber(mobileNumber);

		result = userApi.resendKycOTP(req);

		return new ResponseEntity<KycResponse>(result, HttpStatus.OK);
	}
	@RequestMapping(value="/SchoolPayment", method=RequestMethod.POST,
			produces={MediaType.APPLICATION_JSON_VALUE}, consumes={MediaType.APPLICATION_JSON_VALUE})
	ResponseEntity<SchoolPayment> paymentSchool(@RequestBody SchoolPayment dto,
			HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		System.err.println(dto.getRollNumber());
		System.err.println(dto.getFirstName());
		
		return new ResponseEntity<SchoolPayment>( HttpStatus.OK);
	}
	

}