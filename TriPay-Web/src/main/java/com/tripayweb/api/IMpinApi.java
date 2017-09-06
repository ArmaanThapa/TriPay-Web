package com.tripayweb.api;

import com.tripayweb.model.app.request.ChangeMpinRequest;
import com.tripayweb.model.app.request.DeleteMpinRequest;
import com.tripayweb.model.app.request.SetMpinRequest;
import com.tripayweb.model.app.response.ChangeMpinResponse;
import com.tripayweb.model.app.response.DeleteMpinResponse;
import com.tripayweb.model.app.response.SetMpinResponse;

public interface IMpinApi {

	SetMpinResponse setMPINRequest(SetMpinRequest request);

	ChangeMpinResponse changeMPINRequest(ChangeMpinRequest request);

	DeleteMpinResponse deleteMPINRequest(DeleteMpinRequest request);
}
