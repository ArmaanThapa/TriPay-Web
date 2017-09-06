package com.tripayweb.app.api;

import com.tripayweb.app.model.request.DTHBillPaymentRequest;
import com.tripayweb.app.model.request.ElectricityBillPaymentRequest;
import com.tripayweb.app.model.request.GasBillPaymentRequest;
import com.tripayweb.app.model.request.InsuranceBillPaymentRequest;
import com.tripayweb.app.model.request.LandlineBillPaymentRequest;
import com.tripayweb.app.model.response.DTHBillPaymentResponse;
import com.tripayweb.app.model.response.GasBillPaymentResponse;
import com.tripayweb.app.model.response.InsuranceBillPaymentResponse;
import com.tripayweb.app.model.response.LandlineBillPaymentResponse;
import com.tripayweb.app.model.response.ElectricityBillPaymentResponse;

public interface IBillPaymentApi {

	ElectricityBillPaymentResponse electricBill(ElectricityBillPaymentRequest request);

	GasBillPaymentResponse gasBill(GasBillPaymentRequest request);

	InsuranceBillPaymentResponse insuranceBill(InsuranceBillPaymentRequest request);

	LandlineBillPaymentResponse landline(LandlineBillPaymentRequest request);

	DTHBillPaymentResponse dthBill(DTHBillPaymentRequest request);

}
