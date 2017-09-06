package com.tripayweb.controller.web.merchant;

import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.api.IMerchantApi;
import com.tripayweb.app.api.IUserApi;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.AllTransactionRequest;
import com.tripayweb.app.model.request.PagingDTO;
import com.tripayweb.app.model.request.ReceiptsRequest;
import com.tripayweb.app.model.request.SessionDTO;
import com.tripayweb.app.model.request.TransactionFilter;
import com.tripayweb.app.model.request.TransactionRequest;
import com.tripayweb.app.model.response.AllTransactionResponse;
import com.tripayweb.app.model.response.MTransactionResponseDTO;
import com.tripayweb.app.model.response.ReceiptsResponse;
import com.tripayweb.app.model.response.UserDetailsResponse;
import com.tripayweb.model.app.response.MerchantTransactionResponse;
import com.tripayweb.model.web.Status;
import com.tripayweb.model.web.UserListDTO;
import com.tripayweb.util.Authorities;
import com.tripayweb.util.JSONParserUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Merchant")
public class ReportController {

    private final IMerchantApi merchantApi;
    private final IAuthenticationApi authenticationApi;
    private final IUserApi userApi;

    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    public ReportController(IMerchantApi merchantApi, IAuthenticationApi authenticationApi,IUserApi userApi) {
        this.merchantApi = merchantApi;
        this.authenticationApi = authenticationApi;
        this.userApi = userApi;
    }

//    @RequestMapping(value="/Transactions",method= RequestMethod.GET)
//    public String getAllTransactions(HttpServletRequest request, HttpServletResponse response, HttpSession session,Model model){
//        AllTransactionRequest dto = new AllTransactionRequest();
//        try {
//            String sessionId = (String) session.getAttribute("msession");
//            if (sessionId != null) {
//                UserDetailsResponse userDetailsResponse = authenticationApi.getUserDetailsFromSession(sessionId);
//                if (userDetailsResponse != null) {
//                    String authority = userDetailsResponse.getAuthority();
//                    if (authority.contains(Authorities.MERCHANT) && authority.contains(Authorities.AUTHENTICATED)) {
//                        List<MerchantTransactionResponse> transactionsList = new ArrayList<>();
//                        dto.setSessionId(sessionId);
//                        model.addAttribute("user", userDetailsResponse);
//                        AllTransactionResponse allTransactionResponse = merchantApi.getAllTransaction(dto);
//                        if (allTransactionResponse != null) {
//                            JSONArray jsonArray = allTransactionResponse.getJsonArray();
//                            if (jsonArray != null) {
//                                System.err.println("jsonArray is not null");
//                                for (int i = 0; i < jsonArray.length(); i++) {
//                                    JSONObject json = jsonArray.getJSONObject(i);
//                                    System.err.println("inside for loop of json object " + i);
//                                    MerchantTransactionResponse transactionResponse = new MerchantTransactionResponse();
//                                    System.err.println(JSONParserUtil.getDouble(json, "amount"));
//                                    transactionResponse.setAmount("" + JSONParserUtil.getDouble(json, "amount"));
//                                    transactionResponse.setTransactionRefNo(JSONParserUtil.getString(json, "orderId"));
//                                    System.err.println(JSONParserUtil.getDouble(json, "orderId"));
//                                    transactionResponse.setVpayqwikRefNo(JSONParserUtil.getString(json, "transactionRefNo"));
//                                    transactionResponse.setStatus(JSONParserUtil.getString(json, "status"));
//                                    long milliseconds = JSONParserUtil.getLong(json, "created");
//                                    Calendar calendar = Calendar.getInstance();
//                                    calendar.setTimeInMillis(milliseconds);
//                                    transactionResponse.setDateTime(dateFormat.format(calendar.getTime()));
//
//                                    JSONObject user = JSONParserUtil.getObject(json, "user");
//
//                                    if (user != null) {
//                                        JSONObject userDetail = JSONParserUtil.getObject(user, "userDetail");
//                                        if (userDetail != null) {
//                                            transactionResponse.setUsername(JSONParserUtil.getString(userDetail, "firstName") + " " + JSONParserUtil.getString(userDetail, "lastName"));
//                                            System.err.println(JSONParserUtil.getString(userDetail, "email"));
//                                            transactionResponse.setEmail(JSONParserUtil.getString(userDetail, "email"));
//                                            transactionResponse.setMobileNumber(JSONParserUtil.getString(userDetail, "contactNo"));
//                                        }
//                                    }
//                                    transactionsList.add(transactionResponse);
//                                }
//                            }
//                        }
//                        model.addAttribute("transactionList", transactionsList);
//                        return "Merchant/Transactions";
//                    }
//                }
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return "redirect:/Merchant/Home";
//    }



