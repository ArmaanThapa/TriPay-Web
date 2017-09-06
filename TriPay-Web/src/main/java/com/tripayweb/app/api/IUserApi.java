package com.tripayweb.app.api;

import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.*;
import com.tripayweb.app.model.response.*;
import com.tripayweb.model.app.response.RedeemCodeResponse;
import com.tripayweb.model.web.SharePointDTO;

public interface IUserApi {

	UserDetailsResponse getUserDetails(UserDetailsRequest request,Role role);
	
	UserDetailsResponse reSendEmailTop(UserDetailsRequest request);

	EditProfileResponse editProfile(EditProfileRequest request);

	MerchantResponse getAllMerchants(SessionDTO dto);

	ChangePasswordResponse changePassword(ChangePasswordRequest request);

	ChangePasswordResponse updateMerchantPassword(ChangePasswordRequest request);

	UploadPictureResponse uploadPicture(UploadPictureRequest request);

	ReceiptsResponse getReceipts(ReceiptsRequest request);

	ReceiptsResponse getMerchantReceipts(ReceiptsRequest request);

	ReceiptsResponse getSuccessfulTransactions(SessionDTO dto);

	ReceiptsResponse updateFavouriteTransaction(FavouriteRequest dto);

	MPINResponse setMPIN(SetMPINRequest request);

	MPINResponse changeMPIN(ChangeMPINRequest request);

	MPINResponse deleteMPIN(DeleteMPINRequest request);

	MPINResponse forgotMPIN(ForgotMpinRequest request);

	MPINResponse verifyMPIN(SetMPINRequest request);

	InviteFriendEmailResponse inviteEmailFriend(InviteFriendEmailRequest request);

	InviteFriendMobileResponse inviteMobileFriend(InviteFriendMobileRequest request);

	UserDetailsByAdminResponse userTransactionAndDetails(UserDetailsByAdminRequest request);
	
	RedeemCodeResponse redeemPromoCode(RedeemCodeRequest request);

	SharePointsResponse sharePoints(SharePointDTO dto);

	KycResponse kycVerification (KycVerificationDTO request);
	

	KycResponse kycOTPVerification (KycVerificationDTO request);
	
	KycResponse resendKycOTP (KycVerificationDTO request);

}
