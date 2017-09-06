package com.tripayweb.app.api;


import com.tripayweb.app.model.request.OnePayRequest;
import com.tripayweb.app.model.request.TransactionRequest;
import com.tripayweb.app.model.response.OnePayResponse;
import com.tripayweb.app.model.response.TransactionDTO;
import com.thirdparty.model.ResponseDTO;

public interface ITransactionApi {
    ResponseDTO validateTransaction(TransactionRequest dto);
    ResponseDTO validateMTransaction(TransactionRequest dto);
    ResponseDTO getAllServices();
    OnePayResponse getOnePayResponse(OnePayRequest dto);

    int getChoiceByServiceCode(String code);
    ResponseDTO getAllBanks();
    ResponseDTO getIFSCByBank(String bankCode);
}
