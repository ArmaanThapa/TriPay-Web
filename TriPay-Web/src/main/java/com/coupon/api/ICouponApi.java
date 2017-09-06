package com.coupon.api;

import com.tripayweb.model.web.CarrotFryDTO;

public interface ICouponApi {

	void sendCoupons(String email);
	void sendCarrotFryCoupons(CarrotFryDTO dto);
}
