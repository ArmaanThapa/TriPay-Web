package com.tripayweb.api;

import com.tripayweb.model.app.request.TelcoRequest;
import com.tripayweb.model.app.response.TelcoReponse;

public interface ITelcoApi {

	TelcoReponse request(TelcoRequest req);
}
