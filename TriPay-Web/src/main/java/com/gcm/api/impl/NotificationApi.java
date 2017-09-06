package com.gcm.api.impl;

import com.gcm.api.INotificationApi;
import com.gcm.model.NotificationDTO;
import com.gcm.utils.APIConstants;
import com.google.recaptcha.utils.ReCaptchaConstants;
import com.tripayweb.util.JSONParserUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.thirdparty.model.ResponseDTO;

public class NotificationApi implements INotificationApi {
    @Override
    public ResponseDTO sendNotification(NotificationDTO dto) {
    ResponseDTO resp = new ResponseDTO();
        try {

			Client client = Client.create();

			WebResource webResource = client
					.resource(APIConstants.SEND_NOTIFICATION);
			System.out.println("inside send cloud notification");
			ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization","key="+APIConstants.APP_KEY).post(ClientResponse.class, dto.toJSON());
				String output = response.getEntity(String.class);
                System.out.print("Output :::: "+output);
			if (response.getStatus() != 200) {
				System.out.println("Failed : response message : " + output);

			} else {

			}
		} catch (Exception e) {

		}

        return resp;

    }
}
