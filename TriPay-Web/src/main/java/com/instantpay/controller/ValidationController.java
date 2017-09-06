package com.instantpay.controller;

import com.instantpay.model.request.AmountRequest;
import com.instantpay.model.request.ValidationRequest;
import com.instantpay.util.IPayConvertUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.instantpay.api.IValidationApi;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/InstantPay")
public class ValidationController {

    private final IValidationApi validationApi;
    public ValidationController(IValidationApi validationApi){
        this.validationApi = validationApi;
    }

 @RequestMapping(value="/GetAmount",method= RequestMethod.POST)
 ResponseEntity<String> getExactAmount(@ModelAttribute AmountRequest dto, HttpServletRequest request, HttpServletResponse response){
    String strResponse = "";
    ValidationRequest ndto = null;
     String serviceProvider = dto.getServiceProvider().substring(1);
     dto.setServiceProvider(serviceProvider);
     switch (serviceProvider.toUpperCase()){
         // To differentiate service provider
         case "ATV":   //Airtel TV
         case "DTV":    //Dish TV
         case "RTV":    //Reliance TV
         case "STV":    //Sun Direct TV
         case "TTV":    //Tata Sky TV
         case "VTV":    //Videocon TV
              ndto = IPayConvertUtil.convertDTHRequest(dto);
             break;
         //Airtel Landline
         case "ATL":
             ndto = IPayConvertUtil.convertAirtelLandlineRequest(dto);
             break;
         //BSNL Landline
         case "BGL":
             ndto = IPayConvertUtil.convertBSNLLandlineRequest(dto);
             break;
         //MTNL Landline
         case "MDL":
             ndto = IPayConvertUtil.convertMTNLLandlineRequest(dto);
             break;
         //Reliance Landline
         case "RGL":
             ndto = IPayConvertUtil.convertRelianceLandlineRequest(dto);
             break;
         //Tata Docomo Landline
         case "TCL":
             ndto = IPayConvertUtil.convertTataDocomoLandlineRequest(dto);
             break;
         //TRIPURA Electricity
         case "TTE":

         //Tata Power
         case "NDE":

         //Telangana Electricity
         case "STE":

         //AP Electricity
         case "SAE":

         //Paschim Kshetra Vitaran - MADHYA PRADESH
         case "MPE":

         //Odisha Discoms - ODISHA
         case "DOE":

         //Noida Power - NOIDA
         case "NUE":

         case "MME":

         //Jodhpur Vidyut Vitran Nigam - RAJASTHAN
         case "DRE":

         //Jamshedpur Utilities & Services (JUSCO)
         case "JUE":

         //Jaipur Vidyut Vitran Nigam - RAJASTHAN
         case "JRE":

         //India Power
         case "IPE":

         //DNHPDCL - DADRA & NAGAR HAVELI
         case "DNE":

         //DHBVN - HARYANA
         case "DHE":

         //CSEB - CHHATTISGARH
         case "CCE":

         //CESC - WEST BENGAL
         case "CWE":

         //BSES Yamuna - DELHI
         case "BYE":

         //BSES Rajdhani - DELHI
         case "BRE":

         //BEST Undertaking - MUMBAI
         case "BME":

         //BESCOM - BENGALURU
         case "BBE":

         //APDCL - ASSAM
         case "AAE":

         //Ajmer Vidyut Vitran Nigam - RAJASTHAN
         case "ARE":
             ndto = IPayConvertUtil.convertGenericElectricityRequest(dto);
             break;
         //MSEDC - MAHARASHTRA
         case "MDE":
             ndto = IPayConvertUtil.convertMDEElectricityRequest(dto);
             break;
         //Reliance Electricity
         case "REE":
             ndto = IPayConvertUtil.convertREEElectricityRequest(dto);
             break;
         //Torrent Power
         case "TPE":
             ndto = IPayConvertUtil.convertTPEElectricityRequest(dto);
             break;
         //Indraprastha Gas
         case "IPG":
         //Gujarat Gas
         case "GJG":
         //GSPC Gas
         case "GSG":
         //Adani Gas
         case "ADG":
            ndto = IPayConvertUtil.convertGenericGasRequest(dto);
             break;
        //Mahanagar Gas
         case "MMG":
             ndto = IPayConvertUtil.convertMMGGasRequest(dto);
             break;
        //ICICI Prudential Life Insurance
         case "IPI":
         //Tata AIA Life Insurance
         case "TAI":
         //IndiaFirst Life Insurance
         case "ILI":
         //Bharti AXA Life Insurance
         case "BAI":
             ndto = IPayConvertUtil.convertInsuranceRequest(dto);
             break;
         default:
             break;
     }
     if(ndto != null){
         strResponse = validationApi.getAmount(ndto);
     }

     return new ResponseEntity<String>(strResponse.toString(), HttpStatus.OK);
 }

}
