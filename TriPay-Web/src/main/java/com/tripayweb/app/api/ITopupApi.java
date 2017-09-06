package com.tripayweb.app.api;

import com.tripayweb.app.model.request.BrowsePlansRequest;
import com.tripayweb.app.model.request.DataCardTopupRequest;
import com.tripayweb.app.model.request.GetOperatorAndCircleForMobRequest;
import com.tripayweb.app.model.request.GetOperatorAndCircleRequest;
import com.tripayweb.app.model.request.PostpaidTopupRequest;
import com.tripayweb.app.model.request.PrepaidTopupRequest;
import com.tripayweb.app.model.response.BrowsePlansResponse;
import com.tripayweb.app.model.response.DataCardTopupResponse;
import com.tripayweb.app.model.response.GetOperatorAndCircleForMobResponse;
import com.tripayweb.app.model.response.GetOperatorAndCircleResponse;
import com.tripayweb.app.model.response.PostpaidTopupResponse;
import com.tripayweb.app.model.response.PrepaidTopupResponse;

public interface ITopupApi {

	PrepaidTopupResponse prePaid(PrepaidTopupRequest request);

	PostpaidTopupResponse postPaid(PostpaidTopupRequest request);

	DataCardTopupResponse dataCard(DataCardTopupRequest request);

	GetOperatorAndCircleResponse operatorAndcircle(GetOperatorAndCircleRequest request);

	GetOperatorAndCircleForMobResponse operatorAndcircleForMob(GetOperatorAndCircleForMobRequest request);
	
	BrowsePlansResponse getPlansForMobile(BrowsePlansRequest request);

}
