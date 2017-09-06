package com.tripayweb.app.api;

import com.tripayweb.app.model.request.ListStoreApiRequest;
import com.tripayweb.app.model.request.OfflinePaymentRequest;
import com.tripayweb.app.model.request.PayAtStoreRequest;
import com.tripayweb.app.model.request.SendMoneyBankRequest;
import com.tripayweb.app.model.request.SendMoneyMobileRequest;
import com.tripayweb.app.model.response.ListStoreApiResponse;
import com.tripayweb.app.model.response.OfflinePaymentResponse;
import com.tripayweb.app.model.response.PayAtStoreResponse;
import com.tripayweb.app.model.response.SendMoneyBankResponse;
import com.tripayweb.app.model.response.SendMoneyMobileResponse;;

public interface ISendMoneyApi {

	SendMoneyMobileResponse sendMoneyMobileRequest(SendMoneyMobileRequest request);

	PayAtStoreResponse payAtStoreResponseRequest(PayAtStoreRequest request);

	ListStoreApiResponse listStoreResponseRequest(ListStoreApiRequest request);

	SendMoneyBankResponse sendMoneyBankRequest(SendMoneyBankRequest request);
	
	OfflinePaymentResponse offlinePayment(OfflinePaymentRequest request);
	
	OfflinePaymentResponse verifyOTP (OfflinePaymentRequest request);
	
	SendMoneyBankResponse sendMoneyToMBankRequest(SendMoneyBankRequest request);
}
