package com.tripayweb.app.api;

import com.tripayweb.app.model.request.BookBusSeatRequest;
import com.tripayweb.app.model.request.BusBookingDetailsRequest;
import com.tripayweb.app.model.request.BusDetailsRequest;
import com.tripayweb.app.model.request.BusSourcesRequest;
import com.tripayweb.app.model.response.BookBusSeatResponse;
import com.tripayweb.app.model.response.BusBookingDetailsResponse;
import com.tripayweb.app.model.response.BusDetailsResponse;
import com.tripayweb.app.model.response.BusSourcesResponse;
import com.thirdparty.model.ResponseDTO;

public interface IBusApi {

	BusSourcesResponse getSources (BusSourcesRequest request);
	
	BusDetailsResponse getBusdetails (BusDetailsRequest request);
	
	BusBookingDetailsResponse getTripDetails(BusBookingDetailsRequest request);
	
	BookBusSeatResponse blockBusTicket(BookBusSeatRequest request);
	
	String saveBlockingDetails(BookBusSeatRequest request,String ip, String status);
	
	String bookBusTicketinweb(String request);
	
	String getBusTicketBook(String req);
	
	String busBookingSuccess(String apiRefNo,String blockId,String clientId,
							String bookingDate,String transactionRefNo,String ds, String currentDate);
	
	
}
