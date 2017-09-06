package com.tripayweb.app.api;

import com.tripayweb.app.model.request.AllTransactionRequest;
import com.tripayweb.app.model.request.LoginRequest;
import com.tripayweb.app.model.response.AllTransactionResponse;
import com.tripayweb.app.model.response.LoginResponse;

public interface IMerchantApi {

    LoginResponse login(LoginRequest request);

    AllTransactionResponse getAllTransaction(AllTransactionRequest request);

}
