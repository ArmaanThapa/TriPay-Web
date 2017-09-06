package com.google.recaptcha.api.impl;

import com.google.recaptcha.api.IVerificationApi;
import com.google.recaptcha.model.JCaptchaRequest;
import com.google.recaptcha.model.JCaptchaResponse;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.multitype.MultiTypeCaptchaService;

public class VerificationApiImpl implements IVerificationApi{

	private MultiTypeCaptchaService captchaService;
    public VerificationApiImpl(MultiTypeCaptchaService captchaService){
        this.captchaService = captchaService;
    }
    
//    @Override
//	public ReCaptchaResponse isValidCapatcha(ReCaptchaRequest request) {
//		ReCaptchaResponse resp = new ReCaptchaResponse();
//		try {
//
//			Client client = Client.create();
//
//			WebResource webResource = client
//					.resource(ReCaptchaConstants.URL);
//			System.out.println("inside Recaptcha Verification Api");
//			MultivaluedMapImpl formData = new MultivaluedMapImpl();
//			formData.add(ReCaptchaConstants.KEY_SECRET, ReCaptchaConstants.SECRET);
//			formData.add(ReCaptchaConstants.KEY_RESPONSE, request.getResponse());
//			formData.add(ReCaptchaConstants.KEY_REMOTE_IP, ReCaptchaConstants.IP_ADDRESS);
//			System.out.println("form data filled");
//			ClientResponse response = webResource.accept("application/json").post(ClientResponse.class, formData);
//				String output = response.getEntity(String.class);
//                System.out.print("Output :::: "+output);
//			if (response.getStatus() != 200) {
//				System.out.println("Failed : response message : " + output);
//			} else {
//
//				org.json.JSONObject jobj = new org.json.JSONObject(output);
//				
//				if(jobj != null){
//					resp.setSuccess(JSONParserUtil.getBoolean(jobj, "success"));
//				}
//				System.out.println("Output from Server .... \n");
//				System.out.println(output);
//			}
//		} catch (Exception e) {
//
//		}
//
//		return resp;
//	}

    @Override
    public JCaptchaResponse isValidJCaptcha(JCaptchaRequest request) {
        boolean validCaptcha = false;
        JCaptchaResponse response  = new JCaptchaResponse();

        try {

            validCaptcha = captchaService.validateResponseForID(request.getSessionId(), request.getCaptchaResponse());
            System.err.println("captcha is ::"+validCaptcha);

        }
        catch (CaptchaServiceException e) {
            //should not happen, may be thrown if the id is not valid
//            logger.warn("validateCaptcha()", e);
        }
        response.setValid(validCaptcha);
        return response;
    }


}
