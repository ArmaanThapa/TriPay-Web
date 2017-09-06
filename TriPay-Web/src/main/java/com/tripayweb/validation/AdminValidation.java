package com.tripayweb.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.model.app.request.SpecificUserTransactionRequest;
import com.tripayweb.model.app.request.TotalTransactionRequest;
import com.tripayweb.model.app.request.TotalUsersRequest;
import com.tripayweb.model.error.SpecificUserTransactionError;
import com.tripayweb.model.error.TotalTransactionError;
import com.tripayweb.model.error.TotalUsersError;
import com.tripayweb.util.LogCat;

public class AdminValidation {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {

		AdminValidation adminValidation = new AdminValidation();

		TotalUsersRequest totalUsersRequest = new TotalUsersRequest();
		LogCat.print(totalUsersRequest.toString());
		totalUsersRequest.setPage("2sss");
		totalUsersRequest.setSize("55");
		TotalUsersError usersError = adminValidation.checkError(totalUsersRequest);
		LogCat.print(usersError.toString());

		TotalTransactionRequest transactionRequest = new TotalTransactionRequest();
		LogCat.print(transactionRequest.toString());
		transactionRequest.setPage("554");
		transactionRequest.setSize("2www");
		TotalTransactionError transactionError = adminValidation.checkError(transactionRequest);
		LogCat.print(usersError.toString());

		SpecificUserTransactionRequest request = new SpecificUserTransactionRequest();
		LogCat.print(request.toString());
		request.setPage("2sss");
		request.setSize("55");
		SpecificUserTransactionError error = adminValidation.checkError(request);
		LogCat.print(error.toString());

	}

	public TotalUsersError checkError(TotalUsersRequest usersRequest) {
		TotalUsersError error = new TotalUsersError();
		boolean valid = true;
		if (CommonValidation.isNull(usersRequest.getPage())) {
			error.setPage("pages should not be null");
			valid = false;

		} else if (!CommonValidation.isNumeric(usersRequest.getPage())) {
			error.setPage("pages must be numeric ");
			valid = false;

		}
		if (CommonValidation.isNull(usersRequest.getSize())) {
			error.setPage("page size should not be null");
			valid = false;

		} else if (!CommonValidation.isNumeric(usersRequest.getPage())) {
			error.setPage(" page size must be numeric ");
			valid = false;
		}
		return error;
	}

	public TotalTransactionError checkError(TotalTransactionRequest totalTransactionRequest) {
		TotalTransactionError error = new TotalTransactionError();
		boolean valid = true;
		if (CommonValidation.isNull(totalTransactionRequest.getPage())) {
			error.setPage("pages should not be null");
			valid = false;

		} else if (!CommonValidation.isNumeric(totalTransactionRequest.getPage())) {
			error.setPage("pages must be numeric ");
			valid = false;

		}
		if (CommonValidation.isNull(totalTransactionRequest.getSize())) {
			error.setPage("page size should not be null");
			valid = false;

		} else if (!CommonValidation.isNumeric(totalTransactionRequest.getPage())) {
			error.setPage(" page size must be numeric ");
			valid = false;
		}
		return error;

		}

		
		public SpecificUserTransactionError checkError(SpecificUserTransactionRequest request) {
			SpecificUserTransactionError error = new SpecificUserTransactionError();
			boolean valid = true;
			if (CommonValidation.isNull(request.getPage())) {
				error.setPage("pages should not be null");
				valid = false;

			} else if (!CommonValidation.isNumeric(request.getPage())) {
				error.setPage("pages must be numeric ");
				valid = false;

			}
			if (CommonValidation.isNull(request.getSize())) {
				error.setPage("page size should not be null");
				valid = false;

			} else if (!CommonValidation.isNumeric(request.getPage())) {
				error.setPage(" page size must be numeric ");
				valid = false;

			}

		error.setValid(valid);
		return error;
	}

}
