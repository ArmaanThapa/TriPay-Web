package com.tripayweb.api;

import com.tripayweb.model.app.request.TransactionUserRequest;
import com.tripayweb.model.app.response.TransactionUserResponse;

public interface IUserApi {

	TransactionUserResponse transactionRequest(TransactionUserRequest request);
}
