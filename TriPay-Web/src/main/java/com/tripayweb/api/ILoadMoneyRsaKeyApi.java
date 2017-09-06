package com.tripayweb.api;

import com.tripayweb.model.app.request.LoadMoneyRsaKeyRequest;
import com.tripayweb.model.app.response.LoadMoneyRsaKeyResponse;

public interface ILoadMoneyRsaKeyApi {

	LoadMoneyRsaKeyResponse request(LoadMoneyRsaKeyRequest request);
}
