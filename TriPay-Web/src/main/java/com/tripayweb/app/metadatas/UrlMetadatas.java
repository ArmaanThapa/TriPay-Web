package com.tripayweb.app.metadatas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Language;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.Version;
import com.tripayweb.app.model.request.ReceiptsRequest;

public class UrlMetadatas {

		protected final static Logger logger = LoggerFactory.getLogger(UrlMetadatas.class);
//		private static final String DOMAIN = "http://172.16.3.10:8089/Api";
//		public static final String  DOMAIN="http://192.168.1.6:8080/VPayQwik-App/Api";

//		 private static final String DOMAIN = "http://172.16.3.10/Api"; // DON'T  // CHANGE IT
		//private static final String DOMAIN = "http://66.207.206.54:8034//TriPay-App/Api";
		// private static final String DOMAIN = "http://10.1.1.12:8080/Api";
	    private static final String DOMAIN = "http://localhost:8089/TriPay-App/Api";
		private static final String SEPARATOR = "/";
		private static final String ADD_MERCHANT = "Merchant/Save";
        private static final String SETTLEMENT_REPORTS = "getReports";
		private static final String LOGIN = "Login";
		private static final String FORGET_PASSWORD = "ForgotPassword";
		private static final String CHANGE_PASSWORD_WITH_OTP = "RenewPassword";
		private static final String RESET_PASSWORD_WITH_OTP = "ResetPasswordWithOtp";
		private static final String ALL_TRANSACTION = "getTotalTransactions";
		private static final String ALL_USER = "getTotalUsers";
		private static final String USER_TRANSACTION = "getUserTransactions";
		private static final String MERCHANT_TRANSACTIONS = "Merchant/GetTransactions";
		private static final String VPAYQWIK_MERCHANT_TRANSACTIONS = "Merchant/GetVPayQwikTransactions";
		private static final String EMAIL_LOG = "Report/Email";
		private static final String MESSAGE_LOG = "Report/SMS";
		private static final String BLOCK_USER = "BlockUser";
		private static final String UNBLOCK_USER = "UnblockUser";
		private static final String LOGOUT = "Logout";
		private static final String PREPAID_TOPUP = "MobileTopup/ProcessPrepaid";
		private static final String POSTPAID_TOPUP = "MobileTopup/ProcessPostpaid";
		private static final String DATACARD_TOPUP = "MobileTopup/ProcessDataCard";
		private static final String REGISTER = "Register";
		private static final String MOBILE_OTP = "Activate/Mobile";
		private static final String RESEND_MOBILE_OTP = "Resend/Mobile/OTP";
		private static final String RESEND_KYC_OTP = "Resend/KYC/OTP";
		private static final String VERIFY_EMAIL = "Activate/Email";
		private static final String BETWEEN_DATES = "Report/Transaction";
		private static final String UPDATE_PASSWORD = "UpdatePassword/Process";
		private static final String USER_DETAILS = "GetUserDetails";
		private static final String EDIT_PROFILE = "EditProfile/Process";
		private static final String CHANGE_PASSWORD = "ChangePassword/Process";
		private static final String UPLOAD_PICTURE = "UploadPicture/Process";
		private static final String RECEIPTS = "GetReceipts";
		private static final String SUCCESSFUL_TRANSACTIONS = "STransactions";
		private static final String SET_MPIN = "SetMpin";
		private static final String CHANGE_MPIN = "ChangeMpin";
		private static final String DELETE_MPIN = "ForgotMpin";
		private static final String VERIFY_MPIN = "VerifyMpin";
		private static final String ELECTRICITY_BILL = "BillPay/ProcessElectricity";
		private static final String GAS_BILL = "BillPay/ProcessGas";
		private static final String GET_SERVICE_TYPES = "GetServiceTypes";
		private static final String INSURANCE_BILL = "BillPay/ProcessInsurance";
		private static final String LANDLINE_BILL = "BillPay/ProcessLandline";
		private static final String DTH_BILL = "BillPay/ProcessDTH";
		private static final String OPERATOR_AND_CIRCLE = "Plans/GetOperatorsCircles";
		private static final String FORGOT_MPIN = "ForgotMpin";
		private static final String OPERATOR_AND_CIRCLE_FOR_MOB = "Plans/GetTelco";
		private static final String PLANS_FOR_MOBILE = "Plans/GetPlans";
		private static final String PAY_AT_STORE = "PayAtStore/Process";
		private static final String LOAD_MONEY = "LoadMoney/Process";
		private static final String LOAD_MONEY_REDIRECT = "LoadMoney/Redirect";
		private static final String LOAD_MONEY_VNET = "LoadMoney/InitiateVNet";
		private static final String LOAD_MONEY_REDIRECT_VNET = "LoadMoney/RedirectVNet";
		private static final String SEND_MONEY_MOBILE = "SendMoney/Mobile";
		private static final String BANK_INITIATE = "SendMoney/Bank/Initiate";
		private static final String BANK_SUCCESS = "SendMoney/Bank/Success";
		private static final String BANK_FAILURE = "SendMoney/Bank/Failure";
		
