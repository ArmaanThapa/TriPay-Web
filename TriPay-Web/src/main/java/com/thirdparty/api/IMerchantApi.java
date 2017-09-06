package com.thirdparty.api;

import com.thirdparty.model.*;


public interface IMerchantApi {
    AuthenticationResponse authenticateMerchant(AuthenticateDTO dto);
    ResponseDTO authenticateUser(LoginDTO dto);
    ResponseDTO registerUser(RegisterDTO dto);
    ResponseDTO verifyOTP(VerifyDTO dto);
    ResponseDTO forgotPassword(ForgotPasswordDTO dto);
    ResponseDTO validateOTP(VerifyDTO dto);
    ResponseDTO changePassword(ChangePasswordDTO dto);
    ResponseDTO processPayment(PaymentDTO dto);
    StatusResponse checkStatus(StatusCheckDTO dto);
}
