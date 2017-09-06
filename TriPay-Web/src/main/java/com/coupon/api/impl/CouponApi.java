package com.coupon.api.impl;

import com.coupon.api.ICouponApi;
import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.model.web.CarrotFryDTO;
import com.tripayweb.util.CommonUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CouponApi implements ICouponApi {

	@Override
	public void sendCoupons(String email) {

		Client client = Client.create();
		WebResource webResource = client.resource(UrlMetadatas.COUPONS + "?emailID=" + email);

		ClientResponse response = webResource.get(ClientResponse.class);
		System.out.println("URL :: " + UrlMetadatas.COUPONS + "?emailID=" + email);

	}

	@Override
	public void sendCarrotFryCoupons(CarrotFryDTO dto) {
		Client client = Client.create();
		WebResource webResource = client.resource("https://www.vpayqwik.com/CouponDist/Coupons/send")
				.queryParam("email",dto.getEmail())
				.queryParam("amount",dto.getAmount())
				.queryParam("mobile",dto.getMobileNo())
				.queryParam("city", CommonUtil.generateRandomCity());
		ClientResponse response = webResource.get(ClientResponse.class);

	}

}
