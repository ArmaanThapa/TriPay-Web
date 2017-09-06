package com.tripayweb.app.api.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jettison.json.JSONObject;

import com.tripayweb.app.api.IBusApi;
import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Language;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.Version;
import com.tripayweb.app.model.request.BookBusSeatRequest;
import com.tripayweb.app.model.request.BusBookingDetailsRequest;
import com.tripayweb.app.model.request.BusDetailsRequest;
import com.tripayweb.app.model.request.BusSourcesRequest;
import com.tripayweb.app.model.response.BookBusSeatResponse;
import com.tripayweb.app.model.response.BusBookingDetailsResponse;
import com.tripayweb.app.model.response.BusDetailsResponse;
import com.tripayweb.app.model.response.BusSourcesResponse;
import com.tripayweb.app.utils.SecurityUtils;
import com.tripayweb.util.APIUtils;
import com.tripayweb.util.LogCat;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.thirdparty.model.ResponseDTO;

public class BusApi implements IBusApi{

	@Override
	public BusSourcesResponse getSources(BusSourcesRequest request) {
		BusSourcesResponse resp = new BusSourcesResponse();

		try {
			Client client = Client.create();
			client.addFilter(new LoggingFilter(System.out));
			WebResource webResource = client.resource("http://103.24.202.25/Buses/sources");
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("ConsumerKey", request.getConsumerKey())
					.header("ConsumerSecret", request.getConsumerSecret()).get(ClientResponse.class);
			String strResponse = response.getEntity(String.class);
			LogCat.print("All Sources :: " + strResponse);
			if (response.getStatus() != 200) {
				LogCat.print("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {

				LogCat.print("RESPONSE :: " + strResponse);
				if (strResponse != null) {
					org.json.JSONArray array = new org.json.JSONArray(strResponse);
					System.err.println("RESPONSE ARRAY : : "+array.toString());
					if (array != null) {
						//resp.setSuccess(true);
					//	resp.setCode("S00");
					//	resp.setMessage("Sources Received");
					//	resp.setStatus("SUCCESS");
						resp.setResponse(array.toString());
					} else {
						resp.setSuccess(false);
						resp.setCode("F00");
						resp.setMessage("Service unavailable");
						resp.setStatus("FAILED");
						resp.setResponse(APIUtils.getFailedJSON().toString());
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
					resp.setStatus("FAILED");
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	@Override
	public BusDetailsResponse getBusdetails(BusDetailsRequest request) {
		BusDetailsResponse resp = new BusDetailsResponse();
		try {
			System.out.println("http://103.24.202.25/Buses/AvailableBuses?sourceId="
					+ request.getSource() + "&destinationId=" + request.getDestinaion() + "&journeyDate="
					+ request.getJourneyDate() + "&tripType=" + request.getTripType() + "&userType="
					+ request.getUserType() /*+ "&returnDate=" + request.getReturnDate()*/);
			Client client = Client.create();
			WebResource webResource = client.resource("http://103.24.202.25/Buses/AvailableBuses?sourceId="
					+ request.getSource() + "&destinationId=" + request.getDestinaion() + "&journeyDate="
					+ request.getJourneyDate() + "&tripType=" + request.getTripType() + "&userType="
					+ request.getUserType()/* + "&returnDate=" + request.getReturnDate()*/);
		
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("ConsumerKey", request.getConsumerKey())
					.header("ConsumerSecret", request.getConsumerSecret()).get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			 System.out.println("Output from Server .... \n");
			 System.out.println(output);
			 resp.setMessage(output);
			 resp.setCode(response.getStatus() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	

	@Override
	public BusBookingDetailsResponse getTripDetails (BusBookingDetailsRequest request) {
		
		BusBookingDetailsResponse resp = new BusBookingDetailsResponse();
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("http://103.24.202.25/Buses/TripDetails?tripId="
					+ request.getTripId() + "&sourceId=" + request.getSourceId() + "&destinationId="
					+ request.getDestinationId() + "&journeyDate=" + request.getJourneyDate() + "&tripType="
					+ request.getTripType() + "&userType=" + request.getUserType() + "&provider=" 
					+ request.getProvider() + "&travelOPerator=" + request.getTravels() );

			System.out.println("http://103.24.202.25/Buses/TripDetails?tripId=" + request.getTripId() + "&sourceId="
					+ request.getSourceId() + "&destinationId=" + request.getDestinationId() + "&journeyDate="
					+ request.getJourneyDate() + "&tripType=" + request.getTripType()
					+ "&userType=" + request.getUserType() + "&provider=" + request.getProvider() 
					+ "&travelOPerator=" + request.getTravels());
			
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("ConsumerKey", request.getConsumerKey())
					.header("ConsumerSecret", request.getConsumerSecret()).get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			System.out.println(output);
			resp.setMessage(output);
			resp.setCode(response.getStatus() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	
	@Override
	public BookBusSeatResponse blockBusTicket(BookBusSeatRequest request) {
		BookBusSeatResponse resp = new BookBusSeatResponse();
		try {
			JSONObject payload = new JSONObject();
	//		payload.put("IsOfflineBooking", false);
			payload.put("TripId", request.getTripId());
			payload.put("BoardingId", request.getBoardingId());
			payload.put("NoofSeats", request.getNoOfSeats());
			payload.put("Fares", request.getFare());
			payload.put("Servicetax", request.getServiceTax());
			payload.put("ServiceCharge", request.getServiceCharge());
			payload.put("ConvenienceFee", request.getConvenienceFee());
			payload.put("SeatNos", request.getSeatNos());
			payload.put("Seatcodes", request.getSeatCodes());
			payload.put("Titles", request.getTitle());
			payload.put("Names", request.getName());
			payload.put("Ages", request.getAge());
			payload.put("Genders", request.getGender());
			payload.put("MobileNo", request.getMobileNo());
			payload.put("EmergencyMobileNo", request.getEmergencyMobileNo());
			payload.put("Address", request.getAddress());
			payload.put("City", request.getCity());
			payload.put("State", request.getState());
			payload.put("PostalCode", request.getPostalCode());
			payload.put("IdCardType", request.getIdCardType());
			payload.put("IdCardNo", request.getIdCardNo());
			payload.put("IdCardIssuedBy", request.getIdCardIssuedBy());
			payload.put("EmailId", request.getEmailId());
			payload.put("Provider", request.getProvider());
			payload.put("Operator", request.getOperator());
			payload.put("PartialCancellationAllowed", request.getPartialCancellationAllowed());
			payload.put("BoardingPointDetails", request.getBoardingPointDetails());
			payload.put("BusTypeName", request.getBusTypeName());
			payload.put("DepartureTime", request.getDepartureTime());
			payload.put("CancellationPolicy", request.getCancellationPolicy());
			payload.put("DisplayName", request.getDisplayName());
			payload.put("SourceId", request.getSourceId());
			payload.put("SourceName", request.getSourceName());
			payload.put("DestinationId", request.getDestinationId());
			payload.put("DestinationName", request.getDestinationName());
			payload.put("JourneyDate", request.getJourneyDate());
			payload.put("ReturnDate",request.getReturnDate());
			payload.put("TripType", request.getTripType());
	//		payload.put("BusType", request.getBusType());
	//		payload.put("User", request.getUser());
			payload.put("UserType", request.getUserType());
			// payload.put("transactionRefNo", request.getTransctionrefno());

			System.err.println("PAYLOAD :: " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource("http://103.24.202.25/Buses/BlockBusTicket");
			ClientResponse response = webResource.header("ConsumerKey", "59285FC07292811D72785AC287ACE3E8")
					.header("ConsumerSecret", "04DAF1E2C80A758DCC6BCEBE436D25BC")
					.header("Content-Type", "application/json").post(ClientResponse.class, payload);

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			else{
			JSONObject obj = new JSONObject(output);
			String blockingRefNo = obj.getString("BlockingReferenceNo");
			String bookingRefNo = obj.getString("BookingReferenceNo");
			String code = obj.getString("ResponseStatus");
			String status = obj.getString("BookingStatus");
			resp.setMessage(output);
			resp.setBlockingRefNo(blockingRefNo);
			resp.setBookingRefNo(bookingRefNo);
			resp.setCodeCondtion(code);
			resp.setStatus(status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	
	@Override
	public String saveBlockingDetails(BookBusSeatRequest request, String ip,String status) {
		
		BookBusSeatResponse resp = new BookBusSeatResponse();
		System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+request.getTransctionRefNo());
		String data = "";
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
		//	Date journeyDate = (Date)formatter.parse(request.getJourneyDate());
		//	System.out.println(journeyDate);
		
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("tripId", request.getTripId());
			payload.put("boardingId", request.getBoardingId());
			payload.put("boardingAddress", request.getBoardingPointDetails());
			payload.put("blockingRefNo", request.getBlockingRefNo());
			payload.put("bookingRefNo", request.getBookingRefNo());
			payload.put("bookingStatus", status );
			payload.put("noOfSeats", request.getNoOfSeats());
			payload.put("seatNo", request.getSeatNos());
			payload.put("fare", request.getFare());
			payload.put("sourceId", request.getSourceId());
			payload.put("sourceName", request.getSourceName());
			payload.put("destinationId", request.getDestinationId());
			payload.put("destinationName", request.getDestinationName());
			payload.put("provider",request.getProvider());
			payload.put("bookingDate", request.getDateOfBooking());
			payload.put("journeyDate", request.getJourneyDate());
			payload.put("returnDate", request.getReturnDate());
			payload.put("departureTime", request.getDepartureTime());
			payload.put("busTypeName", request.getBusTypeName());
			payload.put("travelOperator", request.getOperator());
			payload.put("salutation", request.getTitle());
			payload.put("name", request.getName());
			payload.put("age", request.getAge());
			payload.put("gender", request.getGender());
			payload.put("address", request.getAddress());
			payload.put("postalCode", request.getPostalCode());
			payload.put("mobileNo", request.getMobileNo());
			payload.put("emergencyMobileNo", request.getEmergencyMobileNo());
			payload.put("emailId", request.getEmailId());
			payload.put("transactionRefNo", request.getTransctionRefNo());
			payload.put("ipAddress", "100.10.10.10");
			System.out.println("Payload : : "+payload.toString());
			System.out.println(UrlMetadatas.busBookingUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.busBookingUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);
			String strResponse = response.getEntity(String.class);
			if (response.getStatus() != 200) {
				LogCat.print("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {

				LogCat.print("RESPONSE :: " + strResponse);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					final String status1 = (String) jobj.get("status");
					final String code = (String) jobj.get("code");
					final String message = (String) jobj.get("message");

					if (code.equalsIgnoreCase("S00")) {
						resp.setSuccess(true);
					} else {
						resp.setSuccess(false);
					}
					resp.setCode(code);
					resp.setStatus(status1);
					resp.setMessage(message);
					resp.setResponse(strResponse);

				
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
					resp.setStatus("FAILED");
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			}
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.getResponse();
	}

	@Override
	public String bookBusTicketinweb(String request) {

		BookBusSeatResponse resp = new BookBusSeatResponse();
		System.out.println("Reference No :"+request);
		try {
			Client client = Client.create();
			WebResource webResource = client
					.resource("http://103.24.202.25/Buses/BookBusTicket?referenceNo=" + request);
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("ConsumerKey", "59285FC07292811D72785AC287ACE3E8")
					.header("ConsumerSecret", "04DAF1E2C80A758DCC6BCEBE436D25BC").get(ClientResponse.class);

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			resp.setMessage(output);
			// resp.setCode(response.getStatus() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public String getBusTicketBook(String req) {
		String data = "";
		try {
			Client client = Client.create();
			WebResource webResource = client
					.resource("http://103.24.202.25/Buses/BusTicketBookingDetails?referenceNo=" + req + "&type=2");
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("ConsumerKey", "59285FC07292811D72785AC287ACE3E8")
					.header("ConsumerSecret", "04DAF1E2C80A758DCC6BCEBE436D25BC").get(ClientResponse.class);

			String output = response.getEntity(String.class);
			data = output;
			System.out.println("Output from Server .... \n");
			System.out.println(output);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			// resp.setMsg(output);
			// resp.setCode(response.getStatus() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	public String busBookingSuccess(String apiRefNo, String blockId, String clientId, String bookingDate,
			String transactionRefNo, String sessionId, String currentDate) {
		String data = "";
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", sessionId);
			payload.put("apiRefNo", apiRefNo);
			payload.put("blockId", blockId);
			payload.put("clientId", clientId);
			payload.put("bookingDate", "2016-12-07");
			payload.put("transactionRefNo", transactionRefNo);
			System.out.println("Payload : " +payload.toString());
			Client client = Client.create();
			WebResource webResource = client.resource(UrlMetadatas.busBookingSuccessResponseUrl(Version.VERSION_1,
					Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			String output = response.getEntity(String.class);
			data = output;

			System.out.println("Output from Server .... \n" + response.getStatus());
			System.out.println(output);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	
	
	
	

	
	

}
