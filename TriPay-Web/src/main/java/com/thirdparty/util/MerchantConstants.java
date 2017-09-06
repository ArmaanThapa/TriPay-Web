package com.thirdparty.util;

public class MerchantConstants {
//    private static final String BASE_URL="http://66.207.206.54:8035/PayQwikApp/";
    private static final String BASE_URL="http://172.16.3.10/";
//    private static final String BASE_URL="http://127.0.0.1:8089/vpayqwik-app/";

    public static final String AUTHENTICATE_URL = BASE_URL+"Authenticate/Merchant";
    public static final String AUTHENTICATE_USER = BASE_URL+"AuthenticateUser";
    public static final String REGISTER_USER = BASE_URL+"Register";
    public static final String VERIFY_OTP = BASE_URL+"Activate/Mobile";
    public static final String FORGOT_PASSWORD = BASE_URL+"ForgotPassword";
    public static final String VALIDATE_FORGOT_PASSWORD_OTP = BASE_URL+"Change";
    public static final String CHANGE_PASSWORD = BASE_URL+"RenewPassword";
    public static final String PROCESS_PAYMENT = BASE_URL+"Authenticate/Payment";
    public static final String STATUS_CHECK = BASE_URL+"Authenticate/StatusCheck";

}
