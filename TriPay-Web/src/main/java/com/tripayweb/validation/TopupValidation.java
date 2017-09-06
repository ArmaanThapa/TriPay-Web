package com.tripayweb.validation;

import com.tripayweb.app.model.request.DataCardTopupRequest;
import com.tripayweb.app.model.request.PostpaidTopupRequest;
import com.tripayweb.app.model.request.PrepaidTopupRequest;
import com.tripayweb.model.error.TopupError;

public class TopupValidation {

	public TopupError checkError(PrepaidTopupRequest dto) {
		TopupError error = new TopupError();
		boolean valid = true;
		if (CommonValidation.isNull(dto.getServiceProvider())) {
//			dto.setServiceProvider("Please Select Operator");
			valid = false;
		} else if (!CommonValidation.containsAlphabets(dto.getServiceProvider())) {
//			dto.setServiceProvider("Please Enter Valid Provider");
			valid = false;
		}
		if (CommonValidation.isNull(dto.getMobileNo())) {
//			dto.setMobileNo("Enter Mobile Number");
			valid = false;
		} else if (!CommonValidation.isNumeric(dto.getMobileNo())) {
//			dto.setMobileNo("Mobile Number contains only digits");
			valid = false;
		} else if (!CommonValidation.checkLength10(dto.getMobileNo())) {
			dto.setMobileNo("Mobile Number must be 10 digits long");
			valid = false;
		}
		if (CommonValidation.isNull(dto.getArea())) {

			valid = false;
		} else if (!CommonValidation.containsAlphabets(dto.getArea())) {
			valid = false;
		}

		if (CommonValidation.isNull(dto.getAmount())) {
			valid = false;
		} else if (!CommonValidation.isNumeric(dto.getAmount())) {
			valid = false;
		} else if (!CommonValidation.isGreaterthan10(Double.parseDouble(dto.getAmount()))) {
			valid = false;
		}
		error.setValid(valid);
		return error;
	}

	public TopupError checkError(PostpaidTopupRequest dto) {
		TopupError error = new TopupError();
		boolean valid = true;
		if (CommonValidation.isNull(dto.getServiceProvider())) {
			dto.setServiceProvider(" ");
			valid = false;
		} else if (!CommonValidation.containsAlphabets(dto.getServiceProvider())) {
			valid = false;
		}
		if (CommonValidation.isNull(dto.getMobileNo())) {
			valid = false;
		} else if (!CommonValidation.isNumeric(dto.getMobileNo())) {
			valid = false;
		} else if (!CommonValidation.checkLength10(dto.getMobileNo())) {
			valid = false;
		}
		if (CommonValidation.isNull(dto.getArea())) {
			valid = false;
		} else if (!CommonValidation.containsAlphabets(dto.getArea())) {
			valid = false;
		}

		if (CommonValidation.isNull(dto.getAmount())) {
			valid = false;
		} else if (!CommonValidation.isNumeric(dto.getAmount())) {
			valid = false;
		} else if (!CommonValidation.isGreaterthan10(Double.parseDouble(dto.getAmount()))) {
			valid = false;
		}
		error.setValid(valid);
		return error;
	}

	public TopupError checkError(DataCardTopupRequest dto) {
		TopupError error = new TopupError();
		boolean valid = true;
		if (CommonValidation.isNull(dto.getServiceProvider())) {
			dto.setServiceProvider(" ");
			valid = false;
		} else if (!CommonValidation.containsAlphabets(dto.getServiceProvider())) {
			valid = false;
		}
		if (CommonValidation.isNull(dto.getMobileNo())) {
			valid = false;
		} else if (!CommonValidation.isNumeric(dto.getMobileNo())) {
			valid = false;
		} else if (!CommonValidation.checkLength10(dto.getMobileNo())) {
			valid = false;
		}
		if (CommonValidation.isNull(dto.getArea())) {
			valid = false;
		} else if (!CommonValidation.containsAlphabets(dto.getArea())) {
			valid = false;
		}

		if (CommonValidation.isNull(dto.getAmount())) {
			valid = false;
		} else if (!CommonValidation.isNumeric(dto.getAmount())) {
			valid = false;
		} else if (!CommonValidation.isGreaterthan10(Double.parseDouble(dto.getAmount()))) {
			valid = false;
		}
		error.setValid(valid);
		return error;
	}

}
