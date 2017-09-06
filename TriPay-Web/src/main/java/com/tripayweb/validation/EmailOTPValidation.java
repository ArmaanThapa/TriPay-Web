package com.tripayweb.validation;

public class EmailOTPValidation {

//	private final UserRepository userRepository;
//	private final UserDetailRepository userDetailRepository;
//
//	public EmailOTPValidation(UserRepository userRepository,
//			UserDetailRepository userDetailRepository) {
//		this.userRepository = userRepository;
//		this.userDetailRepository = userDetailRepository;
//	}

//	public VerifyEmailOTPError checkEmailToken(String key) {
//		VerifyEmailOTPError error = new VerifyEmailOTPError();
//		boolean valid = true;
//		
//		User user = userRepository.findByEmailToken(key);
//		UserDetail userDetail = user.getUserDetail();
//		List<UserDetail> userDetails = userDetailRepository.checkMail(userDetail.getEmail());
//		if (userDetails != null) {
//			LogCat.print("User Detail found with email :: " + userDetail.getEmail() + " :: " + userDetails.size());
//			for (UserDetail ud : userDetails) {
//				User u = userRepository.findByUserDetails(ud);
//				if (u.getEmailStatus() == Status.Active) {
//					valid = false;
//					error.setMessage("Verified Email already exist. Please try using another email.");		
//				}
//			}
//		}
//		user = userRepository.findByEmailTokenAndEmailStatusAndMobileStatus(key,
//				Status.Inactive, Status.Active);
//		if (user == null) {
//			valid = false;
//			error.setMessage("Email Verification failed.");
//		} 
//		error.setValid(valid);
//		return error;
//	}
}
