package com.coupon.api;

import com.tripayweb.app.model.request.PromoCodeRequest;
import com.tripayweb.app.model.request.SessionDTO;
import com.tripayweb.app.model.response.PromoCodeResponse;

public interface IPromoCodeApi {
	
	PromoCodeResponse generateRequest(PromoCodeRequest request);
	PromoCodeResponse listPromo(SessionDTO session);
	
}
