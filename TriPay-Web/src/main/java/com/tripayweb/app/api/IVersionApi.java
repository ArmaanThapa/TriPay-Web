package com.tripayweb.app.api;

import com.tripayweb.app.model.request.SessionDTO;
import com.tripayweb.app.model.request.Utility;
import com.tripayweb.app.model.request.VersionRequest;
import com.tripayweb.app.model.response.VersionResponse;

public interface IVersionApi {

	VersionResponse listVersion(SessionDTO session);

	VersionResponse validateVersion(Utility dto);

	VersionResponse updateVersion(VersionRequest dto);
}