		private static final String MBANK_INITIATE = "SendMoney/MBankTransfer/Initiate";
	//	private static final String BANK_SUCCESS = "SendMoney/Bank/Success";
	//	private static final String BANK_FAILURE = "SendMoney/Bank/Failure";
		
		private static final String LIST_STORE_API = "ListStoreApi";
		private static final String AUTHORITY = "Authenticate/SessionId";
		private static final String TRANSACTION_VALIDATION = "Validate/Transaction";
		private static final String VALIDATE_MERCHANT_TRX = "Validate/MTransaction";
		private static final String INVITE_MOBILE_FRIEND = "Invite/Mobile";
		private static final String INVITE_EMAIL_FRIEND = "Invite/Email";
		private static final String USER_BY_ADMIN = "getTransactions";
		private static final String RESEND_EMAIL_OTP = "ReSendEmailOTP";
		private static final String CALL_BACK = "InstantPay/Callback";
		private static final String SAVE_PROMO_CODE = "PromoCode/Save";
		private static final String LIST_PROMO_CODE = "PromoCode/List";
		private static final String REDEEM_PROMO = "RedeemProcess";
		private static final String KYC_REQUEST = "AccountUpdateRequest";
		private static final String KYC_OTP_VERIFICATION = "AccountUpdate";
		//private static final String KYC_REQUEST_WEB = "AccountUpdateRequestWeb";
		private static final String KYC_WEB_OTP_VERIFICATION = "AccountUpdateWeb";
		private static final String SINGLE_USER = "getTransactions";
		private static final String VALIDATE_VERSION = "AuthenticateVersion";
		private static final String UPDATE_VERSION = "UpdateVersion";
		private static final String LIST_VERSION = "Version/All";
		private static final String UPDATE_FAVOURITE = "UpdateFavourite";
		private static final String GET_SERVICES = "getServices";
		private static final String GET_TRANSACTION_DTO = "GetTransactionDTO";
		private static final String GET_ALL_BANKS = "listBanks";
		private static final String IFSC_BY_BANK = "listIFSC";
		private static final String OFFLINE_PAYMENT = "Merchant/RequestOffline";
		private static final String OFFLINE_OTP = "Merchant/ProcessOffline";
		public static String COUPONS = "http://localhost:8080/GetOffers/coupons";
		private static String CARROTFRY_COUPONS = "http://localhost:8080/CouponDist/Coupons/send";
		private static String NEFT_LIST = "BankTransferList";
		private static String VALUES_LIST = "GetValues";
		private static String SHARE_POINTS = "SharePoints";
		private static String SERVICE_URL = "GetAllServices";
		private static String GET_MERCHANTS = "getMerchants";
		private static String REFUND_AMOUNT = "RefundAmount";
		private static String PROMO_TRANSACTIONS = "PCTransactions";
		private static String MERCHANT_CHPASSWORD = "Merchant/ChangePassword";
		private static String ADMIN_ALLTRANSACTIONS = "getTotalTransactionsByType";
		private static String MERCHANT_LIST = "Merchant/All";
		//Travel Api URL:
		private static final String BUS_BOOKING = "SaveBookingDetails";
		private static final String BUS_BOOKING_RESPONSE = "BookBusResponse";
		private static String MERCHANT_NEFT_LIST = "/MBankTransferList";
		private static String MERCHANT_TXLIST="getMTransactions";


		private static final String SINGLE_TRANSACTION_POST = "getTransactions";

         public static String getSettlementsTransactionURL(Version version, Role role, Device device, Language language) {
            String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
                + device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + SETTLEMENT_REPORTS;
             logger.info(url);
            return url;
        }


    public static String getSingleUserTransactionURL(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + SINGLE_TRANSACTION_POST;
		logger.info(url);
		return url;
	}

