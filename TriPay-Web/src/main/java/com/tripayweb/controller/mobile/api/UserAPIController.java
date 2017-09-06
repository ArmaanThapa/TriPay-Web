package com.tripayweb.controller.mobile.api;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.model.request.*;
import com.tripayweb.app.model.response.*;
import com.tripayweb.model.error.KycError;
import com.tripayweb.model.error.MpinError;
import com.tripayweb.model.web.SharePointDTO;
import com.tripayweb.util.Authorities;
import com.tripayweb.util.ModelMapKey;
import com.tripayweb.validation.CommonValidation;
import com.tripayweb.validation.KycValidation;
import com.tripayweb.validation.MpinValidation;
import org.json.JSONArray;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tripayweb.app.api.IUserApi;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Role;
import com.tripayweb.model.app.response.RedeemCodeResponse;
import com.tripayweb.util.APIUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/Api/{version}/{role}/{device}/{language}/User")
public class UserAPIController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;

	private final IUserApi userApi;
	private final MpinValidation mpinValidation;
	private final IAuthenticationApi authenticationApi;
	private final KycValidation kycValidation;

	public UserAPIController(IUserApi userApi, MpinValidation mpinValidation, IAuthenticationApi authenticationApi,
			KycValidation kycValidation) {
		this.userApi = userApi;
		this.mpinValidation = mpinValidation;
		this.authenticationApi = authenticationApi;
		this.kycValidation = kycValidation;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/GetUserDetails", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<UserDetailsResponse> getUserDetails(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody UserDetailsRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		UserDetailsResponse result = new UserDetailsResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {

				result = userApi.getUserDetails(dto, Role.USER);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else if (role.equalsIgnoreCase(Role.MERCHANT.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = userApi.getUserDetails(dto, Role.MERCHANT);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<UserDetailsResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/EditProfile", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<EditProfileResponse> editProfile(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody EditProfileRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		EditProfileResponse result = new EditProfileResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = userApi.editProfile(dto);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<EditProfileResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/GetMerchants", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<MerchantResponse> listMerchants(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody SessionDTO dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		MerchantResponse result = new MerchantResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = userApi.getAllMerchants(dto);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
		}
		return new ResponseEntity<MerchantResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/SharePoints", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<SharePointsResponse> sharePointsToUser(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody SharePointDTO dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		SharePointsResponse result = new SharePointsResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = userApi.sharePoints(dto);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<SharePointsResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/ChangePassword", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<ChangePasswordResponse> changePassword(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody ChangePasswordRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		ChangePasswordResponse result = new ChangePasswordResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = userApi.changePassword(dto);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else if (role.equalsIgnoreCase(Role.MERCHANT.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = userApi.updateMerchantPassword(dto);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<ChangePasswordResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/UploadPicture", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<UploadPictureResponse> uploadPicture(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @ModelAttribute UploadPictureRequest dto,
			@RequestHeader(value = "sessionId", required = false) String sessionId, HttpServletRequest request,
			HttpServletResponse response) {
		UploadPictureResponse result = new UploadPictureResponse();
		result.setCode("F00");
		result.setSuccess(false);
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				System.err.println("Inside Upload Picture DTO");
				MultipartFile file = dto.getProfilePicture();
				System.err.println("file names" + file.getOriginalFilename());
				String contentType = file.getContentType();
				System.out.print("content type ::" + contentType);
				boolean validImage = isValidImage(file);
				System.out.println(validImage);
				if (validImage) {
					dto.setSessionId(sessionId);
					String rootDirectory = request.getSession().getServletContext().getRealPath("/");
					System.err.print(rootDirectory);
					String status = saveImage(rootDirectory, file);
					System.err.print(status);
					if (status.contains("|")) {
						String saved[] = status.split("\\|");
						System.err.print("saved[0]" + saved[0]);
						if (saved[0].contains("true")) {
							System.err.println("path of file is" + saved[1]);
							dto.setPath(saved[1]);
							result = userApi.uploadPicture(dto);
							result.setCode("S00");
							result.setMessage(result.getMessage());
							result.setSuccess(true);
						}
					}
				} else {
					System.err.println("Image not uploaded");
					result.setMessage("Please enter valid file format and valid size");
				}
			} else {
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<UploadPictureResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/GetReceipts", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<ReceiptsResponse> getReceipts(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody ReceiptsRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		ReceiptsResponse result = new ReceiptsResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = userApi.getReceipts(dto);
				JSONArray receiptsArray = (JSONArray) result.getAdditionalInfo();
				result.setAdditionalInfo(null);
				return new ResponseEntity<ReceiptsResponse>(result, HttpStatus.OK);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else if (role.equalsIgnoreCase(Role.MERCHANT.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = userApi.getMerchantReceipts(dto);
				return new ResponseEntity<ReceiptsResponse>(result, HttpStatus.OK);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<ReceiptsResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/STransactions", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<ReceiptsResponse> getTransactions(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody SessionDTO dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		ReceiptsResponse result = new ReceiptsResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = userApi.getSuccessfulTransactions(dto);
				return new ResponseEntity<ReceiptsResponse>(result, HttpStatus.OK);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<ReceiptsResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/UpdateFavourite", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<ReceiptsResponse> updateFavourite(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody FavouriteRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		ReceiptsResponse result = new ReceiptsResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = userApi.updateFavouriteTransaction(dto);
				return new ResponseEntity<ReceiptsResponse>(result, HttpStatus.OK);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<ReceiptsResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/SetMPIN", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<MPINResponse> setMPIN(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody SetMPINRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		MPINResponse result = new MPINResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = userApi.setMPIN(dto);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<MPINResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/ChangeMPIN", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<MPINResponse> changeMPIN(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody ChangeMPINRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		MPINResponse result = new MPINResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = userApi.changeMPIN(dto);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<MPINResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/DeleteMPIN", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<MPINResponse> deleteMPIN(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody DeleteMPINRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		MPINResponse result = new MPINResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = userApi.deleteMPIN(dto);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<MPINResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/ForgotMPIN", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<MPINResponse> deleteMPIN(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody ForgotMpinRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		MPINResponse result = new MPINResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				MpinError error = mpinValidation.checkError(dto);
				if (error.isValid()) {
					result = userApi.forgotMPIN(dto);
				} else {
					result.setSuccess(false);
					result.setCode("F04");
					result.setDetails(error.toString());
				}
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<MPINResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/VerifyMPIN", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<MPINResponse> verifyMPIN(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody SetMPINRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		MPINResponse result = new MPINResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = userApi.verifyMPIN(dto);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<MPINResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/Invite/Email", method = RequestMethod.POST)
	public ResponseEntity<InviteFriendEmailResponse> inviteEmailFriend(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody InviteFriendEmailRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response, ModelMap mp, HttpSession session) {
		InviteFriendEmailResponse result = new InviteFriendEmailResponse();
		String sessionId = (String) dto.getSessionId();
		if (sessionId != null && sessionId.length() != 0) {
			if (role.equalsIgnoreCase(Role.USER.getValue())) {
				if (device.equalsIgnoreCase(Device.ANDROID.getValue())
						|| device.equalsIgnoreCase(Device.WINDOWS.getValue())) {
					result = userApi.inviteEmailFriend(dto);
				} else {
					result.setSuccess(false);
					result.setCode("F00");
					result.setMessage("Unknown device");
					result.setStatus("FAILED");
					result.setResponse(APIUtils.getFailedJSON().toString());
				}
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unauthorised access");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		}
		return new ResponseEntity<InviteFriendEmailResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/Invite/Mobile", method = RequestMethod.POST)
	public ResponseEntity<InviteFriendMobileResponse> inviteMobileFriend(
			@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
			@PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
			@RequestBody InviteFriendMobileRequest dto, @RequestHeader(value = "hash", required = false) String hash,
			HttpServletRequest request, HttpServletResponse response, ModelMap mp, HttpSession session) {
		InviteFriendMobileResponse result = new InviteFriendMobileResponse();
		String sessionId = (String) dto.getSessionId();
		if (sessionId != null && sessionId.length() != 0) {
			if (role.equalsIgnoreCase(Role.USER.getValue())) {
				if (device.equalsIgnoreCase(Device.ANDROID.getValue())
						|| device.equalsIgnoreCase(Device.WINDOWS.getValue())) {
					result = userApi.inviteMobileFriend(dto);
				} else {
					result.setSuccess(false);
					result.setCode("F00");
					result.setMessage("Unknown device");
					result.setStatus("FAILED");
					result.setResponse(APIUtils.getFailedJSON().toString());
				}
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unauthorised access");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		}

		return new ResponseEntity<InviteFriendMobileResponse>(result, HttpStatus.OK);
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
		Thread thread = null;
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

	@RequestMapping(value = "/Redeem/Code", method = RequestMethod.POST)
	public ResponseEntity<RedeemCodeResponse> redeemCode(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody RedeemCodeRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		RedeemCodeResponse result = new RedeemCodeResponse();
		String sessionId = dto.getSessionId();
		if (sessionId != null && sessionId.length() != 0) {
			if (role.equalsIgnoreCase(Role.USER.getValue())) {
				if (device.equalsIgnoreCase(Device.ANDROID.getValue())
						|| device.equalsIgnoreCase(Device.WINDOWS.getValue())) {
					result = userApi.redeemPromoCode(dto);
				} else {
					result.setSuccess(false);
					result.setCode("F00");
					result.setMessage("Unknown device");
					result.setStatus("FAILED");
					result.setResponse(APIUtils.getFailedJSON().toString());
				}
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unauthorised access");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		}

		return new ResponseEntity<RedeemCodeResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/KycRequest", method = RequestMethod.POST)
	public ResponseEntity<KycResponse> kycRequest(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody KycVerificationDTO dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		KycResponse result = new KycResponse();
		KycError error = new KycError();
		String sessionId = dto.getSessionId();
		if (sessionId != null && sessionId.length() != 0) {
			if (role.equalsIgnoreCase(Role.USER.getValue())) {
				if (device.equalsIgnoreCase(Device.ANDROID.getValue())
						|| device.equalsIgnoreCase(Device.WINDOWS.getValue())) {

					error = kycValidation.checkKycError(dto);
					if (error.isValid()) {
						result = userApi.kycVerification(dto);
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
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unauthorised access");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		}

		return new ResponseEntity<KycResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/KycRequest/OTPVerification", method = RequestMethod.POST)
	public ResponseEntity<KycResponse> otpVerification(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody KycVerificationDTO dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		KycResponse result = new KycResponse();
		KycError error = new KycError();
		String sessionId = dto.getSessionId();
		if (sessionId != null && sessionId.length() != 0) {
			if (role.equalsIgnoreCase(Role.USER.getValue())) {
				if (device.equalsIgnoreCase(Device.ANDROID.getValue())
						|| device.equalsIgnoreCase(Device.WINDOWS.getValue())) {
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
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unauthorised access");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		}

		return new ResponseEntity<KycResponse>(result, HttpStatus.OK);
	}


}
