package com.tripayweb.app.api;

import com.tripayweb.app.model.request.*;
import com.tripayweb.app.model.response.*;

import java.util.Date;
import java.util.List;

public interface IAdminApi {

	LoginResponse login(LoginRequest request);

	ServiceTypeResponse getServiceType();

	ServiceTypeResponse getService();

	AllTransactionResponse getAllTransaction(AllTransactionRequest request);


	AllTransactionResponse getSettlementTransactions(AllTransactionRequest request);


	AllTransactionResponse getPromoTransaction(AllTransactionRequest request);

	UserTransactionResponse getUserTransactionValues(SessionDTO dto);

	AllUserResponse getAllUser(AllUserRequest request);

	AllUserResponse getAllMerchants(AllUserRequest request);

	UserTransactionResponse getUserTransaction(UserTransactionRequest request);

	UserTransactionResponse refundLoadMoneyTransactions(RefundDTO dto);

	MessageLogResponse getMessageLog(MessageLogRequest request);

	EmailLogResponse getEmailLog(EmailLogRequest request);

	BlockUnBlockUserResponse blockUser(BlockUnBlockUserRequest request);

	BlockUnBlockUserResponse unblockUser(BlockUnBlockUserRequest request);

	AllTransactionResponse getDaily(AllTransactionRequest request);

	AllUserResponse getAllTransactions(AllTransactionRequest request);


	AllTransactionResponse getSingleUserTransaction(UserTransactionRequest request);
	
	BlockUserResponse userBlock(BlockUserRequest request);

	AddMerchantResponse addMerchant(MRegistrationRequest request);
	
	AllTransactionResponse getSingleUser(UserTransactionRequest request);

	List<NEFTResponse> getNEFTList(SessionDTO dto,boolean flag,Date date1,Date date2);
	List<NEFTResponse> getNEFTList(SessionDTO dto);
	ReceiptsResponse getSingleMerchantTransactionList(ReceiptsRequest request);
	
	
}