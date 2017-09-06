package com.tripayweb.app.api;

import com.tripayweb.app.model.request.CallBackRequest;
import com.tripayweb.app.model.response.CallBackResponse;
import com.tripayweb.app.model.response.IPayCallBack;

public interface ICallBackApi {

	CallBackResponse attempt(IPayCallBack request);

}
