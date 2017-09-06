package com.tripayweb.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.model.request.EditProfileRequest;
import com.tripayweb.model.error.EditProfileError;
import com.tripayweb.util.LogCat;

public class EditProfileValidation {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());


	public EditProfileError checkError(EditProfileRequest request) {
		EditProfileError error = new EditProfileError();
		boolean valid = true;

		if (CommonValidation.isNull(request.getFirstName())) {
			error.setFirstName("First Name Required");
			valid = false;
		} else if (!CommonValidation.containsAlphabets(request.getFirstName())) {
			error.setFirstName("First Name must be alphabets only");
			valid = false;
		}
		if (CommonValidation.isNull(request.getLastName())) {
			error.setLastName("Last Name Required");
			valid = false;
		} else if (!CommonValidation.containsAlphabets(request.getLastName())) {
			error.setLastName("Last Name must be alphabets only");
			valid = false;
		}
		if (CommonValidation.isNull(request.getEmail())) {
			error.setEmail("Email Required");
			valid = false;
		} else if (!CommonValidation.isValidMail(request.getEmail())) {
			error.setEmail("Please enter valid email");
			valid = false;
		}

		error.setValid(valid);
		return error;
	}
}
