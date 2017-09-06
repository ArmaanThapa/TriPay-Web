package com.tripayweb.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InviteFriendValidation {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	public InviteFriendsError checkErrorByEmail(InviteFriendsDTO dto) {
//		InviteFriendsError error = new InviteFriendsError();
//		boolean valid = true;
//		if (CommonValidation.isNull(dto.getEmail())) {
//			error.setEmail("please enter email address");
//			valid = false;
//		}
//		if (CommonValidation.isNull(dto.getReceiversName())) {
//			error.setEmail("please enter name of your friend");
//			valid = false;
//		}
//		if (CommonValidation.isNull(dto.getMessage())) {
//			error.setEmail("please enter message");
//			valid = false;
//		}
//
//		if (!CommonValidation.isValidMail(dto.getEmail())) {
//			LogCat.print("invalid mail");
//			error.setEmail("enter valid mail address");
//			valid = false;
//		}
//
//		if (!CommonValidation.containsAlphabets(dto.getReceiversName())) {
//			error.setReceiversName("Name contains only alphabets");
//			valid = false;
//		}
//
//		error.setValid(valid);
//		return error;
//	}

//	public InviteFriendsError checkErrorByMobile(InviteFriendsDTO dto) {
//		InviteFriendsError error = new InviteFriendsError();
//		boolean valid = true;
//		if (CommonValidation.isNull(dto.getMobileNo())) {
//			error.setMobileNo("please enter email address");
//			valid = false;
//		}
//		if (CommonValidation.isNull(dto.getReceiversName())) {
//			error.setReceiversName("please enter name of your friend");
//			valid = false;
//		}
//
//		if (CommonValidation.isNull(dto.getMessage())) {
//			error.setMessage("please enter message");
//			valid = false;
//		}
//
//		if (!CommonValidation.checkLength10(dto.getMobileNo())) {
//			LogCat.print("Invalid Mobile number");
//			error.setMobileNo("Please enter 10 digit mobile number");
//			valid = false;
//		}
//
//		if (!CommonValidation.isNumeric(dto.getMobileNo())) {
//			LogCat.print("Invalid Mobile number");
//			error.setMobileNo("Mobile No. contains only digits");
//			valid = false;
//		}
//
//		if (!CommonValidation.containsAlphabets(dto.getReceiversName())) {
//			error.setReceiversName("Name contains only alphabets");
//			valid = false;
//		}
//	
//		error.setValid(valid);
//		return error;
//	}

}
