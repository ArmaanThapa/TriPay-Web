package com.tripayweb.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.tripayweb.app.model.request.BookBusSeatRequest;
import com.tripayweb.app.model.request.BusBookingDetailsRequest;
import com.tripayweb.app.model.request.BusDetailsRequest;
import com.tripayweb.model.error.BlockBusError;
import com.tripayweb.model.error.BusBookingError;
import com.tripayweb.model.error.BusDetailsError;

public class BusValidation {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat dateFilter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	
	public BusDetailsError checkBusDetailsError(BusDetailsRequest request) {
		boolean valid = true;
		BusDetailsError error = new BusDetailsError();
		if(CommonValidation.isNull(request.getSource())) {
			error.setSource("Enter source ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getDestinaion())) {
			error.setDestinaion("Enter destination ");
		}
		if(request.getSource().equalsIgnoreCase(request.getDestinaion())) {
			error.setDestinaion("Source and destination cannot be same ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getJourneyDate())) {
			error.setJourneyDate("Select journey date ");
			valid = false;
		}
		/*try {
		SimpleDateFormat  sdfSource = new SimpleDateFormat("dd-MM-yyyy");
		Date date= sdfSource.parse(request.getJourneyDate());
			System.out.println("Date : "+date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		*/
		
		String currentTime = String.valueOf(System.currentTimeMillis());
		System.out.println("Current time in milli second : "+currentTime);
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		long milliSeconds = Long.parseLong(currentTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		String currentDate = formatter.format(calendar.getTime());
		System.err.println("Current Date time  : "+currentDate);
		System.err.println("Journey date : " +request.getJourneyDate());
		
		
		
		error.setValid(valid);
		return error;
	}
	
	public BusBookingError checkBookingError (BusBookingDetailsRequest request) {
		boolean valid = true;
		BusBookingError error = new BusBookingError();
		
		if(CommonValidation.isNull(request.getTripId())) {
			error.setTripId("Trip Id cannot be null");
			valid = false;
		} 
		if(CommonValidation.isNull(request.getSourceId())) {
			error.setSourceId("Enter source ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getDestinationId())){
			error.setDestinationId("Enter destination ");
			valid = false;
		}
		if(request.getSourceId().equalsIgnoreCase(request.getDestinationId())) {
			error.setDestinationId("Source and destination cannot be same ");
			valid = false;
		}
		if((request.getJourneyDate() == null)) {
			error.setJourneyDate("Please enter journey date ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getProvider())) {
			error.setProvider("Select any provider ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getTravels())) {
			error.setTravels("Select any travels ");
			valid = false;
		}
		error.setValid(valid);
		return error;
		
	}
	
	public BlockBusError checkBlockBusError(BookBusSeatRequest request) {
		
		boolean valid = true;
		BlockBusError error = new BlockBusError();
		
		/*if(CommonValidation.isNull(request.getTransctionRefNo())) {
			error.setTransctionRefNo("Transaction Id cannot be null ");
			valid = false;
		}*/
		if(CommonValidation.isNull(request.getTripId())) {
			error.setTripId("Trip Id cannot be null ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getBoardingId())) {
			error.setBoardingId("Select any boarding point ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getNoOfSeats())) {
			error.setNoOfSeats("Select no of seats you want to book ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getTitle())) {
			error.setTitle("Select title ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getName())) {
			error.setName("Enter passenger's name ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getAge())) {
			error.setAge("Enter passenger's age ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getGender())) {
			error.setGender("Select gender ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getAddress())) {
			error.setAddress("Enter address ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getTitle())) {
			error.setTitle("Enter postal code ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getIdCardType())) {
			error.setIdCardType("Select Identity card type ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getIdCardIssuedBy())) {
			error.setIdCardIssuedBy("Select identity card issued by ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getIdCardNo())) {
			error.setIdCardNo("Enter Identity card number ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getMobileNo())) {
			error.setMobileNo("Enter mobile number ");
			valid = false;
		}
		/*if(CommonValidation.isNull(request.getEmergencyMobileNo())) {
			error.setEmergencyMobileNo("Enter emergency mobile number ");
			valid = false;
		}*/
		if(CommonValidation.isNull(request.getEmailId())) {
			error.setEmailId("Enter email Id ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getProvider())) {
			error.setProvider("Provider cannot be null ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getOperator())) {
			error.setOperator("Operator cannot be null ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getBoardingPointDetails())) {
			error.setBoardingPointDetails("Enter boarding point ");
			valid = false;
		}
		/*if(CommonValidation.isNull(request.getBusType())) {
			error.setBusType("Select bus type ");
			valid = false;
		}*/
		if(CommonValidation.isNull(request.getDepartureTime())) {
			error.setDepartureTime("Select departure time ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getSourceId())) {
			error.setSourceId("Source Id cannot be null ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getSourceName())) {
			error.setSourceName("Select source ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getDestinationId())) {
			error.setDestinationId("Destination Id cannot be null");
			valid = false;
		}
		if(CommonValidation.isNull(request.getDestinationName())) {
			error.setDestinationName("Select destination ");
			valid = false;
		}
		if(request.getJourneyDate() == null) {
			error.setJourneyDate("Enter date of journey ");
			valid = false;
		}
		if(CommonValidation.isNull(request.getIdCardNo())) {
			error.setIdCardNo("Enter Identity card number ");
			valid = false;
		}
		
		
		
		
		
		error.setValid(valid);
		return error;
	}
	
	
}
