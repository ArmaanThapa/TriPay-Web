package com.tripayweb.api;

import com.tripayweb.model.app.request.SpecificUserTransactionRequest;
import com.tripayweb.model.app.request.TotalTransactionRequest;
import com.tripayweb.model.app.request.TotalUsersRequest;
import com.tripayweb.model.app.response.SpecificUserTransactionResponse;
import com.tripayweb.model.app.response.TotalTransactionResponse;
import com.tripayweb.model.app.response.TotalUsersResponse;

public interface IAdminApi {
	
	TotalUsersResponse totalUsersRequest(TotalUsersRequest totalUsersRequest);
	TotalTransactionResponse totalTransactionRequest(TotalTransactionRequest totalTransactionRequest);
	SpecificUserTransactionResponse specificUserTransactionRequest(SpecificUserTransactionRequest specificUserTransactionRequest);
	

}
