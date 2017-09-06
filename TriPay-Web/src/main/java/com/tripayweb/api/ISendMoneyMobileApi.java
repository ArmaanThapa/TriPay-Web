package com.tripayweb.api;

import com.tripayweb.app.model.Role;
import com.tripayweb.model.app.request.SendMoneyMobileRequest;
import com.tripayweb.model.app.response.SendMoneyMobileResponse;

public interface ISendMoneyMobileApi {

	SendMoneyMobileResponse request(SendMoneyMobileRequest request, Role role);

}
