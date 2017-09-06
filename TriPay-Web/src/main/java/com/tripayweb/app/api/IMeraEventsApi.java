package com.tripayweb.app.api;

import com.tripayweb.app.model.request.MeraEventsBookingRequest;
import com.tripayweb.app.model.request.MeraEventsCommonRequest;
import com.tripayweb.app.model.request.MeraEventsListRequest;
import com.tripayweb.app.model.request.MeraEventsTicketDetailsRequest;
import com.tripayweb.app.model.response.MeraEventCategoryListResponse;
import com.tripayweb.app.model.response.MeraEventCityListResponse;
import com.tripayweb.app.model.response.MeraEventDetailsResponse;
import com.tripayweb.app.model.response.MeraEventGalleryDetailsResponse;
import com.tripayweb.app.model.response.MeraEventTicketCalculationResponse;
import com.tripayweb.app.model.response.MeraEventsListResponse;
import com.tripayweb.app.model.response.MeraEventsResponse;
import com.tripayweb.app.model.response.MeraEventsTicketDetailsResponse;

public interface IMeraEventsApi {

	MeraEventsResponse getAuthorizationCode (MeraEventsCommonRequest request);
	
	MeraEventsResponse getAccessToken(MeraEventsCommonRequest request);
	
	MeraEventsListResponse getEventList(MeraEventsListRequest request);
	
	MeraEventDetailsResponse getEventDetails(MeraEventsTicketDetailsRequest request);
	
	MeraEventsTicketDetailsResponse getEventTicketDetails(MeraEventsTicketDetailsRequest request);
	
	MeraEventGalleryDetailsResponse getGalleryDetails(MeraEventsTicketDetailsRequest request);
	
	MeraEventCategoryListResponse getEventCategory(MeraEventsCommonRequest request);
	
	MeraEventCityListResponse getCities(MeraEventsCommonRequest request);
	
	MeraEventTicketCalculationResponse calculateTotalAmount(MeraEventsBookingRequest request);
	
	void saveAttendeeDetails(MeraEventsCommonRequest request);
	
	
}
