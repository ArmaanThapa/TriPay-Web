package com.tripayweb.app.api;

import com.tripayweb.app.model.request.VNetRequest;
import com.tripayweb.app.model.response.EBSRedirectResponse;
import com.tripayweb.app.model.response.LoadMoneyResponse;

import javax.servlet.http.HttpServletRequest;

import com.tripayweb.app.model.request.LoadMoneyRequest;
import com.tripayweb.app.model.response.VNetResponse;
import com.tripayweb.app.model.response.VRedirectResponse;
import com.thirdparty.model.ResponseDTO;

public interface ILoadMoneyApi {
	
	LoadMoneyResponse loadMoneyRequest(LoadMoneyRequest request);
	EBSRedirectResponse processRedirectResponse(HttpServletRequest request);
	VNetResponse initiateVnetBanking(VNetRequest request);
	ResponseDTO handleRedirectRequest(VRedirectResponse dto);
	ResponseDTO verifyEBSTransaction(EBSRedirectResponse response);
	EBSRedirectResponse processRedirectSDK(EBSRedirectResponse response);
}
