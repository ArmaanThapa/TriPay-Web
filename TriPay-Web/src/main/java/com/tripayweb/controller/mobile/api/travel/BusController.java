/*package com.tripayweb.controller.mobile.api.travel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.api.IBusApi;
import com.tripayweb.app.api.ISendMoneyApi;
import com.tripayweb.app.api.IUserApi;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.BookBusSeatRequest;
import com.tripayweb.app.model.request.BusBookingDetailsRequest;
import com.tripayweb.app.model.request.BusDetailsRequest;
import com.tripayweb.app.model.request.BusSourcesRequest;
import com.tripayweb.app.model.request.OfflinePaymentRequest;
import com.tripayweb.app.model.request.UserDetailsRequest;
import com.tripayweb.app.model.response.BookBusSeatResponse;
import com.tripayweb.app.model.response.BusBookingDetailsResponse;
import com.tripayweb.app.model.response.BusDetailsResponse;
import com.tripayweb.app.model.response.BusSourcesResponse;
import com.tripayweb.app.model.response.OfflinePaymentResponse;
import com.tripayweb.app.model.response.UserDetailsResponse;
import com.tripayweb.model.error.BlockBusError;
import com.tripayweb.model.error.BusBookingError;
import com.tripayweb.model.error.BusDetailsError;
import com.tripayweb.util.APIUtils;
import com.tripayweb.validation.BusValidation;
import com.thirdparty.model.ResponseDTO;

@Controller
@RequestMapping("/Api/{version}/{role}/{device}/{language}/Travel/Bus")
public class BusController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());


	private final String consumerKey = "59285FC07292811D72785AC287ACE3E8";
	private final String consumerSecret = "04DAF1E2C80A758DCC6BCEBE436D25BC";
	private MessageSource messageSource;

	private final IAuthenticationApi authenticationApi;
	private final BusValidation busValidation;
	private final IUserApi userApi;

	public BusController(IAuthenticationApi authenticationApi, BusValidation busValidation,IUserApi userApi) {
		this.authenticationApi = authenticationApi;
		this.busValidation = busValidation;
		this.userApi = userApi;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/getSources", method = RequestMethod.POST)
	public ResponseEntity<String> getSources(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language,
			@RequestHeader(value = "hash", required = false) String hash, @RequestBody BusSourcesRequest dto,
			HttpServletRequest request, HttpServletResponse response) {
		BusSourcesResponse resp = new BusSourcesResponse();
		dto.setConsumerKey(consumerKey);
		dto.setConsumerSecret(consumerSecret);
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				System.out.println(dto.getSessionId());
				if (dto.getSessionId() != null && dto.getSessionId().length() > 0) {
					System.out.println(consumerKey);
					System.out.println(consumerSecret);
	//				UserDetailsResponse userDetailsResponse = userApi.getUserDetails(req, Role.USER);
					if((dto.getConsumerKey() != null) && (dto.getConsumerSecret() != null)) {
//						resp = busApi.getSources(dto);
					} else {
						resp.setSuccess(false);
						resp.setCode("F00");
						resp.setMessage("Invalid Input!");
						resp.setStatus("FAILED");
						resp.setResponse(APIUtils.getFailedJSON().toString());
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Session Null");
					resp.setStatus("FAILED");
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			} else {
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Unknown device");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			resp.setSuccess(false);
			resp.setCode("F00");
			resp.setMessage("Unauthorised access");
			resp.setStatus("FAILED");
			resp.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<String>(resp.getResponse(), HttpStatus.OK);
	}

	@RequestMapping(value = "/getAvailableBuses", method = RequestMethod.POST)
	public ResponseEntity<String> getSources(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language,
			@RequestHeader(value = "hash", required = false) String hash, @RequestBody BusDetailsRequest dto,
			HttpServletRequest request, HttpServletResponse response) {
		BusDetailsResponse resp = new BusDetailsResponse();
		BusDetailsError error = new BusDetailsError();
		dto.setConsumerKey(consumerKey);
		dto.setConsumerSecret(consumerSecret);
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				if (dto.getSessionId() != null && dto.getSessionId().length() > 0) {
					if ((dto.getConsumerKey() != null) && (dto.getConsumerSecret() != null)) {
						error = busValidation.checkBusDetailsError(dto);
						if (error.isValid()) {
							dto.setUserType("5");
							if (dto.getReturnDate().equals("")) {
								dto.setTripType("1");
								// dto.setReturnDate(dto.getJourneyDate());
							} else {
								dto.setTripType("2");
							}
//							resp = busApi.getBusdetails(dto);
							if (resp.getCode().equals("200")) {
								resp.setCode("S00");
								resp.setStatus("SUCCESS");
								return new ResponseEntity<String>(resp.getMessage(), HttpStatus.OK);
							} else {
								// data = resp.getMessage();
								return new ResponseEntity<String>(resp.getMessage(), HttpStatus.OK);
							}
						} else {
							resp.setCode("F04");
							resp.setMessage("Invalid Input");
							resp.setDetails(error);
						}
					} else {
						resp.setSuccess(false);
						resp.setCode("F00");
						resp.setMessage("Invalid Input!");
						resp.setStatus("FAILED");
						resp.setResponse(APIUtils.getFailedJSON().toString());
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Session Null");
					resp.setStatus("FAILED");
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			} else {
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Unknown device");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			resp.setSuccess(false);
			resp.setCode("F00");
			resp.setMessage("Unauthorised access");
			resp.setStatus("FAILED");
			resp.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<String>(resp.getMessage(), HttpStatus.OK);
	}

	@RequestMapping(value = "/getTripDetails", method = RequestMethod.POST)
	public ResponseEntity<String> getBookingDetails(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language,
			@RequestHeader(value = "hash", required = false) String hash, @RequestBody BusBookingDetailsRequest dto,
			HttpServletRequest request, HttpServletResponse response) {
		BusBookingDetailsResponse resp = new BusBookingDetailsResponse();
		BusBookingError error = new BusBookingError();
		System.err.println("INSIDE TRIP DETAILS CONTROLLER");
		dto.setConsumerKey(consumerKey);
		dto.setConsumerSecret(consumerSecret);
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				if (dto.getSessionId() != null && dto.getSessionId().length() > 0) {
					if ((dto.getConsumerKey() != null) && (dto.getConsumerSecret() != null)) {
						error = busValidation.checkBookingError(dto);
						if (error.isValid()) {
							String d = dto.getTravels().replaceAll(" ", "");
							dto.setTravels(d);
							dto.setTripType("1");
							dto.setUserType("5");
//							resp = busApi.getTripDetails(dto);
							return new ResponseEntity<String>(resp.getMessage(), HttpStatus.OK);
						} else {
							resp.setSuccess(false);
							resp.setCode("F00");
							resp.setMessage("Invalid Input!");
							resp.setStatus("FAILED");
							resp.setResponse(APIUtils.getFailedJSON().toString());
						}
					} else {
						resp.setSuccess(false);
						resp.setCode("F00");
						resp.setMessage("Invalid Input!");
						resp.setStatus("FAILED");
						resp.setResponse(APIUtils.getFailedJSON().toString());
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Session Null");
					resp.setStatus("FAILED");
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			} else {
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Unknown device");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			resp.setSuccess(false);
			resp.setCode("F00");
			resp.setMessage("Unauthorised access");
			resp.setStatus("FAILED");
			resp.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<String>(resp.getMessage(), HttpStatus.OK);
	}

	@RequestMapping(value = "/blockSeat", method = RequestMethod.POST)
	public ResponseEntity<String> bookSelectedSeat(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language,
			@RequestHeader(value = "hash", required = false) String hash, @RequestBody BookBusSeatRequest dto,
			HttpServletRequest request, HttpServletResponse response) {
		BookBusSeatResponse resp = new BookBusSeatResponse();
		BlockBusError error = new BlockBusError();
		dto.setConsumerKey(consumerKey);
		dto.setConsumerSecret(consumerSecret);
		String data = "";
		String currentDate = "";
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				try {
					if(dto.getSessionId() != null && dto.getSessionId().length() > 0) {
						
						boolean valid = validateSession(dto.getSessionId());
						
						if((dto.getConsumerKey() != null) && (dto.getConsumerSecret() != null)) {
							error = busValidation.checkBlockBusError(dto);
							if (error.isValid()) {
								dto.setTripType("1");
								dto.setUserType("5");
								dto.setConvenienceFee("0");
								long transctionrefno = System.currentTimeMillis();
								System.out.println(transctionrefno);
								dto.setTransctionRefNo("" + transctionrefno);

//								resp = busApi.blockBusTicket(dto);
								System.out.println(resp);
								System.err.println(resp.getCodeCondtion());
								if (resp.getCodeCondtion().equals("200")) {
									System.out.println(dto.getTransctionRefNo());
									try {
										String currentTime = String.valueOf(System.currentTimeMillis());
										DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
										long milliSeconds = Long.parseLong(currentTime);
										Calendar calendar = Calendar.getInstance();
										calendar.setTimeInMillis(milliSeconds);
										currentDate = formatter.format(calendar.getTime());
										System.err.println("Current Date time  : "+currentDate);
										dto.setBlockingRefNo(resp.getBlockingRefNo());
										dto.setBookingRefNo(resp.getBookingRefNo());
										
									} catch (Exception e) {
										e.printStackTrace();
									}
									// Calling App
								System.out.println(dto.getJourneyDate());
								System.out.println(dto.getReturnDate());
								String journeyDate = convertDate(dto.getJourneyDate());
								System.out.println(journeyDate);
								String returnDate = convertDate(dto.getReturnDate());
								System.out.println(returnDate);
								String bookingDate = convertDate(currentDate);
								System.out.println(bookingDate);
								
								dto.setJourneyDate(journeyDate);
								dto.setReturnDate(returnDate);
								dto.setDateOfBooking(bookingDate);								
//								String appapical = busApi.saveBlockingDetails(dto, "", resp.getStatus());
								String appapical ="";
									JSONObject obj = new JSONObject(appapical);
									String msg = obj.getString("code");
									if (msg.equalsIgnoreCase("S00")) {
										System.out.println(resp.getCode());
//										String resp1 = busApi.bookBusTicketinweb(resp.getBookingRefNo());
//										String c = busApi.getBusTicketBook(resp.getBookingRefNo());
String c="";
										JSONObject ticket = new JSONObject(c);
//										String a = ticket.getString("BookingDate");
//										String a3 = ticket.getString("BookingRefNo");
//										String bookid = ticket.getString("BlockId");
////										String a18 = ticket.getString("ClientID");
										// Calling App
//										String successResp = busApi.busBookingSuccess(a3, bookid, a18, a,
												"" + transctionrefno, dto.getSessionId(), currentDate);

										return new ResponseEntity<String>(c, HttpStatus.OK);
									} else {
										return new ResponseEntity<String>(appapical, HttpStatus.OK);
									}
								} else {
									return new ResponseEntity<String>(resp.getMessage(), HttpStatus.OK);
								}
							} else {
								resp.setSuccess(false);
								resp.setCode("F00");
								resp.setMessage("Invalid Input!");
								resp.setStatus("FAILED");
							}
						} else {
							resp.setSuccess(false);
							resp.setCode("F00");
							resp.setMessage("Session Null");
							resp.setStatus("FAILED");
							resp.setResponse(APIUtils.getFailedJSON().toString());
						}
					} else {
						resp.setSuccess(false);
						resp.setCode("F00");
						resp.setMessage("Invalid Input!");
						resp.setStatus("FAILED");
						resp.setResponse(APIUtils.getFailedJSON().toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Unknown device");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
				return new ResponseEntity<String>(resp.getMessage(), HttpStatus.OK);
			}
		} else {
			resp.setSuccess(false);
			resp.setCode("F00");
			resp.setMessage("Unauthorised access");
			resp.setStatus("FAILED");
			resp.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<String>(resp.getMessage(), HttpStatus.OK);
	}

	public String convertDate(String inputDate) {

		String journeyDate = inputDate;
		String convertedDate = "";
		try {
			SimpleDateFormat sdfSource = new SimpleDateFormat("dd-MM-yyyy");
			Date date = sdfSource.parse(journeyDate);
			System.out.println("Date : " + date);
			SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd");
			convertedDate = sdfDestination.format(date);
			System.err.println("Converted Date : " + convertedDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertedDate;
	}

	public boolean validateSession(String sessionId) {
		UserDetailsRequest dto = new UserDetailsRequest();
		boolean activeSession = false;
		dto.setSessionId(sessionId);
		 UserDetailsResponse result = userApi.getUserDetails(dto, Role.USER);
		if(result.getCode().equalsIgnoreCase("S00")) {
			activeSession = true;
			return activeSession;
		}
		activeSession = false;
		return activeSession;
	}
	
	
	
}
*/