    @RequestMapping(value = "/Transactions", method = RequestMethod.GET)
    public String getReceiptsOfMerchant(HttpServletRequest request,
                                        HttpServletResponse response,Model model,HttpSession session) {
//    	System.out.println("inside transactions get....");
//    	        ReceiptsResponse result = new ReceiptsResponse();
        String sessionId = (String) session.getAttribute("msession");
       if (sessionId != null && sessionId.length() != 0) {
           String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
          if (authority != null) {
              if (authority.contains(Authorities.MERCHANT) && authority.contains(Authorities.AUTHENTICATED)) {
//                   ReceiptsRequest dto = new ReceiptsRequest();
//
//                    dto.setSessionId(sessionId);
//                   dto.setPage(0);
//                    dto.setSize(100000);
//                    result = userApi.getMerchantReceipts(dto);
//                    List<MTransactionResponseDTO> transactionList = new ArrayList<>();
//                   if(result.isSuccess()) {
//                        try {
//                            JSONObject json = new JSONObject(result.getResponse());
//                            JSONObject details = JSONParserUtil.getObject(json,"details");
//                            JSONArray content = JSONParserUtil.getArray(details,"content");
//                            if(content != null) {
//                                for (int i=0 ; i < content.length(); i++) {
//                                	JSONObject temp = content.getJSONObject(i);
//                                
//                                    boolean debit = JSONParserUtil.getBoolean(temp,"debit");
//                                   if(debit) {
//                                        MTransactionResponseDTO transaction = new MTransactionResponseDTO();
//                                        long milliseconds = JSONParserUtil.getLong(temp, "date");
//                                        Calendar calendar = Calendar.getInstance();
//                                        calendar.setTimeInMillis(milliseconds);
//                                        transaction.setDate(dateFormat.format(calendar.getTime()));
//                                        transaction.setTransactionRefNo(JSONParserUtil.getString(temp, "transactionRefNo"));
//                                        transaction.setAmount(JSONParserUtil.getDouble(temp, "amount"));
//                                        transaction.setContactNo(JSONParserUtil.getString(temp, "contactNo"));
//                                        transaction.setEmail(JSONParserUtil.getString(temp, "email"));
//                                       transaction.setStatus(Status.valueOf(JSONParserUtil.getString(temp, "status")));
//                                        transactionList.add(transaction);
//                                    }
//                                }
//                                model.addAttribute("transactionList",transactionList);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                       }
//                  }
//                    return "Merchant/Transactions";
            	  return "Merchant/Transactions";
            }
            }
    	 
    	   }
       return "redirect:/Merchant/Home";   
       }
@RequestMapping(method=RequestMethod.POST,value="/MerchantTransactionInJSON")
public ResponseEntity<UserListDTO> getTransactionListInJSON(@ModelAttribute PagingDTO dto,HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model){
	UserListDTO resultSet = new UserListDTO();
	ReceiptsResponse result = new ReceiptsResponse();
     String sessionId = (String) session.getAttribute("msession");
     System.out.println("this is session id="+sessionId);
     if (sessionId != null && sessionId.length() != 0) {
         String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
         if (authority != null) {
             if (authority.contains(Authorities.MERCHANT) && authority.contains(Authorities.AUTHENTICATED)) {
                 ReceiptsRequest receipts = new ReceiptsRequest();

                 receipts.setSessionId(sessionId);
                 receipts.setPage(dto.getPage());
                 receipts.setSize(dto.getSize());
                 result = userApi.getMerchantReceipts(receipts);
                System.out.println("this is the response"+result.toString());
                 List<MTransactionResponseDTO> transactionList = new ArrayList<>();
                 if(result.isSuccess()) {
                     try {
                         JSONObject json = new JSONObject(result.getResponse());
                         JSONObject details = JSONParserUtil.getObject(json,"details");
                         JSONArray content = JSONParserUtil.getArray(details,"content");
                         if(content != null) {
                             for (int i=0 ; i < content.length(); i++) {
                                 JSONObject temp = content.getJSONObject(i);
                                 boolean debit = JSONParserUtil.getBoolean(temp,"debit");
                                 if(debit) {
                                     MTransactionResponseDTO transaction = new MTransactionResponseDTO();
                                     long milliseconds = JSONParserUtil.getLong(temp, "date");
                                     Calendar calendar = Calendar.getInstance();
                                     calendar.setTimeInMillis(milliseconds);
                                     transaction.setDate(dateFormat.format(calendar.getTime()));
                                     transaction.setTransactionRefNo(JSONParserUtil.getString(temp, "transactionRefNo"));
                                     transaction.setAmount(JSONParserUtil.getDouble(temp, "amount"));
                                     transaction.setContactNo(JSONParserUtil.getString(temp, "contactNo"));
                                     transaction.setEmail(JSONParserUtil.getString(temp, "email"));
                                    //transaction.setTotalPages(totalPages);
                                     transaction.setStatus(Status.valueOf(JSONParserUtil.getString(temp, "status")));
                                     transactionList.add(transaction);
                                 }
                            }
                             //model.addAttribute("transactionList",transactionList);
                             resultSet.setTotalPages(details.getLong("totalPages"));
                             resultSet.setFirstPage(details.getBoolean("firstPage"));
                             resultSet.setLastPage(details.getBoolean("lastPage"));
                             resultSet.setNumberOfElements(details.getLong("numberOfElements"));
                             resultSet.setJsonArray(transactionList);
                             //return new ResponseEntity<UserListDTO>(resultSet,HttpStatus.OK) ;
                         }
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                 }
                
             }
         }
     }
     return new ResponseEntity<UserListDTO>(resultSet,HttpStatus.OK) ;
 }
    @RequestMapping(value="/MTransactions",method= RequestMethod.POST)
    ResponseEntity<AllTransactionResponse> getAllTransactions(SessionDTO sessionDTO, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model){
        AllTransactionRequest dto = new AllTransactionRequest();
        AllTransactionResponse allTransactionResponse = new AllTransactionResponse();
        try {
            String sessionId = sessionDTO.getSessionId();
            if (sessionId != null) {
                UserDetailsResponse userDetailsResponse = authenticationApi.getUserDetailsFromSession(sessionId);
                if (userDetailsResponse != null) {
                    String authority = userDetailsResponse.getAuthority();
                    if (authority.contains(Authorities.MERCHANT) && authority.contains(Authorities.AUTHENTICATED)) {
                        List<MerchantTransactionResponse> transactionsList = new ArrayList<>();
                        dto.setSessionId(sessionId);
                        allTransactionResponse = merchantApi.getAllTransaction(dto);

                        return new ResponseEntity<AllTransactionResponse>(allTransactionResponse , HttpStatus.OK);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<AllTransactionResponse>(allTransactionResponse,HttpStatus.OK);
    }
    @RequestMapping(method=RequestMethod.POST,value="/TransactionFiltered")
    public String getTransactionList(@ModelAttribute TransactionFilter filter ,HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model) throws ParseException  {
    	 List<MTransactionResponseDTO> transactionList = new ArrayList<>();
    	String toDateTime = filter.getEndDate()+" 23:59";
		String fromDateTime = filter.getStartDate()+" 00:00";
		
		Date to = dateFormat.parse(toDateTime);
		
		Date from = dateFormat.parse(fromDateTime);
    	UserListDTO resultSet = new UserListDTO();
    	ReceiptsResponse result = new ReceiptsResponse();
         String sessionId = (String) session.getAttribute("msession");
         System.out.println("this is session id="+sessionId);
         if (sessionId != null && sessionId.length() != 0) {
             String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
             if (authority != null) {
                 if (authority.contains(Authorities.MERCHANT) && authority.contains(Authorities.AUTHENTICATED)) {
                     ReceiptsRequest receipts = new ReceiptsRequest();

                     receipts.setSessionId(sessionId);
                     receipts.setPage(0);
                     receipts.setSize(100000);
                     result = userApi.getMerchantReceipts(receipts);
                    System.out.println("this is the response"+result.toString());
                    
                     if(result.isSuccess()) {
                         try {
                             JSONObject json = new JSONObject(result.getResponse());
                             JSONObject details = JSONParserUtil.getObject(json,"details");
                             JSONArray content = JSONParserUtil.getArray(details,"content");
                             if(content != null) {
                                 for (int i=0 ; i < content.length(); i++) {
                                     JSONObject temp = content.getJSONObject(i);
                                     boolean debit = JSONParserUtil.getBoolean(temp,"debit");
                                     
                                    	 long milliseconds = JSONParserUtil.getLong(temp,"date");
                                    	 Calendar calender=Calendar.getInstance();
                                    	 calender.setTimeInMillis(milliseconds);
                                    	 Date txdate=calender.getTime();
                                    	 if (txdate.after(from) && txdate.before(to)){
                                         MTransactionResponseDTO transaction = new MTransactionResponseDTO();
                                         System.out.println("the date is:"+txdate);
//                                         Calendar calendar = Calendar.getInstance();
//                                         calendar.setTimeInMillis(milliseconds);
                                         transaction.setDate(dateFormat.format(calender.getTime()));
                                         transaction.setTransactionRefNo(JSONParserUtil.getString(temp, "transactionRefNo"));
                                         transaction.setAmount(JSONParserUtil.getDouble(temp, "amount"));
                                         transaction.setContactNo(JSONParserUtil.getString(temp, "contactNo"));
                                         transaction.setEmail(JSONParserUtil.getString(temp, "email"));
                                        //transaction.setTotalPages(totalPages);
                                         transaction.setStatus(Status.valueOf(JSONParserUtil.getString(temp, "status")));
                                         transactionList.add(transaction);
                                         System.out.println("the transaction list:"+transactionList);
                                     }
                                
                                                                  
                                 }
                                 model.addAttribute("txList", transactionList);
                                 System.out.println("the transaction list:"+transactionList);
                             }
                            
                             return "Merchant/transactionsFiltered";
                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                    
                 }
             }
         }
         return "redirect:/Merchant/Home" ;
     }
}