	public static String getAllMerchantURL(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + MERCHANT_LIST;
		logger.info(url);
		return url;
	}

	public static String getAllTransactionsAdminURL(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + ADMIN_ALLTRANSACTIONS;
		logger.info(url);
		return url;
	}

	public static String renewMerchantPasswordURL(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + MERCHANT_CHPASSWORD;
		logger.info(url);
		return url;
	}

	public static String getPromoTransactionsURL(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + PROMO_TRANSACTIONS;
			logger.info(url);
			return url;
		}


		public static String getVpayqwikMerchantTransactionsURL(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + VPAYQWIK_MERCHANT_TRANSACTIONS;
			logger.info(url);
			return url;
		}


		public static String getRefundAmountURL(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + REFUND_AMOUNT;
			logger.info(url);
			return url;
		}

		public static String getMerchantsURL(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + GET_MERCHANTS;
			logger.info(url);
			return url;
		}
		public static String getMerchantsTransactions(Version version, Role role, Device device, Language language,String username) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR + device.getValue() + SEPARATOR + language.getValue() +SEPARATOR+username+SEPARATOR+MERCHANT_TXLIST;
			logger.info(url);
			return url;
		}


		public static String getServiceURL(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + SERVICE_URL;
			logger.info(url);
			return url;
		}

		public static String getServiceTypesURL(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + GET_SERVICE_TYPES;
			logger.info(url);
			return url;
		}

		public static String getSharePointsURL(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + SHARE_POINTS;
			logger.info(url);
			return url;
		}


		public static String getValuesListURL(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + VALUES_LIST;
			logger.info(url);
			return url;
		}


		public static String getNeftListURL(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + NEFT_LIST;
			logger.info(url);
			return url;
		}
		public static String getNeftListMerchantURL(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + MERCHANT_NEFT_LIST;
			logger.info(url);
			return url;
		}

		public static String getCarrotfryCouponsURL() {
			String url = CARROTFRY_COUPONS;
			logger.info(url);
			return url;
		}


		public static String getAllBanksURL(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + GET_ALL_BANKS;
			logger.info(url);
			return url;
		}

		public static String getIfscURL(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + IFSC_BY_BANK+SEPARATOR;
			logger.info(url);
			return url;
		}


		public static String getOnePayTransactionURL(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + GET_TRANSACTION_DTO;
			logger.info(url);
			return url;
		}


		public static String getMerchantTransactions(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + MERCHANT_TRANSACTIONS;
			logger.info(url);
			return url;
		}

		public static String listAllVersions(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + LIST_VERSION;
			logger.info(url);
			return url;
		}

		public static String validateVersion(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + VALIDATE_VERSION;
			logger.info(url);
			return url;
		}

		public static String updateVersion(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + UPDATE_VERSION;
			logger.info(url);
			return url;
		}

		public static String getAllServices(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + GET_SERVICES;
			logger.info(url);
			return url;
		}

		public static String transactionCheck(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + TRANSACTION_VALIDATION;
			logger.info(url);
			return url;
		}
		
		public static String merchantTransactionCheck(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + VALIDATE_MERCHANT_TRX;
			logger.info(url);
			return url;
		}

		public static String initiateBankTransfer(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + BANK_INITIATE;
			logger.info(url);
			return url;
		}

		public static String successBankTransfer(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + BANK_SUCCESS;
			logger.info(url);
			return url;
		}

		public static String failureBankTransfer(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + BANK_FAILURE;
			logger.info(url);
			return url;
		}
		
		public static String initiateMBankTransfer(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + MBANK_INITIATE;
			logger.info(url);
			return url;
		}

		public static String getLoginUrl(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + LOGIN;
			logger.info(url);
			return url;
		}







		

		/*public static String kycRequestWalletUrl(Version version, Role role, Device device, Language language) {
			String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
					+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + KYC_REQUEST_WEB;
			logger.info(url);
			return url;
		}*/
		
		

	public static String getPayAtStoreUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + PAY_AT_STORE;
		logger.info(url);
		return url;
	}

	public static String getLoadMoneyUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + LOAD_MONEY;
		logger.info(url);
		return url;
	}

	public static String getLoadMoneyResponseUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + LOAD_MONEY_REDIRECT;
		logger.info(url);
		return url;
	}

	public static String getLoadMoneyVNetUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + LOAD_MONEY_VNET;
		logger.info(url);
		return url;
	}

	public static String getLoadMoneyVNetResponseUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + LOAD_MONEY_REDIRECT_VNET;
		logger.info(url);
		return url;
	}

	public static String getSendMoneyMobileUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + SEND_MONEY_MOBILE;
		logger.info(url);
		return url;
	}

	public static String getListStoreUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + LIST_STORE_API;
		logger.info(url);
		return url;
	}

	public static String getChangePasswordWithOTPUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + CHANGE_PASSWORD_WITH_OTP;
		logger.info(url);
		return url;
	}

	public static String getPasswordOtpUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + FORGET_PASSWORD;
		logger.info(url);
		return url;
	}

	public static String resetPasswordOtpUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + RESET_PASSWORD_WITH_OTP;
		logger.info(url);
		return url;
	}

	public static String getRegisterUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + REGISTER;
		logger.info(url);
		return url;
	}

	public static String getMobileUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + MOBILE_OTP;
		logger.info(url);
		return url;
	}

	public static String getResendMobileUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + RESEND_MOBILE_OTP;
		logger.info(url);
		return url;
	}

	public static String getResendKycOtpUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + RESEND_KYC_OTP;
		logger.info(url);
		return url;
	}

	public static String getVerifyEmailUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + VERIFY_EMAIL;
		logger.info(url);
		return url;
	}

	public static String getAllTransactionUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + ALL_TRANSACTION;
		logger.info(url);
		return url;
	}

	public static String getAllUserUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + ALL_USER;
		logger.info(url);
		return url;
	}

	public static String getUserTransactionUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + USER_TRANSACTION;
		logger.info(url);
		return url;
	}

	public static String getMessageLogUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + MESSAGE_LOG;
		logger.info(url);
		return url;
	}

	public static String getEmailLogUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + EMAIL_LOG;
		logger.info(url);
		return url;
	}

	public static String blockUserUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + BLOCK_USER;
		logger.info(url);
		return url;
	}

	public static String unblockUserUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + UNBLOCK_USER;
		logger.info(url);
		return url;
	}

	public static String getLogoutUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + LOGOUT;
		logger.info(url);
		return url;
	}

	public static String getPrePaidTopupUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + PREPAID_TOPUP;
		logger.info(url);
		return url;
	}

	public static String getPostPaidTopupUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + POSTPAID_TOPUP;
		logger.info(url);
		return url;
	}

	public static String getDataCardTopupUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + DATACARD_TOPUP;
		logger.info(url);
		return url;
	}

	public static String getGetOperatorCircleUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + OPERATOR_AND_CIRCLE_FOR_MOB;
		logger.info(url);
		return url;
	}

	public static String getUserDetailsUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + USER_DETAILS;
		logger.info(url);
		return url;
	}

	public static String getEditProfileUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + EDIT_PROFILE;
		logger.info(url);
		return url;
	}

	public static String getChangePasswordUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + CHANGE_PASSWORD;
		logger.info(url);
		return url;
	}

	public static String getUpdatePasswordUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + UPDATE_PASSWORD;
		logger.info(url);
		return url;
	}

	public static String getUploadPictureUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + UPLOAD_PICTURE;
		logger.info(url);
		return url;
	}

	public static String getReceiptsUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + RECEIPTS;
		logger.info(url);
		return url;
	}

	public static String getTransactionsUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + SUCCESSFUL_TRANSACTIONS;
		logger.info(url);
		return url;
	}

	public static String getFavouriteUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + UPDATE_FAVOURITE;
		logger.info(url);
		return url;
	}

	public static String getSetMPINUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + SET_MPIN;
		logger.info(url);
		return url;
	}

	public static String getChangeMPINUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + CHANGE_MPIN;
		logger.info(url);
		return url;
	}

	public static String getDeleteMPINUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + DELETE_MPIN;
		logger.info(url);
		return url;
	}

	public static String getForgotMPINUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + FORGOT_MPIN;
		logger.info(url);
		return url;
	}

	public static String getVerifyMPINUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + VERIFY_MPIN;
		logger.info(url);
		return url;
	}

	public static String getElectricityBillUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + ELECTRICITY_BILL;
		logger.info(url);
		return url;
	}

	public static String getGasBillUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + GAS_BILL;
		logger.info(url);
		return url;
	}

	public static String getInsuranceBillUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + INSURANCE_BILL;
		logger.info(url);
		return url;
	}

	public static String getLandlineBillUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + LANDLINE_BILL;
		logger.info(url);
		return url;
	}

	public static String getDthBillUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + DTH_BILL;
		logger.info(url);
		return url;
	}

	public static String getOperatorAndCircleUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + OPERATOR_AND_CIRCLE;
		logger.info(url);
		return url;
	}

	public static String getOperatorAndCircleForMobUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + OPERATOR_AND_CIRCLE_FOR_MOB;
		logger.info(url);
		return url;
	}

	public static String getBetweenDateTransactions(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + BETWEEN_DATES;
		logger.info(url);
		return url;
	}

	public static String getAuthority(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + AUTHORITY;
		logger.info(url);
		return url;
	}

	public static String getPlans(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + PLANS_FOR_MOBILE;
		logger.info(url);
		return url;
	}

	public static String getBlockUser(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + BLOCK_USER;
		logger.info(url);
		return url;
	}

	public static String getUserUnBlock(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + UNBLOCK_USER;
		logger.info(url);
		return url;
	}

	public static String getInviteEmailFriendUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + INVITE_EMAIL_FRIEND;
		logger.info(url);
		return url;
	}

	public static String getInviteMobileFriendUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + INVITE_MOBILE_FRIEND;
		logger.info(url);
		return url;
	}

	public static String getUserDetailByAdminUrl(Version version, Role role, Device device, Language language,
			String usernumber) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + usernumber + SEPARATOR
				+ USER_BY_ADMIN;
		logger.info(url);
		return url;
	}

	public static String reSendEmailOTPUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + RESEND_EMAIL_OTP;
		logger.info(url);
		return url;
	}

	public static String getCallBackUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + CALL_BACK;
		logger.info(url);
		return url;
	}

	public static String addMerchant(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + ADD_MERCHANT;
		logger.info(url);
		return url;
	}

	public static String savePromoCode(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + SAVE_PROMO_CODE;
		logger.info(url);
		return url;
	}

	/*
	 * public static String kycRequestWalletUrl(Version version, Role role,
	 * Device device, Language language) { String url = DOMAIN + SEPARATOR +
	 * version.getValue() + SEPARATOR + role.getValue() + SEPARATOR +
	 * device.getValue() + SEPARATOR + language.getValue() + SEPARATOR +
	 * KYC_REQUEST_WEB; logger.info(url); return url; }
	 */

	public static String listPromoCode(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + LIST_PROMO_CODE;
		logger.info(url);
		return url;
	}

	public static String redeemPromoCode(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + REDEEM_PROMO;
		logger.info(url);
		return url;
	}

	public static String kycRequestUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + KYC_REQUEST;
		logger.info(url);
		return url;
	}

	public static String kycOtpVerificationUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + KYC_OTP_VERIFICATION;
		logger.info(url);
		return url;
	}

	/*
	 * public static String kycRequestWalletUrl(Version version, Role role,
	 * Device device, Language language) { String url = DOMAIN + SEPARATOR +
	 * version.getValue() + SEPARATOR + role.getValue() + SEPARATOR +
	 * device.getValue() + SEPARATOR + language.getValue() + SEPARATOR +
	 * KYC_REQUEST_WEB; logger.info(url); return url; }
	 */

	public static String kycWebOtpVerificationUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + KYC_WEB_OTP_VERIFICATION;
		logger.info(url);
		return url;
	}

	public static String getSingle(Version version, Role role, Device device, Language language, String number) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + number + SEPARATOR + SINGLE_USER;
		logger.info(url);
		return url;
	}

	public static String offlinePaymentUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + OFFLINE_PAYMENT;
		logger.info(url);
		return url;
	}

	public static String offlinePaymentOTP(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + OFFLINE_OTP;
		logger.info(url);
		return url;
	}

	public static String busBookingUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + BUS_BOOKING;
		logger.info(url);
		return url;
	}

	public static String busBookingSuccessResponseUrl(Version version, Role role, Device device, Language language) {
		String url = DOMAIN + SEPARATOR + version.getValue() + SEPARATOR + role.getValue() + SEPARATOR
				+ device.getValue() + SEPARATOR + language.getValue() + SEPARATOR + BUS_BOOKING_RESPONSE;
		logger.info(url);
		return url;
	}
	
	
	

}
