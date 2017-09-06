package com.tripayweb.app.api.impl;

import java.util.Date;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.tripayweb.app.api.IMeraEventsApi;
import com.tripayweb.app.model.ResponseStatus;
import com.tripayweb.app.model.request.MeraEventsBookingRequest;
import com.tripayweb.app.model.request.MeraEventsCommonRequest;
import com.tripayweb.app.model.request.MeraEventsListRequest;
import com.tripayweb.app.model.request.MeraEventsTicketDetailsRequest;
import com.tripayweb.app.model.response.BusSourcesResponse;
import com.tripayweb.app.model.response.MeraEventCategoryListResponse;
import com.tripayweb.app.model.response.MeraEventCityListResponse;
import com.tripayweb.app.model.response.MeraEventDetailsResponse;
import com.tripayweb.app.model.response.MeraEventGalleryDetailsResponse;
import com.tripayweb.app.model.response.MeraEventTicketCalculationResponse;
import com.tripayweb.app.model.response.MeraEventsListResponse;
import com.tripayweb.app.model.response.MeraEventsResponse;
import com.tripayweb.app.model.response.MeraEventsTicketDetailsResponse;
import com.tripayweb.util.APIUtils;
import com.tripayweb.util.LogCat;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class MeraEventsApi implements IMeraEventsApi{

	@Override
	public MeraEventsResponse getAuthorizationCode(MeraEventsCommonRequest request) {
		MeraEventsResponse resp = new MeraEventsResponse();
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(APIUtils.BASE_STAGE+APIUtils.AUTH_CODE+"?clientId="+request.getClientId());
			LogCat.print(APIUtils.BASE_STAGE+APIUtils.AUTH_CODE+"?clientId="+request.getClientId());
			ClientResponse response = webResource.accept("application/json").type("application/json").get(ClientResponse.class);
			String strResponse = response.getEntity(String.class);
			LogCat.print("Response : : " +strResponse);
			if (response.getStatus() != 200) {
				LogCat.print("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus(ResponseStatus.FAILURE);
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				if (strResponse != null) {
					JSONObject jsonObj = new JSONObject(strResponse);
					if (jsonObj != null) {
					LogCat.print("Json Response : " +jsonObj);
					String authorizationCode = jsonObj.getJSONObject("response").getString("authorizationCode");
					resp.setSuccess(true);
					resp.setCode("S00");
					resp.setMessage("Client Authenticated");
					resp.setStatus(ResponseStatus.SUCCESS);
					resp.setResponse(authorizationCode);
					} else {
						resp.setSuccess(false);
						resp.setCode("F00");
						resp.setMessage("Service unavailable");
						resp.setStatus(ResponseStatus.FAILURE);
						resp.setResponse(APIUtils.getFailedJSON().toString());
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
					resp.setStatus(ResponseStatus.FAILURE);
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	
	@Override
	public MeraEventsResponse getAccessToken(MeraEventsCommonRequest request) {
		MeraEventsResponse resp = new MeraEventsResponse();
		try {
			MultivaluedMapImpl formData = new MultivaluedMapImpl();
			formData.add("clientId", request.getClientId());
			formData.add("clientSecret", request.getClientSecret());
			formData.add("authorizationCode", request.getAuthorizationCode());
			LogCat.print("Form Data : "+formData);
			Client client = Client.create();
			WebResource webResource = client.resource(APIUtils.BASE_STAGE+APIUtils.ACCESS_TOKEN);
			LogCat.print(APIUtils.BASE_STAGE+APIUtils.ACCESS_TOKEN);
			ClientResponse response = webResource.accept("application/json").post(ClientResponse.class,formData);
			String strResponse = response.getEntity(String.class);
			LogCat.print("Response : : " +strResponse);
			if (response.getStatus() != 200) {
				LogCat.print("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus(ResponseStatus.FAILURE);
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				if (strResponse != null) {
					JSONObject jsonObj = new JSONObject(strResponse);
					if (jsonObj != null) {
					LogCat.print("Json Response : " +jsonObj);
					String accessToken = jsonObj.getJSONObject("response").getString("accessToken") ;
					resp.setSuccess(true);
					resp.setCode("S00");
					resp.setMessage("Access token generated");
					resp.setStatus(ResponseStatus.SUCCESS);
					resp.setResponse(accessToken);
					} else {
						resp.setSuccess(false);
						resp.setCode("F00");
						resp.setMessage("Service unavailable");
						resp.setStatus(ResponseStatus.FAILURE);
						resp.setResponse(APIUtils.getFailedJSON().toString());
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
					resp.setStatus(ResponseStatus.FAILURE);
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}



	@Override
	public MeraEventsListResponse getEventList(MeraEventsListRequest request) {
		MeraEventsListResponse resp = new MeraEventsListResponse();
		try {
			Client client = Client.create();
			LogCat.print(""+request.getCountryId());
			WebResource webResource = client.resource(APIUtils.BASE_STAGE+APIUtils.EVENT_LIST
					+"?access_token="+request.getAccess_token()+"&countryId="+request.getCountryId());
			LogCat.print(APIUtils.BASE_STAGE+APIUtils.EVENT_LIST
					+"?access_token="+request.getAccess_token()+"&countryId="+request.getCountryId());
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			String strResponse = response.getEntity(String.class);
			LogCat.print("Response : : " +strResponse);
			if (response.getStatus() != 200) {
				LogCat.print("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus(ResponseStatus.FAILURE);
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				if (strResponse != null) {
					JSONObject jsonObj = new JSONObject(strResponse);
					if (jsonObj != null) {
					LogCat.print("Json Response : " +jsonObj);
					JSONArray eventList = jsonObj.getJSONObject("response").getJSONArray("eventList");
					LogCat.print("Event List : : "+eventList.length());
					if(eventList != null) {
						int count=0;
						for (int i=1; i< eventList.length(); i++) {
							JSONObject jsonObject = eventList.getJSONObject(i);
							int id = jsonObject.getInt("id");
							String title = jsonObject.getString("title");
							String thumbImage = jsonObject.getString("thumbImage");
							String bannerImage = jsonObject.getString("bannerImage");
							String timeZone = jsonObject.getString("timeZone");
							String startDate = jsonObject.getString("startDate");
							String endDate = jsonObject.getString("endDate");
							String venueName = jsonObject.getString("venueName");
							String eventUrl = jsonObject.getString("eventUrl");
							String categoryName = jsonObject.getString("categoryName");
							String themeColor = jsonObject.getString("themeColor");
							String subCategoryName = jsonObject.getString("subCategoryName");
							String defaultBannerImage = jsonObject.getString("defaultBannerImage");
							String defaultThumbImage = jsonObject.getString("defaultThumbImage");
							String registrationType = jsonObject.getString("registrationType");
							String eventMode = jsonObject.getString("eventMode");
							String cityName = jsonObject.getString("cityName");
							String countryName = jsonObject.getString("countryName");
							boolean bookMarked = Boolean.parseBoolean(jsonObject.getString("bookMarked"));
							String latitude = jsonObject.getString("latitude");
							String longitude = jsonObject.getString("longitude");
							String booknowButtonValue = jsonObject.getString("booknowButtonValue");
							float minTicketPrice = (float) jsonObject.getDouble("minTicketPrice");
							float maxTicketPrice = (float) jsonObject.getDouble("maxTicketPrice");
							int limitSingleTicketType = jsonObject.getInt("limitSingleTicketType");
							int isMobileApiVisible = jsonObject.getInt("isMobileApiVisible");
							int isStandardApiVisible = jsonObject.getInt("isStandardApiVisible");
							
							resp.setEventId(id);
							resp.setTitle(title);
							resp.setThumbImage(thumbImage);
							resp.setBannerImage(bannerImage);
							resp.setTimeZone(timeZone);
				//			resp.setStartDate(startDate);
				//			resp.setEndDate(endDate);
							resp.setVenueName(venueName);
							resp.setEventUrl(eventUrl);
							resp.setCategoryName(categoryName);
							resp.setSubCategoryName(subCategoryName);
							resp.setDefaultBannerImage(defaultBannerImage);
							resp.setDefaultThumbImage(defaultThumbImage);
							resp.setRegistrationType(registrationType);
							resp.setEventMode(eventMode);
							resp.setCityName(cityName);
							resp.setCountryName(countryName);
							resp.setBookmarked(bookMarked);
							resp.setLatitude(latitude);
							resp.setLongitude(longitude);
							resp.setBooknowButtonValue(booknowButtonValue);
							resp.setMinTicketPrice(minTicketPrice);
							resp.setMaxTicketPrice(maxTicketPrice);
							resp.setLimitSingleTicketType(limitSingleTicketType);
							resp.setIsMobileApiVisible(isMobileApiVisible);
							resp.setIsStandardApiVisible(isStandardApiVisible);
							count=count++;
							LogCat.print(""+count);
							}
						}
						resp.setSuccess(true);
						resp.setCode("S00");
						resp.setMessage("Event List : ");
						resp.setStatus(ResponseStatus.SUCCESS);
						resp.setResponse(strResponse);
					} else {
						resp.setSuccess(false);
						resp.setCode("F00");
						resp.setMessage("Service unavailable");
						resp.setStatus(ResponseStatus.FAILURE);
						resp.setResponse(APIUtils.getFailedJSON().toString());
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
					resp.setStatus(ResponseStatus.FAILURE);
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	@Override
	public MeraEventDetailsResponse getEventDetails(MeraEventsTicketDetailsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MeraEventsTicketDetailsResponse getEventTicketDetails(MeraEventsTicketDetailsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MeraEventGalleryDetailsResponse getGalleryDetails(MeraEventsTicketDetailsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MeraEventCategoryListResponse getEventCategory(MeraEventsCommonRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MeraEventCityListResponse getCities(MeraEventsCommonRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MeraEventTicketCalculationResponse calculateTotalAmount(MeraEventsBookingRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAttendeeDetails(MeraEventsCommonRequest request) {
		// TODO Auto-generated method stub
		
	}



}
